package perscholas.ecommercecasestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.service.CartService;
import perscholas.ecommercecasestudy.service.UserService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private CartService cartService;


    @RequestMapping("/show")
    public ModelAndView showCart(Principal principal, HttpSession session){
        ModelAndView response = new ModelAndView("/cart");

        User currentUser = userDAO.findByUsername(principal.getName());

        List<CartItem> cartItems = cartService.listCartItems(currentUser);

        Integer q = 0;
        for(CartItem i : cartItems){
            q += i.getQuantity();
        }
        session.setAttribute("cartCount",q);

        response.addObject("cartItems", cartItems);
        BigDecimal subtotal = cartService.calculateSubTotal(cartItems);
        BigDecimal taxes = cartService.calculateTaxes(cartItems);
        BigDecimal total = subtotal.add(taxes);

        response.addObject("subtotal",subtotal);
        response.addObject("taxes",taxes);
        response.addObject("total",total);

        return response;
    }

    @PostMapping("/addToCart")
    public ModelAndView addToCart(@RequestParam("id") Integer id,
                                  @RequestParam("quantity") Integer quantity,
                                  Principal principal,
                                  HttpSession session){
        ModelAndView response = new ModelAndView();

        response.setViewName("redirect:/product/"+id);
        String message;

        if(principal == null){
            message = "You must login to add this product to your cart.";
        }else{

            User currentUser = userDAO.findByUsername(principal.getName());

            Integer addedQty = cartService.addProduct(id,quantity,currentUser);
            message = addedQty+" items added to your cart.";
            response.addObject("message",message);

        }
        User currentUser = userDAO.findByUsername(principal.getName());
        Integer q = 0;
        List<CartItem> cartItems = cartService.listCartItems(currentUser);
        for(CartItem i : cartItems){
            q += i.getQuantity();
        }
        session.setAttribute("cartCount",q);

        return response;

    }

    @PostMapping("/updateCart")
    public ModelAndView updateCartQuantity(@RequestParam("quantity") Integer quantity,
                                            @RequestParam("id") Integer id,
                                           Principal principal,
                                           HttpSession session){
        ModelAndView response = new ModelAndView("redirect:/cart/show");
        User user = userDAO.findByUsername(principal.getName());
        cartService.updateQuantity(quantity,id,user);
        Integer q = 0;
        List<CartItem> cartItems = cartService.listCartItems(user);
        for(CartItem i : cartItems){
            q += i.getQuantity();
        }
        session.setAttribute("cartCount",cartItems.size()*q);
        return response;
    }

    @PostMapping("/removeFromCart")
    public ModelAndView removeFromCart(@RequestParam("id")Integer id, Principal principal, HttpSession session){
        ModelAndView response = new ModelAndView("redirect:/cart/show");
        User user =userDAO.findByUsername(principal.getName());
        cartService.removeFromCart(id,user);
        Integer q = 0;
        List<CartItem> cartItems = cartService.listCartItems(user);
        for(CartItem i : cartItems){
            q += i.getQuantity();
        }
        session.setAttribute("cartCount",q);

        return response;
    }
}

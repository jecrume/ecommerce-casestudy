package perscholas.ecommercecasestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.CartDAO;
import perscholas.ecommercecasestudy.database.entity.Cart;
import perscholas.ecommercecasestudy.service.CartService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartDAO cartDao;

    @Autowired
    private CartService cartService;

    @RequestMapping("/show")
    public ModelAndView showCart(HttpSession session){
        ModelAndView response = new ModelAndView("/cart");

        String sessionToken = RequestContextHolder.currentRequestAttributes().getSessionId();

       Cart displayCart = (Cart) session.getAttribute("shoppingCart");

        cartService.calculateSubTotal(displayCart);
        cartService.calculateTaxes(displayCart);
        cartService.calculateTotalPrice(displayCart);

        response.addObject("cartItems", displayCart.getCartItems());
        response.addObject("subtotal",displayCart.getSubTotal());
        response.addObject("taxes",displayCart.getSalesTaxes());
        response.addObject("total",displayCart.getTotalPrice());
        response.addObject("sessionToken", sessionToken);
        return response;
    }
}

package perscholas.ecommercecasestudy.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.OrderDAO;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.entity.*;
import perscholas.ecommercecasestudy.service.CartService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderDAO orderDao;


    @Autowired
    private UserDAO userDao;

    @Autowired
    CartService cartService;

    public static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/orderForm")
    public ModelAndView order(Principal principal){
        ModelAndView response = new ModelAndView();

        response.setViewName("order");

        User currentUser = userDao.findByUsername(principal.getName());

        List<CartItem> cartItems = cartService.listCartItems(currentUser);

        response.addObject("cartItems", cartItems);
        BigDecimal subtotal = cartService.calculateSubTotal(cartItems);
        BigDecimal taxes = cartService.calculateTaxes(cartItems);
        BigDecimal total = subtotal.add(taxes);

        response.addObject("subtotal",subtotal);
        response.addObject("taxes",taxes);
        response.addObject("total",total);

        return response;
    }

    @PostMapping("/placeOrder")
    public ModelAndView placeOrder(
                                   @RequestParam("streetAddress") String streetAddress,
                                   @RequestParam("city") String city,
                                   @RequestParam("state") String state,
                                   @RequestParam("zip") String zipcode,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("name")String name,
                                   @RequestParam("subtotal") BigDecimal subtotal,
                                   @RequestParam("taxes") BigDecimal taxes,
                                   @RequestParam("total") BigDecimal total,

                                   Principal principal,
                                   HttpSession session) {
        ModelAndView response = new ModelAndView();
        User currentUser = userDao.findByUsername(principal.getName());
        String shippingAddress = streetAddress+", "+city+","+state+" "+zipcode;
        Order newOrder = new Order();
//        Cart sessionCart = (Cart) session.getAttribute("shoppingCart");
//        newOrder.setCartId(sessionCart.getId());
        newOrder.setDate(LocalDateTime.now());
        newOrder.setShippingAddress(shippingAddress);
        newOrder.setUserId(currentUser.getId());
        newOrder.setCustomerName(name);
        newOrder.setPhoneNumber(phone);
        newOrder.setTotal(total);
        newOrder.setSubtotal(subtotal);
        newOrder.setTaxes(taxes);
       User user = userDao.findByUsername(principal.getName());

        Integer userId = user.getId();
        newOrder.setUserId(userId);

        //get the cartItems
        List<CartItem> cartItems = cartService.listCartItems(user);
        //put the cartItems in the order.productList
        response.addObject("cartItems",cartItems);
        List<CartItem> productList = new ArrayList<>();
        for(CartItem product : cartItems){
            productList.add(product);
        }

        newOrder.setProductList(productList);
        orderDao.save(newOrder);
        Integer orderId = newOrder.getId();
        response.addObject("orderId",orderId);
        response.addObject("phone",phone);
        response.addObject("name",name);
        response.addObject("streetAddress",shippingAddress);
        response.addObject("subtotal",subtotal);
        response.addObject("taxes",taxes);
        response.addObject("total",total);

        response.setViewName("/orderSuccess");
        cartService.removeAllFromCart(userId);


        Integer q = 0;
        List<CartItem> updatedCart = cartService.listCartItems(currentUser);

        if(updatedCart != null) {
            for (CartItem i : updatedCart) {
                q += i.getQuantity();
            }
        }
        session.setAttribute("cartCount",q);

        return response;
    }
}

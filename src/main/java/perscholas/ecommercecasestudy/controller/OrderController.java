package perscholas.ecommercecasestudy.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.OrderDAO;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.entity.*;
import perscholas.ecommercecasestudy.database.form.OrderFormBean;
import perscholas.ecommercecasestudy.service.CartService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public ModelAndView order(Principal principal) {
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
    public ModelAndView placeOrder(@RequestParam("subtotal") BigDecimal subtotal,
                                   @RequestParam("taxes") BigDecimal taxes,
                                   @RequestParam("total") BigDecimal total,
                                   @Valid OrderFormBean form,
                                   BindingResult errors,
                                   Principal principal,
                                   HttpSession session) throws Exception {


        ModelAndView response = new ModelAndView();

        //if there are errors show messages on page

        if (errors.hasErrors()) {
            // this list is populated by the controller with all error messages
            // in the binding result.
            List<String> errorMessages = new ArrayList<>();

            for (FieldError error : errors.getFieldErrors()) {
                // add the error message to the errorMessages list in the form bean
                errorMessages.add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("errorMessages", errorMessages);
            response.addObject("formBeanKey", form);
            response.setViewName("order");

        }else{
            User currentUser = userDao.findByUsername(principal.getName());
            String shippingAddress = form.getStreetAddress()+", "+form.getCity()+","+form.getState()+" "+form.getZipcode();
            Order newOrder = new Order();

            newOrder.setDate(LocalDateTime.now());
            newOrder.setShippingAddress(shippingAddress);
            newOrder.setUserId(currentUser.getId());
            newOrder.setCustomerName(form.getName());
            newOrder.setPhoneNumber(form.getPhone());
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
            response.addObject("productList",productList);
            Integer orderId = newOrder.getId();
            response.addObject("orderId",orderId);
            response.addObject("phone",form.getPhone());
            response.addObject("name",form.getName());
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
            }

        return response;
    }
}

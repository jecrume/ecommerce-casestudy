package perscholas.ecommercecasestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.CartDAO;
import perscholas.ecommercecasestudy.database.entity.Cart;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartDAO cartDao;

    @RequestMapping("/show")
    public ModelAndView showCart(HttpSession session){
        ModelAndView response = new ModelAndView("cart");

        String sessionToken = RequestContextHolder.currentRequestAttributes().getSessionId();

       // Cart displayCart = cartDao.findBySessionToken(sessionToken);

       // response.addObject("cartItems", displayCart.getCartItems());
        response.addObject("sessionToken", sessionToken);
        return response;
    }
}

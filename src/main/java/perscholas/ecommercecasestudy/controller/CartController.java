package perscholas.ecommercecasestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping("/show")
    public ModelAndView showCart(){
        ModelAndView response = new ModelAndView("cart");
        response.addObject("title","User Cart");
        response.addObject("userClickShowCart",true);
        response.addObject("cartLines",null);

        return response;
    }
}

package perscholas.ecommercecasestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    public static final Logger LOG = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("login");
        return response;
    }

    @RequestMapping(value = "/login/logoutSuccess", method = RequestMethod.GET)
    public ModelAndView loginSuccess(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("/user/userprofile");
        return response;
    }

}

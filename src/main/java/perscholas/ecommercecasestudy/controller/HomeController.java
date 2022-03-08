package perscholas.ecommercecasestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller

public class HomeController {

    public static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductDAO productDao;

    @Autowired
    private UserDAO userDAO;

   @Autowired
  private CartService cartService;

    @RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session, Principal principal) throws Exception {
        ModelAndView response = new ModelAndView();

        List<Product> products = productDao.findProductByRandom();
        List<Product> prodSpecial = productDao.findProductByCategory();
        LOG.debug(products.get(0).getProductName()+" "+ products.get(1).getMainImgUrl());
        response.addObject("productsList",products);

        response.addObject("specialProdList",prodSpecial);

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        numbers.forEach( (n) -> { System.out.println(n); } );
        if(principal != null) {
            User currentUser = userDAO.findByUsername(principal.getName());

            List<CartItem> cartItems = cartService.listCartItems(currentUser);

            Integer q = 0;
            for (CartItem i : cartItems) {
                q += i.getQuantity();
            }
            session.setAttribute("cartCount", q);
        }else{
            session.setAttribute("cartCount", 0);
        }
        response.setViewName("index");
        return response;
    }


}

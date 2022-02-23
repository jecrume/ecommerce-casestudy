package perscholas.ecommercecasestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class HomeController {

    public static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductDAO productDao;

    @RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        List<Product> products = productDao.findProductByRandom();
        List<Product> prodSpecial = productDao.findProductByCategory();
        LOG.debug(products.get(1).getProductName()+" "+ products.get(1).getMainImgUrl());
        response.addObject("productsList",products);

        response.addObject("specialProdList",prodSpecial);

        response.setViewName("index");
        return response;
    }
}

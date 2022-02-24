package perscholas.ecommercecasestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Product;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    public static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDAO productDao;

    @GetMapping(value = "/home")
    public ModelAndView home(){
        ModelAndView response = new ModelAndView();
        response.setViewName("/category");
        List<Product> home = productDao.findProductByExactCategory("home");
        response.addObject("productList",home);
        response.addObject("category","Home");

        return response;
    }

    @GetMapping(value = "/av")
    public ModelAndView av(){
        ModelAndView response = new ModelAndView();
        response.setViewName("/category");
        List<Product> av = productDao.findProductByExactCategory("av");
        response.addObject("productList",av);
        response.addObject("category","Audio & Visual");

        return response;
    }

    @GetMapping(value = "/gaming")
    public ModelAndView gaming(){
        ModelAndView response = new ModelAndView();
        response.setViewName("/category");
        List<Product> gaming = productDao.findProductByExactCategory("gaming");
        response.addObject("category","Gaming");
        response.addObject("productList",gaming);

        return response;
    }

    @GetMapping(value = "/toys")
    public ModelAndView toys(){
        ModelAndView response = new ModelAndView();
        response.setViewName("/category");
        List<Product> toys = productDao.findProductByExactCategory("toys");
        response.addObject("toysList",toys);
        response.addObject("category","Toys");

        return response;
    }

    @GetMapping(value = "/interesting")
    public ModelAndView interesting(){
        ModelAndView response = new ModelAndView();
        response.setViewName("/category");
        List<Product> interesting = productDao.findProductByExactCategory("interesting");
        response.addObject("interestingList",interesting);
        response.addObject("category","Interesting");

        return response;
    }
}


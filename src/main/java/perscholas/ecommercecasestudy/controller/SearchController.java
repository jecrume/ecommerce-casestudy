package perscholas.ecommercecasestudy.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    public static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDAO productDao;

    @RequestMapping(value = {"/results"})
    public ModelAndView results(@RequestParam("keyword") String keyword, HttpServletRequest request){
        ModelAndView response = new ModelAndView();
        response.setViewName("/search");
        LOG.debug("The search term is: "+keyword);
        if(!StringUtils.isEmpty(keyword)) {
            List<Product> searchResult = productDao.findProductByDescriptionAndSpecAndName(keyword);
            for(Product products : searchResult){
                LOG.debug(products.getProductName());
            }
            response.addObject("searchResult", searchResult);
            response.addObject("keyword",keyword);
        }else{
            response.addObject("searchResult",null);
            response.addObject("keyword",keyword);
        }

        return response;
    }

}

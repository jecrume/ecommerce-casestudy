package perscholas.ecommercecasestudy.controller;

import com.sun.xml.bind.v2.TODO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    public static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDAO producerDao;

    @GetMapping(value = {"/{productId}"})
    public ModelAndView product(@PathVariable(value = "productId") String id, HttpServletRequest request){
        ModelAndView response = new ModelAndView();
        response.setViewName("/product");
        Integer prodId = Integer.parseInt(id);
        Product product = producerDao.findProductById(prodId);
        List<Product> productsList = producerDao.findProductByRandom();
        LOG.debug("The product id is: "+product.getId() + "/n The product category is: "+product.getCategory());
        List<String> imgUrlList = Arrays.asList(StringUtils.splitPreserveAllTokens(product.getSecondaryImgUrls()));
        response.addObject("productsList",productsList);
        response.addObject("currentProduct",product);
        response.addObject("imgUrlList", imgUrlList);
        return response;
    }


}

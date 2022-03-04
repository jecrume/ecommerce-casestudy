package perscholas.ecommercecasestudy.controller;

import com.sun.xml.bind.v2.TODO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.CartDAO;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Cart;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    public static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDAO producerDao;

    @Autowired
    private CartDAO cartDao;

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
    @PostMapping("/addToCart")
    public ModelAndView addToCart(HttpSession session, @RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity  ){
        ModelAndView response = new ModelAndView();

        //check for the session TODO change this to use Spring session to track session
        String sessionToken = RequestContextHolder.currentRequestAttributes().getSessionId();
        //if session doesn't exist create it
        if(sessionToken == null){
            session.setAttribute("sessionToken", UUID.randomUUID().toString());
            Cart shoppingCart = new Cart();
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setDate(LocalDateTime.now());
            cartItem.setProduct(producerDao.findProductById(id));
            shoppingCart.getCartItems().add(cartItem);
            shoppingCart.setSessionToken(sessionToken);
            shoppingCart.setDate(LocalDateTime.now());
            cartDao.save(shoppingCart);
        } else {

            Cart shoppingCart = cartDao.findBySessionToken(sessionToken);
            //add product

            Product p = producerDao.findProductById(id);
            //find the item and update the item

            boolean isInTheSet = false;
            for (CartItem item : shoppingCart.getCartItems()) {
                if (p.getId().equals(item.getProduct().getId())) {
                    isInTheSet = true;
                }
            }

            if (isInTheSet) {

                for (CartItem item : shoppingCart.getCartItems()) {
                    item.setQuantity(item.getQuantity() + quantity);

                }
                cartDao.save(shoppingCart);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setDate(LocalDateTime.now());
                cartItem.setQuantity(quantity);
                cartItem.setProduct(p);
                shoppingCart.getCartItems().add(cartItem);
                cartDao.save(shoppingCart);
            }

        }

        response.setViewName("redirect:/cart");


        return response;
    }


}

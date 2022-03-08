package perscholas.ecommercecasestudy.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    public static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDAO producerDao;

//    @Autowired
//    private CartDAO cartDao;

    @Autowired
    private CartService cartService;

    @GetMapping(value = {"/{productId}"})
    public ModelAndView product(@PathVariable(value = "productId") String id, HttpServletRequest request){
        ModelAndView response = new ModelAndView();
        response.setViewName("/product");
        Integer prodId = Integer.parseInt(id);
        Product product = producerDao.findProductById(prodId);
        List<Product> productsList = producerDao.findProductByRandom();
        LOG.debug("The product id is: "+product.getId() + "\n The product category is: "+product.getCategory());
        List<String> imgUrlList = Arrays.asList(StringUtils.splitPreserveAllTokens(product.getSecondaryImgUrls()));
        response.addObject("productsList",productsList);
        response.addObject("currentProduct",product);
        response.addObject("imgUrlList", imgUrlList);
        return response;
    }
    @PostMapping("/addToCart")
    public ModelAndView addToCart(HttpSession session, @RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity, HttpServletRequest request){
        ModelAndView response = new ModelAndView();

        //check for the session TODO change this to use Spring session to track session
        String sessionToken = RequestContextHolder.currentRequestAttributes().getSessionId();
        //if session doesn't exist create it
        LOG.debug("The session token is: "+sessionToken);

//        Cart shoppingCart;
//        //if the session token is new then create a new cart
//        if(session.getAttribute("shoppingCart")==null){
//
//            shoppingCart= new Cart();
//            CartItem cartItem = new CartItem();
//            cartItem.setQuantity(quantity);
//
//            cartItem.setProduct(producerDao.findProductById(id));
//            shoppingCart.getCartItems().add(cartItem);
//            shoppingCart.setSessionToken(sessionToken);
//
//
//            cartService.calculateSubTotal(shoppingCart);
//            cartService.calculateTaxes(shoppingCart);
//            cartService.calculateTotalPrice(shoppingCart);
//            cartDao.save(shoppingCart);
//            session.setAttribute("shoppingCart",shoppingCart);
//            //request.setAttribute("cartId",shoppingCart.getId());
//        } else {
//
//            shoppingCart = (Cart) session.getAttribute("shoppingCart");
//            //add product
//
//            Product p = producerDao.findProductById(id);
//            //find the item and update the item
//
//            boolean isInTheSet = false;
//            //if the cart exists and is not empty
//
//
//            if(!shoppingCart.getCartItems().isEmpty()) {
//
//                //check if the product is in the hash set
//                for (CartItem item : shoppingCart.getCartItems()) {
//                    if (p.getId().equals(item.getProduct().getId())) {
//                        isInTheSet = true;
//                    }
//                }
//                //if it is in the hashset update the quantity
//                if (isInTheSet) {
//
//                    for (CartItem item : shoppingCart.getCartItems()) {
//                        item.setQuantity(item.getQuantity() + quantity);
//
//                    }
//                    cartDao.save(shoppingCart);
//                    session.setAttribute("shoppingCart",shoppingCart);
//                }
//            } else {//if the cart is empty or the product is not already in the cart
//                CartItem cartItem = new CartItem();
//
//                cartItem.setQuantity(quantity);
//                cartItem.setProduct(p);
//                shoppingCart.getCartItems().add(cartItem);
//                cartService.calculateSubTotal(shoppingCart);
//                cartService.calculateTaxes(shoppingCart);
//                cartService.calculateTotalPrice(shoppingCart);
//                cartDao.save(shoppingCart);
//                session.setAttribute("shoppingCart",shoppingCart);
                //request.setAttribute("cartId",shoppingCart.getId());

            //}

        //}

        response.setViewName("redirect:/cart/show");


        return response;
    }


}

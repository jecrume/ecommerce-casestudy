package perscholas.ecommercecasestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.dao.WishListDAO;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.database.entity.Wishlist;

import java.security.Principal;
import java.util.List;

@Controller

public class WishListController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private WishListDAO wishlistDAO;

@RequestMapping("/view")
public ModelAndView showWishlist(Principal principal){
    ModelAndView response = new ModelAndView();
    User currentUser = userDAO.findByUsername(principal.getName());
    List<Wishlist> wishlist = wishlistDAO.findByUserId(currentUser.getId());

    response.addObject("wishLists",wishlist);

    response.setViewName("wishlistview");
    return response;
}
@PostMapping("/addToWishList")
    public ModelAndView addToWishList(@RequestParam("productId") Integer productId, Principal principal){
    ModelAndView response = new ModelAndView("wishlistview");
    User currentUser = userDAO.findByUsername(principal.getName());
    Product product = productDAO.findProductById(productId);

    Wishlist wishList = wishlistDAO.findByUserAndProduct(currentUser,product);

    if(wishList == null){


        wishList = new Wishlist();
        wishList.setProduct(product);
        wishList.setUser(currentUser);
        wishlistDAO.save(wishList);
    }



    return response;

}

    @PostMapping("/removeFromWishList")
    public ModelAndView removeFromWishList(@RequestParam("productId") Integer productId, Principal principal){
        ModelAndView response = new ModelAndView("wishlistview");
        User currentUser = userDAO.findByUsername(principal.getName());
        Product product = productDAO.findProductById(productId);

        Wishlist wishList = wishlistDAO.findByUserAndProduct(currentUser,product);


        wishlistDAO.delete(wishList);

        return response;

    }



}

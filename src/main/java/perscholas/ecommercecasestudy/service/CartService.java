package perscholas.ecommercecasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Cart;
import perscholas.ecommercecasestudy.database.entity.User;

import javax.servlet.http.HttpSession;

@Service("cartService")
public class CartService {

    //This might need to be changed to a new cartDAO that has not yet been created
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private HttpSession session;

    private Cart getCart(){
        return ((User)session.getAttribute("user")).getCart();
    }
}

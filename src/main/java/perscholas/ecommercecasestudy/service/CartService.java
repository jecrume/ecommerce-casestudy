package perscholas.ecommercecasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Cart;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.User;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

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

   public void calculateSubTotal(Cart cart){

        BigDecimal subtotal = new BigDecimal(0.00);
        for(CartItem item :cart.getCartItems()){
            subtotal = subtotal.add(item.getProduct().getPrice());
        }

        cart.setSubTotal(subtotal);

    }

    public void calculateTaxes(Cart cart){
        BigDecimal taxes= new BigDecimal(0.00);
        BigDecimal taxOfItemPrice = new BigDecimal(0.00);
        BigDecimal salesTaxRate = new BigDecimal(0.055);
        for(CartItem item : cart.getCartItems()){
            taxOfItemPrice = item.getProduct().getPrice().multiply(salesTaxRate);
            taxes = taxes.add(taxOfItemPrice);
        }

        cart.setSalesTaxes(taxes);

    }

    public void calculateTotalPrice(Cart cart){
        BigDecimal totalPrice = cart.getSubTotal().add(cart.getSalesTaxes());
        cart.setTotalPrice(totalPrice);
    }

}

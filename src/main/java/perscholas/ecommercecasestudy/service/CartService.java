package perscholas.ecommercecasestudy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perscholas.ecommercecasestudy.controller.ProductController;
import perscholas.ecommercecasestudy.database.dao.CartItemsDAO;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.database.entity.User;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    public static final Logger LOG = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartItemsDAO cartItemsDao;

    @Autowired
    private ProductDAO productDAO;

    public List<CartItem> listCartItems(User user){
        return cartItemsDao.findByUser(user);
    }

    public BigDecimal calculateSubTotal(List<CartItem> cart){

        BigDecimal subtotal = new BigDecimal(0.00);
        for(CartItem item :cart){
            BigDecimal price = item.getProduct().getPrice();
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            BigDecimal subSubTotal = price.multiply((BigDecimal) quantity);
            subtotal = subtotal.add(subSubTotal);
        }

        LOG.debug("Cart Subtotal: "+subtotal);
        return subtotal;

    }

    public BigDecimal calculateTaxes(List<CartItem> cart){
        BigDecimal taxes= new BigDecimal(0.00);
        BigDecimal taxOfItemPrice = new BigDecimal(0.00);
        BigDecimal salesTaxRate = new BigDecimal(0.055);
        for(CartItem item : cart){
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            BigDecimal itemSubtotal = item.getProduct().getPrice().multiply(quantity);
            taxOfItemPrice = itemSubtotal.multiply(salesTaxRate);
            taxes = taxes.add(taxOfItemPrice);
        }

        LOG.debug("Cart sales taxes: "+taxes);


        return taxes;

    }

    public Integer addProduct(Integer productId, Integer quantity, User user){
        Integer addedQuantity = quantity;

        Product product = productDAO.findProductById(productId);

        CartItem cartItem = cartItemsDao.findByUserAndProduct(user,product);

        if(cartItem != null){
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }else{
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setProduct(product);
        }

        cartItemsDao.save(cartItem);

        return addedQuantity;
    }

    public void updateQuantity(Integer quantity, Integer productId, User user){
        LOG.debug("The new quantity is:"+quantity);
        cartItemsDao.updateQuantity(quantity,productId,user.getId());
        Product product = productDAO.findProductById(productId);

    }

    public void removeFromCart( Integer productId, User user){


        cartItemsDao.delete(productId,user.getId());

    }

    public void removeAllFromCart(Integer userId){
        cartItemsDao.deleteCartItemByUserId(userId);
    }




}

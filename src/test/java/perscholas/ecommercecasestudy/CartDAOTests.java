package perscholas.ecommercecasestudy;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import perscholas.ecommercecasestudy.database.dao.CartItemsDAO;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.database.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CartDAOTests {

    @Autowired
    private CartItemsDAO cartDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Order(1)
    public void testAddToCartItem(){
        Product product = entityManager.find(Product.class,196);
        User user = entityManager.find(User.class,2);

        CartItem newItem = new CartItem();
        newItem.setUser(user);
        newItem.setProduct(product);
        newItem.setQuantity(1);

        CartItem savedCartItem = cartDao.save(newItem);

        assertTrue(savedCartItem.getId() > 0);

    }

    @Test
    @Order(2)
    public void testGetCartItemsByCustomer(){
        User user = new User();
        user.setId(2);

        List<CartItem> cartItems = cartDao.findByUser(user);

        assertEquals(1,cartItems.size());
    }

    @Test
    @Order(3)
    public void updateCartItemTest(){
        User user = new User();
        user.setId(3);

        List<CartItem> cartItems = cartDao.findByUser(user);

        cartDao.updateQuantity(9,cartItems.get(0).getProduct().getId(),user.getId());

        Integer newQuantity = cartItems.get(0).getQuantity();

        assertEquals(9,cartItems.get(0).getQuantity());
    }

    @Test
    @Order(4)
    public void deleteCartItemTest(){
        User user = new User();

        user.setId(3);

        List<CartItem> cartItems = cartDao.findByUser(user);

        Integer deletedProductId = cartItems.get(0).getProduct().getId();

        cartDao.delete(deletedProductId, user.getId());

        Product deletedProduct = new Product();
        deletedProduct.setId(deletedProductId);
        CartItem deletedItem = cartDao.findByUserAndProduct(user,deletedProduct);

        assertNull(deletedItem);

    }
}

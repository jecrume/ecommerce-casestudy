package perscholas.ecommercecasestudy;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CartDAOTests {

    @Autowired
    private CartItemsDAO cartDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
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
    public void testGetCartItemsByCustomer(){
        User user = new User();
        user.setId(2);

        List<CartItem> cartItems = cartDao.findByUser(user);

        assertEquals(1,cartItems.size());
    }
}

package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import perscholas.ecommercecasestudy.database.entity.CartItem;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.database.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartItemsDAO extends JpaRepository<CartItem,Integer> {

    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndProduct(User user, Product product);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cart_items SET cart_items.quantity = ?1 WHERE cart_items.product_id = ?2 AND cart_items.user_id = ?3", nativeQuery = true)
    public void updateQuantity(Integer quantity, Integer productId, Integer userId);

    @Transactional
    @Modifying
    @Query(value = "delete from cart_items where cart_items.product_id = ?1 AND cart_items.user_id = ?2",nativeQuery = true)
    public void delete(Integer productId,Integer userId);

    @Transactional
    @Modifying
    @Query(value="delete  from cart_items where cart_items.user_id = ?1", nativeQuery = true)
    public void deleteCartItemByUserId(Integer userId);
}

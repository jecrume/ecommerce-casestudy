package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import perscholas.ecommercecasestudy.database.entity.Product;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.database.entity.Wishlist;

import java.util.List;

public interface WishListDAO extends JpaRepository <Wishlist,Long> {


    @Query(value = "SELECT * FROM wishlist WHERE wishlist.user_Id = :userId", nativeQuery = true)
    public List<Wishlist> findByUserId(Integer userId);

    public Wishlist findByUserAndProduct(User user, Product product);
}

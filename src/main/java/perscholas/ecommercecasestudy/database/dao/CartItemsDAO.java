package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perscholas.ecommercecasestudy.database.entity.CartItem;


public interface CartItemsDAO extends JpaRepository<CartItem,Integer> {
}

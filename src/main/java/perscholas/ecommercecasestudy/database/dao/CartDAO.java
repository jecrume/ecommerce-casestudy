package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import perscholas.ecommercecasestudy.database.entity.Cart;


public interface CartDAO extends JpaRepository <Cart,Integer> {

    Cart findCartById(Integer id);

    Cart findBySessionToken(String sessionToken);

    boolean existsBySessionToken(String sessionToken);

}

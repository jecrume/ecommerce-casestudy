package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import perscholas.ecommercecasestudy.database.entity.Order;

import java.util.List;

public interface OrderDAO extends JpaRepository <Order, Long> {

    public Order getOrderById(Integer id);

}

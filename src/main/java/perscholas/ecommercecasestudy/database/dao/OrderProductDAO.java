package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import perscholas.ecommercecasestudy.database.entity.OrderProduct;

public interface OrderProductDAO extends JpaRepository <OrderProduct,Long> {

    public OrderProduct getOrderProductById(Integer id);

    public OrderProduct getOrderProductByOrderId(Integer id);
}

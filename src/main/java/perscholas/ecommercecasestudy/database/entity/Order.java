package perscholas.ecommercecasestudy.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "cart_id")
    private Integer cartId;

    @ManyToMany
    @JoinTable(name = "order_product",
               joinColumns = @JoinColumn(name ="order_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<Product>();
}

package perscholas.ecommercecasestudy.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(name = "order_date")
    private LocalDateTime date;

    @Column(name="ship_address",columnDefinition = "TEXT")
    private String shippingAddress;



    @Column(name="customer_name")
    private String customerName;

    @Column(name="phone")
    private String phoneNumber;

    @Column(name="taxes")
    private BigDecimal taxes;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToMany
    @JoinTable(name = "order_product",
               joinColumns = @JoinColumn(name ="order_id"),
                inverseJoinColumns = @JoinColumn(name = "cart_items_id"))
    private List<CartItem> productList = new ArrayList<CartItem>();
}

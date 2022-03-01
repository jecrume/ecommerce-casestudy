package perscholas.ecommercecasestudy.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name="cart")
public class Cart {

    @Id
    private Integer id;

    @Column(name = "item_id")
    private Integer productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "item_qty")
    private Integer productQty;

    @Column(name = "item_price")
    private BigDecimal productPrice;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "cart_product",
                    joinColumns = {
                                    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false,
                                    updatable = false)},
                    inverseJoinColumns ={
                                    @JoinColumn(name="cart_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Product> products = new HashSet<>();
}

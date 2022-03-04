package perscholas.ecommercecasestudy.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDateTime date;

    @Transient
    private BigDecimal subTotal;

    @Transient
    private BigDecimal salesTaxes;

    @Transient
    private BigDecimal totalPrice;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "cart_product",
                    joinColumns = {
                                    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false,
                                    updatable = false)},
                    inverseJoinColumns ={
                                    @JoinColumn(name="cart_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<CartItem> cartItems = new HashSet<>();

    @Transient
    private Integer cartItemCount;

    @Column(name="session_token")
    private String sessionToken;
}

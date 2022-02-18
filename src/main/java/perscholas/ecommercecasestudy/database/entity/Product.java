package perscholas.ecommercecasestudy.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name="specs")
    private String productSpecs;

    @Column(name = "color")
    private String color;

    @Column(name = "category")
    private String category;

    @Column(name = "avail_qty")
    private Integer availableQty;

    @Column(name="main_img_url")
    private String mainImgUrl;

    @Column(name ="secondary_img_url")
    private String secondaryImgUrls;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="weight")
    private Double weight;
}

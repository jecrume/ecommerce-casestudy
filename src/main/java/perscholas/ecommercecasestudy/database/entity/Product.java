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

    @Column(name = "product_name", columnDefinition = "TINYTEXT")
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name="specs", columnDefinition = "TEXT")
    private String productSpecs;

    @Column(name = "color")
    private String color;

    @Column(name = "category")
    private String category;

    @Column(name = "avail_qty")
    private Integer availableQty;

    @Column(name="main_img_url", columnDefinition = "TINYTEXT")
    private String mainImgUrl;

    @Column(name ="secondary_img_url", columnDefinition = "TEXT")
    private String secondaryImgUrls;

    @Column(name = "price", columnDefinition = "DECIMAL")
    private BigDecimal price;

    @Column(name="weight", columnDefinition = "DECIMAL")
    private Double weight;
}

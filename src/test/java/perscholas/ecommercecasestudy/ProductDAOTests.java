package perscholas.ecommercecasestudy;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import perscholas.ecommercecasestudy.database.dao.ProductDAO;
import perscholas.ecommercecasestudy.database.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductDAOTests {

    @Autowired
    private ProductDAO productDAO;

    @Test
    @Order(1)
    public void saveProductTest(){

        Product product = new Product();

        product.setProductName("Test1 Product Name");
        product.setProductDescription("Test1 Description");
        product.setProductSpecs("Test1 Product Specs");
        product.setCategory("Test1 Category");
        product.setAvailableQty(999);
        product.setColor("Test1 Color");
        product.setMainImgUrl("https://via.placeholder.com/150");
        product.setSecondaryImgUrls("https://via.placeholder.com/150 https://via.placeholder.com/150 https://via.placeholder.com/150");
        product.setPrice(new BigDecimal(0.00));
        product.setWeight(0.01);

        productDAO.save(product);

        assertTrue(product.getId()>0);
    }

    @Test
    @Order(2)
    public void getProductTest(){
        Product product = productDAO.findProductByName("Test1 Product Name");
        assertTrue(product.getProductName().equals("Test1 Product Name"));

    }

    @Test
    @Order(3)
    public void updateProductTest(){
        Product product = productDAO.findProductByName("Test1 Product Name");
        product.setAvailableQty(0);
        productDAO.save(product);
        product = productDAO.findProductByName("Test1 Product Name");
        assertEquals(0, product.getAvailableQty()) ;
    }

    @Test
    @Order(4)
    public void deleteProductTest(){
        Product product = productDAO.findProductByName("Test1 Product Name");
        productDAO.delete(product);
        Optional<Product> optionalProduct = Optional.ofNullable(productDAO.findProductById(product.getId()));

        Product tempProduct = null;

        if(optionalProduct.isPresent()){
            tempProduct = optionalProduct.get();
        }

        assertNull(tempProduct);
    }



}

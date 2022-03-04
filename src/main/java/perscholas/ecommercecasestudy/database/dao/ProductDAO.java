package perscholas.ecommercecasestudy.database.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import perscholas.ecommercecasestudy.database.entity.Product;


import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Long> {


    //CREATE
    public Product save(Product save);

    //READ
    @Query(value = "SELECT * FROM products where id = :id",nativeQuery = true)
    public Product findProductById(Integer id);

    @Query(value="SELECT * FROM products where products.product_description LIKE %:desc%",nativeQuery = true)
    public List<Product> findProductByDescription(String desc);

    @Query(value = "SELECT * FROM products WHERE lower(products.product_description) LIKE %:keyword% OR lower(products.specs) LIKE %:keyword% OR lower(products.product_name) LIKE %:keyword%",nativeQuery = true)
    public List<Product> findProductByDescriptionAndSpecAndName(@Param("keyword")String keyword);

    @Query(value = "SELECT * FROM products ORDER BY RAND() LIMIT 15",nativeQuery = true)
    public List<Product> findProductByRandom();

    @Query(value = "SELECT * FROM products WHERE products.category='interesting' ORDER BY RAND() LIMIT 10", nativeQuery = true)
    public List<Product> findProductByCategory();

    @Query(value = "SELECT * FROM products WHERE products.category = :category", nativeQuery = true)
    public List<Product> findProductByExactCategory(String category);
    //DELETE
    public void delete(Product delete);

}

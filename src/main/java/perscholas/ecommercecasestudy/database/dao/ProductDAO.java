package perscholas.ecommercecasestudy.database.dao;

import org.hibernate.query.NativeQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import perscholas.ecommercecasestudy.database.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Persistence;
import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Long> {


    //CREATE
    public Product save(Product save);

    //READ
    @Query(value = "SELECT * FROM products where id = :id",nativeQuery = true)
    public Product findProductById(Integer id);

    @Query(value="SELECT * FROM products where product_description LIKE '%:desc%'",nativeQuery = true)
    public List<Product> findProductByDescription(String desc);

    @Query(value = "SELECT * FROM products WHERE product_description OR specs OR product_name LIKE '%:keyword%'",nativeQuery = true)
    public List<Product> findProductByDescriptionAndSpecAndName(String keyword);

    @Query(value = "SELECT * FROM products ORDER BY RAND() LIMIT 15",nativeQuery = true)
    public List<Product> findProductByRandom();

    @Query(value = "SELECT * FROM products WHERE products.category='interesting' ORDER BY RAND() LIMIT 10", nativeQuery = true)
    public List<Product> findProductByCategory();

    //DELETE
    public void delete(Product delete);

}
package perscholas.ecommercecasestudy;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.entity.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    public void saveUserTest(){
        User user = new User();

        user.setUserName("Test1 User Name");
        user.setEmail("Test1 Email");
        user.setPassword("Test1 Password");
        user.setFirstName("Test1 First Name");
        user.setLastName("Test1 Last Name");
        user.setPhone("Test1 Phone Number");
        user.setAddress("Test1 Address");

        userDAO.save(user);

        assertTrue(user.getId() > 0);

    }


    @Test
    @Order(2)
    public void getUserTest(){
        User user = userDAO.findByUsername("Test1 User Name");
        assertTrue(user.getUserName().equals("Test1 User Name"));
    }

    @Test
    @Order(3)
    public void updateUserTest(){
        User user = userDAO.findByUsername("Test1 User Name");
        user.setEmail("Test3 Email");
        userDAO.save(user);
        assertTrue(user.getEmail().equals("Test3 Email"));
    }

}

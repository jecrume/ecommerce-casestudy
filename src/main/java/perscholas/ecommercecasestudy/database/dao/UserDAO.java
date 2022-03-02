package perscholas.ecommercecasestudy.database.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.database.entity.UserRole;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Long> {

    public User findById(@Param("id") Integer id);

    public User findByEmail(@Param("email") String email);

    public List<User> findByLastName(@Param("lastName") String lastName);

    public List<User> findByFirstName(@Param("firstName") String firstName);

    public List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    public List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);
    @Query(value = "select * from users where users.username = :username", nativeQuery = true)
    public User findByUsername(@Param("username") String username);

    @Query(value="SELECT u.* FROM users u WHERE u.first_name like '%:firstName%'", nativeQuery = true)
    public List<User> findByFirstNameLike(String firstName);

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(@Param("userId")  Integer userId);

}

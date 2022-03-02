package perscholas.ecommercecasestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import perscholas.ecommercecasestudy.database.entity.UserRole;

import java.util.List;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    @Query("select ur from UserRole ur where ur.user.id = :userId")
    List<UserRole> getUserRoles(@Param("userId")  Integer userId);

}


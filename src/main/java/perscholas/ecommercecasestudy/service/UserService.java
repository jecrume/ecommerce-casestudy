package perscholas.ecommercecasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.security.UserDetailsServiceImpl;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDao;

    public String getCurrentlyLoggedInUser(Authentication authentication){
     if(authentication == null) return null;

    String userName = null;

     Object principal = authentication.getPrincipal();

     if(principal instanceof UserDetails){
         userName= ((UserDetails) principal).getUsername();
     }

     return userName;
    }
}

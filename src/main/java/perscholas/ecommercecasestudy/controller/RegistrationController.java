package perscholas.ecommercecasestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import perscholas.ecommercecasestudy.database.dao.UserDAO;
import perscholas.ecommercecasestudy.database.dao.UserRoleDAO;
import perscholas.ecommercecasestudy.database.entity.User;
import perscholas.ecommercecasestudy.database.entity.UserRole;
import perscholas.ecommercecasestudy.database.form.RegistrationFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    public static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration");

        return response;
    }

    // this method describes what happens when a user submits the form to the back end
    // it handles both the create and update logic for saving the user input to the database
    @RequestMapping(value = "/register/registerSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView registerSubmit(@Valid RegistrationFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        LOG.debug(form.toString());

        if (errors.hasErrors()) {
            // this list is populated by the controller with all error messages
            // in the binding result.
            List<String> errorMessages = new ArrayList<>();

            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                errorMessages.add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("errorMessages", errorMessages);
            response.addObject("formBeanKey", form);
            response.setViewName("registration");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new user object
            User user;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
                user  = new User();
            } else {
                // this is an update so we need to load the user from the database first
                user = userDao.findById(form.getId());
            }

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setUserName(form.getUsername());
            user.setPhone(form.getPhone());


            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);

            // if you are saving a new user without an id.  The return value of save will
            // create a new autoincremented ID record and return the user object with the new id populated
            user = userDao.save(user);

            if ( form.getId() == null ) {
                // this is a create because the incoming id variable on the form is null
                // so ... lets create a user role record for this user also
                UserRole ur = new UserRole();

                ur.setUser(user);
                ur.setUserRole("USER");

                userRoleDao.save(ur);
            }

            // response.setViewName("redirect:/login");
            response.setViewName("redirect:/login");
        }

        return response;
    }
}

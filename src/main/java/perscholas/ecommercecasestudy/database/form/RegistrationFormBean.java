package perscholas.ecommercecasestudy.database.form;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import perscholas.ecommercecasestudy.validation.TwoFieldsAreEqual;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@TwoFieldsAreEqual(fieldOneName = "confirmpassword", fieldTwoName = "password", message = "Password and Conform Password must be the same.")
public class RegistrationFormBean {

    // this is a hidden data value and is only needed to distinguish an edit from a create
    private Integer id;

    // making sure the email is not null and is not empty as in ""
    @NotEmpty(message = "Email is required.")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    @Email(message = "Email must be unique")
    private String email;


    private String firstName;


    private String lastName;

    @NotEmpty(message = "Username is required")
    private String username;

    private String password;
    private String confirmpassword;

    private String address;
    private String phone;



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

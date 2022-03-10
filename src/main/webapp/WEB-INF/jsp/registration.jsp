<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<link rel="stylesheet" href="/pub/css/register.css">


<div class="container">
    <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-dark-logo.png" alt="" class="/pub/img/GoGoGadgets-dark-logo.png">
    <form action="/register/registerSubmit" method="post">

        <h4>${errorMessages}</h4>
        <input type="hidden" name="id" value="${formBeanKey.id}">


        <input type="text" autocomplete="off" name="firstname" id="firstname" placeholder="First Name" value="${formBeanKey.firstName}
" >
        <input type="text" autocomplete="off" name="lastname" id="lastname" placeholder="Last Name" value="${formBeanKey.lastName}
">
        <input type="text" autocomplete="off"  name="number" id="number" placeholder="Phone Number" value="${formBeanKey.phone}
">
        <input type="text" autocomplete="off" name="address" id="address" placeholder="Shipping Address" ${formBeanKey.address}
        >
        <input type="email" autocomplete="off" name="email"  id="email" placeholder="Enter Email"  ${formBeanKey.email}>
        <input type="email" autocomplete="off" name="username"  id="username" placeholder="Enter Username"  ${formBeanKey.username}>
        <input type="password" autocomplete="off" name="password" id="password" placeholder="Enter Password" ${formBeanKey.password}>
        <input type="password" autocomplete="off" name="confirmpassword" id="confirmpassword" placeholder="Confirm Password" ${formBeanKey.confirmpassword}>

        <input type="checkbox" name="checkbox" id="terms-cond" class="checkbox" >
        <label for="terms-cond">I Agree to Go Go Gadgets <a href="#">Terms & Conditions</a></label>
        <br>
        <input type="checkbox" name="checkbox" id="notifications" class="checkbox">
        <label for="notifications">Receive upcoming Offers and Discounts</label>
        <button class="btn-submit">Create Account</button>
    </form>
    <a href=/login/login class="link">Already have an Account? Log in here.</a>
</div>






<jsp:include page="footer.jsp" />
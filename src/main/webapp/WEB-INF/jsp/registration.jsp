<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<div class="container">
    <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-dark-logo.png" alt="" class="/pub/img/GoGoGadgets-dark-logo.png">
    <form action="/registrationSubmit" method="post">
        <input type="text" autocomplete="off" id="firstname" placeholder="First Name" >
        <input type="text" autocomplete="off" id="lastname" placeholder="Last Name" >
        <input type="text" autocomplete="off" id="number" placeholder="Phone Number">
        <input type="text" autocomplete="off" id="address" placeholder="Shipping Address">
        <input type="email" autocomplete="off" id="email" placeholder="Enter Email" >
        <input type="password" autocomplete="off" id="password" placeholder="Enter Password">
        <input type="password" autocomplete="off" id="confirmpassword" placeholder="Confirm Password">

        <input type="checkbox" name="checkbox" id="terms-cond" class="checkbox" >
        <label for="terms-cond">I Agree to Go Go Gadgets <a href="#">Terms & Conditions</a></label>
        <br>
        <input type="checkbox" name="checkbox" id="notifications" class="checkbox">
        <label for="notifications">Receive upcoming Offers and Discounts</label>
        <button class="btn-submit">Create Account</button>
    </form>
    <a href="/login" class="link">Already have an Account? Log in here.</a>
</div>






<jsp:include page="footer.jsp" />
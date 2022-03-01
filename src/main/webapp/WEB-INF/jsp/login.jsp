<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<link href="/pub/css/login.css" rel="stylesheet"/>

<div class="container">
    <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-dark-logo.png" alt="../pub/img/GoGoGadgets-dark-logo.png" class="logo">
    <form action="login/loginSecurityPost" method="post">

        <input type="username" autocomplete="off" id="username" placeholder="Enter Email" >
        <input type="password" autocomplete="off" id="password" placeholder="Enter Password">


        <button class="btn-submit">Log In</button>
    </form>
    <a onclick="location.href='/registration'" class="link">Don't have an Account? Create one here.</a>
</div>


<jsp:include page="footer.jsp" />
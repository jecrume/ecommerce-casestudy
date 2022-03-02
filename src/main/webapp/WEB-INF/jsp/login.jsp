<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<link href="/pub/css/login.css" rel="stylesheet"/>

<div class="container">
    <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-dark-logo.png" alt="/../pub/img/GoGoGadgets-dark-logo.png" class="logo">
    <h2>User Login</h2>

    <form action="/login/loginSecurityPost" method="post">
        <h1 style="color:red">${errorMessage}</h1>
        <input type="username" name="username" autocomplete="off" id="username" placeholder="Enter Email" >
        <input type="password" name="password" autocomplete="off" id="password" placeholder="Enter Password">


        <button class="btn-submit" type="submit" name="submit">Log In</button>
    </form>
    <a onclick="location.href='/registration'" class="link">Don't have an Account? Create one here.</a>
</div>


<jsp:include page="footer.jsp" />
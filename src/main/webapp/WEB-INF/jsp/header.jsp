<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>

<head>
    <title>PerScholas Case Study</title>

    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/pub/css/header.css">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
<%--    <script--%>
<%--            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"--%>
<%--            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"--%>
<%--            crossorigin="anonymous"></script>--%>

<%--    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>--%>
<%--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.1/css/all.css" rel="stylesheet">
</head>
<body>
<div class="nav-bar">
<div class="nav">
    <a onclick = "location.href= '/'" >
    <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-dark-logo.png"  class="brand-logo" alt="pub/img/GoGoGadgets-dark-logo.png">
    </a>
    <div class="nav-items">

        <div class="search">
            <form class="header-form "action="/search/results" method="get">
            <input id="search-box" type="text"  name= "keyword" class="search-box" placeholder="Search brands, products, etc.">
            <button  class="search-btn">Search</button>
            </form>
        </div>
        <c:if test="${not empty pageContext.request.userPrincipal}">
            <security:authorize access="isAuthenticated()">
                Welcome, <security:authentication property="name" />
             </security:authorize>
        </c:if>
        <a href="/user/profile" class="user"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/user.png" alt="pub/img/user.png"></a>

        <security:authorize access="!isAuthenticated()">
        <a  href="/login/login">Login</a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">

                <a  href="/login/logout">Logout</a>
        </security:authorize>

        <a href="/cart/show" class="cart"><i class="fa badge fa-lg" value=${sessionScope.cartCount}>&#xf07a;</i></a>

    </div>
</div>

<ul class="links-container">
    <li class="link-item"><a onclick="location.href='/category/home'" class="link">Home</a></li>
    <li class="link-item"><a onclick="location.href='/category/av'" class="link">Audio & Visual</a></li>
    <li class="link-item"><a onclick="location.href='/category/gaming'" class="link">Gaming</a></li>
    <li class="link-item"><a onclick="location.href='/category/toys'" class="link">Toys</a></li>
    <li class="link-item"><a onclick="location.href='/category/interesting'" class="link">Interesting</a></li>

</ul>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('.drop').hover(function () {
            $(this).find('.sm-menu').first().stop(true, true).slideDown(125);
        }, function () {
            $(this).find('.sm-menu').first().stop(true, true).slideUp(105)
        });
    });
</script>
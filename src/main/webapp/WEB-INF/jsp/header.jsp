<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>
<body>
<div class="nav-bar">
<div class="nav">
    <a onclick = "location.href= '/'" >
    <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-dark-logo.png"  class="brand-logo" alt="pub/img/GoGoGadgets-dark-logo.png">
    </a>
    <div class="nav-items">

        <div class="search">
            <form action="/search/results" method="get">
            <input type="text"  name= "keyword" class="search-box" placeholder="Search brands, products, etc.">
            <button  class="search-btn">Search</button>
            </form>
        </div>
        <c:if test="${not empty pageContext.request.userPrincipal}">
            <security:authorize access="isAuthenticated()">
                Welcome, <security:authentication property="firstName" />
             </security:authorize>
        </c:if>
        <a href="#" class="user"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/user.png" alt="pub/img/user.png"></a>
        <a href="/cart/show" class="cart"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/cart.png" alt="pub/img/cart.png"></a>

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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
<jsp:include page="header.jsp" />

<link rel="stylesheet" href="/pub/css/home.css">
<link rel="stylesheet" href="/pub/css/search.css">

<section class="search-results">
    <h2 class="heading">${category} Products</h2>
    <div class="product-container">
        <c:forEach items ="${productList}" var="product">
        <div class="product-card">
            <div class="product-image">
                <a onclick="location.href='/product/${product.id}'">
                <img src="${product.mainImgUrl}" class="product-thumb" alt="">
                </a>
                <button class="card-btn">Add To Wish List</button>
            </div>
            <div class="product-info">
                <h2 class="product-brand">${product.productName}</h2>
                <p class="product-short-desc">${product.productDescription}</p>
                <span class="price">$${product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</span>
            </div>
        </div>
        </c:forEach>
    </div>
</section>

<jsp:include page="footer.jsp" />
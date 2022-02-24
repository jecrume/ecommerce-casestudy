<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
<jsp:include page="header.jsp" />

<link href="pub/css/home.css" rel="stylesheet" />
<header class="hero-section">
    <div class="content">
        <img src="https://gogogadget-media.s3.amazonaws.com/img/images/GoGoGadgets-light-logo.png" alt="pub/img/GoGoGadgets-light-logo.png" class="logo">
        <p class="sub-heading">Best Gadgets Across the Internet</p>
    </div>

</header>

<section class="product">
    <h2 class="product-category">Hot Products</h2>
    <button class="pre-btn"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/arrow.png" alt=""></button>
    <button class="nxt-btn"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/arrow.png" alt=""></button>
    <div class="product-container">
        <c:forEach items="${productsList}" var="product">

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

<section class="product">
    <h2 class="product-category"> Interesting Products</h2>
    <button class="pre-btn"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/arrow.png" alt=""></button>
    <button class="nxt-btn"><img src="https://gogogadget-media.s3.amazonaws.com/img/images/arrow.png" alt=""></button>
    <div class="product-container">
        <c:forEach items="${specialProdList}" var="product">
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

<section class="category-container">
    <a href="#" class="category">
        <img src="https://gogogadget-media.s3.amazonaws.com/img/images/gaming-category.jpg" alt="pub/img/gaming-category.jpg">
        <p class="category-title">Gaming <br> Gadgets</p>
    </a>
    <a href="#" class="category">
        <img src="https://gogogadget-media.s3.amazonaws.com/img/images/audio-category4.jpg" alt="pub/img/audio-category4.jpg">
        <p class="category-title">Audio & Visual <br> Gadgets</p>
    </a>
    <a href="#" class="category">
        <img src="https://gogogadget-media.s3.amazonaws.com/img/images/3dprinting-category.jpg" alt="pub/img/3dprinting-category.jpg">
        <p class="category-title">Interesting <br> Gadgets</p>
    </a>
    <a href="#" class="category">
        <img src="https://gogogadget-media.s3.amazonaws.com/img/images/toys-category.jpg" alt="pub/img/toys-category.jpg">
        <p class="category-title">Toy <br> Gadgets</p>
    </a>

    <a href="#" class="category">
        <img src="https://gogogadget-media.s3.amazonaws.com/img/images/home-category1.jpg" alt="pub/img/home-category1.jpg">
        <p class="category-title">Home <br> Gadgets</p>
    </a>
</section>

<script src="/pub/js/home.js"></script>

<jsp:include page="footer.jsp" />
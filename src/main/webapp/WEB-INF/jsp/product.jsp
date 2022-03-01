<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>

<jsp:include page="header.jsp" />
<link href="/pub/css/product.css" rel="stylesheet" />
<link rel="stylesheet" href="/pub/css/home.css">

<section class="product-details">
    <div class="image-slider" style="background-image: url('${currentProduct.mainImgUrl}');">
        <div class="product-images">
            <img src="${currentProduct.mainImgUrl}" class="active" alt="">
            <c:forEach items="${imgUrlList}" var="img">
            <img src="${img}" class="active" alt="">

            </c:forEach>

        </div>
    </div>

    <div class="details">
        <h2 class="product-brand">${currentProduct.productName}</h2>
        <span class="product-price">$${currentProduct.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</span>
<%--        <span class="product-actual-price"></span>--%>
<%--        <span class="product-discount">(50% Off)</span>--%>
        <h2 class="heading">Description</h2>
        <p class="des">${currentProduct.productDescription}
        <div id="buttons">
            <button class="btn-cart-btn">Add To Cart</button>
            <button class="btn-wishlist-btn">Add To Wishlist</button>
        </div>
    </div>

</section>

<section class="detailed-des">

    </p>
    <h2 class="heading">Features</h2>
    <p class="des">${currentProduct.productSpecs}</p>
</section>


<section class="product">
    <h2 class="product-category">You May Also Like</h2>
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








<script src="/pub/js/home.js"></script>
<script src="/pub/js/product.js"></script>

<jsp:include page="footer.jsp" />
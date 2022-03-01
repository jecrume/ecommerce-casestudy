<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
<jsp:include page="header.jsp" />

<link href="/pub/css/search.css" rel="stylesheet"/>
<c:choose>
    <c:when test="${not empty searchResult}">
        <h1>Search Results for "${keyword}"</h1>
        <div class="product-container">

         <c:forEach items="${searchResult}" var="product">

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
    </c:when>

    <c:otherwise>
        <div class="jumbotron">
            <div class=""text-center>
                <h1>No results for "${keyword}"</h1>
            </div>

            <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
        </div>
    </c:otherwise>
</c:choose>
<jsp:include page="footer.jsp" />

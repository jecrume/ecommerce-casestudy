<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.math.BigDecimal" %>

<jsp:include page="header.jsp" />
<link href="/pub/css/product.css" rel="stylesheet" />
<link rel="stylesheet" href="/pub/css/home.css">

<section class="product-details">
    <div class="image-slider" style="background-image: url('${currentProduct.mainImgUrl}');">
        <div class="product-images">

<%--            <c:choose>--%>
<%--                <c:when test="${fn:contains(src,'youtube')}">--%>
<%--                    <iframe src="${img}" class="active" width="420" height="315"  title="YouTube video player"  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>--%>

<%--                    </iframe>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
                    <img src="${currentProduct.mainImgUrl}" class="active" alt="">
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
            <c:forEach items="${imgUrlList}" var="img">
<%--                <c:choose>--%>
<%--                     <c:when test="${fn:contains(img,'youtube')}">--%>
<%--                         <iframe src="${img}" class="active" width="100" height="80"  title="YouTube video player"  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>--%>

<%--                         </iframe>--%>
<%--                     </c:when>--%>
<%--                     <c:otherwise>--%>
                        <img src="${img}" class="active" alt="">
<%--                     </c:otherwise>--%>
<%--                 </c:choose>--%>
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

            <form id="addToCart" action="/cart/addToCart" method="post">

                <div id="pass-quantity" class="pass-quantity col-lg-3 col-md-4 col-sm-3">
                    <label for="pass-quantity" class="pass-quantity">Quantity</label>
                    <input  name= "quantity" class="form-control" type="number" value="1" min="1" max="${currentProduct.availableQty}">
                </div>



                <input  type="hidden" name="id" value=${currentProduct.id} />
                <button type="submit" class="btn-cart-btn mt-2" id="buttonAddToCart">Add To Cart</button>
            </form>

        <form action="/addToWishList" method="post">
            <input type="hidden" name="productId" value="${currentProduct.id}" >
            <button type="submit" class="btn-wishlist-btn">Add To Wishlist</button>
        </form>
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
                    <form action="/addToWishList" method="post">
                    <button type="submit" class="card-btn">Add To Wish List</button>
                        <input type="hidden" name="productId" value="${product.id}" >
                    </form>
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

<%--<script>--%>
<%--    function customAlert(msg,duration)--%>
<%--    {--%>
<%--        var styler = document.createElement("div");--%>
<%--        styler.setAttribute("style","border: solid 5px Red;width:auto;height:auto;top:50%;left:40%;background-color:#444;color:Silver");--%>
<%--        styler.innerHTML = "<h1>"+msg+"</h1>";--%>
<%--        setTimeout(function()--%>
<%--        {--%>
<%--            styler.parentNode.removeChild(styler);--%>
<%--        },duration);--%>
<%--        document.body.appendChild(styler);--%>
<%--    }--%>
<%--    function caller()--%>
<%--    {--%>
<%--        customAlert("The Product was added to the cart.","2000");--%>
<%--    }--%>
<%--</script>--%>
<jsp:include page="footer.jsp" />
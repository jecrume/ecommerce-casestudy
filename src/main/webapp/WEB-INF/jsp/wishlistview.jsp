<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>

<jsp:include page="header.jsp" />

<link rel="stylesheet" href="/pub/css/wishlistview.css">
<div class="container">
    <div class="row">
        <div class="col-md-3 ">
            <div class="list-group ">
                <a href="#" class="list-group-item list-group-item-action ">Dashboard</a>
                <a href="#" class="list-group-item list-group-item-action">User Management</a>
                <a href="#" class="list-group-item list-group-item-action">Orders</a>
                <a href="#" class="list-group-item list-group-item-action active">Wishlist</a>
                <a href="#" class="list-group-item list-group-item-action">Settings</a>


            </div>
        </div>
        <div class="cart-wrap">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="main-heading mb-10">My wishlist</div>
                        <div class="table-wishlist">
                            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                                <thead>
                                <tr>
                                    <th width="45%">Product Name</th>
                                    <th width="15%">Unit Price</th>
                                    <th width="15%">Stock Status</th>
                                    <th width="15%"></th>
                                    <th width="10%"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${wishLists}" var="wish">
                                <tr>
                                    <td width="45%" >
                                        <div class="display-flex align-center">
                                            <div class="img-product">
                                                <img src="${wish.product.mainImgUrl}" alt="" class="mCS_img_loaded">
                                            </div>
                                            <div class="name-product">
                                                ${wish.product.productName}
                                            </div>
                                        </div>
                                    </td>
                                    <td width="15%" class="price">$${wish.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                                    <td width="15%"><span class="in-stock-box">${wish.product.availableQty} In Stock</span></td>
                                    <td width="15%">
                                        <form action="/"><button class="round-black-btn small-btn">Remove</button></form></td>
                                    <td width="10%" class="text-center"><a href="#" class="trash-icon"><i class="far fa-trash-alt"></i></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="footer.jsp" />
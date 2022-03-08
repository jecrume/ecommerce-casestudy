<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="org.apache.commons.lang3.StringUtils" %>
<jsp:include page="header.jsp" />
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href="/pub/css/cart.css" rel="stylesheet">

<div class="container">

    <c:choose>
        <c:when test="${not empty cartItems}">
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                <tr>
                    <th style="width:50%">Product</th>
                    <th style="width:10%">Price</th>
                    <th style="width:8%">Quantity</th>
                    <th style="width:22%" class="text-center">Subtotal</th>
                    <th style="width:10%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartItems}" var="cartItem">
                <tr>

                    <td data-th="Product">

                        <div class="row">
                            <div class="col-sm-5 hidden-sm"><img id="productImg" src="${cartItem.product.mainImgUrl}" alt="..." class="img-responsive"/></div>
                            <div class="col-sm-11">
                                <h4 >${cartItem.product.productName}</h4>
                               <p >${StringUtils.abbreviate(cartItem.product.productSpecs, 100)}</p>
                            </div>
                        </div>
                    </td>
                    <td data-th="Price">$${cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                    <form action="/cart/updateCart" method="post">
                    <td data-th="Quantity">

                        <input name="quantity" type="number" class="form-control text-center" value="${cartItem.quantity}">
                    </td>
                    <td data-th="Subtotal" class="text-center">$${cartItem.quantity*cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                    <td class="actions" data-th="">
                        <div>


                                <input  type="hidden" name="quantity" value=${cartItem.quantity} />
                                <input  type="hidden" name="id" value=${cartItem.product.id} />
                                <button type="submit" class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></button>

                            </form>
                        </div>
                        <div>
                            <form action="/cart/removeFromCart" method="post">
                                <input  type="hidden" name="id" value=${cartItem.product.id} />
                                <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>
                            </form>
                        </div>
                    </td>

                </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="visible-xs">
                    <td class="text-center"><strong>Total 1.99</strong></td>
                </tr>
                <tr>
                    <td><a href="#" onclick="history.go(-1)" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
                    <td colspan="2" class="hidden-xs"></td>
                    <td><strong>Subtotal $${subtotal.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong></td>
                    <td><strong>Sales Tax $${taxes.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong></td>
                    <td class="hidden-xs text-center"><strong>Total $${total.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong></td>
                    <td><a onclick="location.href='/order/orderForm'" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
                </tr>
                </tfoot>
            </table>
        </c:when>

        <c:otherwise>
            <div class="jumbotron">
                <div class=""text-center>
                    <h1>Your cart is empty</h1>
                </div>

                <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
            </div>
        </c:otherwise>
    </c:choose>



</div>


















<jsp:include page="footer.jsp" />
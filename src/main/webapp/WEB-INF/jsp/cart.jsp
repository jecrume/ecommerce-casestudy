<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
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
                <tr>
                    <c:forEach items="${cartItems}" var="cartItem">
                    <td data-th="Product">
                        <div class="row">
                            <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..." class="img-responsive"/></div>
                            <div class="col-sm-10">
                                <h4 class="nomargin">${cartItem.product.productName}</h4>
                                <p style="text-overflow:ellipsis">${cartItem.product.productSpecs}</p>
                            </div>
                        </div>
                    </td>
                    <td data-th="Price">$${cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                    <td data-th="Quantity">
                        <input type="number" class="form-control text-center" value="${cartItem.quantity}">
                    </td>
                    <td data-th="Subtotal" class="text-center">${cartItem.quantity*cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                    <td class="actions" data-th="">
                        <button class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></button>
                        <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>
                    </td>
                    </c:forEach>
                </tr>
                </tbody>
                <tfoot>
                <tr class="visible-xs">
                    <td class="text-center"><strong>Total 1.99</strong></td>
                </tr>
                <tr>
                    <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
                    <td colspan="2" class="hidden-xs"></td>
                    <td><strong>SubtotalSubtotal $${subtotal}</strong></td>
                    <td><strong>Sales Tax $${taxes}</strong></td>
                    <td class="hidden-xs text-center"><strong>Total $${total}</strong></td>
                    <td><a href="#" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
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
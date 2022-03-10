<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<link rel="stylesheet" href="/pub/css/orderSuccess.css">

<div class="headline">
<h1>Order Placed Successfully.</h1>
<h3>Congratulations the below order confirmation will be e-mailed to you shortly.</h3>
</div>
    <div class="row">
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>

                        <p>${name}</p>
                        <br>
                        <p>${streetAddress}</p>
                        <br>
                        <p>${phone}</p>
                    </address>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 text-right">

                    <p> ${date}</p>

                </div>
            </div>
            <div class="row">
                <div class="text-center">
                    <h1>Order Confirmation ${orderId}</h1>
                </div>
                </span>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Product</th>
                        <th>#</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartItems}" var="cartItem">
                        <tr>
                            <td class="col-md-9"><img  src="${cartItem.product.mainImgUrl}" class = "img-responsive" alt="Responsive image"><h4><em>${cartItem.product.productName}</em></h4></td>
                            <td class="col-md-1" style="text-align: center"> ${cartItem.quantity} </td>
                            <td class="col-md-1 text-center">$${cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                            <td class="col-md-1 text-center">$${cartItem.quantity*cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>  </td>
                        <td>  </td>
                        <td class="text-right">
                            <p>
                                <strong>Subtotal: </strong>
                            </p>
                            <p>
                                <strong>Tax: </strong>
                            </p></td>
                        <td class="text-center">
                            <p>
                                <strong>$${subtotal.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong>
                            </p>
                            <p>
                                <strong>$${taxes.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong>
                            </p></td>
                    </tr>
                    <tr>
                        <td>  </td>
                        <td>  </td>
                        <td class="text-right"><h4><strong>Total: </strong></h4></td>
                        <td class="text-center text-danger"><h4><strong>$${total.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong></h4></td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</form>

<jsp:include page="footer.jsp" />
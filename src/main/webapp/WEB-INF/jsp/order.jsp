<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="java.time.LocalDateTime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<link rel="stylesheet" href="/pub/css/order.css">

<div class="container-fluid">
<form action="/order/placeOrder" method="post" class="container">


    <div class="row">

        <div class="text-center">
            <h1>Order Form</h1>
        </div>
        <div class="well ">
            <div class="row">
                <div class="col-lg-10 col-lg-10 col-lg-10">
                    <address>
                        <label for="name">Full Name: </label>
                        <input id="name" name= "name" type="text" placeholder="Enter Name">
                        <br>
                        <label for="streetAddress">Street Address:</label>
                        <input id="streetAddress" name="streetAddress" type="text" placeholder="enter street address">
                        <br>
                        <label for="city">City: </label>
                        <input id="city" name="city" type="text" placeholder="enter city">
                        <label for="state">State: </label>
                        <input id="state" name ="state" type="text" placeholder="enter state">
                        <label for="zip">Zipcode: </label>
                        <input id="zip" name ="zip" type="text" placeholder="enter zipcode">
                        <br>
                        <label for="phone">Phone: </label>
                        <input id="phone" name ="phone" type="text" placeholder="enter phone number">
                    </address>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                    <c:set var="now" value="<%=new java.util.Date()%>" />
                    <p>Date: <fmt:formatDate type="date"  value="${now}" /></p>

                </div>
            </div>
            <div class="row">

                </span>
                <table class="table table-striped table-bordered p-3">
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
                        <td class="col-md-5"><img id="productImg" src="${cartItem.product.mainImgUrl}" class = "img-fluid" alt="Responsive image"><h4><em>${cartItem.product.productName}</em></h4></td>
                        <td class="col-lg-10" style="text-align: center"> ${cartItem.quantity} </td>
                        <td class="col-lg-1 text-center">$${cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                        <td class="col-lg-1 text-center">$${cartItem.quantity*cartItem.product.price.setScale(2,BigDecimal.ROUND_HALF_UP)}</td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td class="text-right">
                            <p>
                                <strong>Subtotal: </strong>
                            </p>
                            <p>
                                <strong>Tax:  </strong>
                            </p></td>
                        <td class="text-center">
                            <p>
                                <strong>$${subtotal.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong>
                                <input type="hidden" name="subtotal" value="${subtotal}">
                            </p>
                            <p>
                                <strong>$${taxes.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong>
                                <input type="hidden" name="taxes" value="${taxes}">
                            </p></td>
                    </tr>
                    <tr>
                        <td>  </td>
                        <td>   </td>
                        <td class="text-right"><h4><strong>Total: </strong></h4></td>
                        <td class="text-center text-danger"><h4><strong>$${total.setScale(2,BigDecimal.ROUND_HALF_UP)}</strong></h4></td>
                        <input type="hidden" name="total" value="${total}">

                    </tr>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-success btn-lg btn-block">
                    Pay Now<span class="glyphicon glyphicon-chevron-right"></span>
                </button></td>
            </div>
        </div>
    </div>

</form>
</div>

<jsp:include page="footer.jsp" />
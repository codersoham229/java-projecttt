<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Your Cart - Foodie</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><header class="topbar"><a class="brand" href="${pageContext.request.contextPath}/menu">Foodie<span>.</span></a><nav><a href="${pageContext.request.contextPath}/menu">Menu</a><a class="active" href="${pageContext.request.contextPath}/cart">Cart</a></nav></header>
<main class="container narrow"><p class="eyebrow">YOUR ORDER</p><h1>Your cart</h1>
<c:choose><c:when test="${empty cart}"><section class="empty-state"><div class="big-emoji">🛒</div><h2>Your cart is empty</h2><p>Add something delicious from the menu.</p><a class="button" href="${pageContext.request.contextPath}/menu">Browse menu</a></section></c:when>
<c:otherwise>
<div class="panel">
<c:forEach var="entry" items="${cart}">
<div class="cart-row"><div><h3><c:out value="${entry.value.food.name}"/></h3><p>₹<c:out value="${entry.value.food.price}"/> × <c:out value="${entry.value.quantity}"/></p></div><strong>₹<c:out value="${entry.value.subtotal}"/></strong>
<form method="post" action="${pageContext.request.contextPath}/remove-from-cart"><input type="hidden" name="foodId" value="${entry.key}"><button class="remove-button" type="submit">Remove</button></form></div>
</c:forEach>
<div class="total-row"><span>Total</span><strong>₹<c:out value="${total}"/></strong></div>
<div class="actions"><a class="button secondary" href="${pageContext.request.contextPath}/menu">Continue shopping</a><a class="button" href="${pageContext.request.contextPath}/checkout">Checkout →</a></div>
</div></c:otherwise></c:choose></main><footer>Foodie. — Online Food Ordering System</footer></body></html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Checkout - Foodie</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><header class="topbar"><a class="brand" href="${pageContext.request.contextPath}/menu">Foodie<span>.</span></a><nav><a href="${pageContext.request.contextPath}/menu">Menu</a><a href="${pageContext.request.contextPath}/cart">Cart</a></nav></header>
<main class="container narrow"><p class="eyebrow">ALMOST THERE</p><h1>Checkout</h1><p class="muted">Enter the details for delivery. No account is required.</p>
<div class="panel"><div class="total-row"><span>Order total</span><strong>₹${total}</strong></div>
<% if (request.getAttribute("error") != null) { %><p class="error"><%= request.getAttribute("error") %></p><% } %>
<form class="checkout-form" method="post" action="${pageContext.request.contextPath}/checkout">
<label for="customerName">Full name</label><input id="customerName" name="customerName" maxlength="100" required>
<label for="phone">Phone number</label><input id="phone" name="phone" type="tel" maxlength="20" required>
<label for="address">Delivery address</label><textarea id="address" name="address" rows="3" maxlength="255" required></textarea>
<button class="button full" type="submit">Place order</button>
</form><a class="text-link back-link" href="${pageContext.request.contextPath}/cart">← Back to cart</a></div></main><footer>Foodie. — Online Food Ordering System</footer></body></html>

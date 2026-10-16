<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Foodie - Menu</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header class="topbar"><a class="brand" href="${pageContext.request.contextPath}/menu">Foodie<span>.</span></a>
<nav><a class="active" href="${pageContext.request.contextPath}/menu">Menu</a><a href="${pageContext.request.contextPath}/cart">Cart</a></nav></header>
<main class="container">
  <section class="hero"><p class="eyebrow">FRESH • TASTY • DELIVERED</p><h1>Good food, good mood.</h1><p>Choose your favourites and place your order in a few simple steps.</p></section>
  <div class="section-heading"><div><p class="eyebrow">OUR MENU</p><h2>Popular picks</h2></div><a class="text-link" href="${pageContext.request.contextPath}/cart">View cart →</a></div>
  <div class="food-grid">
    <c:forEach var="food" items="${foods}">
      <article class="food-card">
        <div class="food-emoji">
          <c:choose><c:when test="${food.category eq 'Pizza'}">🍕</c:when><c:when test="${food.category eq 'Burgers'}">🍔</c:when><c:when test="${food.category eq 'Wraps'}">🌯</c:when><c:when test="${food.category eq 'Drinks'}">🥤</c:when><c:otherwise>🍟</c:otherwise></c:choose>
        </div>
        <div class="food-info"><span class="category"><c:out value="${food.category}"/></span><h3><c:out value="${food.name}"/></h3><p><c:out value="${food.description}"/></p>
          <div class="food-bottom"><strong>₹<c:out value="${food.price}"/></strong>
            <form method="post" action="${pageContext.request.contextPath}/add-to-cart"><input type="hidden" name="foodId" value="${food.id}"><button type="submit">Add +</button></form>
          </div>
        </div>
      </article>
    </c:forEach>
  </div>
  <c:if test="${empty foods}"><p>No food items are available right now.</p></c:if>
</main>
<footer>Foodie. — Online Food Ordering System</footer>
</body></html>

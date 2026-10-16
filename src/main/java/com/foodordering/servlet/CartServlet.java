package com.foodordering.servlet;

import com.foodordering.model.CartItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) request.getSession().getAttribute("cart");
        if (cart == null) cart = new LinkedHashMap<Integer, CartItem>();
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.values()) total = total.add(item.getSubtotal());
        request.setAttribute("cart", cart);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}

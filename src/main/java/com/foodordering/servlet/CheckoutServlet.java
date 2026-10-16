package com.foodordering.servlet;

import com.foodordering.dao.OrderDAO;
import com.foodordering.model.CartItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) request.getSession().getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/menu");
            return;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.values()) total = total.add(item.getSubtotal());
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = clean(request.getParameter("customerName"));
        String phone = clean(request.getParameter("phone"));
        String address = clean(request.getParameter("address"));
        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            request.setAttribute("error", "Please complete all customer and delivery fields.");
            doGet(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/menu");
            return;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.values()) total = total.add(item.getSubtotal());
        try {
            int orderId = new OrderDAO().placeOrder(name, phone, address, cart.values().stream()
                .collect(java.util.stream.Collectors.toList()), total);
            session.removeAttribute("cart");
            request.setAttribute("orderId", orderId);
            request.setAttribute("total", total);
            request.getRequestDispatcher("/WEB-INF/views/order-success.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Unable to place order. Check the database connection and tables.", e);
        }
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}

package com.foodordering.servlet;

import com.foodordering.dao.FoodDAO;
import com.foodordering.model.CartItem;
import com.foodordering.model.FoodItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<Integer, CartItem>();
            session.setAttribute("cart", cart);
        }

        try {
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            FoodItem food = new FoodDAO().getFoodById(foodId);
            if (food == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Food item not found.");
                return;
            }
            CartItem existing = cart.get(foodId);
            if (existing == null) cart.put(foodId, new CartItem(food, 1));
            else existing.setQuantity(existing.getQuantity() + 1);
            response.sendRedirect(request.getContextPath() + "/cart");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid food item.");
        } catch (SQLException e) {
            throw new ServletException("Unable to add item to cart.", e);
        }
    }
}

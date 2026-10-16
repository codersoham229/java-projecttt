package com.foodordering.servlet;

import com.foodordering.model.CartItem;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            if (cart != null) {
                try { cart.remove(Integer.parseInt(request.getParameter("foodId"))); }
                catch (NumberFormatException ignored) { }
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}

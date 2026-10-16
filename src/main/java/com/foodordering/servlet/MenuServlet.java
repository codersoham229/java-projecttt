package com.foodordering.servlet;

import com.foodordering.dao.FoodDAO;
import com.foodordering.model.FoodItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private FoodDAO foodDAO;
    @Override public void init() { foodDAO = new FoodDAO(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<FoodItem> foods = foodDAO.getAvailableFoods();
            request.setAttribute("foods", foods);
            request.getRequestDispatcher("/WEB-INF/views/menu.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Unable to load the food menu. Check the database connection.", e);
        }
    }
}

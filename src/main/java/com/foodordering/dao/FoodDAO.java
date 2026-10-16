package com.foodordering.dao;

import com.foodordering.model.FoodItem;
import com.foodordering.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    public List<FoodItem> getAvailableFoods() throws SQLException {
        List<FoodItem> foods = new ArrayList<FoodItem>();
        String sql = "SELECT id, name, description, price, category FROM food_items WHERE available = TRUE ORDER BY category, name";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                foods.add(new FoodItem(rs.getInt("id"), rs.getString("name"),
                    rs.getString("description"), rs.getBigDecimal("price"), rs.getString("category")));
            }
        }
        return foods;
    }

    public FoodItem getFoodById(int id) throws SQLException {
        String sql = "SELECT id, name, description, price, category FROM food_items WHERE id = ? AND available = TRUE";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new FoodItem(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getBigDecimal("price"), rs.getString("category"));
                }
            }
        }
        return null;
    }
}

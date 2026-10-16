package com.foodordering.dao;

import com.foodordering.model.CartItem;
import com.foodordering.util.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class OrderDAO {
    public int placeOrder(String customerName, String phone, String address,
                          List<CartItem> cart, BigDecimal total) throws SQLException {
        String orderSql = "INSERT INTO customer_orders (customer_name, phone, delivery_address, total_amount, order_status) VALUES (?, ?, ?, ?, 'Placed')";
        String itemSql = "INSERT INTO order_items (order_id, food_item_id, food_name, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                int orderId;
                try (PreparedStatement ps = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, customerName);
                    ps.setString(2, phone);
                    ps.setString(3, address);
                    ps.setBigDecimal(4, total);
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (!keys.next()) throw new SQLException("Could not create order.");
                        orderId = keys.getInt(1);
                    }
                }
                try (PreparedStatement ps = con.prepareStatement(itemSql)) {
                    for (CartItem item : cart) {
                        ps.setInt(1, orderId);
                        ps.setInt(2, item.getFood().getId());
                        ps.setString(3, item.getFood().getName());
                        ps.setInt(4, item.getQuantity());
                        ps.setBigDecimal(5, item.getFood().getPrice());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                con.commit();
                return orderId;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}

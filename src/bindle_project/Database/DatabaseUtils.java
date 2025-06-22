/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DatabaseUtils {
    public static void SaveToDatabase(JLabel priceLabel, JLabel nameLabel) {
        String title = nameLabel.getText().trim();
        double price;

        // Validate inputs
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Title cannot be empty");
            return;
        }

        try {
            price = Double.parseDouble(priceLabel.getText().trim());
            if (price <= 0) {
                JOptionPane.showMessageDialog(null, "Price must be greater than zero");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price format");
            return;
        }

        // Database operation
        DbConnection dbConn = new MySqlConnection();
        Connection conn = dbConn.openConnection();

        if (conn != null) {
            String sql = "INSERT INTO books (title, author, price, `condition`, seller_id, status) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            try {
                // Assuming some default values since not all fields are provided
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setString(2, "Unknown Author"); // Default author
                pstmt.setDouble(3, price);
                pstmt.setString(4, "New"); // Default condition
                pstmt.setInt(5, 1); // Default seller_id (assumes user with id 1 exists)
                pstmt.setString(6, "available"); // Default status

                int rowsAffected = dbConn.executeUpdate(conn, sql);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Book saved successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save book");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            } finally {
                dbConn.closeConnection(conn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to connect to database");
        }
    }
}
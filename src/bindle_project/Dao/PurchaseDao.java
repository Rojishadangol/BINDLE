/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class PurchaseDao {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle";
    private static final String USER = "root";
    private static final String PASSWORD = "roji@123"; // Update with your actual password or use a config

    public boolean purchaseBook(int bookId, int userId) {
        String sql = "UPDATE books SET status = 'sold', buyer_id = ? WHERE id = ? AND status = 'available'";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId); // Set buyer_id to the purchasing user
            pstmt.setInt(2, bookId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Purchase successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Book not available for purchase.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error purchasing book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException in purchaseBook: " + e.getMessage());
            return false;
        }
    }
}
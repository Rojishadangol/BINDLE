/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ListDao {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle";
    private static final String USER = "root";
    private static final String PASSWORD = "roji@123"; // Update with your actual password or use a config

    public boolean listBook(Book book) {
        if (book == null) {
            JOptionPane.showMessageDialog(null, "Book object is null.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String sql = "INSERT INTO books (title, author, price, condition, seller_id, status) VALUES (?, ?, ?, ?, ?, 'available')";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setDouble(3, book.getPrice());
            pstmt.setString(4, book.getCondition());
            pstmt.setInt(5, book.getSellerId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book listed successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to list book.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error listing book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException in listBook: " + e.getMessage());
            return false;
        }
    }
}
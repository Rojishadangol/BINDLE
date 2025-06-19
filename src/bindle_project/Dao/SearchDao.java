/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SearchDao {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle";
    private static final String USER = "root";
    private static final String PASSWORD = "roji@123"; // Update with your actual password or use a config

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        if (keyword == null) {
            return books; // Return empty list for null input
        }

        String sql = "SELECT * FROM books WHERE (title LIKE ? OR author LIKE ?) AND status = 'available'";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            pstmt.setString(1, "%" + keyword.trim() + "%");
            pstmt.setString(2, "%" + keyword.trim() + "%");

            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price"),
                    rs.getString("condition"),
                    rs.getInt("seller_id"),
                    rs.getString("status")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching books: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException in searchBooks: " + e.getMessage());
            return new ArrayList<>(); // Return empty list on failure
        }
        return books;
    }
}
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

public class BookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle";
    private static final String USER = "root";
    private static final String PASSWORD = "roji@123"; // Update with your actual password

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        if (keyword == null) return books;
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
                    rs.getString("condition"),
                    rs.getString("status")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching books: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
        return books;
    }

    public boolean addBook(String title, String author, double price, String condition, int sellerId) {
        String sql = "INSERT INTO books (title, author, price, condition, seller_id, status) VALUES (?, ?, ?, ?, ?, 'available')";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDouble(3, price);
            pstmt.setString(4, condition);
            pstmt.setInt(5, sellerId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean markAsSold(int bookId) {
        String sql = "UPDATE books SET status = 'sold' WHERE id = ? AND status = 'available'";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book marked as sold!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Book not available for sale.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error marking book as sold: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
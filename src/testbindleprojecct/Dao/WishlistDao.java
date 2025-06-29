/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.Dao;

import testbindleprojecct.Database.MySqlConnection;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDao {
    public final MySqlConnection dbConnection = MySqlConnection.getInstance();

    public List<Book> getWishlistBooks(int userId) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.price, b.image_url " +
                     "FROM books b JOIN wishlist w ON b.id = w.book_id WHERE w.user_id = ?";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("image_url")
                    );
                    book.setId(rs.getString("id"));
                    books.add(book);
                }
            }
        }
        return books;
    }

    public boolean addBookToWishlist(int userId, String bookId) throws SQLException {
        String sql = "INSERT INTO wishlist (user_id, book_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE user_id = user_id";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, bookId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean removeBookFromWishlist(int userId, String bookId) throws SQLException {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND book_id = ?";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, bookId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public void addToWishlist(int userId, String bookId) throws SQLException {
        String sql = "INSERT INTO wishlist (user_id, book_id) VALUES (?, ?)";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, bookId);
            pstmt.executeUpdate();
        }
    }
}
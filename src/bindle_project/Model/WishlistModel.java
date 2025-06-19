/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

/**
 *
 * @author acer
 */
import java.util.*;
import java.io.*;

// Assuming Book class has equals and hashCode properly overridden


public class WishlistModel {
    private User currentUser;
    private Connection connection;

    public WishlistModel(User currentUser, Connection connection) {
        this.currentUser = currentUser;
        this.connection = connection;
    }

    // Add book to wishlist
    public boolean addBook(Book book) {
        String sql = "INSERT IGNORE INTO wishlists (user_id, book_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, currentUser.getId());
            ps.setInt(2, book.getId());
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove book from wishlist
    public boolean removeBook(Book book) {
        String sql = "DELETE FROM wishlists WHERE user_id = ? AND book_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, currentUser.getId());
            ps.setInt(2, book.getId());
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all wishlist books for user
    public List<Book> getWishlistBooks() {
        List<Book> wishlist = new ArrayList<>();
        String sql = "SELECT b.* FROM books b JOIN wishlists w ON b.id = w.book_id WHERE w.user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, currentUser.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = mapResultSetToBook(rs);
                wishlist.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        // Map result set columns to Book object properties
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String description = rs.getString("description");
        String thumbnail = rs.getString("thumbnail");
        // Create and return Book object (adjust constructor accordingly)
        return new Book(id, title, author, description, thumbnail);
    }
}



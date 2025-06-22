/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private User user;
    private Connection connection;

    public Cart(User user) {
        this.User = user;
        this.connection = connection;
    }

    public List<Book> getCartBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT b.id, b.title, b.author, b.price, b.condition, b.seller_id, b.status " +
                         "FROM carts c JOIN books b ON c.book_id = b.id WHERE c.user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
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
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error fetching cart books: " + e.getMessage());
        }
        return books;
    }

    public boolean removeBookFromCart(Book book) {
        try {
            String sql = "DELETE FROM carts WHERE user_id = ? AND book_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, book.getId());
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error removing book from cart: " + e.getMessage());
            return false;
        }
    }

    public Connection getConnection() { return connection; }
    public User getUser() { return user; }

    public void addBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
package bindle_project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private User user;
    private List<Book> books;
    private Connection connection;

    public Cart(int user) {
////        if (user == null || connection == null) {
////            throw new IllegalArgumentException("User or connection cannot be null");
////        }
//        this.user = user;
//        this.books = new ArrayList<>();
//        this.connection = connection;
//        loadBooksFromDatabase();
    }

    public User getUser() {
        return user;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean addBook(Book book) {
        if (book == null || books.contains(book)) {
            return false;
        }
        try {
            String sql = "INSERT INTO cart (user_id, book_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, book.getId());
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            if (rowsAffected > 0) {
                books.add(book);
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error adding book to cart: " + e.getMessage(), e);
        }
    }

    public boolean removeBookFromCart(Book book) {
        if (book == null) {
            return false;
        }
        try {
            String sql = "DELETE FROM cart WHERE user_id = ? AND book_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, book.getId());
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            if (rowsAffected > 0) {
                books.remove(book);
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error removing book from cart: " + e.getMessage(), e);
        }
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    private void loadBooksFromDatabase() {
        try {
            String sql = "SELECT b.* FROM books b JOIN cart c ON b.id = c.book_id WHERE c.user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            books.clear();
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("condition"),
                    rs.getString("availability"));
                books.add(book);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error loading cart from database: " + e.getMessage(), e);
        }
    }
}
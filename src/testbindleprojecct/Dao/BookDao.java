package testbindleprojecct.Dao;

import testbindleprojecct.Database.MySqlConnection;
import testbindleprojecct.Model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public final MySqlConnection dbConnection = MySqlConnection.getInstance();

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, author, price, image_url, description, sold, seller_id FROM books";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price"),
                    rs.getString("image_url")
                );
                book.setId(rs.getString("id"));
                book.setDescription(rs.getString("description"));
                book.setSold(rs.getBoolean("sold"));
                book.setSellerId(rs.getInt("seller_id")); // Handle null with getObject if needed
                books.add(book);
            }
            System.out.println("Retrieved " + books.size() + " books.");
            return books;
        } catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
            throw e;
        }
    }

    public List<Book> searchBooks(String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, author, price, image_url, description, sold, seller_id FROM books WHERE title LIKE ? OR author LIKE ?";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("image_url")
                    );
                    book.setId(rs.getString("id"));
                    book.setDescription(rs.getString("description"));
                    book.setSold(rs.getBoolean("sold"));
                    book.setSellerId(rs.getInt("seller_id")); // Handle null with getObject if needed
                    books.add(book);
                }
                System.out.println("Found " + books.size() + " books for query: " + query);
                return books;
            }
        } catch (SQLException e) {
            System.err.println("Error searching books: " + e.getMessage());
            throw e;
        }
    }

    public Book getBookById(String bookId) throws SQLException {
        String sql = "SELECT id, title, author, price, image_url, description, sold, seller_id FROM books WHERE id = ?";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("image_url")
                    );
                    book.setId(rs.getString("id"));
                    book.setDescription(rs.getString("description"));
                    book.setSold(rs.getBoolean("sold"));
                    book.setSellerId(rs.getInt("seller_id")); // Handle null with getObject if needed
                    System.out.println("Book retrieved: " + bookId);
                    return book;
                }
                System.out.println("Book not found: " + bookId);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + bookId + " - " + e.getMessage());
            throw e;
        }
    }

    public boolean addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (id, title, author, price, image_url, description, sold, seller_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setDouble(4, book.getPrice());
            stmt.setString(5, book.getImageUrl());
            stmt.setString(6, book.getDescription() != null ? book.getDescription() : "");
            stmt.setBoolean(7, book.isSold());
            stmt.setInt(8, book.getSellerId() != null ? book.getSellerId() : 0); // Default to 0 if null
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean markAsSold(String bookId, int sellerId) throws SQLException {
        String sql = "UPDATE books SET sold = TRUE WHERE id = ? AND seller_id = ?";
        try (Connection conn = dbConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookId);
            stmt.setInt(2, sellerId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book marked as sold by seller " + sellerId + ": " + bookId);
            } else {
                System.out.println("No book found or seller mismatch for book " + bookId + " by seller " + sellerId);
            }
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error marking book as sold: " + e.getMessage());
            throw e;
        }
    }
}
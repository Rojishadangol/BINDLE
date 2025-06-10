/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;
import database.DatabaseConnection;
import searchmodel.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ushadhakal
 */
public class userdao {
    public userdao() {
        createTableIfNotExists();
        seedSampleData();
    }

    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT NOT NULL," +
                     "author TEXT," +
                     "category TEXT," +
                     "price REAL," +
                     "condition TEXT)";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void seedSampleData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) AS count FROM books");
            if (rs.next() && rs.getInt("count") > 0) return;

            String sql = "INSERT INTO books (title, author, category, price, condition) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            String[][] sampleBooks = {
                {"The Alchemist", "Paulo Coelho", "Fiction", "5.0", "Good"},
                {"A Brief History of Time", "Stephen Hawking", "Science", "7.5", "Excellent"},
                {"Tarot for Beginners", "Lisa Chamberlain", "Spiritual", "4.0", "Fair"}
            };

            for (String[] book : sampleBooks) {
                ps.setString(1, book[0]);
                ps.setString(2, book[1]);
                ps.setString(3, book[2]);
                ps.setDouble(4, Double.parseDouble(book[3]));
                ps.setString(5, book[4]);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchTerm = "%" + keyword + "%";
            ps.setString(1, searchTerm);
            ps.setString(2, searchTerm);
            ps.setString(3, searchTerm);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getString("condition")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }
    
}


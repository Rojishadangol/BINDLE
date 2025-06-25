/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;


//import bindle_project.Model.Book;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

public class HomepageDao {
//    private Connection conn;
//
//    public HomepageDao(Connection con) {
//        this.conn = con;
//    }
//
//    public List<Book> getLatestBooks() {
//        List<Book> list = new ArrayList<>();
//        if (conn == null) {
//            System.err.println("Database connection is null.");
//            return list;
//        }

//        String sql = "SELECT * FROM books ORDER BY added_date DESC LIMIT 5";
//        try (PreparedStatement stmnt = conn.prepareStatement(sql);
//             ResultSet rs = stmnt.executeQuery()) {
//
//            while (rs.next()) {
//                Book book = new Book(
//                    rs.getInt("id"),
//                    rs.getString("title"),
//                    rs.getString("author"),
//                    rs.getString("condition"),
//                    rs.getString("status")
//                );
//                list.add(book);
//            }
//        } catch (SQLException e) {
//            System.err.println("SQLException in getLatestBooks: " + e.getMessage());
//            return new ArrayList<>(); // Return empty list on failure
//        }
//        return list;
//    }
}
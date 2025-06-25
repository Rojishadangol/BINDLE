/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
/**
 *
 * @author acer
 */
public interface DbConnection {

    Connection openConnection();
    void closeConnection(Connection conn);
    ResultSet runQuery(Connection conn, String query);
    int executeUpdate(Connection conn, String query);

    String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle?serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "roji@123";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    static void initDatabase() {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
           stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
    "id INT AUTO_INCREMENT PRIMARY KEY," +
    "title VARCHAR(255) NOT NULL," +
    "author VARCHAR(255) NOT NULL," +
    "price DECIMAL(10,2) NOT NULL," +
    "`condition` VARCHAR(50) NOT NULL," +  // fix applied here
    "seller_id INT NOT NULL," +
    "status VARCHAR(50) DEFAULT 'available'," +
    "FOREIGN KEY (seller_id) REFERENCES users(id))");

        } catch (SQLException e) {
            throw new RuntimeException("Database initialization error: " + e.getMessage());
        }
    }
    
}

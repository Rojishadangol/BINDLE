/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Model.User;
import bindle_project.Model.LoginRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle";
    private static final String USER = "root";
    private static final String PASSWORD = "roji@123"; // Update with your actual password

    public  boolean register(String email, String password, String name) {
        String sql = "INSERT INTO users (email, password, name, verified) VALUES (?, ?, ?, false)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password); // In production, hash the password
            pstmt.setString(3, name);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during registration: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static User login(LoginRequest loginRequest) {
        
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginRequest.getUsername()); // Assuming email is used as username
            pstmt.setString(2, loginRequest.getPassword());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"), rs.getBoolean("verified"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
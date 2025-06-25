/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Controller.Mail.SMTPSMailSender;
import bindle_project.Model.User;
import bindle_project.Model.LoginRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.UUID;
import javax.swing.JOptionPane;

public class UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/JavaProjectBindle";
    private static final String USER = "root";
    private static final String PASSWORD = "roji@123";

    public static boolean register(String email, String password, String name) {
        String sql = "INSERT INTO users (email, password, name, verified) VALUES (?, ?, ?, false)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email.trim());
            pstmt.setString(2, password.trim());
            pstmt.setString(3, name.trim());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error during registration: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static User login(LoginRequest loginRequest) {
        String sql = "SELECT id, email, name, verified, verification_token FROM users WHERE email = ? AND password = ?";
        System.out.println("Login attempt - Email: " + loginRequest.getEmail() + ", Password: " + loginRequest.getPassword());
        System.out.println("Query: " + sql + " with email: " + loginRequest.getEmail().trim().toLowerCase() + ", password: " + loginRequest.getPassword().trim());
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginRequest.getEmail().trim().toLowerCase());
            pstmt.setString(2, loginRequest.getPassword().trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User found: " + rs.getString("email"));
                    return new User(rs.getInt("id"), rs.getString("email"), rs.getString("name"), rs.getBoolean("verified"), rs.getString("verification_token"));
                } else {
                    System.out.println("No user found for email: " + loginRequest.getEmail().trim().toLowerCase());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static boolean verifyEmail(String token) {
        String sql = "UPDATE users SET verified = true WHERE verification_token = ? AND verified = false";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Email verified successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error verifying email: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword.trim());
            pstmt.setString(2, email.trim());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating password: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void testConnection() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
    public static boolean userExists(String email) {
    String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email.trim().toLowerCase());
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error checking user existence: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

}
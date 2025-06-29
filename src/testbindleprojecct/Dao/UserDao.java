package testbindleprojecct.Dao;

import testbindleprojecct.Controller.Mail.SMTPSMailSender;
import testbindleprojecct.Model.User;
import testbindleprojecct.Model.LoginRequest;
import testbindleprojecct.Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JOptionPane;

public class UserDao {
    private static final MySqlConnection dbConnection = MySqlConnection.getInstance();

    public static boolean register(String email, String password, String name) {
        String sql = "INSERT INTO users (email, password, name, verified) VALUES (?, ?, ?, false)";
        if (userExists(email)) {
            JOptionPane.showMessageDialog(null, "Email already exists: " + email);
            System.out.println("❌ Registration aborted: Email " + email + " already exists.");
            return false;
        }

        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.out.println("❌ Connection is null");
                return false;
            }

            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email.trim().toLowerCase());
                pstmt.setString(2, password.trim());
                pstmt.setString(3, name.trim());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    try (PreparedStatement verifyStmt = conn.prepareStatement("SELECT email FROM users WHERE email = ?")) {
                        verifyStmt.setString(1, email.trim().toLowerCase());
                        try (ResultSet rs = verifyStmt.executeQuery()) {
                            System.out.println("Data verified: " + rs.next());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    return true;
                } else {
                    System.out.println("❌ Insert failed — no rows affected.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("❌ SQL Exception: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            if (conn != null) try { conn.rollback(); } catch (SQLException rollbackEx) { System.out.println("❌ Rollback failed: " + rollbackEx.getMessage()); }
            return false;
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
    }

    public static User login(LoginRequest loginRequest) {
        String sql = "SELECT id, email, password, name, verified FROM users WHERE email = ? AND password = ?";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.out.println("❌ Connection is null during login");
                return null;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, loginRequest.getEmail().trim().toLowerCase());
                pstmt.setString(2, loginRequest.getPassword().trim());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String email = rs.getString("email");
                        String name = rs.getString("name");
                        boolean verified = rs.getBoolean("verified");
                        System.out.println("✅ User found in 'Bindle': " + email);
                        return new User(id, email, name, verified);
                    }
                    System.out.println("❌ No user found for email: " + loginRequest.getEmail().trim().toLowerCase() + " in 'Bindle'");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Login error in 'Bindle': " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
        return null;
    }

    public static boolean verifyEmail(String token) {
        String sql = "UPDATE users SET verified = true WHERE verification_token = ? AND verified = false";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, token);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Email verified successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error verifying email: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
    }

    public static boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newPassword.trim());
                pstmt.setString(2, email.trim());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating password: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
    }

    public static void testConnection() {
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            System.out.println("✅ Connection successful to 'Bindle'!");
        } catch (SQLException e) {
            System.out.println("❌ Connection failed to 'Bindle': " + e.getMessage());
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
    }

    public static boolean userExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email.trim().toLowerCase());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error checking user existence in 'Bindle': " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
        return false;
    }

    public static boolean updateProfile(User user) throws SQLException {
        String sql = "UPDATE users SET email = ?, name = ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getName());
                pstmt.setInt(3, user.getId());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } finally {
            if (conn != null) dbConnection.closeConnection(conn);
        }
    }

    public static boolean deactivateAccount(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } finally {
            if (conn != null) dbConnection.closeConnection(conn);
        }
    }

    // New implementation for getting stored password
    public String getPasswordByEmail(String email) {
        String sql = "SELECT password FROM users WHERE email = ?";
        Connection conn = null;
        try {
            conn = dbConnection.openConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email.trim().toLowerCase());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("password");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving password: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error retrieving password: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
        return null;
    }

    public static User getUserById(int userId) throws SQLException {
        String sql = "SELECT id, email, name, verified FROM users WHERE id = ?"; // Removed 'phone' since it's missing
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.openConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getBoolean("verified")
                );
                user.setPhone(0); // Default value since phone column is missing
                return user;
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { System.out.println("❌ Failed to close ResultSet: " + e.getMessage()); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { System.out.println("❌ Failed to close PreparedStatement: " + e.getMessage()); }
            if (conn != null) try { dbConnection.closeConnection(conn); } catch (SQLException e) { System.out.println("❌ Failed to close connection: " + e.getMessage()); }
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection implements DbConnection {
    private static final MySqlConnection instance = new MySqlConnection();

    private MySqlConnection() {}

    public static MySqlConnection getInstance() {
        return instance;
    }

    @Override
    public Connection openConnection() throws SQLException {
        String username = "root";
        String password = "roji@123";
        String database = "Bindle";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Auto-commit is enabled by default in JDBC
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + database, username, password
            );
            System.out.println("✅ Connected to DB: " + conn.getCatalog());
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Failed to connect: " + e.getMessage());
            throw new SQLException("Database connection failed", e);
        }
    }

    @Override
    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            try {
                conn.close();
                System.out.println("🔌 Connection closed.");
            } catch (SQLException e) {
                System.out.println("❌ Failed to close connection: " + e.getMessage());
                throw e;
            }
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) throws SQLException {
        if (conn == null) throw new SQLException("Connection is null");
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    @Override
    public int executeUpdate(Connection conn, String query) throws SQLException {
        if (conn == null) throw new SQLException("Connection is null");
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(query);
    }
}

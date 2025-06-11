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
    ResultSet runQuery(Connection conn,String query);
    int executeUpdate(Connection conn, String query);
    static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_db";
        String user = "root";
        String pass = "your_password";
        return DriverManager.getConnection(url, user, pass);
    }
}

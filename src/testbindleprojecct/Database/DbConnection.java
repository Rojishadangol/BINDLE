/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package testbindleprojecct.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbConnection {
    Connection openConnection() throws SQLException;
    void closeConnection(Connection conn) throws SQLException;
    ResultSet runQuery(Connection conn, String query) throws SQLException;
    int executeUpdate(Connection conn, String query) throws SQLException;
}
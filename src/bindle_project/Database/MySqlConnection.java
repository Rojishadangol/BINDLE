/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class MySqlConnection implements DbConnection {
    @Override
    public Connection openConnection(){
        String username="root";
        String password="roji@123";
        String database="JavaProjectBindle";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn;
        conn=DriverManager.getConnection("jbc:mysql://localhost:3306/"+
                database,username,password);
        return conn;
        }
        catch(ClassNotFoundException | SQLException e){
            return null;
        }
    }

   
    @Override
    public void closeConnection(Connection conn) {
       try{
           if(conn!=null && !conn.isClosed()){
               conn.close();
           }
       }
       catch(SQLException e){
       }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
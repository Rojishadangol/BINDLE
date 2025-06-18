/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Database.DbConnection;
import bindle_project.Database.MySqlConnection;
import bindle_project.Model.LoginRequest;
import bindle_project.Model.UserData;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import bindle_project.Model.Search;
import java.util.ArrayList;
/**
 *
 * @author acer
 */
public class UserDao {

    public static boolean updatePassword(String email, String newPass) {
        try (Connection con = DbConnection.getConnection()) {
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPass);  // In real apps, hash this!
            ps.setString(2, email);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }    
    public boolean register(UserData user){
        MySqlConnection MySql=new MySqlConnection();
    String query="INSERT INTO users(name,email,password) VALUES()";
    Connection conn=MySql.openConnection();
    try{
    PreparedStatement stmnt= conn.prepareStatement(query);
    stmnt.setString(1,user.getName());
    stmnt.setString(2,user.getEmail());
    stmnt.setString(3,user.getPassword());
    int result = stmnt.executeUpdate();
    return result>0;
    }
    catch(SQLException e){
    return false;}
        finally{
MySql.closeConnection(conn);
}
    }

    public UserData Login(LoginRequest loginData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Search> searchbook(String keyword) {
        List<Search> searchResults = new ArrayList<>();
    MySqlConnection mySql = new MySqlConnection();
    Connection conn = mySql.openConnection();

    String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Search search = new Search();
            search.setId(rs.getInt("id")); // match your DB columns
            search.setTitle(rs.getString("title"));
            search.setAuthor(rs.getString("author"));
            search.setPrice(rs.getDouble("price")); // optional

            searchResults.add(search);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mySql.closeConnection(conn);
    }

    return searchResults;
    }
}

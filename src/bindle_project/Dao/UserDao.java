/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Database.MySqlConnection;
import bindle_project.Model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class UserDao {
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
    }}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle.project.Dao;

import bindle.project.Database.MySqlConnection;
import bindle.project.model.LoginRequest;
import bindle.project.model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class UserDao {
    MySqlConnection MySQL=new MySqlConnection();
    public boolean register(UserData user){
    String query="INSERT INTO users(name,email,password) VALUES()";
    Connection conn=MySQL.openConnection();
    try{
    PreparedStatement stmnt= conn.prepareStatement(query);
    stmnt.setString(2,user.getEmail());
    stmnt.setString(3,user.getPassword());
    int result = stmnt.executeUpdate();
    return result>0;
    }
    catch(SQLException e){
    return false;}
        finally{
MySQL.closeConnection(conn);
}
}
    public UserData login(LoginRequest loginReq){
        String query="SELECT * FROM users WHERE email=? and fpassword=?";
                Connection conn= MySQL.openConnection();
                try{
                    PreparedStatement stmnt= conn.prepareStatement(query);
                    stmnt.setString(1,loginReq.getEmail());
                    stmnt.setString(2,loginReq.getPassword());
                    ResultSet result=stmnt.executeQuery();
                    if (result.next()){
                         String email=result.getString("email");
                    
                    String password=result.getString("fpassword");
                    
                    UserData user= new UserData(email,password);
                    return user;
                    }
                    else{
                            return null;
                    }
                    
                }
                catch(Exception e){
                    return null;
                }
                finally{
                MySQL.closeConnection(conn);}
                
}
    public boolean checkEmail(String email){
    String query="SELECT * FROM users WHERE email=?";
    Connection conn= MySQL.openConnection();
    
    try{
PreparedStatement stmnt= conn.prepareStatement(query);
stmnt.setString(1,email);
ResultSet result=stmnt.executeQuery();
if(result.next()){
return true;
}
    else{
return false;}}
catch(Exception e){
return false;}
finally{
MySQL.closeConnection(conn);}}


}

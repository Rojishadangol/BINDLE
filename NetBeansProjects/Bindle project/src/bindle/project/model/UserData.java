/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle.project.model;

/**
 *
 * @author acer
 */
public class UserData {
       

  
    private String email;
    private String password;
    public UserData(String email,String password){
   
    this.email=email;
    this.password=password;
    }
    //setters
    
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
    this.password=password;
    }
    //getters
    
    public String getEmail(){
    return this.email;
    }
    public String getPassword(){
    return this.password;
    }
}

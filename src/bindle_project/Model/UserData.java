/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

/**
 *
 * @author acer
 */
public class UserData {
     private String Name;
    private String email;
    private String password;
    public UserData(String name,String email,String password){
    this.Name=name;
    this.email=email;
    this.password=password;
    }
    //setters
    public void setName(String name){
        this.Name=name;
        
       
    }
     
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
    this.password=password;
    }
    //getters
    public String getName(){
    return this.Name;
    }
    
    public String getEmail(){
    return this.email;
    }
    public String getPassword(){
    return this.password;
    }
}

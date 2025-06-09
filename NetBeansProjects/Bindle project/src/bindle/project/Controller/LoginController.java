/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle.project.Controller;




import bindle.project.Dao.UserDao;
import bindle.project.model.LoginRequest;
import bindle.project.model.UserData;
import bindle.view.login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class LoginController {
    private boolean isPasswordVisible=false;

    login view;
    public LoginController(login view){
        this.view=view;
        LoginUser loginUser=new LoginUser();
        this.view.loginUser(loginUser);
        this.view.showPasswordButtonListener(new ShowPasswordListener1());
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class LoginUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String email=view.getEmail().getText();
            String password= String.valueOf(view.getPasswordField().getText());
            if(email.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(view,"Fill in all the field");}
            else{
            LoginRequest loginData= new LoginRequest(email,password);
            UserDao userDao=new UserDao();
            UserData user= userDao.login(loginData);
            if (user==null){
            JOptionPane.showMessageDialog(view,"Login Failed");
            }
            else{
                
            JOptionPane.showMessageDialog(view,"Login Successfull");
            
            }}
        }
      
    }
class ShowPasswordListener1 implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        isPasswordVisible = !isPasswordVisible;
        view.togglePassword(isPasswordVisible);    
    }
}

}


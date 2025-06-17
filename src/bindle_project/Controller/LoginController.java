/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Dao.UserDao;
import bindle_project.Model.LoginRequest;
import bindle_project.Model.UserData;
import bindle_project.View.ForgetPassword1;
import bindle_project.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class LoginController {
    private boolean isPasswordVisisble=false;
    LoginView view;
    public LoginController(LoginView view){
        this.view=view;
        LoginUser loginUser=new LoginUser();
        this.view.loginUser(loginUser);
        view.showPasswordButtonListener(new ShowPasswordListener());
                this.view.getForgotPassword().addMouseListener(getForgetMouseListener());

    }
     public MouseAdapter getForgetMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close(); // Close login window
                ForgetPassword1 forgetView = new ForgetPassword1();
                ForgetPasswordController Regcon = new ForgetPasswordController(forgetView);
                Regcon.open(); // Open registration window
            }
        };
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
            UserData user= userDao.Login(loginData);
            if (user==null){
            JOptionPane.showMessageDialog(view,"Login Failed");
            }
            else{
                
            JOptionPane.showMessageDialog(view,"Login Successfull");
           
            }}
            
        }
    }
    
    class ShowPasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisisble= !isPasswordVisisble;
view.tooglePasswordField(isPasswordVisisble);       
        }   
    
    }

}
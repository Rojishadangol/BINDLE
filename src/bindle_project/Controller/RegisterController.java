/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Dao.UseDao;
import bindle_project.Model.UserData;
import bindle_project.View.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class RegisterController {
    RegisterView view= new RegisterView();
        private boolean isPasswordVisisble=false;

    public RegisterController(RegisterView view){
    this.view=view;
    RegisterUser register = new RegisterUser();
    this.view.registerUser(new RegisterUser());
    view.showPasswordButtonListener(new ShowPasswordListener());
        view.showPasswordButtonListener1(new ShowPasswordListener1());


    }
    public void open(){
    view.setVisible(true);
    }
    public void close(){
    view.dispose();
    }
    
    class RegisterUser implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    String name= view.getNameTextField().getText();
    String email=view.getEmailTextField().getText();
    String password=
            String.valueOf(view.getPasswordField().getPassword());
    String confirmPassword=
            String.valueOf(view.getConfirmPassword().getPassword());
    if (name.isEmpty()||email.isEmpty()||password.isEmpty()|confirmPassword.isEmpty()){
        JOptionPane.showMessageDialog(view,"Fill in this field");
    }
    else if(!password.equals(confirmPassword)){
        JOptionPane.showMessageDialog(view,"Passwords do not match");
    }
    else{
    BookDAO userDao=new BookDAO();
    UserData user= new UserData(name,email,password);
    boolean result=userDao.register(user);
    if (result){
                JOptionPane.showMessageDialog(view,"Registered Successfully");
                
            }else{
               JOptionPane.showMessageDialog(view,"Failed to Register");
            }
    }
    }}
    class ShowPasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisisble= !isPasswordVisisble;
view.tooglePasswordField(isPasswordVisisble);        
view.tooglePasswordField(isPasswordVisisble);        

        }}
        class ShowPasswordListener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisisble= !isPasswordVisisble;
view.tooglePasswordField(isPasswordVisisble);  }
    
    }}

package bindle_project.Controller;

import bindle_project.Dao.UserDao;
import bindle_project.View.ForgetPassword1;
import bindle_project.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ForgetPasswordController {
        private boolean isPasswordVisisble=false;


    private final ForgetPassword1 view;

    public ForgetPasswordController(ForgetPassword1 view) {
        this.view = view;
                view.showPasswordButtonListener(new ForgetPasswordController.ShowPasswordListener());
                                view.showPasswordButtonListener1(new ForgetPasswordController.ShowPasswordListener1());
this.view.getCancelButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
close();
LoginView loginView=new LoginView();
LoginController login=new LoginController(loginView);
login.open();            
            }
        });

    }

    // Call this to show the forget password view
    public void open() {
        view.setVisible(true); 
    }

    public void close() {
        view.dispose();
    }

    public static void resetPassword(String email, String newPass, String confirmPass) {
        if (newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields.");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return;
        }

        if (newPass.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters.");
            return;
        }

        boolean success = UserDao.updatePassword(email, newPass);
        if (success) {
            JOptionPane.showMessageDialog(null, "Password updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update password.");
        }
    }
   class ShowPasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisisble= !isPasswordVisisble;
view.tooglePasswordField(isPasswordVisisble);  

        }   
    
    } 
    class ShowPasswordListener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisisble= !isPasswordVisisble;
view.tooglePasswordField1(isPasswordVisisble);  
}
    }}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// LoginController.java
package bindle_project.Controller;

import bindle_project.View.LoginInterfacee;
import bindle_project.View.EditProfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginInterfaceController {
    private LoginInterfacee LoginInterface;

    public LoginInterfaceController(LoginInterfacee LoginInterfacee) {
        this.LoginInterface = LoginInterfacee;

        this.LoginInterface.getSaveButton().addActionListener(new LoginButtonListener());
        this.LoginInterface.getCancelButton().addActionListener(new CancelButtonListener());
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = LoginInterface.getNameField().getText();

            if (username.equals("admin") ) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                LoginInterface.dispose(); // Close login form
                new EditProfile().setVisible(true); // Open edit profile view
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
    }

    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginInterface.dispose();
        }
    }
}



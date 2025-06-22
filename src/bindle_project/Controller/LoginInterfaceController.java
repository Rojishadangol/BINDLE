/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// LoginController.java
package bindle_project.Controller;

import bindle_project.View.DeactivateAccountUI;
import bindle_project.View.LoginInterfacee;
import bindle_project.View.UpdatePassword;


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
    System.out.println("Save button clicked");  // debug line

    String name = LoginInterface.getNameField().getText();
    String email = LoginInterface.getEmailField().getText();
    String phone = LoginInterface.getPhoneField().getText();
    String address = LoginInterface.getAddressField().getText();

    System.out.println("Name: " + name); // debug line

    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out all fields.");
        return;
    }

    try (java.io.FileWriter writer = new java.io.FileWriter("profile.txt", true)) {
        writer.write("Name: " + name + "\n");
        writer.write("Email: " + email + "\n");
        writer.write("Phone: " + phone + "\n");
        writer.write("Address: " + address + "\n");
        writer.write("-------------------------\n");
    } catch (java.io.IOException ex) {
        JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
        return;
    }

    JOptionPane.showMessageDialog(null, "Profile updated successfully!");
}
    }
//    Cancel button functionality
    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginInterface.dispose();
        }
    }


    class ChangePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Change Password button clicked");
            LoginInterface.dispose(); // Close current window
            new UpdatePassword().setVisible(true); // Open Change Password screen
        }
    }

    // Deactivate button functionality
    class DeactivateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Deactivate button clicked");
            LoginInterface.dispose(); // Close current window
            new DeactivateAccountUI().setVisible(true); // Open Deactivate screen
        }
    }
}



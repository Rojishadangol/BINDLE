/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// LoginController.java
package bindle_project.Controller;

import bindle_project.View.LoginInterfacee;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LoginInterfaceController {
    private LoginInterfacee LoginInterface;

    public LoginInterfaceController(LoginInterfacee LoginInterfacee) {
        this.LoginInterface = LoginInterfacee;

        // Attach button listeners
        this.LoginInterface.getSaveButton().addActionListener(new LoginButtonListener());
        this.LoginInterface.getCancelButton().addActionListener(new CancelButtonListener());
        this.LoginInterface.uploadImageButtonListener(new UploadImageListener()); // <-- Change picture button
    }

    // Save button functionality
    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Save button clicked");

            String name = LoginInterface.getNameField().getText();
            String email = LoginInterface.getEmailField().getText();
            String phone = LoginInterface.getPhoneField().getText();
            String address = LoginInterface.getAddressField().getText();

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

    // Cancel button functionality
    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginInterface.dispose();
        }
    }

    // Change Picture button functionality
    class UploadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(LoginInterface);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file.exists() && file.isFile()) {
                    LoginInterface.setSelectedFile(file);

                    // OPTIONAL: Show image in jLabel6 or jLabel8
                    // ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    // LoginInterface.getJLabel6().setIcon(icon); // Create a getter if needed
                } else {
                    JOptionPane.showMessageDialog(LoginInterface,
                            "Invalid file selected.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

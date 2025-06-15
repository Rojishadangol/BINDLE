/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Dao.UserDao;

import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class ForgetPasswordController {

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

    public static void updatePassword(String newPass, String confirmPass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


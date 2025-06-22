/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import bindle_project.Dao.UserDao;
import javax.swing.JOptionPane;

public class AuthModel {
   public UserData register(String email, String password, String name) {
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (UserDao.register(email, password, name)) {
            return new UserData(0, email, name, false); // ID 0 as placeholder, verified = false
        }
        return null;
    }

    public UserData login(LoginRequest loginRequest) {
        User user = UserDao.login(loginRequest);
        if (user != null) {
            return new UserData(user.getId(), user.getEmail(), user.getName(), user.isVerified());
        }
        JOptionPane.showMessageDialog(null, "Invalid login credentials", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public boolean resetPassword(String email, String newPassword) {
        return UserDao.updatePassword(email, newPassword); // Delegate to UserDao
    }

    // Remove the duplicate method
    // public boolean resetPassword(String email, String newPass) { ... } // Deleted
}
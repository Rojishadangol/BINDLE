package bindle_project.Controller;

import bindle_project.Dao.UserDao;
import bindle_project.View.ForgetPassword1;

import javax.swing.JOptionPane;

public class ForgetPasswordController {

    private final ForgetPassword1 view;

    public ForgetPasswordController(ForgetPassword1 view) {
        this.view = view;
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
}

package testbindleprojecct.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.ChangePasswordView;
import testbindleprojecct.Dao.UserDao;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ChangePasswordController {
    private final ChangePasswordView view;
    private final User currentUser;
    private final UserDao userDao;
    private final NavigationController navController;
    private boolean isPasswordVisible = false;

    public ChangePasswordController(ChangePasswordView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.currentUser = currentUser;
        this.userDao = new UserDao();
        this.navController = navController;
        initController();
        // Visibility handled by NavigationController
    }

    private void initController() {
        System.out.println("Attaching listeners to ChangePasswordView buttons for user ID: " + currentUser.getId());
        this.view.showPasswordButtonListener1(new ShowPasswordListener1());
        this.view.showPasswordButtonListener2(new ShowPasswordListener2());
        this.view.showPasswordButtonListener3(new ShowPasswordListener3());

        view.getSaveButton().addActionListener(e -> {
            JPasswordField oldPasswordField = view.getOldPasswordField();
            JPasswordField newPasswordField = view.getNewPasswordField();
            JPasswordField confirmPasswordField = view.getConfirmPasswordField();

            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields are required.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(view, "New password and confirm password do not match.");
                return;
            }

            String storedPassword = userDao.getPasswordByEmail(currentUser.getEmail());
            if (storedPassword == null || !oldPassword.equals(storedPassword)) {
                JOptionPane.showMessageDialog(view, "Incorrect old password.");
                return;
            }

            if (userDao.updatePassword(currentUser.getEmail(), newPassword)) {
                JOptionPane.showMessageDialog(view, "Password updated successfully.");
                view.dispose();
                navController.showProfileView();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update password. Please try again.");
            }
        });

        view.getCancelButton().addActionListener(e -> {
            view.dispose();
            navController.showProfileView();
        });
    }

    private class ShowPasswordListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField1(isPasswordVisible);
        }
    }

    private class ShowPasswordListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField2(isPasswordVisible);
        }
    }

    private class ShowPasswordListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField3(isPasswordVisible);
        }
    }

    public ChangePasswordView getView() {
        return view; // Guaranteed non-null
    }
}
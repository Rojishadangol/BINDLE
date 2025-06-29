package testbindleprojecct.Controller;

import testbindleprojecct.Controller.Mail.SMTPSMailSender;
import testbindleprojecct.Dao.UserDao;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.DeactivateAccountView;

import javax.swing.*;
import java.sql.SQLException;

public class DeactivateAccountController {
    private DeactivateAccountView view;
    private User currentUser;
    private UserDao userDao;
    private final NavigationController navController;

    public DeactivateAccountController(DeactivateAccountView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.currentUser = currentUser;
        this.userDao = new UserDao();
        this.navController = navController;
        initListeners();
        view.setVisible(true);
    }

    private void initListeners() {
        System.out.println("Attaching listeners to DeactivateAccountView buttons for user ID: " + currentUser.getId());
        view.getDeactivateButton().addActionListener(e -> confirmDeactivation());
        view.getCancelButton().addActionListener(e -> {
            view.dispose();
            navController.showProfileView();
        });
    }

    private void confirmDeactivation() {
        String reason = (String) view.getReasonDropdown().getSelectedItem();
        String comments = view.getCommentsArea().getText().trim();
        String password = new String(view.getPasswordField().getPassword()).trim();

        if ("Select a reason".equals(reason)) {
            view.showError("Please select a reason for deactivation.");
            return;
        }
        if (password.isEmpty()) {
            view.showError("Please enter your password for confirmation.");
            return;
        }

        String storedPassword = userDao.getPasswordByEmail(currentUser.getEmail());
        if (storedPassword == null || !password.equals(storedPassword)) {
            view.showError("Incorrect password.");
            return;
        }

        String input = JOptionPane.showInputDialog(view,
                "Are you sure you want to deactivate your account?\n" +
                "This can be undone within 30 days.\n\nType 'DEACTIVATE' to confirm:");
        if (!"DEACTIVATE".equalsIgnoreCase(input)) {
            view.showError("Deactivation cancelled or incorrect confirmation.");
            return;
        }

        try {
            if (userDao.deactivateAccount(currentUser.getId())) {
                SMTPSMailSender.sendMail(currentUser.getEmail(),
                        "Account Deactivation",
                        "Your account has been deactivated. You can reactivate within 30 days.");
                view.showSuccess("Account deactivated successfully.");
                view.dispose();
                navController.showLoginView();
            } else {
                view.showError("Failed to deactivate account.");
            }
        } catch (SQLException e) {
            view.showError("Error: " + e.getMessage());
        }
    }

   public DeactivateAccountView getView() {
        return view; // Guaranteed non-null
    }
}

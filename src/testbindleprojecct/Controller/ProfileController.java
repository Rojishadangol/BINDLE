package testbindleprojecct.Controller;

import testbindleprojecct.Dao.UserDao;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.ProfileView;
import testbindleprojecct.View.ChangePasswordView;
import testbindleprojecct.View.DeactivateAccountView;
import testbindleprojecct.View.HomeView;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class ProfileController {
    private ProfileView view;
    private UserDao userDao;
    private User currentUser;
    private final NavigationController navController;

    public ProfileController(ProfileView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.userDao = new UserDao();
        this.currentUser = currentUser;
        this.navController = navController;
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to ProfileView buttons for user ID: " + currentUser.getId());
        view.getSaveButton().addActionListener(e -> saveProfile());
        view.getCancelButton().addActionListener(e -> {
            navController.showHomeView();
            view.dispose();
        });

        view.getChangePasswordButton().addActionListener(e -> {
            if (currentUser != null) {
                try {
                    ChangePasswordView changePasswordView = new ChangePasswordView();
                    new ChangePasswordController(changePasswordView, currentUser, navController).getView().setVisible(true);
                    view.dispose();
                } catch (Exception ex) {
                    showError("Failed to open Change Password view: " + ex.getMessage());
                    System.err.println("ChangePasswordController initialization failed: " + ex.getMessage());
                }
            } else {
                showError("User not logged in.");
            }
        });

        view.getDeactivateButton().addActionListener(e -> {
            if (currentUser != null) {
                DeactivateAccountView deactivateView = new DeactivateAccountView();
                new DeactivateAccountController(deactivateView, currentUser, navController).getView().setVisible(true);
            } else {
                showError("User not logged in.");
            }
            view.dispose();
        });

        
    }

    private void saveProfile() {
        String name = view.getNameField().getText().trim();
        String email = view.getEmailField().getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            showError("All fields are required.");
            return;
        }

        User updatedUser = new User(currentUser.getId(), email, name, currentUser.isVerified());
        try {
            if (userDao.updateProfile(updatedUser)) {
                currentUser.setName(name);
                currentUser.setEmail(email);
                showSuccess("Profile updated successfully.");
                navController.showHomeView();
            } else {
                showError("Failed to update profile.");
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
        view.dispose();
    }

    private void showError(String message) {
        try {
            if (view != null) {
                Method method = view.getClass().getMethod("showError", String.class);
                method.invoke(view, message);
            } else {
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error invoking showError: " + e.getMessage());
        }
    }

    private void showSuccess(String message) {
        try {
            if (view != null) {
                Method method = view.getClass().getMethod("showSuccess", String.class);
                method.invoke(view, message);
            } else {
                JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            System.err.println("Error invoking showSuccess: " + e.getMessage());
        }
    }

    public ProfileView getView() {
        return view;
    }
}
package testbindleprojecct.Controller;

import testbindleprojecct.View.ForgotPasswordView;
import testbindleprojecct.View.LoginView;
import testbindleprojecct.Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ForgotPasswordController {
    private ForgotPasswordView view;
    private UserDao userDao;
    private final NavigationController navController;

    public ForgotPasswordController(ForgotPasswordView view) {
        this.view = view;
        this.userDao = new UserDao();
        this.navController = new NavigationController(null); // Temporary, no user context
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to ForgotPasswordView buttons");
        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = view.getEmailField().getText();
                String newPassword = new String(view.getNewPasswordField().getPassword());
                String confirmPassword = new String(view.getConfirmPasswordField().getPassword());

                if (email.isEmpty() || !email.contains("@")) {
                    JOptionPane.showMessageDialog(view, "Invalid email address!");
                    return;
                }
                if (newPassword.length() < 8) {
                    JOptionPane.showMessageDialog(view, "Password must be at least 8 characters!");
                    return;
                }
                if (email == null || newPassword == null || confirmPassword == null) {
                    JOptionPane.showMessageDialog(view, "Fields cannot be empty!");
                    return;
                }
                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(view, "Passwords do not match!");
                    return;
                }

                if (!userDao.userExists(email)) {
                    JOptionPane.showMessageDialog(view, "No account found with this email!");
                    return;
                }

                if (userDao.updatePassword(email, newPassword)) {
                    System.out.println("Password reset for email: " + email);
                    JOptionPane.showMessageDialog(view, "Password reset successfully!");
                    view.dispose();
                    navController.showLoginView();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to reset password. Please try again.");
                }
            }
        });

        view.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                navController.showLoginView();
            }
        });

        view.getNewShowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility(view.getNewPasswordField());
            }
        });

        view.getConfirmShowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility(view.getConfirmPasswordField());
            }
        });
    }

    private void togglePasswordVisibility(javax.swing.JPasswordField field) {
        if (field.getEchoChar() == 0) {
            field.setEchoChar('*');
        } else {
            field.setEchoChar((char) 0);
        }
    }

    public ForgotPasswordView getView() {
        return view;
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
}
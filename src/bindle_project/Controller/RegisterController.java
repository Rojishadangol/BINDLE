/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Model.AuthModel;
import bindle_project.Model.UserData;
import bindle_project.View.LoginView;
import bindle_project.View.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class RegisterController {
    private RegisterView view;
    private AuthModel authModel;
    private boolean isPasswordVisible = false;

    public RegisterController(RegisterView view) {
        this.view = view;
        this.authModel = new AuthModel();

        view.getRegisterButton().addActionListener(new RegisterUser()); // Corrected to use getRegisterButton
        view.showPasswordButtonListener(new RegisterController.ShowPasswordListener());
        view.showPasswordButtonListener1(new RegisterController.ShowPasswordListener1());
        view.getAlreadyHaveAnAccount().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToLogin();
            }
        });
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    public void navigateToLogin() {
        close();
        LoginView loginView = new LoginView();
        LoginController login = new LoginController(loginView, authModel);
        login.open();
    }

    private class RegisterUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameTextField().getText().trim();
            String email = view.getEmailTextField().getText().trim();
            String password = new String(view.getPasswordField().getPassword());
            String confirmPassword = new String(view.getConfirmPassword().getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Fill in all fields");
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match");
                return;
            }

            UserData userData = authModel.register(email, password, name);
            if (userData != null) {
                JOptionPane.showMessageDialog(view, "Registered Successfully");
                navigateToLogin();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to Register");
            }
        }
    }

    private class ShowPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField(isPasswordVisible);
        }
    }

    private class ShowPasswordListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField1(isPasswordVisible);
        }
    }
}
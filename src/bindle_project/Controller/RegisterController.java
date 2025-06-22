/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Model.AuthModel;
import bindle_project.Model.UserData;
import bindle_project.View.LoginView;
import bindle_project.View.RegisterView;
import bindle_project.Controller.Mail.SMTPSMailSender;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JOptionPane;

public class RegisterController {
    private RegisterView view;
    private AuthModel authModel;
    private boolean isPasswordVisible = false;
    private String generatedOtp;

    public RegisterController(RegisterView view) {
        this.view = view;
        this.authModel = new AuthModel();

        view.registerUser(new RegisterUser());
        view.showPasswordButtonListener(new ShowPasswordListener());
        view.showPasswordButtonListener1(new ShowPasswordListener1());
        view.getAlreadyHaveAnAccount().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToLogin();
            }
        });
        view.getSendOtpButton().addActionListener(new SendOtpListener());
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
            if (generatedOtp == null || !isOtpVerified()) {
                JOptionPane.showMessageDialog(view, "Please send and verify OTP first.");
                return;
            }

            // OTP is verified, proceed with registration
            UserData userData = authModel.register(email, password, name);
            if (userData != null) {
                JOptionPane.showMessageDialog(view, "Registered Successfully");
                view.setOtpFieldVisible(false);
                view.setVerifyButtonVisible(false);
                view.setSendOtpButtonVisible(false);
                navigateToLogin();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to Register", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private boolean isOtpVerified() {
            // Assume OTP is verified if the field is visible and not empty after verification
            return view.getOtpField().isVisible() && !view.getOtpField().getText().trim().isEmpty();
        }
    }

    private class SendOtpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter an email address.");
                return;
            }
            generatedOtp = generateOtp();
            String subject = "Your OTP for Registration";
            String body = "Your OTP is: " + generatedOtp + ". Please use this to verify your registration.";
            if (SMTPSMailSender.sendMail(email, subject, body)) {
                view.setOtpFieldVisible(true);
                view.setVerifyButtonVisible(true);
                view.setSendOtpButtonVisible(false); // Hide after sending
                view.getVerifyButton().addActionListener(new VerifyOtpListener());
                JOptionPane.showMessageDialog(view, "An OTP has been sent to " + email + ". Please verify.");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to send OTP. Check your email settings.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class VerifyOtpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredOtp = view.getOtpField().getText().trim();
            if (generatedOtp != null && generatedOtp.equals(enteredOtp)) {
                JOptionPane.showMessageDialog(view, "OTP verified successfully!");
                view.getRegisterButton().setEnabled(true); // Enable Register after verification
            } else {
                JOptionPane.showMessageDialog(view, "Invalid OTP. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private String generateOtp() {
        String numbers = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }
}
package testbindleprojecct.Controller;

import testbindleprojecct.View.RegisterView;
import testbindleprojecct.View.LoginView;
import testbindleprojecct.Controller.Mail.SMTPSMailSender;
import testbindleprojecct.Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JOptionPane;

public class RegisterController implements ActionListener {
    private RegisterView view;
    private String generatedOtp = null;
    private boolean isPasswordVisible = false;
    private final NavigationController navController;

    public RegisterController() {
        this.view = new RegisterView();
        this.navController = new NavigationController(null); // Temporary, no user context
        view.setVisible(true);
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to RegisterView buttons");
        view.getRegisterButton().addActionListener(new RegisterUser());
        view.getSendOtpButton().addActionListener(new SendOtpListener());
        view.getShow1Button().addActionListener(new ShowPasswordListener());
        view.getShow2Button().addActionListener(new ShowPasswordListener1());
        view.getAlreadyHaveAnAccountLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToLogin();
            }
        });
    }

    public void navigateToLogin() {
        close();
        navController.showLoginView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    private class RegisterUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameField().getText().trim();
            String email = view.getEmailField().getText().trim();
            String password = new String(view.getPasswordField().getPassword());
            String confirmPassword = new String(view.getConfirmPasswordField().getPassword());

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

            boolean registered = UserDao.register(email, password, name);
            if (registered) {
                JOptionPane.showMessageDialog(view, "Registered Successfully");
                view.setOtpFieldVisible(false);
                view.setVerifyButtonVisible(false);
                view.setSendOtpButtonVisible(false);
                navigateToLogin();
            } else {
                JOptionPane.showMessageDialog(view, "Registration failed. Check console for details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private boolean isOtpVerified() {
            return view.getOtpField().isVisible() && !view.getOtpField().getText().trim().isEmpty();
        }
    }

    private class SendOtpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailField().getText().trim();

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
                view.setSendOtpButtonVisible(false);
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
                view.getRegisterButton().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(view, "Invalid OTP. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
package bindle_project.Controller;

import bindle_project.View.ForgetPassword1;
import bindle_project.View.HomeScreen;
import bindle_project.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class LoginController {
    private boolean isPasswordVisible = false;
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

        // Attach listeners
        this.view.loginUser(new LoginUser());
        this.view.showPasswordButtonListener(new ShowPasswordListener());
        this.view.getForgotPassword().addMouseListener(getForgetMouseListener());
    }

    public MouseAdapter getForgetMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
                ForgetPassword1 forgetView = new ForgetPassword1();
                // Assuming you have a controller for ForgetPassword
                ForgetPasswordController forgetCon = new ForgetPasswordController(forgetView);
                forgetCon.open();
            }
        };
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    // Login button handler
    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmail().getText().trim();
            char[] passwordChars = view.getPasswordField().getPassword();
            String password = new String(passwordChars);

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Fill in all the fields");
                return;
            }

            // Simple hardcoded check (replace with DB or service later)
            if (email.equals("user@example.com") && password.equals("123456")) {
                JOptionPane.showMessageDialog(view, "Login Successful");
                close();

                HomeScreen homeView = new HomeScreen();
                HomeController homeCon = new HomeController(homeView);
                homeCon.open();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid email or password");
            }

            // Clear password from memory
            java.util.Arrays.fill(passwordChars, '0');
        }
    }

    // Show/hide password toggle
    class ShowPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField(isPasswordVisible);
        }
    }
}



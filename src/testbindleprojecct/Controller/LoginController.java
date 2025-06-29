package testbindleprojecct.Controller;

import testbindleprojecct.View.ForgotPasswordView;
import testbindleprojecct.View.HomeView;
import testbindleprojecct.View.LoginView;
import testbindleprojecct.Model.LoginRequest;
import testbindleprojecct.Dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import testbindleprojecct.Model.User;

public class LoginController {
    private boolean isPasswordVisible = false;
    private final LoginView view;
    private NavigationController navController;

    public LoginController(LoginView view, NavigationController navController) {
        this.view = view;
        this.navController = navController; // Initialized with passed instance
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to LoginView buttons");
        this.view.loginUser(new LoginUser());
        this.view.showPasswordButtonListener(new ShowPasswordListener());
        this.view.getForgotPasswordLabel().addMouseListener(getForgetMouseListener());
        
         view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                navController.showRegisterView();
            }
        });
    }

    public MouseAdapter getForgetMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                navController.showForgotPasswordView();
            }
        };}
       
        

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailField().getText().trim();
            char[] passwordChars = view.getPasswordField().getPassword();
            String password = new String(passwordChars);

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Fill in all the fields");
                return;
            }

            LoginRequest loginRequest = new LoginRequest(email, password);
            User user = UserDao.login(loginRequest);

            if (user != null) {
                JOptionPane.showMessageDialog(view, "Login Successful");
                // Update the existing navController with the logged-in user
                navController = new NavigationController(user); // Reassign with new user context
                close();
                navController.showHomeView();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid email or password");
            }

            java.util.Arrays.fill(passwordChars, '0');
        }
    }

    class ShowPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField(isPasswordVisible);
        }
    }
}

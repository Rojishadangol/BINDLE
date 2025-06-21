package bindle_project.Controller;

import bindle_project.Dao.UserDao;
import bindle_project.Model.AuthModel;
import bindle_project.Model.LoginRequest;
import bindle_project.Model.UserData;
import bindle_project.View.ForgetPassword1;
import bindle_project.View.HomeScreen;
import bindle_project.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class LoginController {
    private boolean isPasswordVisible = false; // Corrected typo
    LoginView view;
    AuthModel authModel; // Store AuthModel for use

    public LoginController(LoginView view, AuthModel authModel) {
        this.view = view;
        this.authModel = authModel;
        
        LoginUser loginUser = new LoginUser();
        this.view.loginUser(loginUser); // Assuming LoginView has this method to set the listener
        view.showPasswordButtonListener(new ShowPasswordListener());
        this.view.getForgotPassword().addMouseListener(getForgetMouseListener());
    }

    public MouseAdapter getForgetMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close(); // Close login window
                ForgetPassword1 forgetView = new ForgetPassword1();
                ForgetPasswordController forgetCon = new ForgetPasswordController(forgetView); // Corrected variable name
                forgetCon.open(); // Open forgot password window
            }
        };
    }

    public MouseAdapter getLoginMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close(); // Close login window
                HomeScreen home = new HomeScreen();
                HomeController homeCon = new HomeController(home); // Corrected variable name
                homeCon.open(); // Open forgot password window
            }
        };
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

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

            System.out.println("Login attempt: " + email); // Debug output
            
            LoginRequest loginData = new LoginRequest(email, password);
            UserData user = authModel.login(loginData);
            
            if (user == null) {
                JOptionPane.showMessageDialog(view, "Invalid email or password");
            } else if (!user.isVerified()) {
                JOptionPane.showMessageDialog(view, "Please verify your email first", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Login Successful");
                close();
                HomeScreen homeView = new HomeScreen();
                HomeController homeCon = new HomeController(homeView); // Pass user object
                homeCon.open(); // Use open() method to set visibility
            }
            
            Arrays.fill(passwordChars, '0'); // Clear password from memory
        }
    }
    
    class ShowPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible = !isPasswordVisible; // Corrected typo
            view.tooglePasswordField(isPasswordVisible); // Corrected typo
        }
    }
}
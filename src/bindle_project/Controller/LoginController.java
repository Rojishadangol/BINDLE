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
    private boolean isPasswordVisible = false;
    private LoginView view;
    private AuthModel authModel;

    public LoginController(LoginView view, AuthModel authModel) {
        this.view = view;
        this.authModel = authModel;

        // Ensure components are enabled and attach listeners
        view.getLoginButton().setEnabled(true);
        view.getShowButton().setEnabled(true);
        view.getForgotLabel().setEnabled(true);

        // Attach listeners with debug
        System.out.println("Attaching login listener");
        view.loginUser(new LoginUser());
        System.out.println("Attaching show password listener");
        view.showPasswordButtonListener(new ShowPasswordListener());
        System.out.println("Attaching forgot password listener");
        view.forgotPassword(getForgetMouseAdapter());
    }

    private MouseAdapter getForgetMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Forgot Password listener triggered");
                close();
                ForgetPassword1 forgetView = new ForgetPassword1();
                ForgetPasswordController forgetCon = new ForgetPasswordController(forgetView);
                forgetCon.open();
            }
        };
    }

    private MouseAdapter getLoginMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Login mouse listener triggered");
                close();
                HomeScreen home = new HomeScreen();
                HomeController homeCon = new HomeController(home);
                homeCon.open();
            }
        };
    }

    public void open() {
        System.out.println("Opening LoginView");
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login button listener triggered");
            String email = view.getEmail().getText().trim();
            char[] passwordChars = view.getPasswordField().getPassword();
            String password = new String(passwordChars);

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Fill in all the fields");
                return;
            }

            System.out.println("Login attempt: Email: " + email + ", Password: " + password);
            LoginRequest loginRequest = new LoginRequest(email, password);
            UserData user = authModel.login(loginRequest);

            if (user != null) {
                System.out.println("Login successful for user: " + user.getEmail());
                JOptionPane.showMessageDialog(view, "Login Successful");
                close();
                HomeScreen homeView = new HomeScreen();
                HomeController homeCon = new HomeController(homeView);
                homeCon.open();
            } else {
                System.out.println("Login failed for email: " + email);
                JOptionPane.showMessageDialog(view, "Invalid email or password");
            }

            Arrays.fill(passwordChars, '0'); // Clear password from memory
        }
    }

    class ShowPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Show password listener triggered");
            isPasswordVisible = !isPasswordVisible;
            view.tooglePasswordField(isPasswordVisible);
        }
    }
}
//package bindle_project.Controller;
//
//import bindle_project.Model.AuthModel;
//import bindle_project.View.ForgetPassword1;
//import bindle_project.View.LoginView;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JOptionPane;
//
//public class ForgetPasswordController {
//    private boolean isPasswordVisible = false; // Corrected typo
//    private final ForgetPassword1 view;
//    private final AuthModel authModel = new AuthModel(); // Use AuthModel for consistency
//
//    public ForgetPasswordController(ForgetPassword1 view) {
//        this.view = view;
//        view.showPasswordButtonListener(new ForgetPasswordController.ShowPasswordListener());
//        view.showPasswordButtonListener1(new ShowPasswordListener1()); // Assuming a second button
//        view.getCancelButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                close();
//                LoginView loginView = new LoginView();
//                LoginController login = new LoginController(loginView, authModel); // Reuse AuthModel
//                login.open();
//            }
//        });
//
//        // Add reset button listener
//        view.getResetButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String email = view.getEmailField().getText().trim();
//                String newPass = new String(view.getPasswordField().getPassword());
//                String confirmPass = new String(view.getConfirmPasswordField().getPassword());
//                resetPassword(email, newPass, confirmPass);
//            }
//        });
//    }
//
//    public void open() {
//        view.setVisible(true);
//    }
//
//    public void close() {
//        view.dispose();
//    }
//
//    public void resetPassword(String email, String newPass, String confirmPass) {
//        if (newPass.isEmpty() || confirmPass.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please fill all fields.");
//            return;
//        }
//        if (!newPass.equals(confirmPass)) {
//            JOptionPane.showMessageDialog(null, "Passwords do not match.");
//            return;
//        }
//        if (newPass.length() < 6) {
//            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters.");
//            return;
//        }
//
//        // Delegate to AuthModel (assuming it has a resetPassword method)
//        if (authModel.resetPassword(email, newPass)) { // Add this method to AuthModel
//
//            JOptionPane.showMessageDialog(null, "Password updated successfully!");
//            close();
//            LoginView loginView = new LoginView();
//            LoginController login = new LoginController(loginView, authModel);
//            login.open();
//        } else {
//            JOptionPane.showMessageDialog(null, "Failed to update password.");
//        }
//    }
//
//    class ShowPasswordListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            isPasswordVisible = !isPasswordVisible;
//            view.tooglePasswordField(isPasswordVisible); // Corrected typo
//        }
//    }
//
//    class ShowPasswordListener1 implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            isPasswordVisible = !isPasswordVisible;
//            view.tooglePasswordField1(isPasswordVisible); // Corrected typo
//        }
//    }
//}
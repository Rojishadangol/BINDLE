package testbindleprojecct.View;

import testbindleprojecct.Model.User;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author acer
 */
public class ProfileView extends javax.swing.JFrame {
    private JButton save;
    private JButton cancel;
    private JButton deactivate;
    private JButton changePassword;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel titleLabel;

    public ProfileView(User currentUser) {
        initComponents();
        if (currentUser != null) {
            name.setText(currentUser.getName() != null ? currentUser.getName() : "");
            email.setText(currentUser.getEmail() != null ? currentUser.getEmail() : "");
        }
        // Fallback if components are null
        if (save == null) save = new JButton("Save");
        if (cancel == null) cancel = new JButton("Cancel");
        if (deactivate == null) deactivate = new JButton("Deactivate");
        if (changePassword == null) changePassword = new JButton("ChangePassword");
        if (nameLabel == null) nameLabel = new JLabel("Name");
        if (name == null) name = new JTextField();
        if (emailLabel == null) emailLabel = new JLabel("Email");
        if (email == null) email = new JTextField();
        if (titleLabel == null) titleLabel = new JLabel("Profile");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        try {
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
            jPanel2.setBackground(new java.awt.Color(245, 245, 245)); // Whitish background

            save = new javax.swing.JButton();
            save.setBackground(new java.awt.Color(153, 0, 204));
            save.setText("Save");

            cancel = new javax.swing.JButton();
            cancel.setText("Cancel");

            deactivate = new javax.swing.JButton();
            deactivate.setText("Deactivate");

            changePassword = new javax.swing.JButton();
            changePassword.setText("ChangePassword");

            nameLabel = new javax.swing.JLabel();
            nameLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12));
            nameLabel.setText("Name");

            name = new javax.swing.JTextField();

            emailLabel = new javax.swing.JLabel();
            emailLabel.setText("Email");

            email = new javax.swing.JTextField();

            titleLabel = new javax.swing.JLabel();
            titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));
            titleLabel.setText("Profile");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel))
                            .addGap(41, 41, 41)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(60, 60, 60))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(deactivate)
                            .addGap(67, 67, 67)
                            .addComponent(changePassword)))
                    .addContainerGap())
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titleLabel)
                    .addGap(40, 40, 40)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(42, 42, 42)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(deactivate)
                                .addComponent(changePassword)))
                        .addComponent(emailLabel))
                    .addGap(32, 32, 32)
                    .addComponent(save)
                    .addGap(26, 26, 26)
                    .addComponent(cancel)
                    .addGap(15, 15, 15))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 435, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 463, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );

            pack();
        } catch (Exception e) {
            System.err.println("Error initializing ProfileView components: " + e.getMessage());
            // Fallback initialization
            save = new JButton("Save");
            cancel = new JButton("Cancel");
            deactivate = new JButton("Deactivate");
            changePassword = new JButton("ChangePassword");
            nameLabel = new JLabel("Name");
            name = new JTextField();
            emailLabel = new JLabel("Email");
            email = new JTextField();
            titleLabel = new JLabel("Profile");
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new ProfileView(null).setVisible(true);
        });
    }

    // Getter methods
    public JButton getSaveButton() { return save; }
    public JButton getCancelButton() { return cancel; }
    public JButton getDeactivateButton() { return deactivate; }
    public JButton getChangePasswordButton() { return changePassword; }
    public JLabel getNameLabel() { return nameLabel; }
    public JTextField getNameField() { return name; }
    public JLabel getEmailLabel() { return emailLabel; }
    public JTextField getEmailField() { return email; }
    public JLabel getTitleLabel() { return titleLabel; }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bindle_project.View;

import java.awt.event.ActionListener;

/**
 *
 * @author acer
 */
public class RegisterView extends javax.swing.JFrame {

    /**
     * Creates new form RegisterView
     */
    public RegisterView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegisterButton = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        ConfirmPassword = new javax.swing.JPasswordField();
        show1 = new javax.swing.JButton();
        show2 = new javax.swing.JButton();
        AlreadyHaveAnAccount = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegisterButton.setBackground(new java.awt.Color(204, 102, 255));
        RegisterButton.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        RegisterButton.setForeground(new java.awt.Color(255, 255, 255));
        RegisterButton.setText("Register");
        getContentPane().add(RegisterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 170, 30));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bindle_project/View/Logo.png"))); // NOI18N
        logo.setText("jLabel7");
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 360, 80));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("Create an account");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 300, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 90, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Email");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 67, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Password");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 90, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Confirm Password");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 160, -1));

        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 240, 30));
        getContentPane().add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 240, 30));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 240, 30));

        ConfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(ConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 240, 30));

        show1.setText("Show");
        getContentPane().add(show1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, -1, -1));

        show2.setText("Show");
        getContentPane().add(show2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, -1, -1));

        AlreadyHaveAnAccount.setText("Already Have an Account?");
        getContentPane().add(AlreadyHaveAnAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bindle_project/View/background.png"))); // NOI18N
        background.setText("jLabel6");
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 700, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void ConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AlreadyHaveAnAccount;
    private javax.swing.JPasswordField ConfirmPassword;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Name;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel logo;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton show1;
    private javax.swing.JButton show2;
    // End of variables declaration//GEN-END:variables

public javax.swing.JTextField getNameTextField(){
return Name;
}
public javax.swing.JTextField getEmailTextField(){
return Email;
}
public javax.swing.JPasswordField getPasswordField(){
return ConfirmPassword;
} 
public javax.swing.JPasswordField getConfirmPassword(){
return password;
}
public void registerUser(ActionListener listener){
    RegisterButton.addActionListener(listener);
    
   
}
public javax.swing.JButton getRegisterButton(){
return RegisterButton;}
public javax.swing.JLabel getAlreadyHaveAnAccount(){
    return AlreadyHaveAnAccount;}


public void showPasswordButtonListener(ActionListener listener){
show1.addActionListener(listener);}
public void tooglePasswordField(boolean visible){
password.setEchoChar(visible ? (char) 0:'*');
show1.setText(visible ? "Hide":"Show");}
public void showPasswordButtonListener1(ActionListener listener){
show2.addActionListener(listener);}
public void tooglePasswordField1(boolean visible){
ConfirmPassword.setEchoChar(visible ? (char) 0:'*');
show2.setText(visible ? "Hide":"Show");}}

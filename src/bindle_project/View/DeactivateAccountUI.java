package bindle_project.View;

import java.awt.*;
import javax.swing.*;

public class DeactivateAccountUI extends JFrame {

    private JComboBox<String> reasonDropdown;
    private JTextArea commentsArea;
    private JPasswordField passwordField;
    private JButton deactivateBtn, cancelBtn;

     public DeactivateAccountUI() {
        setTitle("Deactivate Account");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Deactivate Account");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea warning = new JTextArea("⚠ Deactivating your account will:\n" +
                "• Disable access to all features\n" +
                "• Remove you from shared workspaces\n" +
                "• Preserve your data for 30 days\n\n" +
                "This action is reversible within 30 days.");
        warning.setEditable(false);
        warning.setFont(new Font("Arial", Font.PLAIN, 14));
        warning.setBackground(null);
        warning.setBorder(null);
        warning.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel reasonLabel = new JLabel("Reason for deactivation:");
        String[] reasons = {"Select a reason", "Temporary break", "Privacy concerns", "Found alternative", "Other"};
        reasonDropdown = new JComboBox<>(reasons);

        JLabel commentLabel = new JLabel("Additional comments:");
        commentsArea = new JTextArea(3, 30);
        JScrollPane commentScroll = new JScrollPane(commentsArea);

        JLabel passwordLabel = new JLabel("Password (for confirmation):");
        passwordField = new JPasswordField(20);

        // Buttons
        JPanel btnPanel = new JPanel();
        cancelBtn = new JButton("Cancel");
        deactivateBtn = new JButton("Deactivate");

        deactivateBtn.setBackground(Color.BLUE);
        deactivateBtn.setForeground(Color.WHITE);

        cancelBtn.addActionListener(e -> dispose());
        deactivateBtn.addActionListener(e -> showConfirmationDialog());

        btnPanel.add(cancelBtn);
        btnPanel.add(deactivateBtn);

        // Add components
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(warning);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(reasonLabel);
        panel.add(reasonDropdown);
        panel.add(commentLabel);
        panel.add(commentScroll);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnPanel);

        add(panel);
        setVisible(true);
    }

    private void showConfirmationDialog() {
        String input = JOptionPane.showInputDialog(this,
                "Are you sure you want to deactivate your account?\n" +
                        "This can be undone within 30 days.\n\nType 'DEACTIVATE' to confirm:");

        if ("DEACTIVATE".equalsIgnoreCase(input)) {
            JOptionPane.showMessageDialog(this, "✅ Your account has been deactivated.\nAn email confirmation has been sent.");
            dispose(); // close window
        } else if (input != null) {
            JOptionPane.showMessageDialog(this, "❌ Deactivation cancelled or incorrect confirmation.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeactivateAccountUI());
    }
}

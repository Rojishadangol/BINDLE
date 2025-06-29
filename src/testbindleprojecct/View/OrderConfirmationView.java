package testbindleprojecct.View;

import javax.swing.*;
import java.awt.*;

public class OrderConfirmationView extends JFrame {
    private JLabel orderIdLabel;
    private JLabel totalLabel;
    private JLabel deliveryLabel;
    private JButton backButton;

    public OrderConfirmationView(String orderId, double total, String deliveryInfo) {
        initComponents(orderId, total, deliveryInfo);
    }

    private void initComponents(String orderId, double total, String deliveryInfo) {
        setTitle("Order Confirmation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        orderIdLabel = new JLabel("Order ID: " + orderId);
        totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        deliveryLabel = new JLabel("Delivery: " + deliveryInfo);
        backButton = new JButton("Back to Home");

        add(orderIdLabel);
        add(totalLabel);
        add(deliveryLabel);
        add(backButton);

        backButton.addActionListener(e -> {
            dispose();
            new HomeView().setVisible(true); // Replace with actual HomeView navigation
        });
    }

    public JButton getBackButton() { return backButton; }
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
package testbindleprojecct.View;

import testbindleprojecct.Model.CartItem;
import testbindleprojecct.Model.Book;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CheckoutView extends JFrame {
    private JList<CartItem> cartList;
    private JLabel totalLabel;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField instructionsField;
    private JCheckBox codCheckbox;
    private JTextField buyerNameField;
    private JTextField buyerEmailField;
    private JTextField paymentField;
    private JButton confirmButton;
    private JButton backButton;

    public CheckoutView(List<CartItem> cartItems, List<Book> books, double total) {
        initComponents();
        refreshCart(cartItems != null ? cartItems : new ArrayList<>(), books != null ? books : new ArrayList<>(), total);
        setTitle("Bindle - Checkout");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Cart List
        cartList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(cartList);
        add(scrollPane, BorderLayout.CENTER);

        // Total
        totalLabel = new JLabel("Total: $0.00"); // Always initialized
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        add(totalLabel, BorderLayout.NORTH);

        // Delivery and Buyer Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        detailsPanel.add(new JLabel("Address:"));
        addressField = new JTextField(20);
        detailsPanel.add(addressField);
        detailsPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField(10);
        detailsPanel.add(phoneField);
        detailsPanel.add(new JLabel("Instructions (Optional):"));
        instructionsField = new JTextField(20);
        detailsPanel.add(instructionsField);
        detailsPanel.add(new JLabel("Cash on Delivery:"));
        codCheckbox = new JCheckBox();
        detailsPanel.add(codCheckbox);
        detailsPanel.add(new JLabel("Buyer Name:"));
        buyerNameField = new JTextField(20);
        detailsPanel.add(buyerNameField);
        detailsPanel.add(new JLabel("Buyer Email:"));
        buyerEmailField = new JTextField(20);
        detailsPanel.add(buyerEmailField);
        add(detailsPanel, BorderLayout.CENTER);

        // Payment Field
        JPanel paymentPanel = new JPanel();
        paymentField = new JTextField(10);
        paymentPanel.add(new JLabel("Enter Payment Amount (if not COD): $"));
        paymentPanel.add(paymentField);
        add(paymentPanel, BorderLayout.SOUTH);

        // Buttons
        confirmButton = new JButton("Confirm Purchase");
        backButton = new JButton("Back to Cart");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refreshCart(List<CartItem> items, List<Book> books, double total) {
        if (totalLabel == null) {
            throw new IllegalStateException("totalLabel is not initialized"); // Keep for debugging
        }
        DefaultListModel<CartItem> model = new DefaultListModel<>();
        if (items != null) {
            for (CartItem item : items) {
                model.addElement(item);
            }
        }
        cartList.setModel(model);
        totalLabel.setText("Total: $" + String.format("%.2f", total >= 0 ? total : 0.0));
    }

    public JButton getConfirmButton() { return confirmButton; }
    public JButton getBackButton() { return backButton; }
    public String getPaymentAmount() { return paymentField.getText(); }
    public String getAddress() { return addressField.getText(); }
    public String getPhone() { return phoneField.getText(); }
    public String getInstructions() { return instructionsField.getText(); }
    public boolean isCodSelected() { return codCheckbox.isSelected(); }
    public String getBuyerName() { return buyerNameField.getText().trim(); }
    public String getBuyerEmail() { return buyerEmailField.getText().trim(); }
    public JLabel getTotalLabel() { return totalLabel; }
    public void setTotal(double total) {
        totalLabel.setText("Total: $" + String.format("%.2f", total >= 0 ? total : 0.0));
    }
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
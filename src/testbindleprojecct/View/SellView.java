package testbindleprojecct.View;

import testbindleprojecct.Model.Book;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SellView extends JFrame {
    private JTextField titleField, authorField, priceField, imageUrlField, descriptionField,sellerNameField,phoneNumberField;
    private JButton addBookButton, markAsSoldButton,backButton;
    private JList<Book> bookList;

    public SellView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Bindle - Seller Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField(20);
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField(20);
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField(20);
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Image URL:"));
        imageUrlField = new JTextField(20);
        inputPanel.add(imageUrlField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField(20);
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Seller Name:"));
        sellerNameField = new JTextField(20);
        inputPanel.add(sellerNameField);
        inputPanel.add(new JLabel("Mobile Number::"));
        phoneNumberField = new JTextField(20);
        inputPanel.add(phoneNumberField);

        add(inputPanel, BorderLayout.NORTH);

        // Book List
        bookList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(bookList);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        addBookButton = new JButton("Add Book");
        markAsSoldButton = new JButton("Mark as Sold");
        markAsSoldButton.setEnabled(false);
        backButton = new JButton("Back");

        
        buttonPanel.add(addBookButton);
        buttonPanel.add(markAsSoldButton);
                buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        bookList.addListSelectionListener(e -> {
            markAsSoldButton.setEnabled(!bookList.isSelectionEmpty());
        });
    }

    public void setBooks(List<Book> books) {
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : books) {
            model.addElement(book);
        }
        bookList.setModel(model);
    }

    public JTextField getTitleField() { return titleField; }
    public JTextField getAuthorField() { return authorField; }
    public JTextField getPriceField() { return priceField; }
    public JTextField getImageUrlField() { return imageUrlField; }
    public JTextField getDescriptionField() { return descriptionField; }
    public JButton getAddBookButton() { return addBookButton; }
    public JButton getMarkAsSoldButton() { return markAsSoldButton; }
        public JButton getBackButton() { return backButton; }

    public Book getSelectedBook() { return bookList.getSelectedValue(); }
    public JTextField getSellerNameField() { return sellerNameField; }
    public JTextField getPhoneNumberField() { return phoneNumberField; }


    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearFields() {
        titleField.setText("");
        authorField.setText("");
        priceField.setText("");
        imageUrlField.setText("");
        descriptionField.setText("");
    }
}
/*
 * Click https://www.nbsp.com/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://www.nbsp.com/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.View;

import testbindleprojecct.Model.WishlistModel;
import testbindleprojecct.Controller.WishlistController;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.User;
import javax.swing.*;
import java.util.List;

public class WishListView extends JFrame {
    private WishlistModel model;
    private JButton removeButton;
    private JButton backButton;
    private JList<Book> bookList; // Assume a JList for displaying books

    public WishListView(User user) {
        this.model = new WishlistModel(user); // Create model with user
        initComponents();
    }

    private void initComponents() {
        setTitle("Wishlist");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize components
        bookList = new JList<>();
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");
        refreshWishlist(model.getWishlistBooks());

        // Layout
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(bookList));
        panel.add(removeButton);
        panel.add(backButton);
        add(panel);

        // Set up controller
        new WishlistController(this, model); // Pass the WishlistModel
    }

    public void refreshWishlist(List<Book> books) {
        DefaultListModel<Book> listModel = new DefaultListModel<>();
        if (books != null) {
            for (Book book : books) {
                listModel.addElement(book);
            }
        }
        bookList.setModel(listModel);
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getSelectedBookId() {
        Book selectedBook = bookList.getSelectedValue();
        return (selectedBook != null) ? selectedBook.getId() : null;
    }
}
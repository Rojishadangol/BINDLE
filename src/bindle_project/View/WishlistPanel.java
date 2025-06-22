/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package bindle_project.View;

import bindle_project.Model.WishlistModel;
import bindle_project.Model.Book; // Assuming Book is in this package
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 *
 * @author acer
 */
public class WishlistPanel extends JPanel {

    private WishlistModel model;
    private DefaultListModel<Book> listModel;
    private JList<Book> wishlistList;
    private JButton removeButton;
    private JButton viewDetailsButton;

    public WishlistPanel(WishlistModel model) {
        this.model = model;

        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        wishlistList = new JList<>(listModel);
        wishlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wishlistList.setCellRenderer(new BookListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(wishlistList);

        removeButton = new JButton("Remove from Wishlist");
        viewDetailsButton = new JButton("View Book Details");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(removeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshWishlist();

        // Remove book from wishlist
        removeButton.addActionListener(e -> {
            Book selected = wishlistList.getSelectedValue();
            if (selected != null) {
                if (model.removeBook(selected)) {
                    refreshWishlist();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove book.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to remove.");
            }
        });

        // View book details action (placeholder since BookDetailsDialog is missing)
        viewDetailsButton.addActionListener(e -> {
            Book selected = wishlistList.getSelectedValue();
            if (selected != null) {
                JOptionPane.showMessageDialog(this, "Book Details: " + selected.getTitle() + " by " + selected.getAuthor());
                // Replace with actual dialog implementation
                // BookDetailsDialog detailsDialog = new BookDetailsDialog(selected);
                // detailsDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to view.");
            }
        });
    }

    public void refreshWishlist() {
        listModel.clear();
        for (Book book : model.getWishlistBooks()) {
            listModel.addElement(book);
        }
    }

    // Custom renderer to show book title and author
    private static class BookListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Book) {
                Book book = (Book) value;
                label.setText(book.getTitle() + " by " + book.getAuthor());
                // Optionally set icon for thumbnail if available
                // label.setIcon(new ImageIcon(book.getThumbnailImage()));
            }

            return label;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

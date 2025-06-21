/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bindle_project.View;

import bindle_project.Model.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class BookGridScreen extends JPanel {
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookGridScreen() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Define table columns
        String[] columnNames = {"ID", "Title", "Author", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        bookTable = new JTable(tableModel);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);
    }

    public void setBooks(List<Book> books) {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Add new rows
        for (Book book : books) {
            Object[] row = {
                book.getId(), // Assuming Book has getId()
                book.getTitle(), // Assuming Book has getTitle()
                book.getAuthor(), // Assuming Book has getAuthor()
                String.format("$%.2f", book.getPrice()) // Assuming Book has getPrice()
            };
            tableModel.addRow(row);
        }

        // Refresh the table
        revalidate();
        repaint();
    }

    public void setVisible(boolean visible) {
        if (getParent() instanceof JFrame) {
            ((JFrame) getParent()).setVisible(visible);
        } else {
            super.setVisible(visible); // Handle if added to another container
        }
    }

    public int getSelectedBookId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
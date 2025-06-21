package bindle_project.View;

import bindle_project.Controller.OrderHistoryController;
import bindle_project.Model.Order;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author acer
 */
public class OrderHistoryView extends JPanel {

    private OrderHistoryController controller;
    private JTable ordersTable;
    private JTextArea orderDetailsArea;
    private JButton refreshButton;
    private List<Order> orders;

    public OrderHistoryView(OrderHistoryController controller) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));

        // Table for orders summary
        ordersTable = new JTable();
        JScrollPane tableScroll = new JScrollPane(ordersTable);
        tableScroll.setPreferredSize(new Dimension(600, 200));
        add(tableScroll, BorderLayout.NORTH);

        // Order details area
        orderDetailsArea = new JTextArea(10, 60);
        orderDetailsArea.setEditable(false);
        add(new JScrollPane(orderDetailsArea), BorderLayout.CENTER);

        // Refresh button
        refreshButton = new JButton("Refresh");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(refreshButton);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> controller.loadOrders());

        ordersTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = ordersTable.getSelectedRow();
            if (selectedRow >= 0) {
                controller.showOrderDetails(selectedRow);
            }
        });

        controller.loadOrders();
    }

    public void displayOrders(List<Order> orders) {
        this.orders = orders;

        String[] columnNames = {"Date", "Total Price", "Status"};
        Object[][] data = new Object[orders.size()][3];
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            data[i][0] = o.getOrderDate().format(fmt);
            data[i][1] = String.format("$%.2f", o.getTotalPrice());
            data[i][2] = o.getStatus();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ordersTable.setModel(model);
    }

    public void displayOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Shipping Address:\n").append(order.getShippingAddress()).append("\n\n");
        sb.append("Payment Info:\n").append(order.getPaymentInfo()).append("\n\n");
        sb.append("Items:\n");
        for (var entry : order.getItems().entrySet()) {
            bindle_project.Model.Book book = entry.getKey();
            int qty = entry.getValue();
            sb.append(String.format("%s x%d = $%.2f%n", book.getTitle(), qty, book.getPrice() * qty));
        }
        sb.append(String.format("\nTotal: $%.2f%n", order.getTotalPrice()));
        sb.append("Status: ").append(order.getStatus());

        orderDetailsArea.setText(sb.toString());
    }
}
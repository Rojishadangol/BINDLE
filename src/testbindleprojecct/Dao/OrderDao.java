package testbindleprojecct.Dao;

import testbindleprojecct.Model.Order;
import testbindleprojecct.Model.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import testbindleprojecct.Database.DbConnection;
import testbindleprojecct.Database.MySqlConnection;


public class OrderDao {
    public DbConnection db = MySqlConnection.getInstance();

    public boolean createOrder(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement itemStmt = null;
        try {
            conn = db.openConnection();
            if (conn == null) {
                System.err.println("Failed to open database connection");
                return false;
            }
            conn.setAutoCommit(false);
            System.out.println("Starting transaction for order ID: " + order.getId());

            // Insert order
            String orderQuery = "INSERT INTO orders (id, user_id, total_amount, order_date, status) VALUES (?, ?, ?, ?, ?)";
            orderStmt = conn.prepareStatement(orderQuery);
            orderStmt.setString(1, order.getId());
            orderStmt.setInt(2, order.getUserId());
            orderStmt.setDouble(3, order.getTotalAmount());
            orderStmt.setTimestamp(4, new java.sql.Timestamp(order.getOrderDate().getTime()));
            orderStmt.setString(5, order.getStatus());
            int orderRows = orderStmt.executeUpdate();
            System.out.println("Inserted order: " + orderRows + " rows affected");

            // Insert order items
            String itemQuery = "INSERT INTO order_items (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
            itemStmt = conn.prepareStatement(itemQuery);
            for (OrderItem item : order.getItems()) {
                itemStmt.setString(1, order.getId());
                itemStmt.setString(2, item.getBookId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getPrice());
                int itemRows = itemStmt.executeUpdate();
                System.out.println("Inserted order item: bookId=" + item.getBookId() + ", rows affected=" + itemRows);
            }

            conn.commit();
            System.out.println("Transaction committed for order ID: " + order.getId());
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transaction rolled back due to: " + e.getMessage());
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            throw e;
        } finally {
            if (itemStmt != null) itemStmt.close();
            if (orderStmt != null) orderStmt.close();
            if (conn != null) db.closeConnection(conn);
        }
    }
}
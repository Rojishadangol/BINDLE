/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct;

import testbindleprojecct.Dao.OrderDao;
import testbindleprojecct.Model.Order;
import testbindleprojecct.Model.OrderItem;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDaoTest {
    private static OrderDao orderDao;

    @BeforeClass
    public static void setUpClass() {
        orderDao = new OrderDao();
        try {
            System.out.println("Checking database connection to jdbc:mysql://localhost:3306/Bindle...");
            Connection conn = orderDao.db.openConnection();
            System.out.println("✅ Connected to database: " + conn.getCatalog());
            orderDao.db.closeConnection(conn);
            System.out.println("✅ Database connection test completed successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            fail("Test setup failed due to database connection issue: " + e.getMessage());
        }
    }

    @Test
    public void testCreateOrder_Success() throws SQLException {
        try {
            System.out.println("TEST_START_MARKER");
            Order order = new Order("ORDER001", 1, 50.0, new Date(), "Pending");
            order.setItems(new ArrayList<>());
            order.getItems().add(new OrderItem("ORDER001", "BOOK001", 1, 50.0));
            boolean result = orderDao.createOrder(order);
            System.out.println("Create order result: " + result);
            assertTrue("Creating order should succeed", result);
        } catch (SQLException e) {
            System.err.println("SQLException in testCreateOrder: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }
}
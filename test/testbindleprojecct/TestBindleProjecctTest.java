package testbindleprojecct;

import testbindleprojecct.Dao.CartDao;
import testbindleprojecct.Model.CartItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acer
 */
public class TestBindleProjecctTest {
    
    private static CartDao cartDao;

    public TestBindleProjecctTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        cartDao = new CartDao();
        try {
            System.out.println("Checking database connection to jdbc:mysql://localhost:3306/Bindle...");
            Connection conn = cartDao.db.openConnection();
            System.out.println("✅ Connected to database: " + conn.getCatalog());
            cartDao.db.closeConnection(conn);
            System.out.println("✅ Database connection test completed successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            fail("Test setup failed due to database connection issue: " + e.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddToCart_Success() throws SQLException {
        System.out.println("TEST_START_MARKER");
        try {
            boolean result = cartDao.addToCart(1, "BOOK001", 2);
            System.out.println("Add to cart result: " + result);
            assertTrue("Adding to cart should succeed", result);
        } catch (SQLException e) {
            System.err.println("SQLException in testAddToCart: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }

    @Test
    public void testGetCartItems_Success() throws SQLException {
        System.out.println("TEST_START_MARKER");
        try {
            List<CartItem> items = cartDao.getCartItems(1);
            System.out.println("Retrieved cart items count: " + (items != null ? items.size() : "null"));
            assertNotNull("Cart items list should not be null", items);
        } catch (SQLException e) {
            System.err.println("SQLException in testGetCartItems: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }
}
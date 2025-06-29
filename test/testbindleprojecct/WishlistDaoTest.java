/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct;

import testbindleprojecct.Dao.WishlistDao;
import testbindleprojecct.Model.Book;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WishlistDaoTest {
    private static WishlistDao wishlistDao;

    @BeforeClass
    public static void setUpClass() {
        wishlistDao = new WishlistDao();
        try {
            System.out.println("Checking database connection to jdbc:mysql://localhost:3306/Bindle...");
            Connection conn = wishlistDao.dbConnection.openConnection();
            System.out.println("✅ Connected to database: " + conn.getCatalog());
            wishlistDao.dbConnection.closeConnection(conn);
            System.out.println("✅ Database connection test completed successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            fail("Test setup failed due to database connection issue: " + e.getMessage());
        }
    }

    @Test
    public void testAddBookToWishlist_Success() throws SQLException {
        try {
            System.out.println("TEST_START_MARKER");
            boolean result = wishlistDao.addBookToWishlist(1, "BOOK001");
            System.out.println("Add to wishlist result: " + result);
            assertTrue("Adding book to wishlist should succeed", result);
        } catch (SQLException e) {
            System.err.println("SQLException in testAddBookToWishlist: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }

    @Test
    public void testGetWishlistBooks_Success() throws SQLException {
        try {
            System.out.println("TEST_START_MARKER");
            List<Book> books = wishlistDao.getWishlistBooks(1);
            System.out.println("Retrieved wishlist books count: " + (books != null ? books.size() : "null"));
            assertNotNull("Wishlist books list should not be null", books);
        } catch (SQLException e) {
            System.err.println("SQLException in testGetWishlistBooks: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }
}
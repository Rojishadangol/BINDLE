/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct;

import testbindleprojecct.Dao.BookDao;
import testbindleprojecct.Model.Book;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDaoTest {
    private static BookDao bookDao;

    @BeforeClass
    public static void setUpClass() {
        bookDao = new BookDao();
        try {
            System.out.println("Checking database connection to jdbc:mysql://localhost:3306/Bindle...");
            Connection conn = bookDao.dbConnection.openConnection();
            System.out.println("✅ Connected to database: " + conn.getCatalog());
            bookDao.dbConnection.closeConnection(conn);
            System.out.println("✅ Database connection test completed successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            fail("Test setup failed due to database connection issue: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllBooks_Success() throws SQLException {
        try {
            System.out.println("TEST_START_MARKER");
            List<Book> books = bookDao.getAllBooks();
            System.out.println("Retrieved books count: " + (books != null ? books.size() : "null"));
            assertNotNull("Books list should not be null", books);
        } catch (SQLException e) {
            System.err.println("SQLException in testGetAllBooks: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }

    @Test
    public void testAddBook_Success() throws SQLException {
        try {
            System.out.println("TEST_START_MARKER");
            Book book = new Book("Test Book", "Test Author", 10.0, "http://example.com");
            book.setId("BOOK002");
            boolean result = bookDao.addBook(book);
            System.out.println("AddBook result: " + result);
            assertTrue("Adding book should succeed", result);
        } catch (SQLException e) {
            System.err.println("SQLException in testAddBook: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to SQLException: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }
}
package testbindleprojecct;

import testbindleprojecct.Dao.UserDao;
import testbindleprojecct.Model.LoginRequest;
import testbindleprojecct.Model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest {
    private static UserDao userDao;

    @BeforeClass
    public static void setUpClass() {
        userDao = new UserDao();
        try {
            System.out.println("Checking database connection to jdbc:mysql://localhost:3306/Bindle");
            Connection conn = userDao.dbConnection.openConnection();
            System.out.println("✅ Connected to database: " + conn.getCatalog());
            userDao.dbConnection.closeConnection(conn);
            System.out.println("✅ Database connection test completed successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            fail("Test setup failed due to database connection issue: " + e.getMessage());
        }
    }

    @Test
    public void testRegister_Success() {
        System.out.println("TEST_START_MARKER");
        try {
            boolean result = UserDao.register("test@example.com", "password123", "Test User");
            System.out.println("Register result: " + result + " for email: test@example.com");
            assertTrue("Registration should succeed", result);
        } catch (Exception e) {
            System.err.println("Exception in testRegister_Success: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to exception: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }

    @Test
    public void testLogin_Success() {
        System.out.println("TEST_START_MARKER");
        try {
            LoginRequest request = new LoginRequest("test@example.com", "password123");
            User user = UserDao.login(request);
            System.out.println("Login result: " + (user != null ? user.getEmail() : "null"));
            assertNotNull("Login should return a user", user);
            if (user != null) assertEquals("Email should match", "test@example.com", user.getEmail());
        } catch (Exception e) {
            System.err.println("Exception in testLogin_Success: " + e.getMessage());
            e.printStackTrace();
            fail("Test failed due to exception: " + e.getMessage());
        } finally {
            System.out.println("TEST_END_MARKER");
        }
    }
}
package testbindleprojecct.Controller;

import testbindleprojecct.Model.User;
import testbindleprojecct.Model.WishlistModel;
import testbindleprojecct.View.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import testbindleprojecct.Database.MySqlConnection;
import testbindleprojecct.Dao.CartDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.CartItem;
import java.util.List;
import testbindleprojecct.Dao.BookDao;

public class NavigationController {
    private final User currentUser;
    private final Connection dbConnection;

    public NavigationController(User user) {
        this.currentUser = (user != null) ? user : new User(0, "guest", "guest@example.com", false); // Fallback user
        try {
            this.dbConnection = MySqlConnection.getInstance().openConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish database connection: " + e.getMessage());
        }
    }

    public void showLoginView() {
     System.out.println("Opening new LoginView");
        LoginController controller = new LoginController(new LoginView(), this);
        controller.open(); // Use open() method as defined in LoginController
    }

    public void showHomeView() {
        System.out.println("Opening new HomeView");
        new HomeController(new HomeView(), currentUser).open();
    }

    public void showProfileView() {
        try {
            System.out.println("Opening new ProfileView");
            ProfileView profileView = new ProfileView(currentUser);
            new ProfileController(profileView, currentUser, this).getView().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error opening ProfileView: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to open ProfileView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showCartView() {
        try {
            System.out.println("Opening new CartView");
            CartView cartView = new CartView();
            new CartController(cartView, currentUser, this).getView().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error opening CartView: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to open CartView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showWishListView() {
        try {
            System.out.println("Opening new WishListView");
            WishlistModel model = new WishlistModel(currentUser);
            WishListView wishListView = new WishListView(currentUser);
            new WishlistController(wishListView, model).getView().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error opening WishListView: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to open WishListView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showSearchView(String query) {
        try {
            System.out.println("Opening new SearchView");
            SearchView searchView = new SearchView();
            new SearchController(searchView, currentUser, this).getView().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error opening SearchView: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to open SearchView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showSellView() {
        System.out.println("Opening new SellView");
        SellView sellView = new SellView();
        new SellController(sellView, currentUser, this).getView().setVisible(true);
    }

    public void showCheckoutView() {
        try {
            System.out.println("Opening new CheckoutView");
            CartDao cartDao = new CartDao();
            List<CartItem> cartItems = cartDao.getCartItems(currentUser.getId());
            if (cartItems == null) {
                cartItems = new ArrayList<>();
            }
            List<Book> books = new ArrayList<>();
            double total = 0.0;
            BookDao bookDao = new BookDao();
            for (CartItem item : cartItems) {
                Book book = bookDao.getBookById(item.getBookId());
                if (book != null) {
                    books.add(book);
                    total += book.getPrice() * item.getQuantity();
                }
            }
            if (books.isEmpty() && cartItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your cart is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CheckoutView checkoutView = new CheckoutView(cartItems, books, total);
            new CheckoutController(checkoutView, currentUser, this).getView().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error opening CheckoutView: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to open CheckoutView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showChangePasswordView() {
        System.out.println("Opening new ChangePasswordView");
        ChangePasswordView changePasswordView = new ChangePasswordView();
        new ChangePasswordController(changePasswordView, currentUser, this).getView().setVisible(true);
    }

    public void showForgotPasswordView() {
        System.out.println("Opening new ForgotPasswordView");
        ForgotPasswordView forgotPasswordView = new ForgotPasswordView();
        new ForgotPasswordController(forgotPasswordView).getView().setVisible(true);
    }

    public void showDeactivateAccountView() {
        System.out.println("Opening new DeactivateAccountView");
        DeactivateAccountView deactivateAccountView = new DeactivateAccountView();
        new DeactivateAccountController(deactivateAccountView, currentUser, this).getView().setVisible(true);
    }

    public void showListView() {
        try {
            System.out.println("Opening new ListView");
            ListView listView = new ListView();
            new ListController(listView, currentUser, this).getView().setVisible(true);
        } catch (Exception e) {
            System.err.println("Error opening ListView: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to open ListView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showRegisterView() {
        System.out.println("Opening new RegisterView");
        new RegisterController().open();
    }
}
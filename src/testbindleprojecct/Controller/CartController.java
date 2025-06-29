package testbindleprojecct.Controller;

import testbindleprojecct.Dao.BookDao;
import testbindleprojecct.Dao.CartDao;
import testbindleprojecct.Dao.OrderDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.CartItem;
import testbindleprojecct.Model.Order;
import testbindleprojecct.Model.OrderItem;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.CartView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class CartController {
    private CartView view;
    private CartDao cartDao;
    private BookDao bookDao;
    private OrderDao orderDao;
    private User currentUser;
    private List<CartItem> cartItems;
    private List<Book> books;
    private double total;
    private final NavigationController navController;

    public CartController(CartView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.cartDao = new CartDao();
        this.bookDao = new BookDao();
        this.orderDao = new OrderDao();
        this.currentUser = currentUser;
        this.cartItems = new ArrayList<>();
        this.books = new ArrayList<>();
        this.total = 0.0;
        this.navController = navController;

        initController();
        loadCart();
    }

    private void initController() {
        System.out.println("Attaching listeners to CartView buttons for user ID: " + currentUser.getId());
        view.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                navController.showHomeView();
            }
        });
        view.getHeartButton().addActionListener(e -> {
            navController.showWishListView();
            view.dispose();
        });
        view.getSearchButton().addActionListener(e -> {
            navController.showSearchView("");
            view.dispose();
        });

        view.getCheckoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cartItems == null || cartItems.isEmpty()) {
                        view.showError("Cart is empty or not loaded properly.");
                        System.out.println("❌ Attempted to proceed to checkout with empty or uninitialized cart.");
                        return;
                    }
                    navController.showCheckoutView();
                    view.dispose();
                } catch (Exception ex) {
                    System.err.println("Error opening CheckoutView: " + ex.getMessage());
                    view.showError("Failed to proceed to checkout: " + ex.getMessage());
                }
            }
        });
    }

    private void loadCart() {
        if (currentUser != null) {
            try {
                cartItems = cartDao.getCartItems(currentUser.getId());
                books.clear();
                total = 0.0;
                for (CartItem item : cartItems) {
                    Book book = bookDao.getBookById(item.getBookId());
                    if (book != null) {
                        books.add(book);
                        total += book.getPrice() * item.getQuantity();
                    } else {
                        System.out.println("⚠ Book not found for bookId: " + item.getBookId());
                    }
                }
                view.refreshCart(cartItems, books, total);
                System.out.println("Cart loaded with " + cartItems.size() + " items for user ID: " + currentUser.getId());
            } catch (UnsupportedOperationException e) {
                System.err.println("Unsupported operation in CartDao: " + e.getMessage());
                view.showError("Cart functionality not supported yet.");
            } catch (SQLException e) {
                System.err.println("Error loading cart: " + e.getMessage());
                view.showError("Failed to load cart: " + e.getMessage());
                cartItems.clear();
            } catch (Exception e) {
                System.err.println("Unexpected error loading cart: " + e.getMessage());
                view.showError("Failed to load cart due to an unexpected error.");
                cartItems.clear();
            }
        } else {
            System.out.println("❌ No current user available to load cart.");
            cartItems.clear();
        }
    }

    public CartView getView() {
        return view;
    }
}
package testbindleprojecct.Controller;

import testbindleprojecct.Dao.CartDao;
import testbindleprojecct.Dao.WishlistDao;
import testbindleprojecct.Dao.UserDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.BookDetailsView;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookDetailsController {
    private BookDetailsView view;
    private CartDao cartDao;
    private WishlistDao wishlistDao;
    private UserDao userDao;
    private User currentUser;
    private Book book;
    private final NavigationController navController;

    public BookDetailsController(BookDetailsView view, User currentUser, Book book, NavigationController navController) {
        this.view = view;
        this.cartDao = new CartDao();
        this.wishlistDao = new WishlistDao();
        this.userDao = new UserDao();
        this.currentUser = currentUser;
        this.book = book;
        this.navController = navController;
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to BookDetailsView buttons for user ID: " + currentUser.getId());
        User seller = null;
        try {
            System.out.println("Fetching seller for book ID: " + book.getId() + ", sellerId: " + book.getSellerId());
            if (book.getSellerId() != null) {
                seller = userDao.getUserById(book.getSellerId());
                System.out.println("Seller fetched: " + (seller != null ? seller.getName() : "null"));
            } else {
                System.out.println("No sellerId for book: " + book.getId());
            }
        } catch (SQLException ex) {
            view.showError("Error fetching seller details: " + ex.getMessage());
            System.err.println("Error fetching seller for book ID " + book.getId() + ": " + ex.getMessage());
        }
        view.updateDetails(book, seller);

        view.getAddToCartButton().addActionListener(e -> {
            try {
                cartDao.addToCart(currentUser.getId(), book.getId(), 1);
                view.showSuccess("Book added to cart!");
            } catch (SQLException ex) {
                view.showError("Error adding to cart: " + ex.getMessage());
            }
        });

        view.getAddToWishlistButton().addActionListener(e -> {
            try {
                wishlistDao.addToWishlist(currentUser.getId(), book.getId());
                view.showSuccess("Book added to wishlist!");
            } catch (SQLException ex) {
                view.showError("Error adding to wishlist: " + ex.getMessage());
            }
        });

        view.getBackButton().addActionListener(e -> {
            view.dispose();
            navController.showSearchView("");
        });
    }

    public BookDetailsView getView() {
        return view;
    }
}
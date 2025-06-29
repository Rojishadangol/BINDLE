package testbindleprojecct.Controller;

import testbindleprojecct.Dao.BookDao;
import testbindleprojecct.Dao.CartDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.ListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import testbindleprojecct.View.BookDetailsView;

public class ListController {
    private ListView view;
    private User currentUser;
    private BookDao bookDao;
    private CartDao cartDao;
    private final NavigationController navController;

    public ListController(ListView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.currentUser = currentUser;
        this.bookDao = new BookDao();
        this.cartDao = new CartDao();
        this.navController = navController;
        initController();
        loadBooks();
    }

    private void initController() {
        System.out.println("Attaching listeners to ListView buttons for user ID: " + currentUser.getId());
        view.getViewDetailsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book selectedBook = view.getSelectedBook();
                if (selectedBook != null) {
                    BookDetailsView detailView = new BookDetailsView(selectedBook);
                    new BookDetailsController(detailView, currentUser, selectedBook, navController).getView().setVisible(true);
                } else {
                    view.showError("Please select a book.");
                }
            }
        });

        view.getAddToCartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        view.getCartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCart();
            }
        });

        view.getHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                navController.showHomeView();
            }
        });
    }

    private void loadBooks() {
        try {
            List<Book> books = bookDao.getAllBooks();
            view.setBooks(books);
        } catch (SQLException e) {
            view.showError("Failed to load books: " + e.getMessage());
        }
    }

    private void addToCart() {
        if (currentUser == null) {
            view.showError("Please log in to add items to cart.");
            return;
        }
        Book selectedBook = view.getSelectedBook();
        if (selectedBook == null) {
            view.showError("Please select a book to add to cart.");
            return;
        }
        try {
            cartDao.addToCart(currentUser.getId(), selectedBook.getId(), 1);
            view.showSuccess("Book added to cart successfully!");
            System.out.println("Added book ID " + selectedBook.getId() + " to cart for user ID " + currentUser.getId());
        } catch (SQLException e) {
            view.showError("Database error adding to cart: " + e.getMessage());
            System.err.println("Database error adding to cart: " + e.getMessage());
        }
    }

    private void openCart() {
        try {
            navController.showCartView();
            view.dispose();
        } catch (Exception e) {
            view.showError("Failed to open cart: " + e.getMessage());
        }
    }

    private String findBookIdByTitle(String title) throws SQLException {
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book.getId();
            }
        }
        return null;
    }

    public ListView getView() {
        return view;
    }
}
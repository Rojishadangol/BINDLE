package testbindleprojecct.Controller;

import testbindleprojecct.Dao.BookDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.BookDetailsView;
import testbindleprojecct.View.SearchView;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SearchController {
    private SearchView view;
    private BookDao bookDao;
    private User currentUser;
    private final NavigationController navController;

    public SearchController(SearchView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.bookDao = new BookDao();
        this.currentUser = currentUser;
        this.navController = navController;
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to SearchView buttons for user ID: " + currentUser.getId());
        view.getHomeButton().addActionListener(e -> {
            view.dispose();
            navController.showHomeView();
        });

        view.getHeartButton().addActionListener(e -> {
            navController.showWishListView();
            view.dispose();
        });

       

        view.getSearchButton().addActionListener(e -> performSearch());
        view.getSearchTextField().addActionListener(e -> performSearch());
    }

    private void performSearch() {
        String searchQuery = view.getSearchTextField().getText().trim();
        if (searchQuery.isEmpty()) {
            view.showError("Please enter a book name.");
            return;
        }
        System.out.println("Searching for: " + searchQuery);
        try {
            List<Book> books = bookDao.searchBooks(searchQuery);
            System.out.println("Found " + books.size() + " books");
            if (books.isEmpty()) {
                view.showError("No books found for: " + searchQuery);
                System.out.println("No books found for query: " + searchQuery);
                return;
            }
            Book firstMatch = books.get(0);
            System.out.println("First match found: " + firstMatch.getTitle());
            BookDetailsView detailView = new BookDetailsView(firstMatch);
            new BookDetailsController(detailView, currentUser, firstMatch, navController).getView().setVisible(true);
            view.dispose();
        } catch (SQLException ex) {
            view.showError("Search error: " + ex.getMessage());
            System.err.println("Search error: " + ex.getMessage());
        }
    }

    public SearchView getView() {
        return view;
    }
}
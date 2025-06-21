
package bindle_project.Controller;

import bindle_project.Model.Book;
import bindle_project.Model.BookModel;
import bindle_project.Model.UserData;
import bindle_project.View.BookGridScreen;
import bindle_project.View.HomeScreen;
import bindle_project.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HomeController {
    private HomeScreen view;
    private BookGridScreen bookGrid;
    private BookModel bookModel;
    private UserData user;

    public HomeController(HomeScreen view) {
        System.out.println("HomeController constructor called with view: " + (view != null ? "not null" : "null"));
        if (view == null) {
            throw new IllegalStateException("HomeScreen view is null");
        }
        this.view = view;
        this.user = user;
        this.bookGrid = new BookGridScreen();
        this.bookModel = new BookModel();

        // Search button functionality
        if (view.getSearchButton() != null) {
            view.getSearchButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String query = view.getSearchField().getText().trim();
                    if (query.isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Please enter a search query.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    List<Book> books = bookModel.searchBooks(query);
                    bookGrid.setBooks(books);
                    bookGrid.setVisible(true);
                }
            });
        } else {
            System.out.println("SearchButton is null in HomeController");
        }

        // Sell button functionality
        if (view.getSellButton() != null) {
            view.getSellButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int bookId = bookGrid.getSelectedBookId(); // Implement this in BookGridScreen
                    if (bookId == -1) {
                        JOptionPane.showMessageDialog(view, "Please select a book to mark as sold.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (bookModel.markAsSold(bookId)) {
                        JOptionPane.showMessageDialog(view, "Book marked as sold.");
                        bookGrid.setBooks(bookModel.searchBooks(view.getSearchField().getText().trim()));
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to mark book as sold.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } else {
            System.out.println("Sell button is null in HomeController");
        }

        // Logout button functionality
        if (view.getLogoutButton() != null) {
            view.getLogoutButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                    new LoginView().setVisible(true);
                }
            });
        } else {
            System.out.println("Logout button is null in HomeController");
        }
    }

    public void open() {
        if (view != null) {
            view.setExtendedState(JFrame.NORMAL); // Normal state
view.setSize(800, 600); // Match the constructor size
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        } else {
            System.out.println("View is null, cannot open HomeScreen");
        }
    }

    public void close() {
        if (view != null) {
            view.dispose();
        }
    }
}
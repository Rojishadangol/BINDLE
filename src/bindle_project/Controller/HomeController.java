
package bindle_project.Controller;

import bindle_project.Model.Book;
import bindle_project.Model.BookModel;
import bindle_project.Model.UserData;
import bindle_project.View.HomeScreen;
import bindle_project.View.search; // Using Search instead of BookGridScreen
import bindle_project.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HomeController {
    private HomeScreen view;
    private search searchScreen; // Updated from BookGridScreen
    private BookModel bookModel;
    private UserData user;

    public HomeController(HomeScreen view) {
        System.out.println("HomeController constructor called with view: " + (view != null ? "not null" : "null"));
        if (view == null) {
            throw new IllegalStateException("HomeScreen view is null");
        }
        this.view = view;
        this.user = user;
        this.searchScreen = new search(); // Initialize Search screen
        this.bookModel = new BookModel();

        // Search button functionality
        if (view.getSearchButton() != null) {
            view.getSearchButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Navigate to Search screen
                    view.navigateToSearch(); // Calls HomeScreen's navigateToSearch
                }
            });
        } else {
            System.out.println("SearchButton is null in HomeController");
        }

        // Sell button functionality - Disabled for now due to Search not having getSelectedBookId()
        if (view.getSellButton() != null) {
            view.getSellButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // This logic is disabled since Search might not support getSelectedBookId()
                    JOptionPane.showMessageDialog(view, "Sell functionality is not available with the current Search screen.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    // Uncomment and adjust if Search implements getSelectedBookId()
                    // int bookId = searchScreen.getSelectedBookId();
                    // if (bookId == -1) {
                    //     JOptionPane.showMessageDialog(view, "Please select a book to mark as sold.", "Error", JOptionPane.ERROR_MESSAGE);
                    //     return;
                    // }
                    // if (bookModel.markAsSold(bookId)) {
                    //     JOptionPane.showMessageDialog(view, "Book marked as sold.");
                    // } else {
                    //     JOptionPane.showMessageDialog(view, "Failed to mark book as sold.", "Error", JOptionPane.ERROR_MESSAGE);
                    // }
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
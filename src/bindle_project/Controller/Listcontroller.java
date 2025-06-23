package bindle_project.Controller;

import bindle_project.Dao.ListDao;
import bindle_project.Model.Book;
import bindle_project.Model.BookModel;
import bindle_project.Model.User; // Add this import
import bindle_project.View.CartScreen;
import bindle_project.View.HomeScreen;
import bindle_project.View.description;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Listcontroller {
    private HomeScreen view;
    private BookModel bookModel;
    private User currentUser; // Field to store the current user

    public Listcontroller(User currentUser) {
        this.view = view;
        this.bookModel = new BookModel();
        this.currentUser = currentUser; // Initialize with the logged-in user
    }

    public void listBook(String title, String author, double price, String condition) {
        if (bookModel.addBook(title, author, price, condition, currentUser.getId())) {
            JOptionPane.showMessageDialog(view, "Book listed successfully.");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to list book.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void goToDescriptionScreen(String bookTitle) {
        description descriptionScreen = new description(bookTitle);
        descriptionScreen.setVisible(true);
    }

    private void initController() {
        // Assuming 'list' is a typo and should be 'view' or a specific component in HomeScreen
        view.getCartButton().addMouseListener(new MouseAdapter() { // Adjust based on actual component
            @Override
            public void mouseClicked(MouseEvent e) {
                Book selectedBook = null;
                goToCartScreen(selectedBook);
            }
        });
    }

    public void goToCartScreen(Book selectedBook) {
        if (currentUser != null) {
            CartScreen cartScreen = new CartScreen(currentUser); // Line 18
            cartScreen.setVisible(true);
        } else {
            System.out.println("No current user found. Please log in.");
        }
    }
}
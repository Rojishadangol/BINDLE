package bindle_project.Controller;

import bindle_project.Dao.ListDao;
import bindle_project.Model.Book;
import bindle_project.Model.BookModel;
import bindle_project.View.HomeScreen;
import javax.swing.JOptionPane;

public class Listcontroller {
    private HomeScreen view;
    private BookModel bookModel;
    private int userId;

    public Listcontroller(HomeScreen view, int userId) {
        this.view = view;
        this.bookModel = new BookModel();
        this.userId = userId;
    }

    public void listBook(String title, String author, double price, String condition) {
        if (bookModel.addBook(title, author, price, condition, userId)) {
            JOptionPane.showMessageDialog(view, "Book listed successfully.");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to list book.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

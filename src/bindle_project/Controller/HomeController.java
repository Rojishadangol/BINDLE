
import bindle_project.Model.BookModel;
import bindle_project.Model.UserData;
import bindle_project.View.BookGridScreen;
import bindle_project.View.HomeScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class HomeController {
    private HomeScreen view;
    private BookGridScreen bookGrid;
    private BookModel bookModel;
    private UserData user;

    public HomeController(HomeScreen view, UserData user) {
        this.view = view;
        this.user = user;
        this.bookGrid = new BookGridScreen();
        this.bookModel = new BookModel();

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

        // Example: Add button to mark as sold
        JButton sellButton = new JButton("Mark as Sold");
        sellButton.addActionListener(e -> {
            int bookId = 1; // Replace with selected book ID
            if (bookModel.markAsSold(bookId)) {
                JOptionPane.showMessageDialog(view, "Book marked as sold.");
            }
        });
        view.add(sellButton); // Add to appropriate panel
    }
}
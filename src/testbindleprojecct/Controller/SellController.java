package testbindleprojecct.Controller;

import testbindleprojecct.Dao.BookDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.SellView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SellController {
    private SellView view;
    private User currentUser;
    private BookDao bookDao;
    private final NavigationController navController;

    public SellController(SellView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.currentUser = currentUser;
        this.bookDao = new BookDao();
        this.navController = navController;
        initController();
        loadSellerBooks();
    }

    private void initController() {
        System.out.println("Attaching listeners to SellView buttons for user ID: " + currentUser.getId());
        view.getAddBookButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        view.getMarkAsSoldButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAsSold();
            }
        });
         view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                navController.showHomeView();
            }
        });
    }

    private void loadSellerBooks() {
        if (currentUser != null && currentUser.getId() != 0) {
            try {
                List<Book> allBooks = bookDao.getAllBooks();
                List<Book> sellerBooks = new ArrayList<>();
                for (Book book : allBooks) {
                    if (book.getSellerId() != null && book.getSellerId().equals(currentUser.getId()) && !book.isSold()) {
                        sellerBooks.add(book);
                    }
                }
                view.setBooks(sellerBooks);
            } catch (SQLException e) {
                view.showError("Failed to load seller books: " + e.getMessage());
            }
        }
    }

    private void addBook() {
        String title = view.getTitleField().getText().trim();
        String author = view.getAuthorField().getText().trim();
        String priceText = view.getPriceField().getText().trim();
        String imageUrl = view.getImageUrlField().getText().trim();
        String description = view.getDescriptionField().getText().trim();
        String name = view.getSellerNameField().getText().trim();
        String number = view.getPhoneNumberField().getText().trim();

        if (title.isEmpty() || author.isEmpty() || priceText.isEmpty() || imageUrl.isEmpty() || name.isEmpty() || number.isEmpty()) {
            view.showError("Please fill all required fields (Title, Author, Price, and Image URL).");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) {
                view.showError("Price must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            view.showError("Invalid price format.");
            return;
        }

        Book book = new Book(title, author, price, imageUrl);
        book.setId(java.util.UUID.randomUUID().toString());
        book.setDescription(description);
        book.setSold(false);
        book.setSellerId(currentUser.getId());

        try {
            if (bookDao.addBook(book)) {
                view.showSuccess("Book added successfully!");
                view.clearFields();
            } else {
                view.showError("Failed to add book.");
            }
        } catch (SQLException e) {
            view.showError("Database error adding book: " + e.getMessage());
        }
    }

    private void markAsSold() {
        if (currentUser == null || currentUser.getId() == 0) {
            view.showError("Please log in as a seller.");
            return;
        }
        Book selectedBook = view.getSelectedBook();
        if (selectedBook == null) {
            view.showError("Please select a book to mark as sold.");
            return;
        }
        if (selectedBook.getSellerId() == null || !selectedBook.getSellerId().equals(currentUser.getId())) {
            view.showError("You are not authorized to mark this book as sold.");
            return;
        }
        try {
            if (bookDao.markAsSold(selectedBook.getId(), currentUser.getId())) {
                view.showSuccess("Book marked as sold successfully!");
                loadSellerBooks();
            } else {
                view.showError("Failed to mark book as sold.");
            }
        } catch (SQLException e) {
            view.showError("Database error marking book as sold: " + e.getMessage());
        }
    }

    public SellView getView() {
        return view;
    }
}

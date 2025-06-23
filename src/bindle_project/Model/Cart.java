package bindle_project.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private User user;
    private List<Integer> bookIds;

    public Cart(User user) {
        this.user = user;
        this.bookIds = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }
public boolean addBook(Book book) {
        if (book != null && !books.contains(book)) {
            return books.add(book);
        }
        return false;
    }
    public boolean removeBookFromCart(Book book) {
        if (book == null) return false;
        return bookIds.remove(Integer.valueOf(book.getId()));
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void addBook(int bookId) {
        bookIds.add(bookId);
    }

    public Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Iterable<Book> getBooks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
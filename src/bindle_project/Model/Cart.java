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
}
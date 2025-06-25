/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import bindle_project.Dao.BookDao;
import java.util.ArrayList;
import java.util.List;

public class BookModel {
    private BookDao bookDao = new BookDao();

    public List<Book> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return bookDao.searchBooks(query);
    }

    public boolean addBook(String title, String author, double price, String condition, int sellerId) {
        if (title == null || author == null || condition == null) {
            return false;
        }
        return bookDao.addBook(title, author, price, condition, sellerId);
    }

    public boolean markAsSold(int bookId) {
        return bookDao.markAsSold(bookId);
    }
}

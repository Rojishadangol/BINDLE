/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Cart {
    private List<Book> items;
    private int userId;

    public Cart(int userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book != null) {
            items.add(book);
        }
    }

    public List<Book> getItems() {
        return new ArrayList<>(items); // Return a copy to prevent external modification
    }

    public int getUserId() {
        return userId;
    }

    public boolean removeBook(Book book) {
        if (book != null) {
            return items.remove(book);
        }
        return false;
    }
}

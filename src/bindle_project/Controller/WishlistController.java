/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbps://nbsp/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbps://nbsp/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Model.WishlistModel;
import bindle_project.View.WishlistPanel;
import bindle_project.Model.Book;

/**
 *
 * @author acer
 */
public class WishlistController {
    private WishlistModel model;
    private WishlistPanel view;

    public WishlistController(WishlistModel model, WishlistPanel view) {
        this.model = model;
        this.view = view;
    }

    public void addBookToWishlist(Book book) {
        model.addBook(book);
        if (view != null) view.refreshWishlist(); // Safe check for null view
    }

    public boolean removeBookFromWishlist(Book book) {
        boolean success = model.removeBook(book);
        if (view != null) view.refreshWishlist(); // Safe check for null view
        return success;
    }
}

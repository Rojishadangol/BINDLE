/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Model.WishlistModel;
import bindle_project.View.WishlistPanel;
import java.awt.print.Book;

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
        view.refreshWishlist();
    }

    public void removeBookFromWishlist(Book book) {
        model.removeBook(book);
        view.refreshWishlist();
    }
}

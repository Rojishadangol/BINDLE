/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Dao.CartDao;
import bindle_project.Model.Cart;
import bindle_project.View.HomeScreen;
import bindle_project.Model.Book;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class CartController {
   private HomeScreen view;
    private CartDao cartDao;
    private int userId;

    public CartController(HomeScreen view, int userId) {
        this.view = view;
        this.cartDao = new CartDao();
        this.userId = userId;
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
    }

    public void addToCart(Book book) {
        if (book == null) {
            JOptionPane.showMessageDialog(view, "Cannot add null book to cart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            cartDao.addToCart(userId, book);
            int itemCount = cartDao.getCart(userId).getItems().size();
            view.updateCartDisplay(itemCount);
            JOptionPane.showMessageDialog(view, "Book added to cart! Total items: " + itemCount, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error adding book to cart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeFromCart(Book book) {
       if (book == null) {
        JOptionPane.showMessageDialog(view, "Cannot remove null book from cart.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        Cart cart = cartDao.getCart(userId);
        if (cart != null && cart.removeBook(book)) {
                int itemCount = cart.getItems().size();
                view.updateCartDisplay(itemCount);
            JOptionPane.showMessageDialog(view, "Book removed from cart! Total items: " + itemCount, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Book not found in cart.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Error removing book from cart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    public Cart getCartItems() {
        return cartDao.getCart(userId);
    }
}

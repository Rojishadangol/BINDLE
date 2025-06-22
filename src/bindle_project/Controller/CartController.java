/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Model.Cart;
import bindle_project.Model.Book;
import bindle_project.Model.WishlistModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;

public class CartController {
    private Cart cartModel;
    private JFrame view;

    public CartController(Cart model, JFrame view) {
        this.cartModel = model;
        this.view = view;
    }

    public boolean removeBookFromCart(Book book) {
        if (book == null || cartModel == null || cartModel.getConnection() == null) return false;
        return cartModel.removeBookFromCart(book);
    }

    public boolean moveToWishlist(Book book) {
        if (book == null || cartModel == null || cartModel.getConnection() == null) return false;
        try {
            // Remove from cart
            if (!removeBookFromCart(book)) return false;

            // Add to wishlist
            WishlistModel wishlistModel = new WishlistModel(cartModel.getUser(), cartModel.getConnection());
            String sql = "INSERT INTO wishlists (user_id, book_id) VALUES (?, ?)";
            PreparedStatement stmt = cartModel.getConnection().prepareStatement(sql);
            stmt.setInt(1, cartModel.getUser().getId());
            stmt.setInt(2, book.getId());
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error moving book to wishlist: " + e.getMessage());
            return false;
        }
    }
}
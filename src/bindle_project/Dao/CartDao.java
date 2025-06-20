/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Dao;

import bindle_project.Model.Book;
import bindle_project.Model.Cart;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author acer
 */
public class CartDao {
    private Map<Integer, Cart> carts = new HashMap<>();

    public void addToCart(int userId, Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Cart cart = carts.getOrDefault(userId, new Cart(userId));
        cart.addBook(book);
        carts.put(userId, cart);
    }

    public Cart getCart(int userId) {
        return carts.getOrDefault(userId, new Cart(userId));
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package testbindleprojecct.Model;

/**
 *
 * @author acer
 */
public class CartItem {
    private String id;
    private String cartId;
    private String bookId;
    private int quantity;

    public CartItem(String cartId, String bookId, int quantity) {
        this.cartId = cartId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getCartId() {
        return cartId;
    }

    public String getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }
}

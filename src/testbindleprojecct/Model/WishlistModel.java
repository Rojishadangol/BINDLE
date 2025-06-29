/*
 * Click https://www.nbsp.com/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://www.nbsp.com/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.Model;

import testbindleprojecct.Dao.WishlistDao;
import java.util.ArrayList;
import java.util.List;

public class WishlistModel {
    private User user;
    private WishlistDao wishlistDao;
    private List<Book> wishlistBooks;

    public WishlistModel(User user) {
        this.user = user;
        this.wishlistDao = new WishlistDao();
        this.wishlistBooks = new ArrayList<>();
        if (user != null) {
            loadWishlist();
        } else {
            System.out.println("❌ WishlistModel initialized with null user.");
        }
    }

    private void loadWishlist() {
        if (user == null) {
            System.out.println("❌ User is null, cannot load wishlist.");
            return;
        }

        try {
            wishlistBooks = wishlistDao.getWishlistBooks(user.getId());
            System.out.println("✅ Loaded " + wishlistBooks.size() + " items from wishlist for user ID: " + user.getId());
        } catch (Exception e) {
            System.out.println("❌ Error loading wishlist: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean addBookToWishlist(Book book) {
        if (book == null || user == null) {
            System.out.println("❌ Cannot add book to wishlist: Invalid parameters.");
            return false;
        }

        try {
            boolean success = wishlistDao.addBookToWishlist(user.getId(), book.getId());
            if (success) {
                wishlistBooks.add(book);
                System.out.println("✅ Added book ID " + book.getId() + " to wishlist for user ID: " + user.getId());
            }
            return success;
        } catch (Exception e) {
            System.out.println("❌ Error adding book to wishlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeBookFromWishlist(Book book) {
        if (book == null || user == null) {
            System.out.println("❌ Cannot remove book from wishlist: Invalid parameters.");
            return false;
        }

        try {
            boolean success = wishlistDao.removeBookFromWishlist(user.getId(), book.getId());
            if (success) {
                wishlistBooks.remove(book);
                System.out.println("✅ Removed book ID " + book.getId() + " from wishlist for user ID: " + user.getId());
            }
            return success;
        } catch (Exception e) {
            System.out.println("❌ Error removing book from wishlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    

    public List<Book> getWishlistBooks() {
        return new ArrayList<>(wishlistBooks); // Return a copy to prevent external modification
    }

    public User getUser() {
        return user;
    }

    // No need for close() since WishlistDao handles connection closing
}
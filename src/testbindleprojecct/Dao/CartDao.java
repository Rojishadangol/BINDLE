package testbindleprojecct.Dao;

import testbindleprojecct.Model.Cart;
import testbindleprojecct.Model.CartItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import testbindleprojecct.Database.DbConnection;
import testbindleprojecct.Database.MySqlConnection;

public class CartDao {
    public final DbConnection db = MySqlConnection.getInstance();

    private String getOrCreateCartId(int userId) throws SQLException {
        String query = "SELECT id FROM carts WHERE user_id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
        }
        String cartId = UUID.randomUUID().toString();
        String insertQuery = "INSERT INTO carts (id, user_id) VALUES (?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, cartId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
        return cartId;
    }

    public boolean addToCart(int userId, String bookId, int quantity) throws SQLException {
        String cartId = getOrCreateCartId(userId);
        String query = "INSERT INTO cart_items (cart_id, book_id, quantity) VALUES (?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cartId);
            stmt.setString(2, bookId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding to cart: " + e.getMessage());
            throw e;
        }
    }

    public List<CartItem> getCartItems(int userId) throws SQLException {
        String cartId = getOrCreateCartId(userId);
        String query = "SELECT id, cart_id, book_id, quantity FROM cart_items WHERE cart_id = ?";
        List<CartItem> items = new ArrayList<>();
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cartId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CartItem item = new CartItem(
                        rs.getString("cart_id"),
                        rs.getString("book_id"),
                        rs.getInt("quantity")
                    );
                    item.setId(String.valueOf(rs.getInt("id")));
                    items.add(item);
                }
            }
            return items;
        } catch (SQLException e) {
            System.err.println("Error retrieving cart items: " + e.getMessage());
            throw e;
        }
    }

    public boolean clearCart(int userId) throws SQLException {
        String cartId = getOrCreateCartId(userId);
        String query = "DELETE FROM cart_items WHERE cart_id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cartId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error clearing cart: " + e.getMessage());
            throw e;
        }
    }

    public boolean removeItemFromCart(String itemId) throws SQLException {
        String query = "DELETE FROM cart_items WHERE id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(itemId));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error removing item from cart: " + e.getMessage());
            throw e;
        }
    }

    public boolean updateItemQuantity(String itemId, int quantity) throws SQLException {
        String query = "UPDATE cart_items SET quantity = ? WHERE id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, Integer.parseInt(itemId));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating item quantity: " + e.getMessage());
            throw e;
        }
    }

    public Cart getCartByUserId(int userId) throws SQLException {
        String cartId = getOrCreateCartId(userId);
        Cart cart = new Cart(String.valueOf(userId));
        cart.setId(cartId);
        cart.setItems(getCartItems(userId));
        return cart;
    }

    public Cart getCartByUserId(String userId) throws SQLException {
        try {
            int id = Integer.parseInt(userId);
            return getCartByUserId(id);
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid user ID format: " + userId);
        }
    }
}

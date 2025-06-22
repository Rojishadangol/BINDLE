package dao;

import java.sql.*;

public class SecurePurchaseDAO {
    private Connection conn;

    public SecurePurchaseDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertPurchase(SecurePurchaseDAO purchase) throws SQLException {
        String sql = "INSERT INTO secure_purchases (buyer_id, seller_id, book_id, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, purchase.getBuyerId());
        stmt.setInt(2, purchase.getSellerId());
        stmt.setInt(3, purchase.getBookId());
        stmt.setString(4, purchase.getStatus());
        return stmt.executeUpdate() > 0;
    }
}

package bindle_project.Dao;

import java.sql.*;
import bindle_project.Model.SecurePurchaseData;

public class SecurePurchaseDao {
    private Connection conn;

    public SecurePurchaseDao(Connection conn) {
        this.conn = conn;
    }

    public boolean insertPurchase(SecurePurchaseData purchase) throws SQLException {
        String sql = "INSERT INTO secure_purchases (buyer_id, seller_id, book_id, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, purchase.getBuyerId());
        stmt.setInt(2, purchase.getSellerId());
        stmt.setInt(3, purchase.getBookId());
        stmt.setString(4, purchase.getStatus());
        return stmt.executeUpdate() > 0;
    }
}

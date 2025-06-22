package bindle_project.Controller;

import bindle_project.Dao.SecurePurchaseDao;
import bindle_project.Model.SecurePurchaseData;
import dao.SecurePurchaseDAO;
//import model.SecurePurchase;
import java.sql.SQLException;

public class PurchaseController {
    private SecurePurchaseDao dao;

    public PurchaseController(SecurePurchaseDao dao) {
        this.dao = dao;
    }

    public boolean makeSecurePurchase(int buyerId, int sellerId, int bookId) {
        SecurePurchaseData purchase = new SecurePurchaseData(buyerId, sellerId, bookId, "Pending");
        try {
            return dao.insertPurchase(purchase);
            
        } catch (SQLException e) {
            System.out.println("Error inserting purchase: " + e.getMessage());
            return false;
        }
    }
}

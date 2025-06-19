/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Dao.PurchaseDao;
import bindle_project.View.HomeScreen;

/**
 *
 * @author acer
 */
public class PurchaseController {
    private HomeScreen view;
    private PurchaseDao purchaseDao;
    private int userId;

    public PurchaseController(HomeScreen view, int userId) {
        this.view = view;
        this.purchaseDao = new PurchaseDao();
        this.userId = userId;
    }

    public void purchaseBook(int bookId) {
        purchaseDao.purchaseBook(bookId, userId);
    }
}

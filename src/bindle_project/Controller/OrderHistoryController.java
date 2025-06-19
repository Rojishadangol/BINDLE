/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

/**
 *
 * @author acer
 */
import bindle_project.Model.Order;
import bindle_project.Model.OrderHistory;
import bindle_project.View.OrderHistoryView;
import java.util.List;

public class OrderHistoryController {
    private OrderHistory model;
    private OrderHistoryView view;

    public OrderHistoryController() {
        model = OrderHistory.loadFromFile();
        view = new OrderHistoryView(this);
    }

    public OrderHistoryView getView() {
        return view;
    }

    public void loadOrders() {
        List<Order> orders = model.getOrders();
        view.displayOrders(orders);
        if (!orders.isEmpty()) {
            view.displayOrderDetails(orders.get(0));  // Show first order details by default
        }
    }

    public void showOrderDetails(int index) {
        List<Order> orders = model.getOrders();
        if (index >= 0 && index < orders.size()) {
            view.displayOrderDetails(orders.get(index));
        }
    }

    // Method to add a new order to history (call after checkout)
    public void addOrder(Order order) {
        model.addOrder(order);
        loadOrders();
    }
}


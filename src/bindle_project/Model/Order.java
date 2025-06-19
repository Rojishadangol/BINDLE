/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author acer
 */
public class Order {
   

    private Map<Book, Integer> items;
    private String shippingAddress;
    private String paymentInfo;
    private double totalPrice;
    private String status;  // e.g., "Processing", "Shipped", "Delivered"
    private LocalDateTime orderDate;

    public Order(Map<Book, Integer> items, String shippingAddress, String paymentInfo, double totalPrice) {
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.paymentInfo = paymentInfo;
        this.totalPrice = totalPrice;
        this.status = "Processing";  // default initial status
        this.orderDate = LocalDateTime.now();
    }

    // Getters and setters
    public Map<Book, Integer> getItems() { return items; }
    public String getShippingAddress() { return shippingAddress; }
    public String getPaymentInfo() { return paymentInfo; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getOrderDate() { return orderDate; }
}



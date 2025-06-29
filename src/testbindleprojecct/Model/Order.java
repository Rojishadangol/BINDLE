package testbindleprojecct.Model;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private int userId;
    private double totalAmount;
    private Date orderDate;
    private String status;
    private List<OrderItem> items;

    public Order(String id, int userId, double totalAmount, Date orderDate, String status, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}


package testbindleprojecct.Model;

public class OrderItem {
    private int id;
    private String orderId;
    private String bookId;
    private int quantity;
    private double price;

    // No-args constructor
    public OrderItem() {
    }

    // Parameterized constructor
    public OrderItem(int id, String orderId, String bookId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
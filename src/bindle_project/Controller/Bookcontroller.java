package bindle_project.Controller;



import bindle_project.Dao.UserDao;
import bindle_project.Model.Search;



import java.util.List;

public class Bookcontroller {
    private int id;
    private String title;
    private String author;
    private double price;
    private String condition;
    private int sellerId;
    private String status;
    private String description;

    public Bookcontroller(int id, String title, String author, double price, String condition, int sellerId, String status) {
        setId(id); // Use setter for validation
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setCondition(condition);
        setSellerId(sellerId);
        setStatus(status);
        this.description = "No description available"; // Default
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getCondition() { return condition; }
    public int getSellerId() { return sellerId; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }

    // Setters with Validation
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative");
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title.trim();
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) throw new IllegalArgumentException("Author cannot be null or empty");
        this.author = author.trim();
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void setCondition(String condition) {
        if (condition == null || condition.trim().isEmpty()) throw new IllegalArgumentException("Condition cannot be null or empty");
        this.condition = condition.trim();
    }

    public void setSellerId(int sellerId) {
        if (sellerId < 0) throw new IllegalArgumentException("Seller ID cannot be negative");
        this.sellerId = sellerId;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) throw new IllegalArgumentException("Status cannot be null or empty");
        this.status = status.trim();
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "No description available" : description.trim();
    }

    // toString for debugging and display
    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', price=" + price +
                ", condition='" + condition + "', sellerId=" + sellerId + ", status='" + status +
                "', description='" + description + "'}";
    }
}

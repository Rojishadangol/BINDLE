/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import java.util.Objects;

/**
 *
 * @author acer
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String condition;
    private int sellerId;
    private String status;
    private String description;

    public Book(int id, String title, String author, double price, String condition, int sellerId, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.condition = condition;
        this.sellerId = sellerId;
        this.status = status;
        this.description = "No description available"; // Default value
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

    // Setter for description (optional, if needed)
    public void setDescription(String description) {
        this.description = description != null ? description : "No description available";
    }

    @Override
    public String toString() {
        return title + " by " + author + " (Rs. " + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
               Double.compare(book.price, price) == 0 &&
               sellerId == book.sellerId &&
               Objects.equals(title, book.title) &&
               Objects.equals(author, book.author) &&
               Objects.equals(condition, book.condition) &&
               Objects.equals(status, book.status) &&
               Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price, condition, sellerId, status, description);
    }
}
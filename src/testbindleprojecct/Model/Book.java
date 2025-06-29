/*
 * Click nfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.Model;

/**
 *
 * @author acer
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private double price;
    private String imageUrl;
    private String description;
    private boolean sold;
    private Integer sellerId; // Use Integer to handle null values

    public Book(String title, String author, double price, String imageUrl) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(double price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setDescription(String description) { this.description = description; }
    public void setSold(boolean sold) { this.sold = sold; }
    public void setSellerId(Integer sellerId) { this.sellerId = sellerId; }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
    public boolean isSold() { return sold; }
    public Integer getSellerId() { return sellerId; }
}
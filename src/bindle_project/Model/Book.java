/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String condition;
    private String availability;
    private int sellerId;

    public Book(int id, String title, String author, String condition, String availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.condition = condition;
        this.availability = availability;
        this.sellerId = sellerId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getCondition() { return condition; }
    public String getAvailability() { return availability; }
    public int getSellerId() { return sellerId; }
}
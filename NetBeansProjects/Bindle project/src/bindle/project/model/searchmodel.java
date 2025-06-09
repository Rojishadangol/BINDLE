/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle.project.model;

/**
 *
 * @author ushadhakal
 */
public class searchmodel {
    private int id;
    private String title, author, category, condition;
    private double price;

    public searchmodel(int id, String title, String author, String category, double price, String condition) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.condition = condition;
    }

    
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getCondition() { return condition; }
}

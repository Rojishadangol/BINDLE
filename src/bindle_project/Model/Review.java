/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

/**
 *
 * @author acer
 */
import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String bookISBN;  // unique book identifier
    private int rating;       // 1 to 5 stars
    private String comment;
    private LocalDateTime date;

    public Review(String username, String bookISBN, int rating, String comment) {
        this.username = username;
        this.bookISBN = bookISBN;
        this.rating = rating;
        this.comment = comment;
        this.date = LocalDateTime.now();
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getBookISBN() { return bookISBN; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getDate() { return date; }

    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setDate(LocalDateTime date) { this.date = date; }
}


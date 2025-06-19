/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

/**
 *
 * @author acer
 */
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReviewManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Review> reviews;
    private static final String FILE_NAME = "reviews.ser";

    public ReviewManager() {
        reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        // Remove existing review by same user for same book
        reviews.removeIf(r -> r.getUsername().equals(review.getUsername()) && r.getBookISBN().equals(review.getBookISBN()));
        reviews.add(review);
        saveToFile();
    }

    public void deleteReview(String username, String bookISBN) {
        reviews.removeIf(r -> r.getUsername().equals(username) && r.getBookISBN().equals(bookISBN));
        saveToFile();
    }

    public List<Review> getReviewsForBook(String bookISBN) {
        return reviews.stream()
                .filter(r -> r.getBookISBN().equals(bookISBN))
                .collect(Collectors.toList());
    }

    public Optional<Review> getUserReviewForBook(String username, String bookISBN) {
        return reviews.stream()
                .filter(r -> r.getUsername().equals(username) && r.getBookISBN().equals(bookISBN))
                .findFirst();
    }

    public double getAverageRating(String bookISBN) {
        List<Review> bookReviews = getReviewsForBook(bookISBN);
        if (bookReviews.isEmpty()) return 0.0;
        double sum = bookReviews.stream().mapToInt(Review::getRating).sum();
        return sum / bookReviews.size();
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ReviewManager loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ReviewManager) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ReviewManager();
        }
    }
}


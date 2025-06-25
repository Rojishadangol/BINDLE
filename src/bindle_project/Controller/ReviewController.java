/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

/**
 *
 * @author acer
 */
import bindle_project.Model.Review;
import bindle_project.Model.ReviewManager;
import bindle_project.View.ReviewView;
import java.util.List;

public class ReviewController {
    private ReviewManager model;
    private ReviewView view;

    public ReviewController(String username, String bookISBN) {
        model = ReviewManager.loadFromFile();
        view = new ReviewView(this, username, bookISBN);
    }

    public ReviewView getView() {
        return view;
    }

    public List<Review> getReviews(String bookISBN) {
        return model.getReviewsForBook(bookISBN);
    }

    public double getAverageRating(String bookISBN) {
        return model.getAverageRating(bookISBN);
    }

    public Review getUserReview(String username, String bookISBN) {
        return model.getUserReviewForBook(username, bookISBN).orElse(null);
    }

    public void submitReview(String username, String bookISBN, int rating, String comment) {
        Review review = new Review(username, bookISBN, rating, comment);
        model.addReview(review);
        view.loadReviews();
        view.showMessage("Review submitted successfully!", false);
    }

    public void deleteReview(String username, String bookISBN) {
        model.deleteReview(username, bookISBN);
        view.loadReviews();
        view.showMessage("Review deleted.", false);
    }
}


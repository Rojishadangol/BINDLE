/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.View.description;
import bindle_project.View.Listing;
import bindle_project.View.description;
import javax.swing.JFrame;

public class NavigationController {
    private JFrame currentFrame; // Store the current frame to close it

    // Constructor to set the current frame
    public NavigationController(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void goToDescriptionScreen(String bookTitle) {
        try {
            // Create and show the Description screen
            description descriptionScreen = new description(bookTitle);
            descriptionScreen.setVisible(true);

            // Close the current frame (e.g., Listing) if it exists
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        } catch (Exception e) {
            System.err.println("Error navigating to Description screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goToCartScreen() {
        try {
            // Implement navigation to CartScreen
            // Assuming CartScreen is another JFrame class
            // CartScreen cartScreen = new CartScreen();
            // cartScreen.setVisible(true);
            // if (currentFrame != null) {
            //     currentFrame.dispose();
            // }
            System.out.println("CartScreen navigation not implemented yet.");
        } catch (Exception e) {
            System.err.println("Error navigating to CartScreen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goToListingScreen() {
        try {
            // Implement navigation to Listing screen
            Listing listingScreen = new Listing();
            listingScreen.setVisible(true);

            // Close the current frame if it exists
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        } catch (Exception e) {
            System.err.println("Error navigating to Listing screen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
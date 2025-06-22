/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project;
import bindle_project.View.Listing;
import bindle_project.Controller.ListingController;
/**
 *
 * @author ushadhakal
 */
public class Main {
    public static void main(String[] args) {
        Listing listingView = new Listing(); // your Listing UI
        listingView.setVisible(true);        // show UI
        new ListingController(listingView);  // attach controller
    }
    
    
}

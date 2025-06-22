/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;
import bindle_project.View.Listing;
import bindle_project.View.CartScreen;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author ushadhakal
 */
public class ListingController {
    private Listing listingView;

    public ListingController(Listing view) {
        this.listingView = view;
        initController();
    }

    private void initController() {
        listingView.LblMyList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openCartScreen();
            }
        });
    }

    private void openCartScreen() {
        CartScreen cartScreen = new CartScreen();
        cartScreen.setVisible(true);
    }
}


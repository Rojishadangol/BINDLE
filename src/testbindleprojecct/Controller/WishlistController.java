package testbindleprojecct.Controller;

import testbindleprojecct.Model.WishlistModel;
import testbindleprojecct.View.WishListView;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistController {
    private WishListView view;
    private WishlistModel model;

    public WishlistController(WishListView view, WishlistModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        System.out.println("Attaching listeners to WishListView buttons");
        javax.swing.JButton removeButton = view.getRemoveButton();
        if (removeButton != null) {
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedBookId = view.getSelectedBookId();
                    if (selectedBookId != null) {
                        for (testbindleprojecct.Model.Book book : model.getWishlistBooks()) {
                            if (book.getId().equals(selectedBookId)) {
                                if (model.removeBookFromWishlist(book)) {
                                    view.refreshWishlist(model.getWishlistBooks());
                                    JOptionPane.showMessageDialog(view, "Book removed from wishlist.");
                                } else {
                                    JOptionPane.showMessageDialog(view, "Failed to remove book.");
                                }
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Please select a book to remove.");
                    }
                }
            });
        } else {
            System.out.println("❌ Remove button is null in WishListView.");
        }

        view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                new NavigationController(null).showHomeView(); // Temporary, improve with navController
            }
        });
    }

    public WishListView getView() {
        return view;
    }
}
package testbindleprojecct.Controller;

import testbindleprojecct.Database.MySqlConnection;
import testbindleprojecct.Model.User;
import testbindleprojecct.Model.WishlistModel;
import testbindleprojecct.View.HomeView;
import java.sql.Connection;
import java.sql.SQLException;

public class HomeController {
    private final HomeView view;
    private final User currentUser;
    private final NavigationController navController;
    private final Connection dbConnection;

    public HomeController(HomeView view, User currentUser) {
        if (currentUser == null) {
            throw new IllegalArgumentException("Current user cannot be null");
        }
        this.view = view;
        this.currentUser = currentUser;
        this.navController = new NavigationController(currentUser);
        try {
            this.dbConnection = MySqlConnection.getInstance().openConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage());
        }
        initListeners();
        view.setVisible(true);
    }

    private void initListeners() {
        System.out.println("Attaching listeners to HomeView buttons for user ID: " + currentUser.getId());
        view.getSearchButton().addActionListener(e -> {
            navController.showSearchView("");
            view.dispose();
        });
        view.getSellButton().addActionListener(e -> {
            navController.showSellView();
            view.dispose();
        });
        view.getHeartButton().addActionListener(e -> {
            navController.showWishListView();
            view.dispose();
        });
        view.getCartButton().addActionListener(e -> {
            navController.showCartView();
            view.dispose();
        });
        view.getProfileButton().addActionListener(e -> {
            navController.showProfileView();
            view.dispose();
        });
        view.getLogoutButton().addActionListener(e -> {
            navController.showLoginView();
            view.dispose();
        });
        view.getAvailableBooksButton().addActionListener(e -> {
            navController.showListView();
            view.dispose();
        });
    }

    private void search() {
        navController.showSearchView(""); // Can be removed if handled in listener
        view.dispose();
    }

    private void logout() {
        navController.showLoginView();
        view.dispose();
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }
}
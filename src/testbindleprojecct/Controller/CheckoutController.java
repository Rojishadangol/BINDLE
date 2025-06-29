package testbindleprojecct.Controller;

import testbindleprojecct.Dao.BookDao;
import testbindleprojecct.Dao.CartDao;
import testbindleprojecct.Dao.OrderDao;
import testbindleprojecct.Dao.UserDao;
import testbindleprojecct.Model.Book;
import testbindleprojecct.Model.CartItem;
import testbindleprojecct.Model.Order;
import testbindleprojecct.Model.OrderItem;
import testbindleprojecct.Model.User;
import testbindleprojecct.View.CheckoutView;
import testbindleprojecct.View.OrderConfirmationView;
import testbindleprojecct.Controller.Mail.SMTPSMailSender;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class CheckoutController {
    private CheckoutView view;
    private CartDao cartDao;
    private BookDao bookDao;
    private OrderDao orderDao;
    private UserDao userDao;
    private User currentUser;
    private List<CartItem> cartItems;
    private List<Book> books;
    private double total;
    private final NavigationController navController;

    public CheckoutController(CheckoutView view, User currentUser, NavigationController navController) {
        this.view = view;
        this.cartDao = new CartDao();
        this.bookDao = new BookDao();
        this.orderDao = new OrderDao();
        this.userDao = new UserDao();
        this.currentUser = currentUser;
        this.cartItems = new ArrayList<>();
        this.books = new ArrayList<>();
        this.total = 0.0;
        this.navController = navController;
        loadCartData();
        view.refreshCart(cartItems, books, total);
        initController();
    }

    private void loadCartData() {
        if (currentUser != null && currentUser.getId() != 0) {
            try {
                cartItems = cartDao.getCartItems(currentUser.getId());
                books.clear();
                total = 0.0;
                for (CartItem item : cartItems) {
                    Book book = bookDao.getBookById(item.getBookId());
                    if (book != null) {
                        books.add(book);
                        total += book.getPrice() * item.getQuantity();
                    } else {
                        System.err.println("Book not found for bookId: " + item.getBookId());
                    }
                }
                System.out.println("Loaded " + cartItems.size() + " cart items, total: $" + total);
            } catch (SQLException e) {
                System.err.println("Error loading cart data: " + e.getMessage());
                JOptionPane.showMessageDialog(view, "Failed to load cart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void initController() {
        System.out.println("Attaching listeners to CheckoutView buttons for user ID: " + currentUser.getId());
        view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                navController.showCartView();
            }
        });

        view.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showConfirmationDialog();
            }
        });
    }

    private void showConfirmationDialog() {
        System.out.println("Confirm purchase initiated for user ID: " + currentUser.getId());
        String address = view.getAddress().trim();
        String phone = view.getPhone().trim();
        String instructions = view.getInstructions().trim();
        String buyerName = view.getBuyerName().trim();
        String buyerEmail = view.getBuyerEmail().trim();
        boolean isCod = view.isCodSelected();

        if (isCod) {
            if (address.isEmpty() || phone.isEmpty() || buyerName.isEmpty() || buyerEmail.isEmpty()) {
                view.showError("Address, phone, buyer name, and email are required for cash on delivery.");
                System.out.println("❌ Missing required fields for COD.");
                return;
            }
        } else {
            String paymentAmountStr = view.getPaymentAmount();
            double paymentAmount;
            try {
                paymentAmount = Double.parseDouble(paymentAmountStr.trim());
                if (paymentAmount < total || buyerName.isEmpty() || buyerEmail.isEmpty()) {
                    view.showError("Payment amount $" + paymentAmount + " is less than total $" + total + 
                                  ", or buyer details are missing.");
                    System.out.println("❌ Insufficient payment or missing buyer details.");
                    return;
                }
            } catch (NumberFormatException e) {
                view.showError("Invalid payment amount. Please enter a number, and provide buyer details.");
                System.out.println("❌ Invalid payment amount: " + paymentAmountStr);
                return;
            }
        }

        StringBuilder message = new StringBuilder("Order Summary\n");
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            Book book = books.get(i);
            message.append(book.getTitle()).append(" (x").append(item.getQuantity()).append(") - $")
                   .append(String.format("%.2f", book.getPrice() * item.getQuantity())).append("\n");
        }
        message.append("Total: $").append(String.format("%.2f", total)).append("\n");
        if (isCod) {
            message.append("Delivery: Cash on Delivery to ").append(address).append("\n");
            message.append("Phone: ").append(phone).append("\n");
            if (!instructions.isEmpty()) message.append("Instructions: ").append(instructions).append("\n");
            message.append("Buyer: ").append(buyerName).append(" (").append(buyerEmail).append(")");
        } else {
            message.append("Payment: $").append(view.getPaymentAmount()).append("\n");
            message.append("Buyer: ").append(buyerName).append(" (").append(buyerEmail).append(")");
        }
        message.append("\n");

        int result = JOptionPane.showConfirmDialog(view, message.toString(), "Confirm Purchase",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            processOrder(buyerName, buyerEmail, address, phone, instructions, isCod);
        }
    }

    private void processOrder(String buyerName, String buyerEmail, String address, String phone, String instructions, boolean isCod) {
        try {
            double orderTotal = 0.0;
            List<OrderItem> orderItems = new ArrayList<>();
            String orderId = UUID.randomUUID().toString();

            for (int i = 0; i < cartItems.size(); i++) {
                CartItem cartItem = cartItems.get(i);
                Book book = books.get(i);
                orderTotal += book.getPrice() * cartItem.getQuantity();
                orderItems.add(new OrderItem(0, orderId, cartItem.getBookId(), cartItem.getQuantity(), book.getPrice()));
            }

            Order order = new Order(orderId, currentUser.getId(), orderTotal, new Date(), "Pending", orderItems);
            if (orderDao.createOrder(order)) {
                for (Book book : books) {
                    notifySeller(book, orderId, buyerName, buyerEmail, address, phone, instructions, isCod);
                }
                cartDao.clearCart(currentUser.getId());
                OrderConfirmationView confirmationView = new OrderConfirmationView(orderId, orderTotal, 
                    isCod ? "Cash on Delivery to " + address : "Paid Online");
                confirmationView.setVisible(true);
                view.dispose();
            } else {
                view.showError("Failed to process order.");
            }
        } catch (SQLException e) {
            view.showError("Database error processing order: " + e.getMessage());
            System.err.println("Error processing order: " + e.getMessage());
        }
    }

    private void notifySeller(Book book, String orderId, String buyerName, String buyerEmail, String address, 
                             String phone, String instructions, boolean isCod) {
        try {
            if (book.getSellerId() == null) {
                System.err.println("No seller ID for book ID " + book.getId());
                return;
            }
            User seller = userDao.getUserById(book.getSellerId());
            if (seller != null && seller.getEmail() != null) {
                String subject = "Order Confirmation Notification";
                StringBuilder body = new StringBuilder("Hello " + seller.getName() + ",\n\n");
                body.append("Your book titled '").append(book.getTitle()).append("' has been purchased and the order is confirmed.\n");
                body.append("Order ID: ").append(orderId).append("\n");
                body.append("Quantity: ").append(getQuantityForBook(book.getId())).append("\n");
                body.append("Buyer: ").append(buyerName).append(" (").append(buyerEmail).append(")\n");
                if (isCod) {
                    body.append("Delivery: Cash on Delivery to ").append(address).append("\n");
                    body.append("Phone: ").append(phone).append("\n");
                    if (!instructions.isEmpty()) body.append("Instructions: ").append(instructions).append("\n");
                } else {
                    body.append("Payment: Online\n");
                }
                body.append("Please arrange delivery for this item.\n\nThank you!");

                if (SMTPSMailSender.sendMail(seller.getEmail(), subject, body.toString())) {
                    bookDao.markAsSold(book.getId(), book.getSellerId());
                    System.out.println("Notification sent to seller ID " + book.getSellerId() + " at " + seller.getEmail());
                } else {
                    System.err.println("Failed to send email to seller ID " + book.getSellerId());
                    view.showError("Failed to notify seller for book: " + book.getTitle());
                }
            } else {
                System.err.println("Seller not found for book ID " + book.getId());
                view.showError("Seller not found for book: " + book.getTitle());
            }
        } catch (SQLException e) {
            System.err.println("Error marking book as sold: " + e.getMessage());
            view.showError("Failed to mark book as sold: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Failed to send notification to seller: " + e.getMessage());
            view.showError("Failed to notify seller: " + e.getMessage());
        }
    }

    private int getQuantityForBook(String bookId) {
        for (CartItem item : cartItems) {
            if (item.getBookId().equals(bookId)) {
                return item.getQuantity();
            }
        }
        return 1; // Default to 1 if not found
    }

    public CheckoutView getView() {
        return view;
    }
}

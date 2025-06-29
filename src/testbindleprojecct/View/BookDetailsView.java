     package testbindleprojecct.View;

     import testbindleprojecct.Model.Book;
     import testbindleprojecct.Model.User;
     import javax.swing.*;
     import java.awt.*;
     import javax.imageio.ImageIO;
     import java.net.URL;
     import java.awt.image.BufferedImage;

     public class BookDetailsView extends JFrame {
         private JLabel titleLabel;
         private JTextArea descriptionArea;
         private JLabel authorLabel;
         private JLabel priceLabel;
         private JLabel sellerLabel;
         private JLabel sellerPhoneLabel;
         private JLabel imageLabel;
         private JButton addToCartButton;
         private JButton addToWishlistButton;
         private JButton backButton;

         public BookDetailsView(Book book) {
             initComponents(book);
             setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             setLocation(0, 0);
             setTitle("Bindle - Book Details");
             setSize(600, 450);
         }

         private void initComponents(Book book) {
             setLayout(new BorderLayout(10, 10));

             JPanel mainPanel = new JPanel(new BorderLayout());
             mainPanel.setBackground(Color.WHITE);

             JPanel detailsPanel = new JPanel(new GridLayout(6, 1, 10, 10));
             titleLabel = new JLabel("Title: " + book.getTitle());
             titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
             authorLabel = new JLabel("Author: " + (book.getAuthor() != null ? book.getAuthor() : "Not Available"));
             authorLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
             priceLabel = new JLabel("Price: $" + String.format("%.2f", book.getPrice()));
             priceLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
             sellerLabel = new JLabel("Seller: Not Available");
             sellerLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
             sellerPhoneLabel = new JLabel("Seller Phone: Not Available");
             sellerPhoneLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
             descriptionArea = new JTextArea(book.getDescription() != null ? book.getDescription() : "No description available");
             descriptionArea.setEditable(false);
             descriptionArea.setLineWrap(true);
             descriptionArea.setWrapStyleWord(true);
             descriptionArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
             JScrollPane scrollPane = new JScrollPane(descriptionArea);

             detailsPanel.add(titleLabel);
             detailsPanel.add(authorLabel);
             detailsPanel.add(priceLabel);
             detailsPanel.add(sellerLabel);
             detailsPanel.add(sellerPhoneLabel);
             detailsPanel.add(scrollPane);

             imageLabel = new JLabel(loadImage(book.getImageUrl(), "Book Image"));
             imageLabel.setPreferredSize(new Dimension(150, 200));

             mainPanel.add(detailsPanel, BorderLayout.CENTER);
             mainPanel.add(imageLabel, BorderLayout.WEST);

             add(mainPanel, BorderLayout.CENTER);

             JPanel buttonPanel = new JPanel(new FlowLayout());
             addToCartButton = new JButton("Add to Cart");
             addToWishlistButton = new JButton("Add to Wishlist");
             backButton = new JButton("Back");
             buttonPanel.add(addToCartButton);
             buttonPanel.add(addToWishlistButton);
             buttonPanel.add(backButton);
             add(buttonPanel, BorderLayout.SOUTH);
         }

         private ImageIcon loadImage(String path, String description) {
             if (path == null || path.isEmpty()) {
                 return new ImageIcon(new BufferedImage(150, 200, BufferedImage.TYPE_INT_ARGB));
             }
             try {
                 URL url = new URL(path).toURI().toURL(); // Handle external URL
                 BufferedImage img = ImageIO.read(url);
                 if (img != null) {
                     return new ImageIcon(img);
                 }
             } catch (Exception e) {
                 System.err.println("Image not found: " + path + " for " + description);
             }
             // Fallback to classpath resource or default image
             URL resourceUrl = getClass().getResource(path);
             if (resourceUrl != null) {
                 return new ImageIcon(resourceUrl);
             }
             return new ImageIcon(new BufferedImage(150, 200, BufferedImage.TYPE_INT_ARGB));
         }

         public void updateDetails(Book book, User seller) {
             titleLabel.setText("Title: " + book.getTitle());
             authorLabel.setText("Author: " + (book.getAuthor() != null ? book.getAuthor() : "Not Available"));
             priceLabel.setText("Price: $" + String.format("%.2f", book.getPrice()));
             sellerLabel.setText("Seller: " + (seller != null && seller.getName() != null ? seller.getName() : "Not Available"));
             sellerPhoneLabel.setText("Seller Phone: " + (seller != null && seller.getPhone() != null ? seller.getPhone() : "Not Available"));
             descriptionArea.setText(book.getDescription() != null ? book.getDescription() : "No description available");
         }

         public JButton getAddToCartButton() {
             return addToCartButton;
         }

         public JButton getAddToWishlistButton() {
             return addToWishlistButton;
         }

         public void showError(String message) {
             JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
         }

         public void showSuccess(String message) {
             JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
         }

         public JButton getBackButton() {
             return backButton;
         }
     }
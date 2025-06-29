package testbindleprojecct.View;

import testbindleprojecct.Model.Book;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.DefaultListModel;
import java.net.URL;

/**
 *
 * @author acer
 */
public class ListView extends JFrame {
    private JList<Book> bookList;
    private JButton addToCartButton;
    private JButton viewDetailsButton;
    private JButton cartButton;
    private JButton homeButton;
    private JButton searchButton;
    private JButton heartButton;
    private JLabel titleLabel;
    private JLabel subTitleLabel;

    public ListView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Bindle - Book List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Bindle");
        titleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
        subTitleLabel = new JLabel("Reselling academic and non-academic books");
        subTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 10));
        JPanel logoPanel = new JPanel();
        ImageIcon logoIcon = loadImage("/testbindleprojecct/images/newlogo_1.png", "Logo");
        if (logoIcon != null) {
            logoPanel.add(new JLabel(logoIcon));
        } else {
            logoPanel.add(new JLabel("No Logo"));
            System.err.println("Logo image not found: /images/newlogo_1.png");
        }
        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(subTitleLabel, BorderLayout.SOUTH);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        searchButton = new JButton(loadImage("/testbindleprojecct/images/SearchIcon.png", "Search"));
heartButton = new JButton(loadImage("/testbindleprojecct/images/mylist.png", "Heart Icon"));
cartButton = new JButton(loadImage("/testbindleprojecct/images/CART.png", "Cart Icon"));
homeButton = new JButton(loadImage("/testbindleprojecct/images/homeButtonImage.png", "Home Icon"));
        navPanel.add(searchButton);
        navPanel.add(heartButton);
        navPanel.add(cartButton);
        navPanel.add(homeButton);
        headerPanel.add(navPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);
        
        // In initComponents(), after creating buttons:
Dimension buttonSize = new Dimension(40, 40); // Adjust as needed
searchButton.setPreferredSize(buttonSize);
heartButton.setPreferredSize(buttonSize);
cartButton.setPreferredSize(buttonSize);
homeButton.setPreferredSize(buttonSize);

searchButton.setToolTipText("Search");
homeButton.setToolTipText("Home");
// etc.
        // Book List
        bookList = new JList<>();
        bookList.setCellRenderer(new BookListRenderer());
        JScrollPane scrollPane = new JScrollPane(bookList);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        viewDetailsButton = new JButton("View Details");
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(new Color(255, 0, 51));
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(addToCartButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Helper method to load images with fallback and debugging
    private ImageIcon loadImage(String path, String description) {
        URL resourceUrl = getClass().getResource(path);
        System.out.println("Attempting to load resource: " + path + " (Class: " + getClass().getName() + ")");
        if (resourceUrl == null) {
            System.err.println("Resource not found in classpath: " + path + " for " + description);
            return new ImageIcon(new BufferedImage(80, 100, BufferedImage.TYPE_INT_ARGB)); // Fallback to blank image
        }
        ImageIcon icon = new ImageIcon(resourceUrl);
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Failed to load " + description + " image from: " + path);
            return new ImageIcon(new BufferedImage(80, 100, BufferedImage.TYPE_INT_ARGB));
        }
        return icon;
    }

    // Custom renderer for JList to display book details with photo
    private class BookListRenderer extends JPanel implements ListCellRenderer<Book> {
        private JLabel photoLabel;
        private JLabel textLabel;
        private ImageIcon defaultIcon;

        public BookListRenderer() {
            setLayout(new BorderLayout(10, 0));
            photoLabel = new JLabel();
            photoLabel.setPreferredSize(new Dimension(80, 100)); // Max size
            textLabel = new JLabel();
            textLabel.setVerticalAlignment(JLabel.TOP);
            add(photoLabel, BorderLayout.WEST);
            add(textLabel, BorderLayout.CENTER);

            // Load default image (e.g., a placeholder)
            defaultIcon = loadImage("/testbindleprojecct/images/default.jpg.png", "Default Image");
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index,
                boolean isSelected, boolean cellHasFocus) {
            // Load and set the photo
            String imageUrl = book.getImageUrl();
            ImageIcon icon = defaultIcon; // Default to placeholder
            if (imageUrl != null && !imageUrl.isEmpty()) {
                ImageIcon originalIcon = loadImage(imageUrl, "Book Image");
                if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    Image img = originalIcon.getImage();
                    int width = Math.min(80, img.getWidth(null));
                    int height = Math.min(100, img.getHeight(null));
                    if (width < 80 || height < 100) {
                        float scale = Math.min(80.0f / width, 100.0f / height);
                        width = (int) (width * scale);
                        height = (int) (height * scale);
                    }
                    icon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
                }
            }
            photoLabel.setIcon(icon);

            // Set text details with HTML formatting
            textLabel.setText("<html><b>" + book.getTitle() + "</b><br>" + book.getAuthor() + "<br>Rs. " +
                    String.format("%.2f", book.getPrice()) + "</html>");

            // Handle selection
            setOpaque(true);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                textLabel.setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                textLabel.setForeground(list.getForeground());
            }

            return this;
        }
    }

    public void setBooks(List<Book> books) {
        if (bookList == null) {
            throw new IllegalStateException("bookList is not initialized");
        }
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : books) {
            model.addElement(book);
        }
        bookList.setModel(model);
    }

    public Book getSelectedBook() {
        return bookList != null ? bookList.getSelectedValue() : null;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getAddToCartButton() {
        return addToCartButton;
    }

    public JButton getViewDetailsButton() {
        return viewDetailsButton;
    }

    public JButton getCartButton() {
        return cartButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getHeartButton() {
        return heartButton;
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> new ListView().setVisible(true));
    }
}
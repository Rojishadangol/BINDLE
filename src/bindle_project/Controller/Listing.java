package Controller;

import Model.Book;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Listing extends JFrame {

    JPanel mainPanel = new JPanel(new GridLayout(0, 5, 10, 10));
    Bookcontroller controller = new Bookcontroller();

    public Listing() {
        setTitle("Bindle Bookstore");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        loadBooks();
        setVisible(true);
    }

    private void loadBooks() {
        List<Book> books = controller.fetchBooks();

        for (Book book : books) {
            JPanel bookCard = new JPanel();
            bookCard.setLayout(new BoxLayout(bookCard, BoxLayout.Y_AXIS));

            JLabel title = new JLabel("<html><b>" + book.getTitle() + "</b></html>");
            JLabel author = new JLabel("by " + book.getAuthor());
            JLabel price = new JLabel("Rs. " + book.getPrice());

            JButton addToCartBtn = new JButton("Add to Cart");
            addToCartBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Added to cart: " + book.getTitle());
            });

            bookCard.add(title);
            bookCard.add(author);
            bookCard.add(price);
            bookCard.add(addToCartBtn);
            bookCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            mainPanel.add(bookCard);
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new Listing();
    }
}

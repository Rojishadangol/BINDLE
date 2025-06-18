package bindle_project.Model;

public class List {
    private int id;
    private String title;
    private String author;
    private double price;
    private String imagePath;

    public List(int id, String title, String author, double price, String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imagePath = imagePath;
    }
}

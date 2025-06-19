/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

/**
 *
 * @author acer
 */
import java.io.*;
import java.util.*;

public class OrderHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Order> orders;

    private static final String FILE_NAME = "order_history.ser";

    public OrderHistory() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        saveToFile();
    }

    public List<Order> getOrders() {
        return orders;
    }

    // Save orders to file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load orders from file
    public static OrderHistory loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (OrderHistory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new OrderHistory();  // Return empty if file not found
        }
    }
}

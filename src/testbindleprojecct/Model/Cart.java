/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class Cart {
    private String id;
    private String userId;
    private List<CartItem> items;

    public Cart(String userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }
}


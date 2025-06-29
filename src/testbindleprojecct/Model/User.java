/*
 * Click https://www.nbsp.com/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://www.nbsp.com/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testbindleprojecct.Model;

public class User {
    private int id;
    private String email;
    private String name;
    private boolean verified;
    private int phone; // Stored as int, but returned as String for display

    public User(int id, String email, String name, boolean verified) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.verified = verified;
    }

    // Getters
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public boolean isVerified() { return verified; }
    public String getPhone() { return String.valueOf(phone); } // Convert int to String

    // Setters
    public void setId(int id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setVerified(boolean verified) { this.verified = verified; }
    public void setPhone(int phone) { this.phone = phone; }
}
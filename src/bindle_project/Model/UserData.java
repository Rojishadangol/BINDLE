/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

public class UserData {
    private int id;
    private String email;
    private String name;
    private boolean verified;

    public UserData(int id, String email, String name, boolean verified) {
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
}

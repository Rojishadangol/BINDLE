/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import java.util.Objects;

/**
 *
 * @author acer
 */
public class User {
    private int id;
    private String email;
    private String password; // Should be hashed in production and not exposed
    private String name;
    private boolean verified;
    private String verificationToken; // Added for email verification

    public User(int id, String email, String password, String name, boolean verified) {
        this.id = id;
        this.email = email;
        this.password = password; // For internal use only, should be hashed
        this.name = name;
        this.verified = verified;
        this.verificationToken = null; // Default to null, set during registration
    }

    public User(int id, String email, String name, boolean verified, String verificationToken) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.verified = verified;
        this.verificationToken = verificationToken;
        this.password = null; // Password not needed if fetched separately
    }

    // Getters
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; } // Remove or secure in production
    public String getName() { return name; }
    public boolean isVerified() { return verified; }
    public String getVerificationToken() { return verificationToken; }

    // Setters (for internal use)
    public void setVerificationToken(String verificationToken) { this.verificationToken = verificationToken; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
               verified == user.verified &&
               Objects.equals(email, user.email) &&
               Objects.equals(password, user.password) &&
               Objects.equals(name, user.name) &&
               Objects.equals(verificationToken, user.verificationToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, verified, verificationToken);
    }
}
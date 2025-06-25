/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Model;

import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private boolean verified;
    private String verificationToken;

    public User(int id, String email, String password, String name, boolean verified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.verified = verified;
        this.verificationToken = null;
    }

    public User(int id, String email, String name, boolean verified, String verificationToken) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.verified = verified;
        this.verificationToken = verificationToken;
        this.password = null;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public boolean isVerified() { return verified; }
    public String getVerificationToken() { return verificationToken; }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

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
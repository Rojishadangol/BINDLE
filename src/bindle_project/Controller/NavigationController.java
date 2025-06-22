/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;
import bindle_project.View.CartScreen;
/**
 *
 * @author ushadhakal
 */
public class NavigationController {
    public void goToCartScreen() {
        CartScreen cartScreen = new CartScreen(currentUser);
        cartScreen.setVisible(true);
    }
    
}

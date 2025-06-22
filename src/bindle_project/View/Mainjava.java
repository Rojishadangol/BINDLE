/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.View;

import bindle_project.Controller.updatecontroller;

/**
 *
 * @author diyan
 */
public class Mainjava {
     public static void main(String[] args) {
        UpdatePassword view = new UpdatePassword();
        updatecontroller Controller = new updatecontroller(view);
        view.setVisible(true); // Make the UI visible
    }
}

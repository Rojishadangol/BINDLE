/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testbindleprojecct;


import testbindleprojecct.Controller.RegisterController;
import testbindleprojecct.View.RegisterView;

/**
 *
 * @author acer
 */
public class TestBindleProjecct {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RegisterView view=new RegisterView();
    RegisterController controller=new RegisterController();
    controller.open();
    
    }
    
}

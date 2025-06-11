
import bindle_project.Controller.RegisterController;
import bindle_project.View.RegisterView;
import bindle_project.View.search;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author acer
 */
public class Bindle_project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         RegisterView view=new RegisterView();
    RegisterController controller=new RegisterController(view);
    controller.open();
    javax.swing.SwingUtilities.invokeLater(() -> new search().setVisible(true));
    }
    
}

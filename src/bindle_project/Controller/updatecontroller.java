/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;



import bindle_project.View.UpdatePassword;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 *
 * @author diyan
 */
public class updatecontroller {
   
    
    private boolean isPasswordVisible1 = false;
    private boolean isPasswordVisible2 = false;
    private boolean isPasswordVisible3 = false;

    private final UpdatePassword updateView;

    public updatecontroller(UpdatePassword updateView){
        this.updateView = updateView;

        updateView.showpasswordButtonListener1(new ShowPasswordListener1());
        updateView.showpasswordButtonListener2(new ShowPasswordListener2());
        updateView.showpasswordButtonListener3(new ShowPasswordListener3());
    }

    class ShowPasswordListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible1 = !isPasswordVisible1;
            updateView.tooglePaawordField1(isPasswordVisible1);
        }
    }

    class ShowPasswordListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible2 = !isPasswordVisible2;
            updateView.tooglePaawordField2(isPasswordVisible2);
        }
    }

    class ShowPasswordListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible3 = !isPasswordVisible3;
            updateView.tooglePaawordField3(isPasswordVisible3);
        }
    }
}






       
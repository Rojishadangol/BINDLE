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
   
       private boolean isPasswordVisible =false;
       private final UpdatePassword updateView;
       public updatecontroller(UpdatePassword updateView){
       this.updateView= updateView;
       updateView.showpasswordButtonListener(new ShowPasswordListener());
       updateView.showpasswordButtonListener1(new ShowPasswordListener());
       updateView.showpasswordButtonListener2(new ShowPasswordListener());


       }
       class ShowPasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isPasswordVisible= !isPasswordVisible;
            updateView.tooglePaawordField1(isPasswordVisible);
                        updateView.tooglePaawordField2(isPasswordVisible);
                                    updateView.tooglePaawordField3(isPasswordVisible);
        }
        



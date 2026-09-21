
package com.mmdc.motor_ph_portal;


import javax.swing.WindowConstants;
import javax.swing.JFrame;


public class Motor_PH_Portal extends JFrame {

    public static void main(String[] args) {
  
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
    }
    
    public void init() {
        setTitle ("Motor PH Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
}


package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.User;
import javax.swing.JOptionPane;


public class Admin_Class extends User {
 
    public Admin_Class(String employeeID, String firstName, String lastName, String email, String birthday, 
                String addressID, String street, String barangay, String city, String province,
                String postalcode,  String phoneNumber, String sssNum, 
                String philHealthNum, String tinNum, String pagibigNum, 
                String username, String password) {
        super(employeeID, firstName, lastName, birthday, 
                 addressID, street, barangay, city, province,
                 postalcode, email, phoneNumber, sssNum, 
                 philHealthNum, tinNum, pagibigNum, 
                 username, password);
        
    }
   
    
    @Override
    public void login(String username, String password) {
    JOptionPane.showMessageDialog(null, "Employee login successful for: " + firstName + lastName, 
                                  "Login Successful", JOptionPane.INFORMATION_MESSAGE);

    // Redirect to the Admin Access Employee Portal
    EmployeePortal empPortal = new EmployeePortal(); 
    empPortal.setVisible(true);
    }

    @Override
    public String getRole() {
        return "Admin";
    } 

    @Override
    public void payrollCalc(String employeeId) {
    }

  
}

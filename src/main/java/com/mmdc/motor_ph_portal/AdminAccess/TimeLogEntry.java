
package com.mmdc.motor_ph_portal.AdminAccess;

 
public class TimeLogEntry {
     private String firstName;
     private String lastName;
     private String date;
     private String timeIn;
     private String timeOut;

    public TimeLogEntry(String firstName, String lastName, String date, String timeIn, String timeOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }
        // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }
    

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }
    
    
}


package com.mmdc.motor_ph_portal;


public class LeaveRecord {
     private int leaveId;
     private String employeeId;
     private String firstName;
     private String lastName;
     private String startDate;
     private String endDate;
     private String leaveType;
     private String status;

    public LeaveRecord(int leaveId, String employeeId, String firstName, String lastName, 
                       String startDate, String endDate, String leaveType, String status) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = status;
    }

    // Getters for each field
    public int getLeaveId() { return leaveId; }
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getLeaveType() { return leaveType; }
    public String getStatus() { return status; }

  
}

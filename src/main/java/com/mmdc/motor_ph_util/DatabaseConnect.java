package com.mmdc.motor_ph_util;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.AdminAccess.Payroll;
import com.mmdc.motor_ph_portal.AdminAccess.PayrollCalculation;
import com.mmdc.motor_ph_portal.LeaveRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public abstract class DatabaseConnect {

    protected String url;
    protected String user;
    protected String password;
    protected String employee_id;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public DatabaseConnect() {
        this.url = "jdbc:mysql://localhost:3306/payrollsystem_db?useSSL=false";
        this.user = "root";
        this.password = "@dm1nistr4tor";
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    // CONNECTION TO SQL
    public Connection connect() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Trying to connect");

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Established Successfully");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "MySQL Driver not found." + ex.getMessage());
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DatabaseConnect.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            JOptionPane.showMessageDialog(null, "Unable to make connection with DB: " + ex.getMessage());
        }
        return conn; // Return the connection object

    }
    // EMPLOYEE DETAILS KEY RELEASED
    public Admin_Class getEmployeeDetails(String employeeId) {
        Admin_Class employee = null;

        try {
            conn = connect();
            String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.birthday, " +
                     "e.phone, e.sss_num, e.philhealth_num, e.tin, e.pagibig_num, " +
                     "a.street, a.barangay, a.city, a.province, a.postalcode " + 
                     "FROM employee e " +
                     "JOIN address a ON e.address_id = a.address_id " +
                     "WHERE e.employee_id = ?";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, employeeId);
            rs = pst.executeQuery();

            if (rs.next()) {
                employee = new Admin_Class(
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("birthday"),
                        null,
                        rs.getString("street"),
                        rs.getString("barangay"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postalcode"),
                        rs.getString("phone"),
                        rs.getString("sss_num"),
                        rs.getString("philhealth_num"),
                        rs.getString("tin"),
                        rs.getString("pagibig_num"),
                        null,null) {};

            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Consider logging this instead
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle closing exceptions
            }
        }
        return employee;
    }
    
    public int getNextEmployeeId() {
    String sql = "SELECT MAX(employee_id) FROM employee";
    try (Connection conn = connect(); 
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(1) + 1; // Next available Employee ID
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 10001; // Start from a default base if table is empty
}
    
    //EMPLOYEE ID AUTO INCREMENT
    public int addEmployeeAndReturnId(Admin_Class employee, int addressId) throws SQLException {
    String sql = "INSERT INTO employee (first_name, last_name, email, birthday, " +
                 "address_id, phone, sss_num, philhealth_num, tin, pagibig_num) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        pst.setString(1, employee.getFirstName());
        pst.setString(2, employee.getLastName());
        pst.setString(3, employee.getEmail());
        pst.setString(4, employee.getBirthday());
        pst.setInt(5, addressId);
        pst.setString(6, employee.getPhoneNumber());
        pst.setString(7, employee.getSssNum());
        pst.setString(8, employee.getPhilHealthNum());
        pst.setString(9, employee.getTinNum());
        pst.setString(10, employee.getPagibigNum());

        pst.executeUpdate();

        try (ResultSet rs = pst.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    }
    throw new SQLException("Failed to insert employee and retrieve ID.");
}

    public int addAddressAndReturnId(String street, String barangay, 
                                 String city, String province, String postalcode) throws SQLException {
    String sql = "INSERT INTO address (street, barangay, city, province, postalcode) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        pst.setString(1, street);
        pst.setString(2, barangay);
        pst.setString(3, city);
        pst.setString(4, province);
        pst.setString(5, postalcode);
        
        pst.executeUpdate();
        
        try (ResultSet rs = pst.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    }
    throw new SQLException("Failed to insert address and retrieve ID.");
}

    // UPDATE BTN FOR EMPLOYEE PROFILE
    public void updateEmployee(Admin_Class employeeId) {
        Admin_Class employee = employeeId;
        String employeeSql = "UPDATE employee SET last_name = ?, phone = ? WHERE employee_id = ?";
        String addressSql = "UPDATE address SET street = ?, barangay = ?, city = ?, province = ?, "
                + "postalcode = ? WHERE address_id = (SELECT address_id FROM employee WHERE employee_id = ?)";
    
    try {
        conn = connect();
        
        // Update employee table
         try (PreparedStatement employeePst = conn.prepareStatement(employeeSql)) {
                employeePst.setString(1, employee.getLastName()); // Set last name
                employeePst.setString(2, employee.getPhoneNumber()); // Set phone number
                employeePst.setString(3, employee.getEmployeeID()); // Set employee ID
                employeePst.executeUpdate();
            }
              
            // Update address table
            try (PreparedStatement aPst = conn.prepareStatement(addressSql)) {
                aPst.setString(1, employee.getStreet());
                aPst.setString(2, employee.getBarangay());
                aPst.setString(3, employee.getCity());
                aPst.setString(4, employee.getProvince());
                aPst.setString(5, employee.getPostalcode());
                aPst.setString(6, employee.getEmployeeID()); // Set employee ID for address update
                
                aPst.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Employee data successfully updated!");
            
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback on error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Error updating employee: " + e.getMessage());
            e.printStackTrace(); // For debugging
            
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //DELETE BTN FOR EMPLOYEE PROFILE
    public void deleteEmployee(String employeeId) {
        try {
            conn = connect();
            conn.setAutoCommit(false); // Start transaction

            // First, get the address_id before deleting the employee
            int addressId = 0;
            String getAddressIdSql = "SELECT address_id FROM employee WHERE employee_id = ?";
            try (PreparedStatement getAddressPst = conn.prepareStatement(getAddressIdSql)) {
                getAddressPst.setString(1, employeeId);
                ResultSet rs = getAddressPst.executeQuery();
                if (rs.next()) {
                    addressId = rs.getInt("address_id");
                } else {
                    // Employee not found
                    conn.rollback();
                    JOptionPane.showMessageDialog(null, "No employee found with ID: " + employeeId);
                    return;
                }
            }

            // Delete from employee table first (due to foreign key constraint)
            String deleteEmployeeSql = "DELETE FROM employee WHERE employee_id = ?";
            int employeeRowsAffected = 0;
            try (PreparedStatement employeePst = conn.prepareStatement(deleteEmployeeSql)) {
                employeePst.setString(1, employeeId);
                employeeRowsAffected = employeePst.executeUpdate();
            }

            // Delete the associated address if it exists
            int addressRowsAffected = 0;
            if (addressId > 0) {
                String deleteAddressSql = "DELETE FROM address WHERE address_id = ?";
                try (PreparedStatement addressPst = conn.prepareStatement(deleteAddressSql)) {
                    addressPst.setInt(1, addressId);
                    addressRowsAffected = addressPst.executeUpdate();
                }
            }

            // Check if both deletions were successful
            if (employeeRowsAffected > 0) {
                conn.commit(); // Commit the transaction
                String message = "Employee Profile Deleted!";
                if (addressRowsAffected > 0) {
                    message += " (Including associated address)";
                }
                JOptionPane.showMessageDialog(null, message);
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Failed to delete employee.");
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback on error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Database error while deleting employee: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback on error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Error deleting employee: " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Reset auto-commit
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // PAYROLL DETAILS 
      public PayrollCalculation getPayrollDetails(String employeeId, String selectedPayPeriod, int monthNumber) {
    PayrollCalculation payroll = null;

    try {
        conn = connect();

        String cutOff = "";
        String sql = "";

        if (selectedPayPeriod.equals("1st Cut-off")) {
            cutOff = "1st";
            sql = "SELECT * FROM employee_payslip_1st_cutoff_view WHERE employee_id = ? AND cut_off = ? AND MONTH(period_start) = ?";
        } else if (selectedPayPeriod.equals("2nd Cut-off")) {
            cutOff = "2nd";
            sql = "SELECT * FROM employee_payslip_2nd_cutoff_view WHERE employee_id = ? AND cut_off = ? AND MONTH(period_start) = ?";
        } else {
            // Invalid selection
            return null;
        }

        pst = conn.prepareStatement(sql);
        pst.setString(1, employeeId);
        pst.setString(2, cutOff);
        pst.setInt(3, monthNumber);

        rs = pst.executeQuery();

        if (rs.next()) {
            payroll = new PayrollCalculation(
                rs.getString("employee_id"),
                rs.getString("employee_name"),
                rs.getDouble("daily_rate"),
                rs.getDouble("days_worked"),
                rs.getDouble("rice_allowance"),
                rs.getDouble("phone_allowance"),
                rs.getDouble("clothing_allowance"),
                rs.getDouble("sss"),
                rs.getDouble("philhealth"),
                rs.getDouble("pagibig"),
                rs.getDouble("total_allowance"),
                rs.getDouble("total_deductions"),
                rs.getDouble("gross_income"),
                rs.getDouble("withholding_tax"),
                rs.getDouble("take_home_pay"),
                rs.getString("pay_date"),
                null, null, null, null
            );
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return payroll;
}


    public ArrayList<LeaveRecord> userList(String employeeID) {
    ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
    String sql = "SELECT lr.leave_id, lr.employee_id, e.first_name, e.last_name, " +
                 "lr.start_date, lr.end_date, lr.leave_type, lr.status " +
                 "FROM leave_records lr JOIN employee e ON lr.employee_id = e.employee_id " +
                 "WHERE lr.employee_id = ?";

    try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, employeeID); // ✅ Set employee ID

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                        rs.getInt("leave_id"),
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("leave_type"),
                        rs.getString("status")
                );
                leaveRecords.add(record);
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    return leaveRecords;
}

    public ArrayList<LeaveRecord> leaveMngUserList() {
    ArrayList<LeaveRecord> leaveRecords = new ArrayList<>();
    String sql = "SELECT lr.leave_id, lr.employee_id, e.first_name, e.last_name, " +
                 "lr.start_date, lr.end_date, lr.leave_type, lr.status " +
                 "FROM leave_records lr JOIN employee e ON lr.employee_id = e.employee_id";

    try (Connection conn = connect(); 
         PreparedStatement pst = conn.prepareStatement(sql); 
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            LeaveRecord record = new LeaveRecord(
                    rs.getInt("leave_id"),
                    rs.getString("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getString("leave_type"),
                    rs.getString("status")
            );
            leaveRecords.add(record);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    return leaveRecords;
}
    
    public int getNextLeaveId() {
    String sql = "SELECT MAX(leave_id) FROM leave_records"; // Adjust the table name as necessary
    try (Connection conn = connect(); 
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(1) + 1; // Return the next Leave ID
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
    }
    return 1; // Return 1 if no records exist
}
    
    public boolean addLeaveRequest(LeaveRecord leaveRequest) {
    String sql = "INSERT INTO leave_records (leave_id, employee_id, start_date, end_date, leave_type, status) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = connect(); 
         PreparedStatement pst = conn.prepareStatement(sql)) {
        
        // Set parameters
        pst.setInt(1, leaveRequest.getLeaveId()); // Assuming leaveId is an int
        pst.setString(2, leaveRequest.getEmployeeId());
        pst.setString(3, leaveRequest.getStartDate());
        pst.setString(4, leaveRequest.getEndDate());
        pst.setString(5, leaveRequest.getLeaveType());
        pst.setString(6, leaveRequest.getStatus());
        // Execute the update
        pst.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception for debugging
        return false;
    } catch (Exception e) {
        e.printStackTrace(); // Log any other exceptions
        return false;
    }
}

    public boolean deleteLeaveRecord(LeaveRecord leaveRecord) {
    String sql = "DELETE FROM leave_records WHERE leave_id = ?";
    
    try (Connection conn = connect(); 
         PreparedStatement pst = conn.prepareStatement(sql)) {
        
        pst.setInt(1, leaveRecord.getLeaveId()); // Assuming leaveId is an int
        int rowsAffected = pst.executeUpdate();
        return rowsAffected > 0; // Return true if a record was deleted
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception for debugging
        return false;
    }
}

  
   public boolean updateLeaveRecord(LeaveRecord leaveRecord) {
    String status = leaveRecord.getStatus();
    int leaveNum = leaveRecord.getLeaveId();

    String sql = "UPDATE leave_records SET status = ? WHERE leave_id = ?";

    try (Connection conn = connect(); 
         PreparedStatement pst = conn.prepareStatement(sql)) {
        
        // Set parameters
        pst.setString(1, status);
        pst.setInt(2, leaveNum);

        // Execute the update and return true if successful
        return pst.executeUpdate() > 0; 
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception for debugging
        return false; // Indicate failure
    }
}

   public boolean logTimeIn(String employeeId, String date, String time) {
    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement checkPst = null;
    ResultSet rs = null;

    try {
        // Connect to the database
        conn = connect();

        // 1. Check if employee already timed in today
        String checkSql = "SELECT COUNT(*) FROM attendance_record WHERE employee_id = ? AND date = ? AND time_in IS NOT NULL";
        checkPst = conn.prepareStatement(checkSql);
        checkPst.setString(1, employeeId);
        checkPst.setString(2, date);
        rs = checkPst.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            System.out.println("Employee has already timed in today.");
            return false;
        }

        // 2. Insert new time-in record
        String insertSql = "INSERT INTO attendance_record (employee_id, date, time_in) VALUES (?, ?, ?)";
        pst = conn.prepareStatement(insertSql);
        pst.setString(1, employeeId);
        pst.setString(2, date);  // Format: yyyy-MM-dd
        pst.setString(3, time);  // Format: HH:mm:ss

        int rowsAffected = pst.executeUpdate();
        System.out.println("Time In - Rows affected: " + rowsAffected);

        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("SQL Error in logTimeIn: " + e.getMessage());
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (checkPst != null) checkPst.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
   
   public boolean logTimeOut(String employeeId, String date, String time) {
    Connection conn = null;
    PreparedStatement checkPst = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        // Establish the database connection
        conn = connect();
            String checkSql = "SELECT COUNT(*) FROM attendance_record WHERE employee_id = ? AND date = ? AND time_in IS NOT NULL AND time_out IS NULL";
        checkPst = conn.prepareStatement(checkSql);
        checkPst.setString(1, employeeId);
        checkPst.setString(2, date);
        rs = checkPst.executeQuery();

        if (rs.next() && rs.getInt(1) == 0) {
            System.out.println("No valid time-in record found for today, or already timed out");
            return false;
        }

        // 2. Update the latest matching record (optional ORDER BY for safety)
        String updateSql = "UPDATE attendance_record SET time_out = ? " +
                           "WHERE employee_id = ? AND date = ? AND time_out IS NULL " +
                           "ORDER BY time_in DESC LIMIT 1";

        pst = conn.prepareStatement(updateSql);
        pst.setString(1, time);
        pst.setString(2, employeeId);
        pst.setString(3, date);

        int rowsAffected = pst.executeUpdate();
        System.out.println("Time Out - Rows affected: " + rowsAffected);

        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("SQL Error in logTimeOut: " + e.getMessage());
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (checkPst != null) checkPst.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
     public void show_table(DefaultTableModel attendanceTable) {
       
        try {
            // Establish the database connection
            conn = connect();

            // SQL query to fetch attendance records
            String sql = "SELECT ar.attendance_id, ar.employee_id, ar.date, e.first_name, e.last_name, ar.time_in, ar.time_out, ar.status " +
                        "FROM attendance_record ar " +
                        "JOIN employee e ON ar.employee_id = e.employee_id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            // Clear existing rows in the table model
            attendanceTable.setRowCount(0);

            // Process the result set and populate the table
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("attendance_id"));
                row.add(rs.getString("employee_id"));
                row.add(rs.getString("date"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                row.add(rs.getString("time_in"));
                row.add(rs.getString("time_out"));
                row.add(rs.getString("status"));
                
                attendanceTable.addRow(row);
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            JOptionPane.showMessageDialog(null, "Error fetching attendance records: " + ex.getMessage());
        } finally {
            // Close resources in the finally block
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + ex.getMessage());
            }
            
        }
    }
   
    public String getEmployeeIdByEmail(String email) {
        String employeeID = null;
        try (Connection conn = connect()) {
            String sql = "SELECT employee_id FROM employee WHERE email = ?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    employeeID = rs.getString("employee_id");
                }
            }
        } catch (Exception e) {
            // Handle exceptions
        }
        return employeeID;
    }    
    
   public Admin_Class getEmployeeByUsername(String username) {
    Admin_Class employee = null;

    String sql = "SELECT " +
                 "e.employee_id, e.first_name, e.last_name, e.email, e.birthday, " +
                 "e.address_id, a.street, a.barangay, a.city, a.province, a.postalcode, " +
                 "e.phone, e.sss_num, e.philhealth_num, e.tin, e.pagibig_num, " +
                 "p.username, p.password " +
                 "FROM employee e " +
                 "JOIN address a ON e.address_id = a.address_id " +
                 "JOIN position p ON e.employee_id = p.employee_id " +
                 "WHERE p.username = ?";

    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            employee = new Admin_Class(
                rs.getString("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("birthday"),
                rs.getString("address_id"),
                rs.getString("street"),
                rs.getString("barangay"),
                rs.getString("city"),
                rs.getString("province"),
                rs.getString("postalcode"),
                rs.getString("phone"),
                rs.getString("sss_num"),
                rs.getString("philhealth_num"),
                rs.getString("tin"),
                rs.getString("pagibig_num"),
                rs.getString("username"),
                rs.getString("password")
            ) {};
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return employee;
}
    public String getUsernameByEmployeeID(String employeeID) {
    String username = null;
    String sql = "SELECT username FROM position WHERE employee_id = ?";
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, employeeID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return username;
}
     public boolean updatePassword(String employeeID, String newPassword) {
        boolean isUpdated = false;
        String updateQuery = "UPDATE position SET password = ? WHERE employee_id = ?";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {
            pst.setString(1, newPassword);
            pst.setString(2, employeeID);
            int rowsAffected = pst.executeUpdate();
            isUpdated = rowsAffected > 0;
        } catch (Exception e) {
            // Handle exceptions
        }
        return isUpdated;
    }    
       
     public boolean verifyCredentials(String username, String password) {
    String sql = "SELECT * FROM position WHERE username = ? AND password = ?";
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        return rs.next(); // Returns true if match found
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}

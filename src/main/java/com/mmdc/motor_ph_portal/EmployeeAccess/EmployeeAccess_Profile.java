package com.mmdc.motor_ph_portal.EmployeeAccess;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.LeaveRecord;
import com.mmdc.motor_ph_portal.Login;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.WindowConstants;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class EmployeeAccess_Profile extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    DatabaseConnect dbConnect = new DatabaseConnect() {};
    private String username;
    private String employeeID;

     
    public EmployeeAccess_Profile() {
        initComponents();
        
        setTitle ("Motor PH Employee Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        loadLeaveRecords();
        loadPayslipList();
        time();
        date();
      
        setupTabChangeListener();
        
    }
    
    public EmployeeAccess_Profile(String employeeID, String firstName, String lastName) {
        initComponents();
        
        setTitle ("Motor PH Employee Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        this.employeeID = employeeID;
        
        loadEmployeeData(employeeID);
        loadLeaveRecords();
        loadPayslipList();
        time();
        date();
        setupTabChangeListener();
    }
    
    public EmployeeAccess_Profile(String username) {
        initComponents();
        
        setTitle ("Motor PH Employee Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        this.username = username;
        
        // Load employee data by username and populate fields
        loadAndFillEmployeeDataByUsername(username);
        loadPayslipList();
        loadLeaveRecords();
        time();
        date();
        setupTabChangeListener();
        
    }
    
    
    private void loadEmployeeData(String employeeID) {
        try {
            conn = dbConnect.connect();
            Admin_Class employee = dbConnect.getEmployeeDetails(employeeID);
            if (employee != null) {
                // Set employee ID
                id_field.setText(employee.getEmployeeID());
                
                // Set name fields
                firstName_field.setText(employee.getFirstName());
                lastname_field.setText(employee.getLastName());
                timeLogFirstNameField.setText(employee.getFirstName());
                timeLogLastNameField.setText(employee.getLastName());
                leaveFirstName_field.setText(employee.getFirstName());
                leaveLastName_field.setText(employee.getLastName());
                payslipFirstName_field.setText(employee.getFirstName());
                payslipLastName_field.setText(employee.getFirstName());
                
                // Set employee name display
                String fullName = employee.getFirstName() + " " + employee.getLastName();
                empName.setText(fullName);
                
                // Set other profile fields
                bday_field.setText(employee.getBirthday() != null ? employee.getBirthday() : "");
                street_field.setText(employee.getStreet()!= null ? employee.getStreet() : "");
                brgy_field.setText(employee.getBarangay()!= null ? employee.getBarangay() : "");
                city_field.setText(employee.getCity()!= null ? employee.getCity() : "");
                province_field.setText(employee.getProvince()!= null ? employee.getProvince() : "");
                postalcode_field.setText(employee.getPostalcode()!= null ? employee.getPostalcode() : "");
                contact_field.setText(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "");
                email_field.setText(employee.getEmail() != null ? employee.getEmail() : "");
                sss_field.setText(employee.getSssNum() != null ? employee.getSssNum() : "");
                philhealth_field.setText(employee.getPhilHealthNum() != null ? employee.getPhilHealthNum() : "");
                pagibig_field.setText(employee.getPagibigNum() != null ? employee.getPagibigNum() : "");
                tin_field.setText(employee.getTinNum() != null ? employee.getTinNum() : "");
            } else {
                JOptionPane.showMessageDialog(null, "No employee found with ID: " + employeeID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   public void loadAndFillEmployeeDataByUsername(String username) {
    if (username == null || username.trim().isEmpty()) {
        return; // skip empty input
    }

    try {
        Admin_Class employee = dbConnect.getEmployeeByUsername(username);

        if (employee != null) {
            this.employeeID = employee.getEmployeeID();
            
            // Set employee ID
            id_field.setText(employee.getEmployeeID());
            
            // Set name fields
            firstName_field.setText(employee.getFirstName());
            lastname_field.setText(employee.getLastName());
            timeLogFirstNameField.setText(employee.getFirstName());
            timeLogLastNameField.setText(employee.getLastName());
            leaveFirstName_field.setText(employee.getFirstName());
            leaveLastName_field.setText(employee.getLastName());
            payslipFirstName_field.setText(employee.getFirstName());
            payslipLastName_field.setText(employee.getLastName());

            // Set employee name display
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            empName.setText(fullName);
            
            // Set other profile fields
            bday_field.setText(employee.getBirthday() != null ? employee.getBirthday() : "");
            street_field.setText(employee.getStreet()!= null ? employee.getStreet() : "");
            brgy_field.setText(employee.getBarangay()!= null ? employee.getBarangay() : "");
            city_field.setText(employee.getCity()!= null ? employee.getCity() : "");
            province_field.setText(employee.getProvince()!= null ? employee.getProvince() : "");
            postalcode_field.setText(employee.getPostalcode()!= null ? employee.getPostalcode() : "");
            contact_field.setText(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "");
            email_field.setText(employee.getEmail() != null ? employee.getEmail() : "");
            sss_field.setText(employee.getSssNum() != null ? employee.getSssNum() : "");
            philhealth_field.setText(employee.getPhilHealthNum() != null ? employee.getPhilHealthNum() : "");
            pagibig_field.setText(employee.getPagibigNum() != null ? employee.getPagibigNum() : "");
            tin_field.setText(employee.getTinNum() != null ? employee.getTinNum() : "");
        } else {
            JOptionPane.showMessageDialog(null, "No employee found with username: " + username);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading employee: " + e.getMessage());
    }
}

    
    public final void time(){
    DateTimeFormatter times = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime now =LocalDateTime.now();
    time.setText(times.format(now));          
    }
     public final void date(){
    DateTimeFormatter dates = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now =LocalDateTime.now();
    date.setText(dates.format(now));
    }
    
    public void autoFillLeaveId() {
    try {
        int nextLeaveId = dbConnect.getNextLeaveId(); // Call your existing method
        
        // Assuming you have a JTextField for leave ID display
        if (leaveNum_field != null) {
            leaveNum_field.setText(String.valueOf(nextLeaveId));
            leaveNum_field.setEditable(false); // Make it read-only since it's auto-generated
        }
        
        // Alternative: If you're using a JLabel to display the leave ID
        // leaveIdLabel.setText("Leave ID: " + nextLeaveId);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error generating leave ID: " + e.getMessage());
        // Set a default value in case of error
         if (leaveNum_field != null) {
            leaveNum_field.setText("50042"); 
        }
    }
}
     
    public void loadLeaveRecords() {
        autoFillLeaveId();
        // Only load leave records for the logged-in employee
        if (this.employeeID == null || this.employeeID.trim().isEmpty()) {
            return; // Skip if no employee ID is set
        }
        
        try {
            conn = dbConnect.connect();
            String sql = "SELECT lr.leave_id, lr.employee_id, e.first_name, e.last_name, lr.start_date, lr.end_date, lr.leave_type, lr.status " +
                         "FROM leave_records lr JOIN employee e ON lr.employee_id = e.employee_id " +
                         "WHERE lr.employee_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.employeeID);
            rs = pst.executeQuery();
            
            DefaultTableModel leaveTableModel = (DefaultTableModel) leaveTable.getModel();
            leaveTableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("leave_id"));
                row.add(rs.getString("employee_id"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                row.add(rs.getString("start_date"));
                row.add(rs.getString("end_date"));
                row.add(rs.getString("leave_type"));
                row.add(rs.getString("status"));
                leaveTableModel.addRow(row);
            } 
            
            refreshList();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading leave records: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }
    public void refreshLeaveId() {
    autoFillLeaveId();
}
    public ArrayList refreshList() {
        // Only refresh leave records for the logged-in employee
        if (this.employeeID == null || this.employeeID.trim().isEmpty()) {
            return new ArrayList(); // Return empty list if no employee ID is set
        }
        
        try {
            conn = dbConnect.connect();
            String sql = "SELECT lr.leave_id, lr.employee_id, e.first_name, e.last_name, lr.start_date, lr.end_date, lr.leave_type, lr.status " +
                         "FROM leave_records lr JOIN employee e ON lr.employee_id = e.employee_id " +
                         "WHERE lr.employee_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.employeeID);
            rs = pst.executeQuery();
            
            DefaultTableModel leaveTableModel = (DefaultTableModel) leaveTable.getModel();
            leaveTableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("leave_id"));
                row.add(rs.getString("employee_id"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                row.add(rs.getString("start_date"));
                row.add(rs.getString("end_date"));
                row.add(rs.getString("leave_type"));
                row.add(rs.getString("status"));
                leaveTableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error refreshing leave records: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                // ignore
            }
        }
        
        return new ArrayList(); // Return empty list for compatibility
    }
    
    public ArrayList loadTimeLog() {
        ArrayList timeLog = new ArrayList();
        if (this.employeeID == null || this.employeeID.trim().isEmpty()) {
            return timeLog;
        }
        try {
            conn = dbConnect.connect();
            String sql = "SELECT e.first_name, e.last_name, ar.date, ar.time_in, ar.time_out " +
                         "FROM attendance_record ar " +
                         "JOIN employee e ON ar.employee_id = e.employee_id " +
                         "WHERE ar.employee_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, this.employeeID);
            rs = pst.executeQuery();
            
            DefaultTableModel timeLogTableModel = (DefaultTableModel)attendance_table.getModel();
            timeLogTableModel.setRowCount(0);
            while(rs.next()){
                Vector<String> v=new Vector<>();
                v.add(rs.getString("first_name"));
                v.add(rs.getString("last_name"));
                v.add(rs.getString("date"));
                v.add(rs.getString("time_in"));
                v.add(rs.getString("time_out"));
                timeLogTableModel.addRow(v);
            }    
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading time log: " + ex.getMessage());
        }  finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                // ignore
            }
        }
        return timeLog;
     }
     
     public void refreshTimeLog() {
         loadTimeLog();
     }
    
    private void setupTabChangeListener() {
        tab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (tab.getSelectedIndex() == 1) { // Time Log tab
                    refreshTimeLog();
                } else if (tab.getSelectedIndex() == 2) { // Leave tab
                    loadLeaveRecords();
                }
            }
        });
    }
    
    public ArrayList loadPayslipList() {
    ArrayList payslipList = new ArrayList();
    
    if (this.employeeID == null || this.employeeID.trim().isEmpty()) {
        return payslipList;
    }

    try {
        conn = dbConnect.connect();

        String sql = "SELECT p.payslip_id, p.payslip_number, p.employee_id, " +
             "pp.start_date, pp.end_date, pp.pay_date, p.status, pp.cut_off " +
             "FROM payslip p " +
             "JOIN pay_period pp ON p.payperiod_id = pp.payperiod_id " +
             "WHERE p.employee_id = ?";


        pst = conn.prepareStatement(sql);
        pst.setString(1, this.employeeID);
        rs = pst.executeQuery();

        DefaultTableModel payslipTableModel = (DefaultTableModel) payslipTable.getModel(); // Assuming you have this JTable
        payslipTableModel.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            Vector<String> row = new Vector<>();
            row.add(rs.getString("pay_date"));
            row.add(rs.getString("payslip_number"));
            row.add(rs.getString("employee_id"));
            row.add(rs.getString("cut_off"));
            row.add(rs.getString("start_date"));
            row.add(rs.getString("end_date"));
            row.add(rs.getString("status"));
            
            payslipTableModel.addRow(row);

            payslipList.add(row); // Add to ArrayList as well (optional)
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error loading payslip list: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            // ignore
        }
    }

    return payslipList;
}
    
   
   private void generatePayslipReport() {
    Connection conn = null;

    try {
        // Get employee ID
        String employeeIdStr = id_field.getText().trim();
        int employeeId = Integer.parseInt(employeeIdStr);

        // Get selected cut-off
        String selectedCutOff = payPeriodComboBox.getSelectedItem().toString();
        String cutOffValue = selectedCutOff.equalsIgnoreCase("1st Cut-off") ? "1st" : "2nd";

        // Get selected month name and convert to month number
        String selectedMonth = monthComboBox.getSelectedItem().toString();
        int monthNumber = Month.valueOf(selectedMonth.toUpperCase()).getValue(); // Java 8+

        // Connect to DB
        conn = dbConnect.connect();

        // Compile report
        String reportPath = "C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 2 24-25\\MotorPHPortal\\src\\main\\java\\com\\mmdc\\motor_ph_util\\reportPayslipTemplate.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(reportPath);

        // Set report parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employee_id", employeeId);
        parameters.put("cut_off", cutOffValue);
        parameters.put("month_number", monthNumber); // Pass month number

        // Fill report
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);

        // Handle no data
        if (jp.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No Payslip Found for Employee ID: " + employeeId + " (" + cutOffValue + " Cut-off, Month: " + selectedMonth + ")",
                    "No Data",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Generate PDF file path
        String outputPath = "C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 3 24-25\\AOOP\\Payslips" + employeeId + "" + selectedMonth + "" + cutOffValue + ".pdf";

        // Export report to PDF
        JasperExportManager.exportReportToPdfFile(jp, outputPath);

        // Try to open the PDF file
        File pdfFile = new File(outputPath);
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(pdfFile);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this,
                            "Error opening PDF: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Desktop is not supported. PDF file generated at:\n" + outputPath,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Notify user
        JOptionPane.showMessageDialog(this,
                "Payslip PDF generated successfully:\n" + outputPath,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
                "Error generating report: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
 




    

            @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        empProfileTab = new javax.swing.JPanel();
        noe_title = new javax.swing.JLabel();
        b_title = new javax.swing.JLabel();
        cn_title = new javax.swing.JLabel();
        email_title = new javax.swing.JLabel();
        firstName_field = new javax.swing.JTextField();
        bday_field = new javax.swing.JTextField();
        contact_field = new javax.swing.JTextField();
        email_field = new javax.swing.JTextField();
        govIdNum_title = new javax.swing.JLabel();
        sss_title = new javax.swing.JLabel();
        pagibig_title = new javax.swing.JLabel();
        phhealth_title = new javax.swing.JLabel();
        tin_title = new javax.swing.JLabel();
        sss_field = new javax.swing.JTextField();
        pagibig_field = new javax.swing.JTextField();
        philhealth_field = new javax.swing.JTextField();
        tin_field = new javax.swing.JTextField();
        noe_title8 = new javax.swing.JLabel();
        lastname_field = new javax.swing.JTextField();
        address_title = new javax.swing.JLabel();
        street_field = new javax.swing.JTextField();
        empProfileTitle = new javax.swing.JLabel();
        brgy_field = new javax.swing.JTextField();
        city_field = new javax.swing.JTextField();
        province_field = new javax.swing.JTextField();
        postalcode_field = new javax.swing.JTextField();
        address_title1 = new javax.swing.JLabel();
        address_title2 = new javax.swing.JLabel();
        address_title3 = new javax.swing.JLabel();
        address_title4 = new javax.swing.JLabel();
        timeLogTab = new javax.swing.JPanel();
        timeLogFirstNameField = new javax.swing.JTextField();
        first_name = new javax.swing.JLabel();
        timeLogLastNameField = new javax.swing.JTextField();
        timeLogLastNameLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendance_table = new javax.swing.JTable();
        timeInBtn = new javax.swing.JButton();
        timeOutBtn = new javax.swing.JButton();
        attendance_title = new javax.swing.JLabel();
        leaveTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        noe_title6 = new javax.swing.JLabel();
        leaveFirstName_field = new javax.swing.JTextField();
        noe_title7 = new javax.swing.JLabel();
        leaveLastName_field = new javax.swing.JTextField();
        leave_type = new javax.swing.JLabel();
        leaveTypeComboBox = new javax.swing.JComboBox<>();
        addLeaveRequestButton = new javax.swing.JButton();
        enddate_lbl1 = new javax.swing.JLabel();
        enddate_chooser = new com.toedter.calendar.JDateChooser();
        leaveNum1 = new javax.swing.JLabel();
        leaveNum_field = new javax.swing.JTextField();
        startdate_lbl2 = new javax.swing.JLabel();
        startdate_chooser = new com.toedter.calendar.JDateChooser();
        leave_title = new javax.swing.JLabel();
        leaveTab1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        payslipTable = new javax.swing.JTable();
        noe_title9 = new javax.swing.JLabel();
        payslipFirstName_field = new javax.swing.JTextField();
        noe_title10 = new javax.swing.JLabel();
        payslipLastName_field = new javax.swing.JTextField();
        payslipBtn = new javax.swing.JButton();
        leave_title1 = new javax.swing.JLabel();
        noe_title11 = new javax.swing.JLabel();
        payPeriodComboBox = new javax.swing.JComboBox<>();
        noe_title12 = new javax.swing.JLabel();
        monthComboBox = new javax.swing.JComboBox<>();
        empName = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        en_title = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();
        img = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));
        jPanel1.setForeground(new java.awt.Color(250, 250, 255));

        tab.setBackground(new java.awt.Color(30, 43, 93));
        tab.setForeground(new java.awt.Color(250, 250, 255));
        tab.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tab.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        tab.setOpaque(true);
        tab.setPreferredSize(new java.awt.Dimension(80, 150));

        empProfileTab.setBackground(new java.awt.Color(30, 43, 93));
        empProfileTab.setForeground(new java.awt.Color(30, 43, 93));

        noe_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noe_title.setForeground(new java.awt.Color(250, 250, 255));
        noe_title.setText("First Name :");

        b_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        b_title.setForeground(new java.awt.Color(250, 250, 255));
        b_title.setText("Birthday :");

        cn_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cn_title.setForeground(new java.awt.Color(250, 250, 255));
        cn_title.setText("Contact Number:");

        email_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        email_title.setForeground(new java.awt.Color(250, 250, 255));
        email_title.setText("Email:");

        firstName_field.setEditable(false);
        firstName_field.setBackground(new java.awt.Color(250, 250, 255));
        firstName_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        firstName_field.setForeground(new java.awt.Color(92, 101, 138));

        bday_field.setEditable(false);
        bday_field.setBackground(new java.awt.Color(250, 250, 255));
        bday_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        bday_field.setForeground(new java.awt.Color(92, 101, 138));

        contact_field.setEditable(false);
        contact_field.setBackground(new java.awt.Color(250, 250, 255));
        contact_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        contact_field.setForeground(new java.awt.Color(92, 101, 138));

        email_field.setEditable(false);
        email_field.setBackground(new java.awt.Color(250, 250, 255));
        email_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        email_field.setForeground(new java.awt.Color(92, 101, 138));

        govIdNum_title.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        govIdNum_title.setForeground(new java.awt.Color(250, 250, 255));
        govIdNum_title.setText("Government ID Numbers :");

        sss_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        sss_title.setForeground(new java.awt.Color(250, 250, 255));
        sss_title.setText("SSS Number :");

        pagibig_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pagibig_title.setForeground(new java.awt.Color(250, 250, 255));
        pagibig_title.setText("PAG-IBIG Number :");

        phhealth_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        phhealth_title.setForeground(new java.awt.Color(250, 250, 255));
        phhealth_title.setText("PhilHealth Number :");

        tin_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tin_title.setForeground(new java.awt.Color(250, 250, 255));
        tin_title.setText("TIN :");

        sss_field.setEditable(false);
        sss_field.setBackground(new java.awt.Color(250, 250, 255));
        sss_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        sss_field.setForeground(new java.awt.Color(92, 101, 138));

        pagibig_field.setEditable(false);
        pagibig_field.setBackground(new java.awt.Color(250, 250, 255));
        pagibig_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        pagibig_field.setForeground(new java.awt.Color(92, 101, 138));

        philhealth_field.setEditable(false);
        philhealth_field.setBackground(new java.awt.Color(250, 250, 255));
        philhealth_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        philhealth_field.setForeground(new java.awt.Color(92, 101, 138));

        tin_field.setEditable(false);
        tin_field.setBackground(new java.awt.Color(250, 250, 255));
        tin_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tin_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_title8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noe_title8.setForeground(new java.awt.Color(250, 250, 255));
        noe_title8.setText("Last Name :");

        lastname_field.setEditable(false);
        lastname_field.setBackground(new java.awt.Color(250, 250, 255));
        lastname_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lastname_field.setForeground(new java.awt.Color(92, 101, 138));

        address_title.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title.setForeground(new java.awt.Color(250, 250, 255));
        address_title.setText("Street Address :");

        street_field.setEditable(false);
        street_field.setBackground(new java.awt.Color(250, 250, 255));
        street_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        street_field.setForeground(new java.awt.Color(92, 101, 138));

        empProfileTitle.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        empProfileTitle.setForeground(new java.awt.Color(253, 56, 29));
        empProfileTitle.setText("EMPLOYEE PROFILE");

        brgy_field.setEditable(false);
        brgy_field.setBackground(new java.awt.Color(250, 250, 255));
        brgy_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        brgy_field.setForeground(new java.awt.Color(92, 101, 138));

        city_field.setEditable(false);
        city_field.setBackground(new java.awt.Color(250, 250, 255));
        city_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        city_field.setForeground(new java.awt.Color(92, 101, 138));

        province_field.setEditable(false);
        province_field.setBackground(new java.awt.Color(250, 250, 255));
        province_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        province_field.setForeground(new java.awt.Color(92, 101, 138));

        postalcode_field.setEditable(false);
        postalcode_field.setBackground(new java.awt.Color(250, 250, 255));
        postalcode_field.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        postalcode_field.setForeground(new java.awt.Color(92, 101, 138));

        address_title1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title1.setForeground(new java.awt.Color(250, 250, 255));
        address_title1.setText("Barangay:");

        address_title2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title2.setForeground(new java.awt.Color(250, 250, 255));
        address_title2.setText("City:");

        address_title3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title3.setForeground(new java.awt.Color(250, 250, 255));
        address_title3.setText("Province:");

        address_title4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        address_title4.setForeground(new java.awt.Color(250, 250, 255));
        address_title4.setText("Postal Code:");

        javax.swing.GroupLayout empProfileTabLayout = new javax.swing.GroupLayout(empProfileTab);
        empProfileTab.setLayout(empProfileTabLayout);
        empProfileTabLayout.setHorizontalGroup(
            empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empProfileTabLayout.createSequentialGroup()
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tin_title)
                            .addComponent(phhealth_title)
                            .addComponent(pagibig_title)
                            .addComponent(sss_title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(philhealth_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pagibig_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sss_field, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tin_field, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addComponent(b_title)
                                .addGap(18, 18, 18)
                                .addComponent(bday_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addComponent(noe_title8)
                                .addGap(18, 18, 18)
                                .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addComponent(noe_title)
                                .addGap(18, 18, 18)
                                .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(empProfileTabLayout.createSequentialGroup()
                                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(address_title2)
                                    .addComponent(address_title))
                                .addGap(18, 18, 18)
                                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(street_field, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(city_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)))
                        .addGap(54, 54, 54)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_title, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cn_title, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contact_field)
                            .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(govIdNum_title))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(empProfileTitle))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(address_title1)
                        .addGap(18, 18, 18)
                        .addComponent(brgy_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(address_title3)
                        .addGap(18, 18, 18)
                        .addComponent(province_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(address_title4)
                        .addGap(18, 18, 18)
                        .addComponent(postalcode_field, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        empProfileTabLayout.setVerticalGroup(
            empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empProfileTabLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(empProfileTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noe_title)
                            .addComponent(firstName_field))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title8)
                            .addComponent(lastname_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(empProfileTabLayout.createSequentialGroup()
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contact_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cn_title))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email_title))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bday_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_title))
                .addGap(12, 12, 12)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address_title)
                    .addComponent(street_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brgy_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address_title1))
                .addGap(9, 9, 9)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(province_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postalcode_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(address_title2)
                    .addComponent(address_title3)
                    .addComponent(address_title4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(govIdNum_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sss_title)
                    .addComponent(sss_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagibig_title)
                    .addComponent(pagibig_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phhealth_title)
                    .addComponent(philhealth_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tin_title)
                    .addComponent(tin_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );

        tab.addTab("Profile", empProfileTab);

        timeLogTab.setBackground(new java.awt.Color(30, 43, 93));
        timeLogTab.setForeground(new java.awt.Color(69, 123, 157));

        timeLogFirstNameField.setEditable(false);
        timeLogFirstNameField.setBackground(new java.awt.Color(250, 250, 255));
        timeLogFirstNameField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeLogFirstNameField.setForeground(new java.awt.Color(92, 101, 138));

        first_name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        first_name.setForeground(new java.awt.Color(250, 250, 255));
        first_name.setText("First Name :");

        timeLogLastNameField.setEditable(false);
        timeLogLastNameField.setBackground(new java.awt.Color(250, 250, 255));
        timeLogLastNameField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        timeLogLastNameField.setForeground(new java.awt.Color(92, 101, 138));

        timeLogLastNameLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timeLogLastNameLbl.setForeground(new java.awt.Color(250, 250, 255));
        timeLogLastNameLbl.setText("Last Name :");

        attendance_table.setBackground(new java.awt.Color(250, 250, 255));
        attendance_table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        attendance_table.setForeground(new java.awt.Color(92, 101, 138));
        attendance_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Date", "Time In", "Time Out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(attendance_table);

        timeInBtn.setBackground(new java.awt.Color(253, 56, 29));
        timeInBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        timeInBtn.setForeground(new java.awt.Color(250, 250, 255));
        timeInBtn.setText("Time In");
        timeInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeInBtnActionPerformed(evt);
            }
        });

        timeOutBtn.setBackground(new java.awt.Color(253, 56, 29));
        timeOutBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        timeOutBtn.setForeground(new java.awt.Color(250, 250, 255));
        timeOutBtn.setText("Time Out");
        timeOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOutBtnActionPerformed(evt);
            }
        });

        attendance_title.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        attendance_title.setForeground(new java.awt.Color(253, 56, 29));
        attendance_title.setText("EMPLOYEE TIME LOG");

        javax.swing.GroupLayout timeLogTabLayout = new javax.swing.GroupLayout(timeLogTab);
        timeLogTab.setLayout(timeLogTabLayout);
        timeLogTabLayout.setHorizontalGroup(
            timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeLogTabLayout.createSequentialGroup()
                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(timeLogTabLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(timeLogLastNameLbl)
                                    .addComponent(first_name))
                                .addGap(31, 31, 31)
                                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeLogFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeLogLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(timeLogTabLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(timeInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(timeOutBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(attendance_title)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        timeLogTabLayout.setVerticalGroup(
            timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeLogTabLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(attendance_title, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(first_name)
                            .addComponent(timeLogFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeLogLastNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeLogLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(timeLogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(timeLogTabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))))
        );

        tab.addTab("Time Log", timeLogTab);

        leaveTab.setBackground(new java.awt.Color(30, 43, 93));
        leaveTab.setPreferredSize(new java.awt.Dimension(650, 450));

        leaveTable.setBackground(new java.awt.Color(250, 250, 255));
        leaveTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveTable.setForeground(new java.awt.Color(92, 101, 138));
        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave No.", "Employee ID", "First Name", "Last Name", "Start Date", "End Date", "Leave Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(leaveTable);

        noe_title6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title6.setForeground(new java.awt.Color(250, 250, 255));
        noe_title6.setText("First Name :");

        leaveFirstName_field.setEditable(false);
        leaveFirstName_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveFirstName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveFirstName_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_title7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title7.setForeground(new java.awt.Color(250, 250, 255));
        noe_title7.setText("Last Name :");

        leaveLastName_field.setEditable(false);
        leaveLastName_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveLastName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveLastName_field.setForeground(new java.awt.Color(92, 101, 138));

        leave_type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leave_type.setForeground(new java.awt.Color(250, 250, 255));
        leave_type.setText("Leave Type :");

        leaveTypeComboBox.setBackground(new java.awt.Color(250, 250, 255));
        leaveTypeComboBox.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveTypeComboBox.setForeground(new java.awt.Color(92, 101, 138));
        leaveTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacation Leave", "Sick Leave", "Emergency Leave", "Birthday Leave" }));

        addLeaveRequestButton.setBackground(new java.awt.Color(253, 56, 29));
        addLeaveRequestButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addLeaveRequestButton.setForeground(new java.awt.Color(250, 250, 255));
        addLeaveRequestButton.setText("File Leave");
        addLeaveRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLeaveRequestButtonActionPerformed(evt);
            }
        });

        enddate_lbl1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        enddate_lbl1.setForeground(new java.awt.Color(250, 250, 255));
        enddate_lbl1.setText("End Date :");

        enddate_chooser.setBackground(new java.awt.Color(250, 250, 255));
        enddate_chooser.setForeground(new java.awt.Color(92, 101, 138));
        enddate_chooser.setDateFormatString("dd MMM yyyy");
        enddate_chooser.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N

        leaveNum1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        leaveNum1.setForeground(new java.awt.Color(250, 250, 255));
        leaveNum1.setText("Leave No. :");

        leaveNum_field.setEditable(false);
        leaveNum_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveNum_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveNum_field.setForeground(new java.awt.Color(92, 101, 138));

        startdate_lbl2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        startdate_lbl2.setForeground(new java.awt.Color(250, 250, 255));
        startdate_lbl2.setText("Start Date :");

        startdate_chooser.setBackground(new java.awt.Color(250, 250, 255));
        startdate_chooser.setForeground(new java.awt.Color(92, 101, 138));
        startdate_chooser.setDateFormatString("dd MMM yyyy");
        startdate_chooser.setFont(new java.awt.Font("Gadugi", 0, 10)); // NOI18N

        leave_title.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        leave_title.setForeground(new java.awt.Color(253, 56, 29));
        leave_title.setText("EMPLOYEE LEAVE REQUEST");

        javax.swing.GroupLayout leaveTabLayout = new javax.swing.GroupLayout(leaveTab);
        leaveTab.setLayout(leaveTabLayout);
        leaveTabLayout.setHorizontalGroup(
            leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addComponent(leaveNum1)
                                .addGap(18, 18, 18)
                                .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addComponent(noe_title7)
                                .addGap(18, 18, 18)
                                .addComponent(leaveLastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addComponent(noe_title6)
                                .addGap(18, 18, 18)
                                .addComponent(leaveFirstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(139, 139, 139)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(leave_type)
                            .addComponent(enddate_lbl1)
                            .addComponent(startdate_lbl2))
                        .addGap(18, 18, 18)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leaveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enddate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startdate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(leave_title)))
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaveTabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addLeaveRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
        );
        leaveTabLayout.setVerticalGroup(
            leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(leave_title, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startdate_lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startdate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enddate_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enddate_lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leave_type)
                            .addComponent(leaveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leaveNum1)
                            .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title6)
                            .addComponent(leaveFirstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noe_title7)
                            .addComponent(leaveLastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addComponent(addLeaveRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        tab.addTab("Leave", leaveTab);

        leaveTab1.setBackground(new java.awt.Color(30, 43, 93));
        leaveTab1.setPreferredSize(new java.awt.Dimension(650, 450));

        payslipTable.setBackground(new java.awt.Color(250, 250, 255));
        payslipTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        payslipTable.setForeground(new java.awt.Color(92, 101, 138));
        payslipTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Payslip Number", "Employee ID", "Cut-Off", "Start Date", "End Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(payslipTable);

        noe_title9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title9.setForeground(new java.awt.Color(250, 250, 255));
        noe_title9.setText("First Name :");

        payslipFirstName_field.setEditable(false);
        payslipFirstName_field.setBackground(new java.awt.Color(250, 250, 255));
        payslipFirstName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        payslipFirstName_field.setForeground(new java.awt.Color(92, 101, 138));

        noe_title10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title10.setForeground(new java.awt.Color(250, 250, 255));
        noe_title10.setText("Last Name :");

        payslipLastName_field.setEditable(false);
        payslipLastName_field.setBackground(new java.awt.Color(250, 250, 255));
        payslipLastName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        payslipLastName_field.setForeground(new java.awt.Color(92, 101, 138));

        payslipBtn.setBackground(new java.awt.Color(253, 56, 29));
        payslipBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        payslipBtn.setForeground(new java.awt.Color(250, 250, 255));
        payslipBtn.setText("View Payslip");
        payslipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payslipBtnActionPerformed(evt);
            }
        });

        leave_title1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        leave_title1.setForeground(new java.awt.Color(253, 56, 29));
        leave_title1.setText("EMPLOYEE PAYSLIP");

        noe_title11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title11.setForeground(new java.awt.Color(250, 250, 255));
        noe_title11.setText("Pay Period:");

        payPeriodComboBox.setBackground(new java.awt.Color(92, 101, 138));
        payPeriodComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        payPeriodComboBox.setForeground(new java.awt.Color(250, 250, 255));
        payPeriodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Cut-off", "2nd Cut-off" }));

        noe_title12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noe_title12.setForeground(new java.awt.Color(250, 250, 255));
        noe_title12.setText("Pay Period Month:");

        monthComboBox.setBackground(new java.awt.Color(92, 101, 138));
        monthComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        monthComboBox.setForeground(new java.awt.Color(250, 250, 255));
        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        javax.swing.GroupLayout leaveTab1Layout = new javax.swing.GroupLayout(leaveTab1);
        leaveTab1.setLayout(leaveTab1Layout);
        leaveTab1Layout.setHorizontalGroup(
            leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTab1Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaveTab1Layout.createSequentialGroup()
                        .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leave_title1)
                            .addGroup(leaveTab1Layout.createSequentialGroup()
                                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(noe_title9)
                                    .addComponent(noe_title10)
                                    .addComponent(noe_title11)
                                    .addComponent(noe_title12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(payslipLastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(payslipFirstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(payPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(payslipBtn))))
                        .addGap(241, 241, 241))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaveTab1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        leaveTab1Layout.setVerticalGroup(
            leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTab1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(leave_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title9)
                    .addComponent(payslipFirstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title10)
                    .addComponent(payslipLastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noe_title12))
                .addGap(9, 9, 9)
                .addGroup(leaveTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payPeriodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noe_title11))
                .addGap(18, 18, 18)
                .addComponent(payslipBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        tab.addTab("Payslip", leaveTab1);

        empName.setFont(new java.awt.Font("Bernard MT Condensed", 1, 36)); // NOI18N
        empName.setForeground(new java.awt.Color(30, 43, 93));
        empName.setText("Employee Name ");

        date.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(253, 56, 29));
        date.setText("Date");

        time.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(253, 56, 29));
        time.setText(" Time");

        en_title.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        en_title.setForeground(new java.awt.Color(92, 101, 138));
        en_title.setText("Employee Number :");

        id_field.setEditable(false);
        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        id_field.setForeground(new java.awt.Color(253, 56, 29));

        img.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 2 24-25\\MotorPHPortal\\src\\main\\java\\images\\icon.png")); // NOI18N

        logOutButton.setBackground(new java.awt.Color(253, 56, 29));
        logOutButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(250, 250, 255));
        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(en_title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOutButton)
                        .addGap(18, 18, 18)
                        .addComponent(date)
                        .addGap(18, 18, 18)
                        .addComponent(time)
                        .addGap(85, 85, 85))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empName)
                        .addGap(20, 464, Short.MAX_VALUE))))
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(empName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(en_title)
                            .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 78, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(time)
                                    .addComponent(date)
                                    .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
                .addGap(4, 4, 4)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void addLeaveRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLeaveRequestButtonActionPerformed
        // add data to sql
          try {
        // Retrieve and parse input values
        String leaveNumText = leaveNum_field.getText().trim();
        int leaveNum = 0; // Initialize as int
        
        String employeeId = this.employeeID; // Use the employee ID from the logged-in user
        String firstName = firstName_field.getText().trim(); // Actual employee first name
        String lastName = lastname_field.getText().trim(); // Actual employee last name
        String startDate = startdate_chooser.getDate() != null ?
            new SimpleDateFormat("yyyy-MM-dd").format(startdate_chooser.getDate()) : "";
        String endDate = enddate_chooser.getDate() != null ?
            new SimpleDateFormat("yyyy-MM-dd").format(enddate_chooser.getDate()) : "";
        String leaveType = leaveTypeComboBox.getSelectedItem().toString();
        String status = "Pending"; // Set status to Pending for the new leave request
        
        // Validate and parse leave number
        if (leaveNumText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Leave Number", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            leaveNum = Integer.parseInt(leaveNumText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Leave Number must be a valid number", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (startDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a Start Date", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (endDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an End Date", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Additional validation: Check if start date is before end date
        if (startdate_chooser.getDate() != null && enddate_chooser.getDate() != null) {
            if (startdate_chooser.getDate().after(enddate_chooser.getDate())) {
                JOptionPane.showMessageDialog(null, "Start Date cannot be after End Date", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Create a LeaveRecord object including status
        LeaveRecord leaveRecord = new LeaveRecord(leaveNum, employeeId, firstName, lastName, startDate, endDate, leaveType, status);
        
        // Add the leave request to the database
        if (dbConnect.addLeaveRequest(leaveRecord)) {
            // Update the table model
            DefaultTableModel model = (DefaultTableModel) leaveTable.getModel();
            model.addRow(new Object[]{leaveNum, employeeId, firstName, lastName, startDate, endDate, leaveType, status});
            
            JOptionPane.showMessageDialog(null, "Leave request added successfully!");
            
            autoFillLeaveId(); // Generate the next leave ID for the next request
            
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add leave request. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        loadLeaveRecords(); // Refresh the leave records display
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error adding leave request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_addLeaveRequestButtonActionPerformed

    private void timeOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOutBtnActionPerformed
        // Time Out Function + update SQL
             try {
        String employeeId = this.employeeID;
        String dateStr = date.getText().trim();   // Expected format: yyyy-MM-dd
        String timeStr = time.getText().trim();   // Expected format: HH:mm or HH:mm:ss

        // Validate inputs
        if (dateStr.isEmpty() || timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please ensure both date and time are entered",
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate date format (yyyy-MM-dd)
        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, 
                "Date must be in yyyy-MM-dd format (e.g., 2025-06-24)",
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate and format time (HH:mm or HH:mm:ss)
        String formattedTime = timeStr;
        if (!timeStr.matches("\\d{2}:\\d{2}(:\\d{2})?")) {
            JOptionPane.showMessageDialog(this, 
                "Time must be in HH:mm or HH:mm:ss format (e.g., 17:30 or 17:30:00)",
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Add seconds if only HH:mm is provided
        if (timeStr.length() == 5) {
            formattedTime = timeStr + ":00";
        }

        System.out.println("Attempting to log time out with:");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Date: " + dateStr);
        System.out.println("Time: " + formattedTime);

        boolean success = dbConnect.logTimeOut(employeeId, dateStr, formattedTime);

        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Time Out Successfully Recorded",
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            refreshTimeLog();
        } else {
            JOptionPane.showMessageDialog(this, 
            "Failed to record Time Out.",
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "An error occurred: " + e.getMessage(),
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_timeOutBtnActionPerformed

    private void timeInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeInBtnActionPerformed
          try {
        String employeeId = this.employeeID;
        String dateStr = date.getText().trim(); // yyyy-MM-dd
        String timeStr = time.getText().trim(); // HH:mm or HH:mm:ss

        // Validate input
        if (dateStr.isEmpty() || timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both date and time.");
            return;
        }

        // Add ":00" to time if only HH:mm
        if (timeStr.matches("\\d{2}:\\d{2}")) {
            timeStr += ":00";
        }

        // Call the logTimeIn method
        boolean success = dbConnect.logTimeIn(employeeId, dateStr, timeStr);

        if (success) {
            JOptionPane.showMessageDialog(this, "Time In recorded successfully.");
            refreshTimeLog();
        } else {
            JOptionPane.showMessageDialog(this, "You have already timed in today.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_timeInBtnActionPerformed

    private void payslipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payslipBtnActionPerformed
        generatePayslipReport(); 
    }//GEN-LAST:event_payslipBtnActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to Log Out?", "Log Out", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION){
            Login loginPage = new Login();
            loginPage.setVisible(true);
            dispose();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeAccess_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // For testing, you can pass a username here
                // new EmployeeAccess_Profile("test_username").setVisible(true);
                new EmployeeAccess_Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addLeaveRequestButton;
    private javax.swing.JLabel address_title;
    private javax.swing.JLabel address_title1;
    private javax.swing.JLabel address_title2;
    private javax.swing.JLabel address_title3;
    private javax.swing.JLabel address_title4;
    private javax.swing.JTable attendance_table;
    private javax.swing.JLabel attendance_title;
    private javax.swing.JLabel b_title;
    private javax.swing.JTextField bday_field;
    private javax.swing.JTextField brgy_field;
    private javax.swing.JTextField city_field;
    private javax.swing.JLabel cn_title;
    private javax.swing.JTextField contact_field;
    private javax.swing.JLabel date;
    private javax.swing.JTextField email_field;
    private javax.swing.JLabel email_title;
    private javax.swing.JLabel empName;
    private javax.swing.JPanel empProfileTab;
    private javax.swing.JLabel empProfileTitle;
    private javax.swing.JLabel en_title;
    private com.toedter.calendar.JDateChooser enddate_chooser;
    private javax.swing.JLabel enddate_lbl1;
    private javax.swing.JTextField firstName_field;
    private javax.swing.JLabel first_name;
    private javax.swing.JLabel govIdNum_title;
    private javax.swing.JTextField id_field;
    private javax.swing.JLabel img;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField lastname_field;
    private javax.swing.JTextField leaveFirstName_field;
    private javax.swing.JTextField leaveLastName_field;
    private javax.swing.JLabel leaveNum1;
    private javax.swing.JTextField leaveNum_field;
    private javax.swing.JPanel leaveTab;
    private javax.swing.JPanel leaveTab1;
    private javax.swing.JTable leaveTable;
    private javax.swing.JComboBox<String> leaveTypeComboBox;
    private javax.swing.JLabel leave_title;
    private javax.swing.JLabel leave_title1;
    private javax.swing.JLabel leave_type;
    private javax.swing.JButton logOutButton;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JLabel noe_title;
    private javax.swing.JLabel noe_title10;
    private javax.swing.JLabel noe_title11;
    private javax.swing.JLabel noe_title12;
    private javax.swing.JLabel noe_title6;
    private javax.swing.JLabel noe_title7;
    private javax.swing.JLabel noe_title8;
    private javax.swing.JLabel noe_title9;
    private javax.swing.JTextField pagibig_field;
    private javax.swing.JLabel pagibig_title;
    private javax.swing.JComboBox<String> payPeriodComboBox;
    private javax.swing.JButton payslipBtn;
    private javax.swing.JTextField payslipFirstName_field;
    private javax.swing.JTextField payslipLastName_field;
    private javax.swing.JTable payslipTable;
    private javax.swing.JLabel phhealth_title;
    private javax.swing.JTextField philhealth_field;
    private javax.swing.JTextField postalcode_field;
    private javax.swing.JTextField province_field;
    private javax.swing.JTextField sss_field;
    private javax.swing.JLabel sss_title;
    private com.toedter.calendar.JDateChooser startdate_chooser;
    private javax.swing.JLabel startdate_lbl2;
    private javax.swing.JTextField street_field;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JLabel time;
    private javax.swing.JButton timeInBtn;
    private javax.swing.JTextField timeLogFirstNameField;
    private javax.swing.JTextField timeLogLastNameField;
    private javax.swing.JLabel timeLogLastNameLbl;
    private javax.swing.JPanel timeLogTab;
    private javax.swing.JButton timeOutBtn;
    private javax.swing.JTextField tin_field;
    private javax.swing.JLabel tin_title;
    // End of variables declaration//GEN-END:variables
}


package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.EmployeeAccess.EmployeeAccess_Profile;
import com.mmdc.motor_ph_portal.Login;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class EmployeePortal extends javax.swing.JFrame {
    
    DatabaseConnect dbConnect = new DatabaseConnect() {};
    
    public EmployeePortal() {
        initComponents();
        setTitle ("Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        
        //Displaying date and Time
        time();
        date();
                
    }
    
    public final void time(){
    DateTimeFormatter times = DateTimeFormatter.ofPattern("hh:mm:ss a");
    LocalDateTime now =LocalDateTime.now();
    time.setText(times.format(now));
    }
    
    public final void date(){
    DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMMM d, y");
    LocalDateTime now =LocalDateTime.now();
    date.setText(dates.format(now));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        logOutButton = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        empPortal_title = new javax.swing.JLabel();
        employeeAccessBtn = new javax.swing.JButton();
        bluePanel = new javax.swing.JPanel();
        attendanceButton = new javax.swing.JButton();
        employeeProfileButton = new javax.swing.JButton();
        payrollButton = new javax.swing.JButton();
        leaveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 450));

        date.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(92, 101, 138));
        date.setText("Date ");

        logOutButton.setBackground(new java.awt.Color(253, 56, 29));
        logOutButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(217, 217, 217));
        logOutButton.setText("LOG OUT");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        time.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(92, 101, 138));
        time.setText(" Time");

        empPortal_title.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        empPortal_title.setForeground(new java.awt.Color(92, 101, 138));
        empPortal_title.setText("EMPLOYEE PORTAL");

        employeeAccessBtn.setBackground(new java.awt.Color(253, 56, 29));
        employeeAccessBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        employeeAccessBtn.setForeground(new java.awt.Color(217, 217, 217));
        employeeAccessBtn.setText("Log In as Employee");
        employeeAccessBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeAccessBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time)
                .addGap(147, 147, 147))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(logo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(employeeAccessBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(logOutButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(empPortal_title)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(empPortal_title, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeAccessBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logo)
                .addGap(13, 13, 13)
                .addComponent(logOutButton)
                .addGap(168, 168, 168)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(time))
                .addContainerGap())
        );

        bluePanel.setBackground(new java.awt.Color(30, 43, 93));

        attendanceButton.setBackground(new java.awt.Color(30, 43, 93));
        attendanceButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        attendanceButton.setForeground(new java.awt.Color(250, 250, 255));
        attendanceButton.setText("Attendance");
        attendanceButton.setBorder(null);
        attendanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceButtonActionPerformed(evt);
            }
        });

        employeeProfileButton.setBackground(new java.awt.Color(30, 43, 93));
        employeeProfileButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        employeeProfileButton.setForeground(new java.awt.Color(250, 250, 255));
        employeeProfileButton.setText("Employee Profile");
        employeeProfileButton.setBorder(null);
        employeeProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeProfileButtonActionPerformed(evt);
            }
        });

        payrollButton.setBackground(new java.awt.Color(30, 43, 93));
        payrollButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        payrollButton.setForeground(new java.awt.Color(250, 250, 255));
        payrollButton.setText("Payroll");
        payrollButton.setBorder(null);
        payrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payrollButtonActionPerformed(evt);
            }
        });

        leaveButton.setBackground(new java.awt.Color(30, 43, 93));
        leaveButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        leaveButton.setForeground(new java.awt.Color(250, 250, 255));
        leaveButton.setText("Leave Management");
        leaveButton.setBorder(null);
        leaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bluePanelLayout = new javax.swing.GroupLayout(bluePanel);
        bluePanel.setLayout(bluePanelLayout);
        bluePanelLayout.setHorizontalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(leaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(attendanceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payrollButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(employeeProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(payrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(attendanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(leaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // log out
                
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to Log Out?", "Employee Portal", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION){
        
        Login loginPage = new Login();
        loginPage.show();
                
        dispose();
        } else if (result == JOptionPane.NO_OPTION){
        EmployeePortal employeePortal = new EmployeePortal ();
        employeePortal.show();
                
        dispose();   
        }
        
    
                
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void employeeProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeProfileButtonActionPerformed
        // go to Employee Profile
        EmployeeProfileTabbed employeeProfile = new EmployeeProfileTabbed();
        employeeProfile.show();
        
        dispose();
    }//GEN-LAST:event_employeeProfileButtonActionPerformed

    private void leaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveButtonActionPerformed
        // go to Leave Management
        LeaveManagement leaveManagement = new LeaveManagement();
        leaveManagement.show();
        
        dispose();
    }//GEN-LAST:event_leaveButtonActionPerformed

    private void payrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payrollButtonActionPerformed
        // go to Payroll
        Payroll payroll = new Payroll();
        payroll.show();
        
        dispose();
    }//GEN-LAST:event_payrollButtonActionPerformed

    private void attendanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceButtonActionPerformed
        // go to Attendance
        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.show();
        
        dispose();
        
    }//GEN-LAST:event_attendanceButtonActionPerformed

    private void employeeAccessBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeAccessBtnActionPerformed
            // go to Employee Portal
        String employeeID = JOptionPane.showInputDialog(this, "Enter Employee ID to view:");

        if (employeeID == null || employeeID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID.");
            return;
        }

        // Step 2: Ask for admin credentials once
        String adminUsername = JOptionPane.showInputDialog(this, "Enter your Admin Username:");

        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(this, passwordField, "Enter your Admin Password:", JOptionPane.OK_CANCEL_OPTION);

        if (option != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Admin password entry cancelled.");
            return;
        }

        String adminPassword = new String(passwordField.getPassword());

        if (adminUsername == null || adminUsername.trim().isEmpty() || adminPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Admin credentials are required.");
            return;
        }

        // Step 3: Verify admin credentials
        if (!dbConnect.verifyCredentials(adminUsername, adminPassword)) {
            JOptionPane.showMessageDialog(this, "Incorrect admin username or password.");
            return;
        }

        // Step 4: Get employee's username from employee ID
        String employeeUsername = dbConnect.getUsernameByEmployeeID(employeeID);

        if (employeeUsername == null || employeeUsername.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No username found for employee ID: " + employeeID);
            return;
        }

        // Step 6: Get employee info and open profile
        Admin_Class employee = dbConnect.getEmployeeByUsername(employeeUsername);

        if (employee == null) {
            JOptionPane.showMessageDialog(this, "Employee not found for username: " + employeeUsername);
            return;
        }

        EmployeeAccess_Profile empPortal = new EmployeeAccess_Profile(
            employee.getEmployeeID(),
            employee.getFirstName(),
            employee.getLastName()
        );
        empPortal.setVisible(true);
        dispose();

    }//GEN-LAST:event_employeeAccessBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeePortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeePortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeePortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeePortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeePortal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attendanceButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel date;
    private javax.swing.JLabel empPortal_title;
    private javax.swing.JButton employeeAccessBtn;
    private javax.swing.JButton employeeProfileButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton leaveButton;
    private javax.swing.JButton logOutButton;
    private javax.swing.JLabel logo;
    private javax.swing.JButton payrollButton;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}

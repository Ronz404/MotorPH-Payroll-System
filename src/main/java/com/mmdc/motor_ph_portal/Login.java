package com.mmdc.motor_ph_portal;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.EmployeeAccess.Employee_Class;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Login form with security controls:
 * - Account Lockout: locks account after 5 failed attempts for 15 minutes (SIP Control 1 — R-01)
 * - Input Validation: regex whitelist on username field to prevent injection (SIP Control 3 — R-04)
 * - PreparedStatement: parameterized queries prevent SQL injection (SIP Control 3 — R-04)
 * - RBAC: role-based redirect to Admin or Employee dashboard (SIP Control 1 — R-01)
 */
public class Login extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    // Security constants for account lockout policy
    private static final int MAX_FAILED_ATTEMPTS = 5;       // Lock after 5 failed attempts
    private static final int LOCKOUT_DURATION_MINUTES = 15;  // Lock for 15 minutes

    public Login() {
        initComponents();
        setTitle("Motor PH Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        
    }

    /**
     * INPUT VALIDATION — Security Control 3 (R-04)
     * Validates username using regex whitelist: only alphanumeric, underscores, dots, hyphens allowed.
     * Prevents special characters that could be used in injection attacks.
     * Maximum length: 50 characters.
     */
    private boolean isValidUsername(String username) {
        // Regex whitelist: letters, digits, underscore, dot, hyphen only
        // Minimum 1 char, maximum 50 chars
        return username != null && username.matches("^[a-zA-Z0-9._-]{1,50}$");
    }

    /**
     * INPUT VALIDATION — Security Control 3 (R-04)
     * Validates password: rejects empty passwords and excessively long ones.
     * Also rejects common SQL injection patterns as an extra defense layer.
     * Maximum length: 128 characters.
     */
    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty() || password.length() > 128) {
            return false;
        }
        // Reject strings containing obvious SQL injection patterns
        String lower = password.toLowerCase();
        if (lower.contains("' or ") || lower.contains("'; drop") || 
            lower.contains("1=1") || lower.contains("--")) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        motorPhTitle1 = new javax.swing.JLabel();
        titleUserID1 = new javax.swing.JLabel();
        userIDText = new javax.swing.JTextField();
        titlePassword1 = new javax.swing.JLabel();
        passwordText = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();
        forgetPassTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(30, 43, 93));

        motorPhTitle1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        motorPhTitle1.setForeground(new java.awt.Color(250, 250, 255));
        motorPhTitle1.setText("EMPLOYEE PORTAL");

        titleUserID1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        titleUserID1.setForeground(new java.awt.Color(250, 250, 255));
        titleUserID1.setText("USERNAME");

        userIDText.setBackground(new java.awt.Color(30, 43, 93));
        userIDText.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        userIDText.setForeground(new java.awt.Color(250, 250, 255));

        titlePassword1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        titlePassword1.setForeground(new java.awt.Color(250, 250, 255));
        titlePassword1.setText("PASSWORD");

        passwordText.setBackground(new java.awt.Color(30, 43, 93));
        passwordText.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        passwordText.setForeground(new java.awt.Color(250, 250, 255));
        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        logInButton.setBackground(new java.awt.Color(253, 56, 29));
        logInButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        logInButton.setForeground(new java.awt.Color(250, 250, 255));
        logInButton.setText("LOG IN");
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        forgetPassTitle.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        forgetPassTitle.setForeground(new java.awt.Color(250, 250, 255));
        forgetPassTitle.setText("Forgot Password?");
        forgetPassTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPassTitleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(titlePassword1)
                                    .addComponent(titleUserID1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userIDText)
                                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(motorPhTitle1)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(logInButton)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(forgetPassTitle)
                .addGap(137, 137, 137))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(motorPhTitle1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleUserID1)
                    .addComponent(userIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titlePassword1)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forgetPassTitle)
                .addGap(98, 98, 98))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 2 24-25\\MotorPH Logo.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(92, 92, 92))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(167, 167, 167))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 380, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 356, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        // ===== SECURITY CONTROL: Input Validation (R-04) =====
        String username = userIDText.getText().trim();
        String password = new String(passwordText.getPassword());
        
        // Validate empty fields
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Fill Out User ID");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Fill Out Password");
            return;
        }

        // INPUT VALIDATION — regex whitelist check (Security Control 3)
        if (!isValidUsername(username)) {
            JOptionPane.showMessageDialog(null, 
                "Invalid username format. Only letters, numbers, dots, hyphens, and underscores are allowed.",
                "Input Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, 
                "Invalid password format. Please check your input.",
                "Input Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DatabaseConnect dbConnect = new DatabaseConnect() {};
        conn = dbConnect.connect();

        try {
            // ===== SECURITY CONTROL: Account Lockout Check (R-01) =====
            // Step 1: Check if account is currently locked
            String lockCheckSql = "SELECT failed_attempts, lockout_until FROM position WHERE username = ?";
            pst = conn.prepareStatement(lockCheckSql);
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                int failedAttempts = rs.getInt("failed_attempts");
                Timestamp lockoutUntil = rs.getTimestamp("lockout_until");

                // Check if account is locked and lockout has not expired
                if (lockoutUntil != null && lockoutUntil.after(new Timestamp(System.currentTimeMillis()))) {
                    long remainingMinutes = (lockoutUntil.getTime() - System.currentTimeMillis()) / 60000;
                    JOptionPane.showMessageDialog(null, 
                        "Account is locked due to too many failed login attempts.\n" +
                        "Please try again in " + (remainingMinutes + 1) + " minute(s).",
                        "Account Locked", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If lockout has expired, reset the counter
                if (lockoutUntil != null && lockoutUntil.before(new Timestamp(System.currentTimeMillis()))) {
                    String resetSql = "UPDATE position SET failed_attempts = 0, lockout_until = NULL WHERE username = ?";
                    PreparedStatement resetPst = conn.prepareStatement(resetSql);
                    resetPst.setString(1, username);
                    resetPst.executeUpdate();
                    resetPst.close();
                }
            }
            rs.close();
            pst.close();

            // ===== SECURITY CONTROL: Authentication with PreparedStatement (R-04) =====
            // Step 2: Attempt login with parameterized query
            String sql = "SELECT e.employee_id, e.first_name, e.last_name, ur.role_name " +
                "FROM employee e " +
                "JOIN position p ON e.employee_id = p.employee_id " +
                "JOIN user_role ur ON p.position_id = ur.position_id " +
                "WHERE p.username = ? AND p.password = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                // ===== LOGIN SUCCESS =====
                String employeeID = rs.getString("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String role = rs.getString("role_name");

                // Reset failed attempts on successful login
                String resetSql = "UPDATE position SET failed_attempts = 0, lockout_until = NULL WHERE username = ?";
                PreparedStatement resetPst = conn.prepareStatement(resetSql);
                resetPst.setString(1, username);
                resetPst.executeUpdate();
                resetPst.close();

                // Log successful login to audit_log
                logLoginAttempt(conn, username, employeeID, true);

                // ===== SECURITY CONTROL: RBAC — Role-Based Access Control (R-01) =====
                // Route user to appropriate dashboard based on their role
                if ("Admin".equalsIgnoreCase(role)) {
                    Admin_Class admin = new Admin_Class(employeeID, firstName, lastName, null, null, null, null,
                            null, null, null, null, null, null, null,
                            null, username, password, role);
                    admin.login(username, password);
                    dispose();
                } else {
                    // Employee role — restricted dashboard without admin functions
                    Employee_Class emp = new Employee_Class(employeeID, firstName, lastName, null, null, null, 
                            null, null, null, null, null,
                            null, null, null, null, null,
                            username, password);
                    emp.login(username, password);
                    dispose();
                }
            
            } else {
                // ===== LOGIN FAILED — Increment lockout counter =====
                String incrementSql = "UPDATE position SET failed_attempts = failed_attempts + 1 WHERE username = ?";
                PreparedStatement incPst = conn.prepareStatement(incrementSql);
                incPst.setString(1, username);
                incPst.executeUpdate();
                incPst.close();

                // Check if we've hit the lockout threshold
                String checkSql = "SELECT failed_attempts FROM position WHERE username = ?";
                PreparedStatement checkPst = conn.prepareStatement(checkSql);
                checkPst.setString(1, username);
                ResultSet checkRs = checkPst.executeQuery();

                if (checkRs.next()) {
                    int currentAttempts = checkRs.getInt("failed_attempts");

                    if (currentAttempts >= MAX_FAILED_ATTEMPTS) {
                        // Lock the account for LOCKOUT_DURATION_MINUTES
                        String lockSql = "UPDATE position SET lockout_until = ? WHERE username = ?";
                        PreparedStatement lockPst = conn.prepareStatement(lockSql);
                        Timestamp lockUntil = new Timestamp(
                            System.currentTimeMillis() + (LOCKOUT_DURATION_MINUTES * 60 * 1000));
                        lockPst.setTimestamp(1, lockUntil);
                        lockPst.setString(2, username);
                        lockPst.executeUpdate();
                        lockPst.close();

                        // Log the lockout event
                        logLoginAttempt(conn, username, null, false);

                        JOptionPane.showMessageDialog(null, 
                            "Account has been locked after " + MAX_FAILED_ATTEMPTS + " failed attempts.\n" +
                            "Please try again after " + LOCKOUT_DURATION_MINUTES + " minutes.",
                            "Account Locked", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int remaining = MAX_FAILED_ATTEMPTS - currentAttempts;
                        // Log failed attempt
                        logLoginAttempt(conn, username, null, false);

                        JOptionPane.showMessageDialog(null, 
                            "Wrong User ID or Password.\n" +
                            remaining + " attempt(s) remaining before account lockout.",
                            "Login Failed", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    // Username doesn't exist — generic error message (no info leakage)
                    JOptionPane.showMessageDialog(null, "Wrong User ID or Password");
                }
                checkRs.close();
                checkPst.close();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Login Failed: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_logInButtonActionPerformed

    /**
     * AUDIT LOGGING — Security Control 4 (R-03/R-09)
     * Logs login attempts (success and failure) to the audit_log table.
     */
    private void logLoginAttempt(Connection conn, String username, String employeeId, boolean success) {
        try {
            String logSql = "INSERT INTO audit_log (action_type, table_name, record_id, field_changed, " +
                            "old_value, new_value, changed_by, notes) " +
                            "VALUES (?, 'position', ?, 'login_attempt', NULL, ?, ?, ?)";
            PreparedStatement logPst = conn.prepareStatement(logSql);
            logPst.setString(1, success ? "LOGIN_SUCCESS" : "LOGIN_FAILED");
            logPst.setString(2, employeeId != null ? employeeId : "UNKNOWN");
            logPst.setString(3, success ? "Authenticated" : "Failed");
            logPst.setString(4, username);
            logPst.setString(5, success ? "User logged in successfully" : 
                            "Failed login attempt for username: " + username);
            logPst.executeUpdate();
            logPst.close();
        } catch (Exception e) {
            // Logging should not interrupt login flow
            System.err.println("Audit log error: " + e.getMessage());
        }
    }

    private void forgetPassTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPassTitleMouseClicked
        ForgotPassword forgotPass = new ForgotPassword();
        forgotPass.show();
        
        dispose();
    }//GEN-LAST:event_forgetPassTitleMouseClicked

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        logInButton.doClick();
    }//GEN-LAST:event_passwordTextActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel forgetPassTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logInButton;
    private javax.swing.JLabel motorPhTitle1;
    public javax.swing.JPasswordField passwordText;
    private javax.swing.JLabel titlePassword1;
    private javax.swing.JLabel titleUserID1;
    private javax.swing.JTextField userIDText;
    // End of variables declaration//GEN-END:variables
}

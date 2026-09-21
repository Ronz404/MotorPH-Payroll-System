package com.mmdc.motor_ph_portal;

import com.mmdc.motor_ph_portal.AdminAccess.Admin_Class;
import com.mmdc.motor_ph_portal.EmployeeAccess.Employee_Class;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Login extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public Login() {
        initComponents();
        setTitle("Motor PH Employee Portal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        
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
        // login action
        String username = userIDText.getText();
        String password = passwordText.getText();
        
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Fill Out User ID");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Fill Out Password");
            return;
        }

        
        DatabaseConnect dbConnect = new DatabaseConnect() {};
        conn = dbConnect.connect();

        try {
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
                String employeeID = rs.getString("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String role = rs.getString("role_name");

                if ("Admin".equalsIgnoreCase(role)) {
                    Admin_Class admin = new Admin_Class(employeeID, firstName, lastName, null, null, null, null,
                            null, null, null, null, null, null, null,
                            null, username, password,role);
                    admin.login(username, password);
                    dispose();
                } else {
                    Employee_Class emp = new Employee_Class(employeeID, firstName, lastName, null, null, null, 
                            null, null, null, null,null,
                            null, null, null, null,null,
                            username, password);
                    emp.login(username, password);
                    dispose();
                }
            
            } else {
                JOptionPane.showMessageDialog(null, "Wrong User ID or Password");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Login Failed:" + ex.getMessage());
        }
    }//GEN-LAST:event_logInButtonActionPerformed

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

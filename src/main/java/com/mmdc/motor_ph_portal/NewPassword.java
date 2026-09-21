
package com.mmdc.motor_ph_portal;

import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


public class NewPassword extends javax.swing.JFrame {
    private String employeeID;
    DatabaseConnect dbConnect = new DatabaseConnect() {};
    
    public NewPassword() {
        initComponents();
        
    }
    public NewPassword(String email) {
        initComponents();
        setTitle("Password Recovery");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        employeeID = dbConnect.getEmployeeIdByEmail(email); // Get employee ID from the database
        if (employeeID == null) {
            JOptionPane.showMessageDialog(null, "Email not found.");
        } else {
            empIdLabel.setText(employeeID);   
        }
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        passwordRecoveryTitle = new javax.swing.JLabel();
        newPassTitle = new javax.swing.JLabel();
        confirmPassTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        confirmPassField = new javax.swing.JPasswordField();
        newPassField = new javax.swing.JPasswordField();
        backButton = new javax.swing.JButton();
        empIdLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(30, 43, 93));

        passwordRecoveryTitle.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        passwordRecoveryTitle.setForeground(new java.awt.Color(250, 250, 255));
        passwordRecoveryTitle.setText("Password Recovery");

        newPassTitle.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        newPassTitle.setForeground(new java.awt.Color(250, 250, 255));
        newPassTitle.setText("New Password");

        confirmPassTitle.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        confirmPassTitle.setForeground(new java.awt.Color(250, 250, 255));
        confirmPassTitle.setText("Confirm Password");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Monina\\MMDC\\Term 2 24-25\\MotorPH Logo.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(150, 150, 150))
        );

        resetButton.setBackground(new java.awt.Color(253, 56, 29));
        resetButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        resetButton.setForeground(new java.awt.Color(250, 250, 255));
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        confirmPassField.setBackground(new java.awt.Color(30, 43, 93));
        confirmPassField.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        confirmPassField.setForeground(new java.awt.Color(250, 250, 255));

        newPassField.setBackground(new java.awt.Color(30, 43, 93));
        newPassField.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        newPassField.setForeground(new java.awt.Color(250, 250, 255));

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        empIdLabel.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        empIdLabel.setForeground(new java.awt.Color(30, 43, 93));
        empIdLabel.setText("######");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordRecoveryTitle)
                            .addComponent(confirmPassTitle)
                            .addComponent(resetButton)
                            .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(newPassTitle))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(empIdLabel))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(backButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passwordRecoveryTitle)
                .addGap(36, 36, 36)
                .addComponent(empIdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmPassTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetButton)
                .addGap(90, 90, 90)
                .addComponent(backButton)
                .addGap(15, 15, 15))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel Password Recovery?", "Password Recovery", JOptionPane.YES_NO_OPTION);
       
        if (result == JOptionPane.YES_OPTION){
        
        Login loginPage = new Login();
        loginPage.show();
                
        dispose();
        } else if (result == JOptionPane.NO_OPTION){
        NewPassword newPass = new NewPassword ();
        newPass.setVisible(true);
                
        setVisible(false);   
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        String newPassword = new String(newPassField.getPassword());
        String confirmPassword = new String(confirmPassField.getPassword());

    if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        return;
    }

    if (!newPassword.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(null, "Passwords do not match.");
        return;
    }

    if (employeeID == null || employeeID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Employee ID not found. Cannot reset password.");
        return;
    }
    boolean isUpdated = dbConnect.updatePassword(employeeID, newPassword);
    if (isUpdated) {
        JOptionPane.showMessageDialog(null, "Password reset successfully.");
        new Login().setVisible(true);
        dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Password reset failed. No matching record.");
    }
            
    }//GEN-LAST:event_resetButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NewPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JLabel confirmPassTitle;
    private javax.swing.JLabel empIdLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField newPassField;
    private javax.swing.JLabel newPassTitle;
    private javax.swing.JLabel passwordRecoveryTitle;
    private javax.swing.JButton resetButton;
    // End of variables declaration//GEN-END:variables
}

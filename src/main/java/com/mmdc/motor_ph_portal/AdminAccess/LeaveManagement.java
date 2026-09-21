package com.mmdc.motor_ph_portal.AdminAccess;

import com.mmdc.motor_ph_portal.LeaveRecord;
import com.mmdc.motor_ph_util.DatabaseConnect;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.ArrayList; 
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class LeaveManagement extends javax.swing.JFrame {

    Connection conn = null;
    private ButtonGroup buttonGroup;

    DatabaseConnect dbConnect = new DatabaseConnect() {};

    public LeaveManagement() {
        initComponents();

        setTitle("Motor PH Employee Leave Management");
        setSize(780, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        conn = dbConnect.connect();
        loadLeaveRecords();
        time();
        date();

        // BUTTON GROUPING - which ensures that only one button can be selected at a time.
        buttonGroup = new ButtonGroup();
        buttonGroup.add(approve_rb);
        buttonGroup.add(reject_rb);

    }

    public final void time() {
        DateTimeFormatter times = DateTimeFormatter.ofPattern("hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        time.setText(times.format(now));
    }

    public final void date() {
        DateTimeFormatter dates = DateTimeFormatter.ofPattern("MMMM d, y");
        LocalDateTime now = LocalDateTime.now();
        date.setText(dates.format(now));
    }

    public void clear() {
        leaveNum_field.setText("");
        id_field.setText("");
        firstName_field.setText("");
        lastName_field.setText("");
        startdate_field.setText("");
        enddate_field.setText("");
        leaveType_field.setText("");
        approve_rb.setSelected(false);
        reject_rb.setSelected(false);
        buttonGroup.clearSelection();
    }

    public void loadLeaveRecords() {
    ArrayList<LeaveRecord> leaveRecords = dbConnect.leaveMngUserList();
    DefaultTableModel leaveTableModel = (DefaultTableModel) leaveTable.getModel();
    leaveTableModel.setRowCount(0); // clear previous rows

    for (LeaveRecord record : leaveRecords) {
        Object[] row = {
            record.getLeaveId(),
            record.getEmployeeId(),
            record.getFirstName(),
            record.getLastName(),
            record.getStartDate(),
            record.getEndDate(),
            record.getLeaveType(),
            record.getStatus()
        };
        leaveTableModel.addRow(row);
    }
}
    public ArrayList refreshList() {
       ArrayList<LeaveRecord> leaveRecords = dbConnect.leaveMngUserList();
    DefaultTableModel leaveTableModel = (DefaultTableModel) leaveTable.getModel();
    leaveTableModel.setRowCount(0); // clear previous rows

    for (LeaveRecord record : leaveRecords) {
        Object[] row = {
            record.getLeaveId(),
            record.getEmployeeId(),
            record.getFirstName(),
            record.getLastName(),
            record.getStartDate(),
            record.getEndDate(),
            record.getLeaveType(),
            record.getStatus()
        };
        leaveTableModel.addRow(row);
    }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        darkbluepanel = new javax.swing.JPanel();
        leave_title = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        noe_title1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        id_field = new javax.swing.JTextField();
        time = new javax.swing.JLabel();
        lbl_firstName = new javax.swing.JLabel();
        firstName_field = new javax.swing.JTextField();
        lbl_lastName = new javax.swing.JLabel();
        lastName_field = new javax.swing.JTextField();
        lbl_leaveType = new javax.swing.JLabel();
        leaveType_field = new javax.swing.JTextField();
        lbl_status = new javax.swing.JLabel();
        lbl_enddate = new javax.swing.JLabel();
        enddate_field = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        approve_rb = new javax.swing.JRadioButton();
        reject_rb = new javax.swing.JRadioButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        leaveNum = new javax.swing.JLabel();
        leaveNum_field = new javax.swing.JTextField();
        lbl_startdate = new javax.swing.JLabel();
        startdate_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(30, 43, 93));
        mainPanel.setPreferredSize(new java.awt.Dimension(650, 450));

        darkbluepanel.setBackground(new java.awt.Color(250, 250, 255));
        darkbluepanel.setForeground(new java.awt.Color(217, 217, 217));
        darkbluepanel.setPreferredSize(new java.awt.Dimension(260, 60));

        leave_title.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        leave_title.setForeground(new java.awt.Color(92, 101, 138));
        leave_title.setText("EMPLOYEE LEAVE MANAGEMENT");

        javax.swing.GroupLayout darkbluepanelLayout = new javax.swing.GroupLayout(darkbluepanel);
        darkbluepanel.setLayout(darkbluepanelLayout);
        darkbluepanelLayout.setHorizontalGroup(
            darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(darkbluepanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leave_title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        darkbluepanelLayout.setVerticalGroup(
            darkbluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leave_title, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, darkbluepanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo)
                .addGap(15, 15, 15))
        );

        greetings.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        greetings.setForeground(new java.awt.Color(250, 250, 255));
        greetings.setText("Greetings!");

        date.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        date.setForeground(new java.awt.Color(250, 250, 255));
        date.setText("Date ");

        backButton.setBackground(new java.awt.Color(253, 56, 29));
        backButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(250, 250, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        noe_title1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        noe_title1.setForeground(new java.awt.Color(250, 250, 255));
        noe_title1.setText("Employee ID :");

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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leaveTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaveTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(leaveTable);

        id_field.setBackground(new java.awt.Color(250, 250, 255));
        id_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        id_field.setForeground(new java.awt.Color(92, 101, 138));
        id_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_fieldKeyReleased(evt);
            }
        });

        time.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        time.setForeground(new java.awt.Color(250, 250, 255));
        time.setText("Time");

        lbl_firstName.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_firstName.setForeground(new java.awt.Color(250, 250, 255));
        lbl_firstName.setText("First Name :");

        firstName_field.setEditable(false);
        firstName_field.setBackground(new java.awt.Color(250, 250, 255));
        firstName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        firstName_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_lastName.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_lastName.setForeground(new java.awt.Color(250, 250, 255));
        lbl_lastName.setText("Last Name :");

        lastName_field.setEditable(false);
        lastName_field.setBackground(new java.awt.Color(250, 250, 255));
        lastName_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        lastName_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_leaveType.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_leaveType.setForeground(new java.awt.Color(250, 250, 255));
        lbl_leaveType.setText("Leave Type :");

        leaveType_field.setEditable(false);
        leaveType_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveType_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveType_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_status.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_status.setForeground(new java.awt.Color(250, 250, 255));
        lbl_status.setText("Status :");

        lbl_enddate.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_enddate.setForeground(new java.awt.Color(250, 250, 255));
        lbl_enddate.setText("End Date :");

        enddate_field.setEditable(false);
        enddate_field.setBackground(new java.awt.Color(250, 250, 255));
        enddate_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        enddate_field.setForeground(new java.awt.Color(92, 101, 138));

        updateBtn.setBackground(new java.awt.Color(253, 56, 29));
        updateBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(250, 250, 255));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        approve_rb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        approve_rb.setForeground(new java.awt.Color(250, 250, 255));
        approve_rb.setText("Approved");

        reject_rb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        reject_rb.setForeground(new java.awt.Color(250, 250, 255));
        reject_rb.setText("Rejected");

        clearBtn.setBackground(new java.awt.Color(253, 56, 29));
        clearBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(250, 250, 255));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(253, 56, 29));
        deleteBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(250, 250, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        leaveNum.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        leaveNum.setForeground(new java.awt.Color(250, 250, 255));
        leaveNum.setText("Leave No. :");

        leaveNum_field.setEditable(false);
        leaveNum_field.setBackground(new java.awt.Color(250, 250, 255));
        leaveNum_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        leaveNum_field.setForeground(new java.awt.Color(92, 101, 138));

        lbl_startdate.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbl_startdate.setForeground(new java.awt.Color(250, 250, 255));
        lbl_startdate.setText("Start Date :");

        startdate_field.setEditable(false);
        startdate_field.setBackground(new java.awt.Color(250, 250, 255));
        startdate_field.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        startdate_field.setForeground(new java.awt.Color(92, 101, 138));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(darkbluepanel, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(updateBtn)
                                .addGap(18, 18, 18)
                                .addComponent(deleteBtn)
                                .addGap(17, 17, 17)
                                .addComponent(clearBtn)))))
                .addGap(23, 23, 23))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(lbl_lastName)
                                .addGap(39, 39, 39)
                                .addComponent(lastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(lbl_firstName)
                                .addGap(39, 39, 39)
                                .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(noe_title1)
                                .addGap(39, 39, 39)
                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(leaveNum)
                                .addGap(39, 39, 39)
                                .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_leaveType)
                            .addComponent(lbl_status)
                            .addComponent(lbl_enddate)
                            .addComponent(lbl_startdate)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(greetings)))
                .addGap(39, 39, 39)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enddate_field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveType_field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(approve_rb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reject_rb))
                    .addComponent(startdate_field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(darkbluepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(greetings)
                .addGap(16, 16, 16)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(leaveNum))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(leaveNum_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_startdate)
                        .addComponent(startdate_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noe_title1)
                    .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_enddate)
                    .addComponent(enddate_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_firstName)
                            .addComponent(firstName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_leaveType)
                            .addComponent(leaveType_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_lastName)
                            .addComponent(lastName_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_status)
                            .addComponent(approve_rb)
                            .addComponent(reject_rb))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBtn)
                            .addComponent(clearBtn)
                            .addComponent(deleteBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(backButton)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(time))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // back to Employee Portal
        EmployeePortal employeePortal = new EmployeePortal();
        employeePortal.show();

        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // Update data to SQL
        try {
            String status = "";
            if (approve_rb.isSelected()) {
                status = "Approved";
            } else if (reject_rb.isSelected()) {
                status = "Rejected";
            } else {
                JOptionPane.showMessageDialog(this, "Please select a status (Approved or Rejected).", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit if no status is selected
            }

            DefaultTableModel leaveRecord1 = (DefaultTableModel) leaveTable.getModel();
            int i = leaveTable.getSelectedRow();

            // Check if a row is selected
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "Please select a leave record to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit if no row is selected
            }

            // Retrieve and convert leave number
            String leaveNumString = leaveNum_field.getText().trim(); // Get the leave number as a String
            int leaveNum;

            // Convert leave number to int
            try {
                leaveNum = Integer.parseInt(leaveNumString); // Convert the String to an int
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Leave Number", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit if the conversion fails
            }

            // Create a LeaveRecord object with the updated information
            LeaveRecord leaveRecord = new LeaveRecord(
                    leaveNum,
                    leaveRecord1.getValueAt(i, 2).toString(), // Employee ID
                    leaveRecord1.getValueAt(i, 3).toString(), // First Name
                    leaveRecord1.getValueAt(i, 4).toString(), // Last Name
                    leaveRecord1.getValueAt(i, 5).toString(), // Start Date
                    leaveRecord1.getValueAt(i, 6).toString(), // End Date
                    leaveRecord1.getValueAt(i, 7).toString(), // Leave Type
                    status // Status
            );

            // Update the leave record in the database
            if (dbConnect.updateLeaveRecord(leaveRecord)) {
                enddate_field.setText(leaveRecord.getEndDate());
                JOptionPane.showMessageDialog(this, "Leave Record Updated!");
                refreshList();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update leave record.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void leaveTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveTableMouseClicked
        // Selected Row Function

        DefaultTableModel leaveRecord1 = (DefaultTableModel) leaveTable.getModel();
        int i = leaveTable.getSelectedRow();

        try {
            leaveNum_field.setText(leaveRecord1.getValueAt(i, 0).toString());
            id_field.setText(leaveRecord1.getValueAt(i, 1).toString());
            firstName_field.setText(leaveRecord1.getValueAt(i, 2).toString());
            lastName_field.setText(leaveRecord1.getValueAt(i, 3).toString());
            startdate_field.setText(leaveRecord1.getValueAt(i, 4).toString());
            enddate_field.setText(leaveRecord1.getValueAt(i, 5).toString());
            leaveType_field.setText(leaveRecord1.getValueAt(i, 6).toString());
            String statusRB = leaveRecord1.getValueAt(i, 7).toString();
            if (i >= 0) {
                Object statusObj = leaveRecord1.getValueAt(i, 7);

                if (statusObj != null) {

                    if ("Approved".equals(statusRB)) {
                        approve_rb.setSelected(true);
                        reject_rb.setSelected(false);
                    } else if ("Rejected".equals(statusRB)) {
                        reject_rb.setSelected(true);
                        approve_rb.setSelected(false);
                    } else if (statusRB.equals("")) {
                        reject_rb.setSelected(false);
                        approve_rb.setSelected(false);
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_leaveTableMouseClicked

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // Clear Textfield
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
      // DELETE RECORD
    try {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?", "Employee Profile Deleting...", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String leaveNumString = leaveNum_field.getText().trim(); // Get the leave number from the field
            int leaveNum;

            // Convert leave number to int
            try {
                leaveNum = Integer.parseInt(leaveNumString); // Convert the String to an int
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Leave Number", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the conversion fails
            }

            // Create a LeaveRecord object with the leave number
            LeaveRecord leaveRecord = new LeaveRecord(leaveNum, null, null, null, null, null, null, null);

            // Attempt to delete the leave record
            if (dbConnect.deleteLeaveRecord(leaveRecord)) {
                JOptionPane.showMessageDialog(null, "Selected Record Deleted");
                clear(); // Clear the fields after deletion
            } else {
                JOptionPane.showMessageDialog(null, "No record found with the specified leave number.");
            }
        } else if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Employee Profile Deletion Not Successful!");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    refreshList();

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void id_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_fieldKeyReleased
        // serach by employee id
        String employeeId = id_field.getText();
        conn = dbConnect.connect();
        Admin_Class employee = dbConnect.getEmployeeDetails(employeeId);
        if (employee != null) {
            firstName_field.setText(employee.getFirstName());
            lastName_field.setText(employee.getLastName());
        }
        DefaultTableModel attendanceTable = (DefaultTableModel) leaveTable.getModel();
        TableRowSorter<DefaultTableModel> table = new TableRowSorter<>(attendanceTable);
        leaveTable.setRowSorter(table);
        table.setRowFilter(RowFilter.regexFilter(id_field.getText()));

    }//GEN-LAST:event_id_fieldKeyReleased

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
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaveManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaveManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton approve_rb;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearBtn;
    private javax.swing.JPanel darkbluepanel;
    private javax.swing.JLabel date;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField enddate_field;
    private javax.swing.JTextField firstName_field;
    private javax.swing.JLabel greetings;
    private javax.swing.JTextField id_field;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastName_field;
    private javax.swing.JLabel lbl_enddate;
    private javax.swing.JLabel lbl_firstName;
    private javax.swing.JLabel lbl_lastName;
    private javax.swing.JLabel lbl_leaveType;
    private javax.swing.JLabel lbl_startdate;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel leaveNum;
    private javax.swing.JTextField leaveNum_field;
    private javax.swing.JTable leaveTable;
    private javax.swing.JTextField leaveType_field;
    private javax.swing.JLabel leave_title;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel noe_title1;
    private javax.swing.JRadioButton reject_rb;
    private javax.swing.JTextField startdate_field;
    private javax.swing.JLabel time;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}

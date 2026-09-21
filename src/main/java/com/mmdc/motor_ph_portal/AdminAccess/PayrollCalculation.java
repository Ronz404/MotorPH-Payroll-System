
package com.mmdc.motor_ph_portal.AdminAccess;

public class PayrollCalculation extends PayrollDetails {
 
    public PayrollCalculation(String employeeID, String employeeName, double daysRate, double daysWorked,
                          double riceA, double phoneA, double clothingA, double sssC,
                          double philhealthC, double pagibigC, double totalAllowances,
                          double totalDeductions, double grossPay, double tax, double netPay,
                          String payDate, String payperiodId, String startDate,
                          String endDate, String cutOff) {
    super(employeeID, employeeName, daysRate, daysWorked, riceA, phoneA, clothingA,
          sssC, philhealthC, pagibigC, totalAllowances, totalDeductions,
          grossPay, tax, netPay, payDate, payperiodId, startDate, endDate, cutOff);
}
    
    
}


package com.mmdc.motor_ph_portal.AdminAccess;


public abstract class PayrollDetails {
    protected String employeeID;
    protected String employeeName;
    protected double daysRate;
    protected double daysWorked;
    protected double riceA;
    protected double phoneA;
    protected double clothingA;
    protected double sssC;
    protected double philhealthC;
    protected double pagibigC;
    protected double totalAllowances;
    protected double totalDeductions;
    protected double grossPay;
    protected double tax;
    protected double netPay;
    protected String payDate;
    protected String payperiodId;
    protected String startDate;
    protected String endDate;
    protected String cutOff;
    
    // Constructor
    public PayrollDetails(String employeeID, String employeeName, double daysRate, double daysWorked,
                      double riceA, double phoneA, double clothingA, double sssC,
                      double philhealthC, double pagibigC, double totalAllowances,
                      double totalDeductions, double grossPay, double tax, double netPay,
                      String payDate, String payperiodId, String startDate,
                      String endDate, String cutOff) {
         this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.daysRate = daysRate;
        this.daysWorked = daysWorked;
        this.riceA = riceA;
        this.phoneA = phoneA;
        this.clothingA = clothingA;
        this.sssC = sssC;
        this.philhealthC = philhealthC;
        this.pagibigC = pagibigC;
        this.totalAllowances = totalAllowances;
        this.totalDeductions = totalDeductions;
        this.grossPay = grossPay;
        this.tax = tax;
        this.netPay = netPay;
        this.payDate = payDate;
        this.payperiodId = payperiodId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cutOff = cutOff;      
    }

    // Getters
    public String getEmployeeID() { return employeeID; }
    public String getEmployeeName() { return employeeName; }
    public double getDaysRate() { return daysRate; }
    public double getDaysWorked() { return daysWorked; }     
    public double getRiceA() { return riceA; }
    public double getPhoneA() { return phoneA; }
    public double getClothingA() { return clothingA; }
    public double getSssC() { return sssC; }
    public double getTotalAllowances() { return totalAllowances;  }
    public double getTotalDeductions() { return totalDeductions;  }
    public double getPhilhealthC() { return philhealthC;   }
    public double getPagibigC() { return pagibigC;  }
    public double getGrossPay() { return grossPay;  }
    public double getNetPay() { return netPay; }
    public double getTax() { return tax; }
    public String getPayDate() { return payDate; }
    public String getPayperiodId() { return payperiodId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getCutOff() { return cutOff; }
    

                
}

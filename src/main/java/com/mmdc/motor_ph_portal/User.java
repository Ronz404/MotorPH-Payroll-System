
package com.mmdc.motor_ph_portal;

public abstract class User {
    protected String employeeID;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String birthday;
    protected String addressID;
    protected String street;
    protected String barangay;
    protected String city;
    protected String province;
    protected String postalcode;
    protected String email;
    protected String phoneNumber;
    protected String sssNum;
    protected String philHealthNum;
    protected String tinNum;
    protected String pagibigNum;
    
    

    public User(String employeeID, String firstName, String lastName, String birthday, 
                String addressID, String street, String barangay, String city, String province,
                String postalcode, String email, String phoneNumber, String sssNum, 
                String philHealthNum, String tinNum, String pagibigNum, 
                String username, String password) {
        
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.addressID = addressID;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.postalcode = postalcode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sssNum = sssNum;
        this.philHealthNum = philHealthNum;
        this.tinNum = tinNum;
        this.pagibigNum = pagibigNum;
      
    }
    public abstract void login(String username, String password);
    
    public abstract void payrollCalc(String employeeId); {

       
}
    public String getEmployeeID() {
        return employeeID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
        
    }
    public String getBirthday() {
        return birthday;
    }

    public String getAddressID() {
        return addressID;
    }
    public String getStreet() {
        return street;
    }

    public String getBarangay() {
        return barangay;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalcode() {
        return postalcode;
    }

    
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSssNum() {
        return sssNum;
    }

    public String getPhilHealthNum() {
        return philHealthNum;
    }

    public String getTinNum() {
        return tinNum;
    }

    public String getPagibigNum() {
        return pagibigNum;
    }

    

    public abstract String getRole(); // This can be overridden in subclasses
    

    
}

   



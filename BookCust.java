/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author User
 */
public class bookCust implements java.io.Serializable{
    
    String custFName;
    String custLName;
    String custPhoneNumber;
    String custEmail;
    String checkInDate;
    String checkOutDate;
    String roomType;
    String paymentMethod;

    public bookCust() {
    }

    public bookCust(String custFName, String custLName, String custPhoneNumber, String custEmail, String checkInDate, String checkOutDate, String roomType, String paymentMethod) {
        this.custFName = custFName;
        this.custLName = custLName;
        this.custPhoneNumber = custPhoneNumber;
        this.custEmail = custEmail;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.paymentMethod = paymentMethod;
    }

    public String getCustFName() {
        return custFName;
    }

    public String getCustLName() {
        return custLName;
    }

    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setCustFName(String custFName) {
        this.custFName = custFName;
    }

    public void setCustLName(String custLName) {
        this.custLName = custLName;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    
    
    
    
    
    
    
}

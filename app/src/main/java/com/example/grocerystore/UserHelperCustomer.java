package com.example.grocerystore;

public class UserHelperCustomer {
    String name,email,phone,addr,mode_payment;

    public UserHelperCustomer() {
    }

    public UserHelperCustomer(String name, String email, String phone, String addr, String mode_payment) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addr = addr;
        this.mode_payment = mode_payment;
    }

    public String getMode_payment() {
        return mode_payment;
    }

    public void setMode_payment(String mode_payment) {
        this.mode_payment = mode_payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}

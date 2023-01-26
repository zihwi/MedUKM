package com.example.med4ukm;

public class UserHelperClass {

String name, matricno, email,phoneNo, password;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String matricno, String email, String phoneNo, String password) {
        this.name = name;
        this.matricno = matricno;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public UserHelperClass(String matricno, String email, String password) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricno() {
        return matricno;
    }

    public void setMatricno(String matricno) {
        this.matricno = matricno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

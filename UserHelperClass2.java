package com.example.med4ukm;

public class UserHelperClass2 {

    String bookname, contact, nric, date, type;

    public UserHelperClass2() {
    }

    public UserHelperClass2(String bookname, String contact, String nric, String date, String type) {
        this.bookname = bookname;
        this.contact = contact;
        this.nric = nric;
        this.date = date;
        this.type = type;
    }

    public String getName() {
        return bookname;
    }

    public void setName(String bookname) {
        this.bookname = bookname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String  type) {
        this.type = type;
    }
}

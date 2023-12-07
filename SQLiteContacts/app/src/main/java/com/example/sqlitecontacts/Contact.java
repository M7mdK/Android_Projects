package com.example.sqlitecontacts;

public class Contact {
    private int id;
    private String name , phone , country;

    public Contact(int id , String name ,String phone ,String country){
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return id + " " + name + "  " + phone + "\n";
    }
}

package com.rohasoft.www.gcash.Modal;

/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class User {

    String username,password,shone,phone,card,city,name,pincode, id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public User(String username, String password, String shone, String phone) {
        this.username = username;
        this.password = password;
        this.shone = shone;
        this.phone = phone;
    }

    public User(String id,String name , String card,  String phone,String city, String pincode) {
       this.id=id;
        this.phone = phone;
        this.card = card;
        this.city = city;
        this.name = name;
        this.pincode = pincode;
    }

    public String getCard() {
        return card;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPincode() {
        return pincode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getShone() {
        return shone;
    }

    public String getPhone() {
        return phone;
    }
}

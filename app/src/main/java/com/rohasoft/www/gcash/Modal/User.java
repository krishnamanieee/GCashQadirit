package com.rohasoft.www.gcash.Modal;

/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class User {

    String username,password,shop,phone,card,city,name,pincode, id, totallimit, partnerCode, shopId;

    String address1,address2;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.shop = "";
        this.phone = "";
        this.address1="";
        this.address2="";
        this.shopId="";
        this.id="";
        this.card = "";
        this.totallimit="";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.partnerCode="";
    }

    public User(String card) {
        this.card = card;
        this.username = "";
        this.password = "";
        this.shop = "";
        this.phone = "";
        this.id="";

        this.address1="";
        this.address2="";
        this.totallimit="";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.shopId="";
    }

    public User(String shopId,String username, String password, String shop, String phone,String partnerCode,String address1,String address2) {
        this.shopId=shopId;
        this.username = username;
        this.password = password;
        this.shop = shop;
        this.phone = phone;
        this.partnerCode=partnerCode;
        this.address1=address1;
        this.address2=address2;
        this.phone = "";
        this.id="";
        this.card = "";
        this.totallimit="";
        this.city = "";
        this.name = "";
        this.pincode = "";
    }

    public User(String id,String name , String card,  String phone,String city, String pincode, String totallimit) {
       this.id=id;
        this.phone = phone;
        this.card = card;
        this.totallimit=totallimit;
        this.city = city;
        this.name = name;
        this.pincode = pincode;
        this.username = "";
        this.shopId="";
        this.password = "";
        this.shop = "";
        this.partnerCode="";

        this.address1="";
        this.address2="";

    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotallimit(String totallimit) {
        this.totallimit = totallimit;
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

    public String getShop() {
        return shop;
    }

    public String getId() {
        return id;
    }

    public String getTotallimit() {
        return totallimit;
    }

    public String getPhone() {
        return phone;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
}

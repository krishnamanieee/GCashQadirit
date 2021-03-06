package com.rohasoft.www.gcash.modal;

import org.json.JSONObject;

/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class User {

    String username, password, shop, phone, card, city, name, pincode, id, totallimit, partnerCode, shopId, points;
    String address1, address2;
    String otp, invoice, amount, tr, reward, rewardPoint;
    int oldinvoice, partnertToltal, settlementAmt;
    JSONObject mJsonObject;

    public User() {
    }

    public User(String partnerCode, String password, String shop, String phone) {
        this.partnerCode = partnerCode;
        this.password = password;
        this.shop = shop;
        this.phone = phone;
    }

    public User(String shop, String partnerCode, int settlementAmt) {

        this.settlementAmt = settlementAmt;
        this.shop = shop;
        this.partnerCode = partnerCode;
        this.invoice = "";
        this.amount = "";
        this.totallimit = "";
        this.oldinvoice = 0;
        this.card = "";
        this.points = "";
        this.phone = "";
        this.otp = "";
        this.username = "";
        this.password = "";
        this.address1 = "";
        this.address2 = "";
        this.shopId = "";
        this.id = "";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.reward = "";
        this.partnertToltal = partnertToltal;

    }

    public User(String card, String partnerCode, String invoice, String amount, String reward, String rewardPoint, String totallimit, int partnertToltal, String phone, String customerName) {
        this.card = card;
        this.partnerCode = partnerCode;
        this.invoice = invoice;
        this.amount = amount;
        this.totallimit = totallimit;
        this.oldinvoice = 0;
        this.shop = "";
        this.reward = reward;
        this.rewardPoint = rewardPoint;
        this.points = "";
        this.phone = phone;
        this.otp = "";
        this.username = "";
        this.password = "";
        this.address1 = "";
        this.address2 = "";
        this.shopId = "";
        this.id = "";
        this.city = "";
        this.name = customerName;
        this.pincode = "";
        this.partnertToltal = partnertToltal;
    }

    public User(int oldinvoice) {
        this.oldinvoice = oldinvoice;
        this.shop = "";
        this.phone = "";
        this.otp = "";
        this.username = "";
        this.password = "";
        this.address1 = "";
        this.address2 = "";
        this.shopId = "";
        this.id = "";
        this.card = "";
        this.totallimit = "";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.partnerCode = "";
        this.invoice = "";
        this.amount = "";
        this.reward = "";
        this.points = "";
        this.partnertToltal = 0;
    }

    public User(String shop, String phone, String otp) {
        this.shop = shop;
        this.phone = phone;
        this.otp = otp;
        this.invoice = "";
        this.reward = "";
        this.oldinvoice = 0;
        this.points = "";
        this.username = "";
        this.password = "";
        this.address1 = "";
        this.address2 = "";
        this.shopId = "";
        this.id = "";
        this.card = "";
        this.totallimit = "";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.partnerCode = "";
        this.invoice = "";
        this.amount = "";
        this.partnertToltal = 0;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.otp = "";
        this.shop = "";
        this.partnertToltal = 0;
        this.phone = "";
        this.address1 = "";
        this.address2 = "";
        this.shopId = "";
        this.reward = "";
        this.id = "";
        this.card = "";
        this.totallimit = "";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.partnerCode = "";
        this.invoice = "";
        this.points = "";
        this.amount = "";
    }

    public User(String card) {
        this.card = card;
        this.oldinvoice = 0;
        this.username = "";
        this.points = "";
        this.reward = "";
        this.password = "";
        this.shop = "";
        this.phone = "";
        this.id = "";
        this.otp = "";
        this.address1 = "";
        this.address2 = "";
        this.totallimit = "";
        this.city = "";
        this.name = "";
        this.pincode = "";
        this.shopId = "";
        this.username = "";
    }

    public User(String shopName, String amount, String partnerCode, String phoneNo, String address1, String address2, String city,
                String pincode, String username) {
        this.id = "";
        this.phone = phoneNo;
        this.card = "";
        this.totallimit = "";
        this.city = city;
        this.name = "";
        this.pincode = pincode;
        this.invoice = "";
        this.amount = "";
        this.oldinvoice = 0;
        this.amount = amount;
        this.tr = "'";
        this.shopId = "";
        this.password = "";
        this.shop = shopName;
        this.partnerCode = partnerCode;
        this.otp = "";
        this.reward = "";
        this.address1 = address1;
        this.address2 = address2;
        this.username = username;


    }

    public User(String id, String name, String card, String phone, String city, String pincode, String totallimit, JSONObject mJsonObject) {
        this.id = id;
        this.phone = phone;
        this.card = card;
        this.totallimit = totallimit;
        this.city = city;
        this.mJsonObject = mJsonObject;
        this.name = name;
        this.pincode = pincode;
        this.invoice = "";
        this.amount = "'";
        this.oldinvoice = 0;
        this.amount = "";
        this.tr = "'";
        this.shopId = "";
        this.partnertToltal = 0;
        this.password = "";
        this.shop = "";
        this.partnerCode = "";
        this.otp = "";
        this.address1 = "";
        this.address2 = "";
        this.points = "";
        this.username = "";


    }


    public User(String id, String name, String card, String phone, String city, String pincode, String totallimit, String invoice, String amount, String tr, String w) {
        this.id = id;
        this.phone = phone;
        this.card = card;
        this.totallimit = totallimit;
        this.city = city;
        this.reward = "";
        this.name = name;
        this.pincode = pincode;
        this.invoice = invoice;
        this.amount = amount;
        this.amount = amount;
        this.tr = tr;
        this.shopId = "";
        this.password = "";
        this.shop = "";
        this.partnerCode = "";
        this.otp = "";
        this.address1 = "";
        this.address2 = "";

    }


    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public void setamount(String amount) {
        this.amount = amount;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public void setOldinvoice(int oldinvoice) {
        this.oldinvoice = oldinvoice;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
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

    public String getInvoice() {
        return invoice;
    }

    public String getamount() {
        return amount;
    }

    public String getTr() {
        return tr;
    }

    public int getOldinvoice() {
        return oldinvoice;
    }


    public String getPoints() {
        return points;
    }

    public int getPartnertToltal() {
        return partnertToltal;
    }

    public String getAmount() {
        return amount;
    }

    public String getReward() {
        return reward;
    }

    public int getSettlementAmt() {
        return settlementAmt;
    }

    public JSONObject getmJsonObject() {
        return mJsonObject;
    }

    public void setmJsonObject(JSONObject mJsonObject) {
        this.mJsonObject = mJsonObject;
    }
}

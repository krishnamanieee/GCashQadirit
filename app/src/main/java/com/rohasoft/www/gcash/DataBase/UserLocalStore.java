package com.rohasoft.www.gcash.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import com.rohasoft.www.gcash.Modal.User;


/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class UserLocalStore {

    public  static final String SP_NAME="userDetails";
    SharedPreferences userlocalDatabase;

    public UserLocalStore(Context context){
        userlocalDatabase=context.getSharedPreferences(SP_NAME,0);
    }
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor=userlocalDatabase.edit();
        spEditor.putString("shopId",user.getShopId());
        spEditor.putString("username",user.getUsername());
        spEditor.putString("password",user.getPassword());
        spEditor.putString("shop",user.getShop());
        spEditor.putString("phone",user.getPhone());
        spEditor.putString("partnerCode",user.getPartnerCode());
        spEditor.putString("address1",user.getAddress1());
        spEditor.putString("address2",user.getAddress2());
        spEditor.putString("city",user.getCity());

        spEditor.commit();
    }
    public void storeOldInvoice(String s){
        SharedPreferences.Editor spEditor=userlocalDatabase.edit();
        spEditor.putString("oldinvoice",s);

        spEditor.commit();

    }

    public String getOldInvoice(){
        String oldinvoice=userlocalDatabase.getString("oldinvoice","");
        return oldinvoice;
    }
    public User getLoggedUser(){
        String shopId=userlocalDatabase.getString("shopId","");
        String username=userlocalDatabase.getString("username","");
        String password=userlocalDatabase.getString("password","");
        String shop=userlocalDatabase.getString("shop","");
        String phone=userlocalDatabase.getString("phone","");
        String partnerCode=userlocalDatabase.getString("partnerCode","");
        String address1=userlocalDatabase.getString("address1","");
        String address2=userlocalDatabase.getString("address2","");
        String city=userlocalDatabase.getString("city","");

        User storedUser=new User(shopId,username,password,shop,phone,partnerCode,address1,address2,city);
        return storedUser;
    }
}

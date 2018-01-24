package com.rohasoft.www.gcash.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.rohasoft.www.gcash.Modal.User;


/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userlocalDatabase;

    public UserLocalStore(Context context) {
        userlocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userlocalDatabase.edit();
        spEditor.putString("username", user.getUsername());
        spEditor.putString("shop", user.getShop());
        spEditor.putString("phone", user.getPhone());
        spEditor.putString("partnerCode", user.getPartnerCode());
        spEditor.putString("address1", user.getAddress1());
        spEditor.putString("address2", user.getAddress2());
        spEditor.putString("city", user.getCity());
        spEditor.putString("points", user.getPoints());
        spEditor.putString("amount", user.getamount());
        spEditor.putString("pincode", user.getPincode());
        Log.e("TAG Result", user.getAddress1());

        spEditor.commit();
    }

    public void storePoint(String s) {
        SharedPreferences.Editor spEditor = userlocalDatabase.edit();
        spEditor.putString("points", s);

        spEditor.commit();

    }

    public String getPointsshop() {
        String points = userlocalDatabase.getString("points", "");
        return points;
    }

    public void storeamount(String s) {
        SharedPreferences.Editor spEditor = userlocalDatabase.edit();
        spEditor.putString("amount", s);

        spEditor.commit();

    }

    public String getamountsshop() {
        String points = userlocalDatabase.getString("amount", "");
        return points;
    }

    public User getLoggedUser() {
        String username = userlocalDatabase.getString("username", "");
        String shop = userlocalDatabase.getString("shop", "");
        String phone = userlocalDatabase.getString("phone", "");
        String partnerCode = userlocalDatabase.getString("partnerCode", "");
        String address1 = userlocalDatabase.getString("address1", "");
        String address2 = userlocalDatabase.getString("address2", "");
        String city = userlocalDatabase.getString("city", "");
        String ponits = userlocalDatabase.getString("points", "");
        String amount = userlocalDatabase.getString("amount", "");
        String pincode = userlocalDatabase.getString("pincode", "");

        User storedUser = new User(shop, ponits, amount, partnerCode, phone, address1, address2, city, pincode
                , username);
        return storedUser;
    }
}

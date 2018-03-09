package com.rohasoft.www.gcash.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rohasoft.www.gcash.modal.GetUserCallBack;
import com.rohasoft.www.gcash.modal.ServerRequest;
import com.rohasoft.www.gcash.modal.User;
import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.modal.dataBase.UserLocalStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


/**
 * Created by krish on 12/9/2017.
 */

public class CoupenAddActivity extends AppCompatActivity {

    TextView mTextViewQrcode, mTextViewName, mTextViewPhone, mTextViewCity, mTextViewPincode, mTextViewReScan;
    EditText mEditTextAddPoint, mEditTextInvoice;
    Button mButton;
    int randomNumber, tot = 0, temp = 0, invoice = 0;
    int reward, amount;
    RadioGroup mRadioGroup;
    Boolean isShopAvailable = false;
    int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputscreen);
        mTextViewQrcode = (TextView) findViewById(R.id.inputscreen_cardno_textview);
        mTextViewName = (TextView) findViewById(R.id.inputscreen_name_textview);
        mTextViewPhone = (TextView) findViewById(R.id.inputscreen_phone_textview);
        mTextViewCity = (TextView) findViewById(R.id.inputscreen_city_textview);
        mTextViewPincode = (TextView) findViewById(R.id.inputscreen_pincode_textview);
        mTextViewReScan = (TextView) findViewById(R.id.inputscreen_rescan_textview);
        mEditTextAddPoint = (EditText) findViewById(R.id.inputscreen_add_point_textview);
        //mEditTextInvoice = (EditText) findViewById(R.id.inputscreen_add_invoice_textview);
        mRadioGroup = (RadioGroup) findViewById(R.id.radiomode);

        mButton = (Button) findViewById(R.id.inputscreen_submit_button);
        String qrValue = getIntent().getExtras().getString("barcode");
        if (qrValue.length() != 0) {
            mTextViewQrcode.setText(qrValue);
            Log.e("TAG", qrValue);
        }
        UserLocalStore mUserLocalStore = new UserLocalStore(CoupenAddActivity.this);
        id = mUserLocalStore.getId();
        final User user = new User(mTextViewQrcode.getText().toString());
        fetchData(user);


        mTextViewReScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QRScanActivity.class);
                startActivity(intent);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectRoption = mRadioGroup.getCheckedRadioButtonId();
                RadioButton mRadioButton = (RadioButton) findViewById(selectRoption);
                String mOption = mRadioButton.getText().toString();

                if (mOption.equals("Amount")) {
                    try {
                        amount = Integer.valueOf(mEditTextAddPoint.getText().toString().trim());
                        reward = reward + amount;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        reward = reward + Integer.valueOf(mEditTextAddPoint.getText().toString().trim());
                        amount = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (reward != 0) {

                    temp = amount;
                    if (temp <= tot) {

                        Random r = new Random();
                        randomNumber = r.nextInt(9999);

                        int i = tot - temp;

                        //  Toast.makeText(getApplicationContext(),""+temp+"/"+tot+"54///" +i,Toast.LENGTH_SHORT).show();

                        User user1 = new User(user.getShop(), mTextViewPhone.getText().toString(), String.valueOf(randomNumber));

                        sendOtp(user1);


                    } else {
                        Toast.makeText(getApplicationContext(), "out limit please enter correctly", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    private void sendOtp(User user) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.sendOtpInBackground(user, new GetUserCallBack() {
            @Override
            public void Done(User returedUser) {

                Random r = new Random();
                int randomInvoice = r.nextInt(9999999);

                Intent intent = new Intent(getApplicationContext(), OTPConfirmActivity.class);
                intent.putExtra("otp", randomNumber);
                int resultAmt = tot - temp;
                intent.putExtra("total", resultAmt);
                intent.putExtra("invoice",/* mEditTextInvoice.getText().toString()*/ String.valueOf(randomInvoice));
                intent.putExtra("amount", amount);
                intent.putExtra("reward", Integer.valueOf(reward));
                intent.putExtra("rewardPoint", Integer.valueOf(mEditTextAddPoint.getText().toString().trim()));
                intent.putExtra("card", mTextViewQrcode.getText().toString());
                intent.putExtra("cusname", mTextViewName.getText().toString());
                intent.putExtra("phone", mTextViewPhone.getText().toString());

                startActivity(intent);


            }
        });
    }


    private void fetchData(User user) {

        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchCardDataInBackground(user, new GetUserCallBack() {
            @Override
            public void Done(User returedUser) {
                if (returedUser == null) {
                    showErrorMessage("No Data Found :(");
                } else {
                    CardIn(returedUser);
                    //  Toast.makeText(getApplicationContext(), "" + returedUser, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void CardIn(User returedUser) {
        mTextViewName.setText(returedUser.getName());
        mTextViewPhone.setText(returedUser.getPhone());
        mTextViewCity.setText(returedUser.getCity());
        mTextViewPincode.setText(returedUser.getPincode());
        mTextViewQrcode.setText(returedUser.getCard());
        tot = Integer.parseInt(returedUser.getTotallimit());

        JSONObject mJsonObject = returedUser.getmJsonObject();
        if (mJsonObject.has("reward"))
            try {
                reward = Integer.parseInt(mJsonObject.getString("reward"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        if (mJsonObject.has("shops")) {
            try {
                String[] shop = mJsonObject.getString("shops").split(",");
                for (int count = 0; count < shop.length; count++) {
                    if (Integer.parseInt(shop[count]) == id) {
                        isShopAvailable = true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!isShopAvailable) {
            showErrorMessage("Customer Not Under This shop");
        }
        //  invoice=Integer.parseInt(returedUser.getInvoice());
        //Toast.makeText(getApplicationContext(),returedUser.getInvoice(),Toast.LENGTH_SHORT).show();
    }

    private void showErrorMessage(String message) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(CoupenAddActivity.this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();


    }


}

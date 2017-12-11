package com.rohasoft.www.gcash.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rohasoft.www.gcash.DataBase.UserLocalStore;
import com.rohasoft.www.gcash.Modal.GetUserCallBack;
import com.rohasoft.www.gcash.Modal.ServerRequest;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;

/**
 * Created by Ayothi selvam on 12/10/2017.
 */

public class OTPConfrimActivity extends AppCompatActivity {

    EditText mEditTextOTP;
    Button mButtonOTP;
    int noOtp = 0, total = 0, amt = 0;
    int  invoiceNo;
    UserLocalStore userLocalStore;
    String oldinvoice="0";
    String card;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);

        mEditTextOTP = (EditText) findViewById(R.id.otpscreen_otp_editText);
        mButtonOTP = (Button) findViewById(R.id.otpscreen_otp_button);
        userLocalStore = new UserLocalStore(getApplicationContext());
        oldinvoice = "0"+userLocalStore.getOldInvoice();

        invoiceNo = 1 + Integer.parseInt(oldinvoice);


        try {
            noOtp = getIntent().getExtras().getInt("otp");
            total = getIntent().getExtras().getInt("total");
            amt = getIntent().getExtras().getInt("amt");
            card = getIntent().getExtras().getString("card");
            Toast.makeText(getApplicationContext(), "" + invoiceNo +"in old"+oldinvoice, Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Log.e("get OTP", e.toString());
        }

        mButtonOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int screenOtp = Integer.parseInt(mEditTextOTP.getText().toString());
                if (noOtp == screenOtp) {
                    UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                    User user1 = userLocalStore.getLoggedUser();


                    Toast.makeText(getApplicationContext(), card + "/" + user1.getPartnerCode(), Toast.LENGTH_SHORT).show();
                    User user = new User(card, user1.getPartnerCode(), String.valueOf(invoiceNo), String.valueOf(amt), String.valueOf(total));


                    storedata(user);

                }































            }
        });

    }

    private void storedata(User user) {

        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storePointInBackground(user, new GetUserCallBack() {
            @Override
            public void Done(User returedUser) {

                    Toast.makeText(getApplicationContext(), "old invoice" + invoiceNo, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                    startActivity(intent);

            }
        });

    }


}

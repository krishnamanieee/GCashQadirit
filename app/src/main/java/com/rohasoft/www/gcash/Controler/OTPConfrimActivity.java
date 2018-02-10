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
    int noOtp = 0, total = 0, amount = 0, reward = 0, rewardPoint = 0;

    String card, invoice, cusname,phone;
    int shopPoint, old;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_otpscreen);

        mEditTextOTP = (EditText) findViewById(R.id.otpscreen_otp_editText);
        mButtonOTP = (Button) findViewById(R.id.otpscreen_otp_button);


        try {
            noOtp = getIntent().getExtras().getInt("otp");
            total = getIntent().getExtras().getInt("total");
            amount = getIntent().getExtras().getInt("amount");
            reward = getIntent().getExtras().getInt("reward");
            rewardPoint = getIntent().getExtras().getInt("rewardPoint");
            card = getIntent().getExtras().getString("card");
            invoice = getIntent().getExtras().getString("invoice");
            cusname = getIntent().getExtras().getString("cusname");
            phone = getIntent().getExtras().getString("phone");
            //Toast.makeText(getApplicationContext(), "" + card, Toast.LENGTH_SHORT).show();
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
                    old = Integer.parseInt(user1.getamount());
                    shopPoint = old + amount;

                    //   Toast.makeText(getApplicationContext(), total + "/" + amt, Toast.LENGTH_SHORT).show();
                    User user = new User(card, user1.getPartnerCode(), String.valueOf(invoice),
                            String.valueOf(amount), String.valueOf(reward), String.valueOf(rewardPoint), String.valueOf(total), shopPoint,phone);


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
                UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                User user1 = userLocalStore.getLoggedUser();


                userLocalStore.storeamount(String.valueOf(shopPoint));

                Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                intent.putExtra("cusname", cusname);
                intent.putExtra("card", card);
               // Log.e("OTP to Success", card + amount);
                intent.putExtra("invoice", invoice);
                intent.putExtra("amt", String.valueOf(amount));
                intent.putExtra("reward", String.valueOf(reward));
                intent.putExtra("rewardPoint", String.valueOf(rewardPoint));
                startActivity(intent);

            }
        });


    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    public void onBackPressed() {
        onBackPressed();
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            System.exit(0);

        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }


}

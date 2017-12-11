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

    String card,invoice,cusname;


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
            amt = getIntent().getExtras().getInt("amt");
            card = getIntent().getExtras().getString("card");
            invoice = getIntent().getExtras().getString("invoice");
            cusname = getIntent().getExtras().getString("cusname");
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


                    //   Toast.makeText(getApplicationContext(), total + "/" + amt, Toast.LENGTH_SHORT).show();
                    User user = new User(card, user1.getPartnerCode(), String.valueOf(invoice), String.valueOf(amt), String.valueOf(total));


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
                Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                intent.putExtra("cusname",cusname);
                intent.putExtra("card",card);
                Log.e("OTP to Success",card+amt);
                intent.putExtra("invoice",invoice);
                intent.putExtra("amt",String.valueOf(amt));
                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;

    }


}

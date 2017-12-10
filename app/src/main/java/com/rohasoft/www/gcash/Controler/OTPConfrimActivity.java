package com.rohasoft.www.gcash.Controler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rohasoft.www.gcash.R;

/**
 * Created by Ayothi selvam on 12/10/2017.
 */

public class OTPConfrimActivity extends AppCompatActivity {

    EditText mEditTextOTP;
    Button mButtonOTP;
    int noOtp=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);

        mEditTextOTP=(EditText) findViewById(R.id.otpscreen_otp_editText);
        mButtonOTP=(Button)findViewById(R.id.otpscreen_otp_button);
        try {
            noOtp=getIntent().getExtras().getInt("otp");
            Toast.makeText(getApplicationContext(),""+noOtp,Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.e("get OTP",e.toString());
        }

        mButtonOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int screenOtp=Integer.parseInt(mEditTextOTP.getText().toString());
                if (noOtp == screenOtp){
                    Toast.makeText(getApplicationContext(),""+noOtp,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

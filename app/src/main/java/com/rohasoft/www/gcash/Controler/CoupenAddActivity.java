package com.rohasoft.www.gcash.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rohasoft.www.gcash.Modal.GetUserCallBack;
import com.rohasoft.www.gcash.Modal.ServerRequest;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;

import java.util.Random;


/**
 * Created by krish on 12/9/2017.
 */

public class CoupenAddActivity extends AppCompatActivity {

    TextView mTextViewQrcode, mTextViewName, mTextViewPhone, mTextViewCity, mTextViewPincode, mTextViewReScan;
    EditText mEditTextAddPoint,mEditTextInvoice;
    Button mButton;
    int randomNumber, tot=0, temp=0,invoice=0;
    String ponit;



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
        mEditTextInvoice = (EditText) findViewById(R.id.inputscreen_add_invoice_textview);

        mButton = (Button) findViewById(R.id.inputscreen_submit_button);
        String qrValue = getIntent().getExtras().getString("barcode");
        if (qrValue.length() != 0) {
            mTextViewQrcode.setText(qrValue);
            Log.e("TAG", qrValue);
        }

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
                ponit = mEditTextAddPoint.getText().toString();

                if (ponit.length() > 0 && ponit.length() < 6) {

                    temp = Integer.parseInt(mEditTextAddPoint.getText().toString());
                  if (temp <= tot) {

                        Random r = new Random();
                        randomNumber = r.nextInt(9999);

                        int i=tot-temp;

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

                    Intent intent = new Intent(getApplicationContext(), OTPConfrimActivity.class);
                    intent.putExtra("otp", randomNumber);
                    int resultAmt=tot-temp;
                    intent.putExtra("total", resultAmt);
                    intent.putExtra("invoice", mEditTextInvoice.getText().toString());
                    intent.putExtra("amt", temp);
                    intent.putExtra("card", mTextViewQrcode.getText().toString());
                    intent.putExtra("cusname", mTextViewName.getText().toString());

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
                    showErrorMessage();
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
        tot=Integer.parseInt(returedUser.getTotallimit());
      //  invoice=Integer.parseInt(returedUser.getInvoice());
       //Toast.makeText(getApplicationContext(),returedUser.getInvoice(),Toast.LENGTH_SHORT).show();
    }

    private void showErrorMessage() {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(CoupenAddActivity.this);
        builder.setMessage("No Data Found :(");
        builder.setPositiveButton("ok", null);
        builder.show();

    }


}

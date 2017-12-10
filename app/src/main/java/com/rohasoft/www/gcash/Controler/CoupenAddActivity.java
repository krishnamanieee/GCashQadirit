package com.rohasoft.www.gcash.Controler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.www.gcash.Modal.GetUserCallBack;
import com.rohasoft.www.gcash.Modal.ServerRequest;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by krish on 12/9/2017.
 */

public class CoupenAddActivity extends AppCompatActivity {

    TextView mTextViewQrcode,mTextViewName,mTextViewPhone,mTextViewCity,mTextViewPincode,mTextViewReScan;
    EditText mEditTextAddPoint;
    Button mButton;

    private static final String URL_DATA = "http://app.qadirit.com/fetchdata.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputscreen);
        mTextViewQrcode = (TextView) findViewById(R.id.inputscreen_cardno_textview);
        mTextViewName=(TextView) findViewById(R.id.inputscreen_name_textview);
        mTextViewPhone=(TextView) findViewById(R.id.inputscreen_phone_textview);
        mTextViewCity=(TextView) findViewById(R.id.inputscreen_city_textview);
        mTextViewPincode=(TextView) findViewById(R.id.inputscreen_pincode_textview);
        mTextViewReScan=(TextView) findViewById(R.id.inputscreen_rescan_textview);
        mEditTextAddPoint=(EditText) findViewById(R.id.inputscreen_add_point_textview);

        mButton=(Button) findViewById(R.id.inputscreen_submit_button);
        String qrValue = getIntent().getExtras().getString("barcode");
        if (qrValue.length() != 0) {
            mTextViewQrcode.setText(qrValue);
            Log.e("TAG",qrValue);
        }

        User  user=new User(mTextViewQrcode.getText().toString());
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
                String ponit=mEditTextAddPoint.getText().toString();

                if (ponit.length() > 0 && ponit.length() <6){

                    Random r = new Random();
                    int randomNumber = r.nextInt(9999);
                    String s=String.valueOf(randomNumber);
                    Toast.makeText(getApplicationContext(),""+randomNumber,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),OTPConfrimActivity.class);
                    intent.putExtra("otp",randomNumber);
                    startActivity(intent);
                }
            }
        });
    }



    private void fetchData(User user) {

        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.fetchCardDataInBackground(user, new GetUserCallBack() {
            @Override
            public void Done(User returedUser) {
                if (returedUser == null){
                    showErrorMessage();
                }else {
                    CardIn(returedUser );
                    Toast.makeText(getApplicationContext(),""+returedUser,Toast.LENGTH_SHORT).show();
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
    }

    private void showErrorMessage() {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(CoupenAddActivity.this);
        builder.setMessage("No Data Found :(");
        builder.setPositiveButton("ok", null);
        builder.show();

    }


}

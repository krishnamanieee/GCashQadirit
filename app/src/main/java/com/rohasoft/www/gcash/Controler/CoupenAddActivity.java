package com.rohasoft.www.gcash.Controler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rohasoft.www.gcash.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by krish on 12/9/2017.
 */

public class CoupenAddActivity extends AppCompatActivity {

    TextView mTextViewQrcode,mTextView;
    Button mButton;

    private static final String URL_DATA = "http://app.qadirit.com/fetchdata.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputscreen);
        mTextViewQrcode = (TextView) findViewById(R.id.inputscreen_cardno_textview);
        mTextView=(TextView) findViewById(R.id.inputscreen_pincode_textview);
        mButton=(Button) findViewById(R.id.login_login_button);
        String qrValue = getIntent().getExtras().getString("barcode");
        if (qrValue.length() != 0) {
            mTextViewQrcode.setText(qrValue);
            Log.e("TAG",qrValue);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }
        });
    }
    private void fetchData() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            mTextView.setText(response);
                            Log.e("JSONObject",response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("card", "7812369452489637");
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}

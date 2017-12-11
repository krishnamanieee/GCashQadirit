package com.rohasoft.www.gcash.Controler;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.rohasoft.www.gcash.DataBase.UserLocalStore;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView mTextViewShopName,mTextViewPartnerCard,mTextViewPhone,mTextViewAddress1,mTextViewAddress2,mTextViewCity;

    public static final int REQUEST_CODE = 100;

    UserLocalStore userLocalStore;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mTextViewShopName=(TextView)findViewById(R.id.shopname_textview);
        mTextViewPartnerCard=(TextView)findViewById(R.id.partner_card_textview);
        mTextViewPhone=(TextView)findViewById(R.id.phone_textview);
        mTextViewAddress1=(TextView)findViewById(R.id.address1_textview);
        mTextViewAddress2=(TextView)findViewById(R.id.address2_textview);
        mTextViewCity=(TextView)findViewById(R.id.city_textview);


        userLocalStore=new UserLocalStore(this);
        User user=userLocalStore.getLoggedUser();
        mTextViewShopName.setText(user.getShop());
        mTextViewPartnerCard.setText(user.getPartnerCode());
        mTextViewPhone.setText(user.getPhone());
        mTextViewAddress1.setText(user.getAddress1());
        mTextViewAddress2.setText(user.getAddress2());
        mTextViewCity.setText(user.getCity());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_qrcode) {
            Intent intent = new Intent(MainActivity.this, QRScanActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }

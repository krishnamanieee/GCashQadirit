package com.rohasoft.www.gcash.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rohasoft.www.gcash.DataBase.UserLocalStore;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ayothi selvam on 12/10/2017.
 */

public class SuccessActivity extends AppCompatActivity {

    TextView mTextViewCustomer,mTextViewCudtomerCard
            ,mTextViewShopName,mTextViewCard,mTextViewPoint
            ,mTextViewInvoice,mTextViewDate;

    String cusname="",card="",amt="",invoive="",ponit="'";
    Button mButtonDone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        mTextViewCustomer=(TextView)findViewById(R.id.success_customer_name_textview);
        mTextViewCudtomerCard=(TextView)findViewById(R.id.success_card_textview);
        mTextViewShopName=(TextView)findViewById(R.id.success_shop_name_textview);
        mTextViewCard=(TextView)findViewById(R.id.success_partner_card_no_textview);
        mTextViewPoint=(TextView)findViewById(R.id.success_point_textview);
        mTextViewInvoice=(TextView)findViewById(R.id.success_invoice_textview);
        mTextViewDate=(TextView)findViewById(R.id.success_date_textview);

        mButtonDone=(Button)findViewById(R.id.success_done_button);



        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String date1=dateFormat.format(date);

        try {
            cusname = getIntent().getExtras().getString("cusname");
            card = getIntent().getExtras().getString("card");
            amt = getIntent().getExtras().getString("amt");
            invoive = getIntent().getExtras().getString("invoice");
            Log.e("'TDG",cusname+card+amt+invoive);

        }catch (Exception e){

        }
        UserLocalStore userLocalStore=new UserLocalStore(this);

        User user=userLocalStore.getLoggedUser();
        mTextViewCustomer.setText(cusname);
        mTextViewCudtomerCard.setText(card);
        mTextViewShopName.setText(user.getShop());
        mTextViewCard.setText(user.getPartnerCode());
        mTextViewPoint.setText(amt);
        mTextViewInvoice.setText(invoive);
        mTextViewDate.setText(date1);
        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}

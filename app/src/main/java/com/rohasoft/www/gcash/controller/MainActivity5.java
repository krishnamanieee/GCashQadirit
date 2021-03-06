package com.rohasoft.www.gcash.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rohasoft.www.gcash.modal.dataBase.UserLocalStore;
import com.rohasoft.www.gcash.modal.GetUserCallBack;
import com.rohasoft.www.gcash.modal.ServerRequest;
import com.rohasoft.www.gcash.modal.User;
import com.rohasoft.www.gcash.R;


public class MainActivity5 extends AppCompatActivity {

    Toolbar mToolbar;
    TextView mTextViewShopName, mTextViewPartnerCard, mTextViewPhone, mTextViewAddress1, mTextViewAddress2, mTextViewCity, mTextViewAmount;

    public static final int REQUEST_CODE = 100;

    UserLocalStore userLocalStore;

    Button mButtonSettlemrnt;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        //mToolbar = (Toolbar) findViewById(R.id.app_bar);
       // setSupportActionBar(mToolbar);
        mTextViewShopName = (TextView) findViewById(R.id.shopname_textview);
        mTextViewPartnerCard = (TextView) findViewById(R.id.partner_card_textview);
        mTextViewPhone = (TextView) findViewById(R.id.phone_textview);
        mTextViewAddress1 = (TextView) findViewById(R.id.address1_textview);
        mTextViewAddress2 = (TextView) findViewById(R.id.address2_textview);
        mTextViewCity = (TextView) findViewById(R.id.city_textview);
        mTextViewAmount = (TextView) findViewById(R.id.ponits_text_view);
        mButtonSettlemrnt = (Button) findViewById(R.id.apply_settlement_btn);


        userLocalStore = new UserLocalStore(this);
        final User user = userLocalStore.getLoggedUser();
        mTextViewShopName.setText(user.getShop());
        mTextViewPartnerCard.setText(user.getPartnerCode());
        mTextViewPhone.setText(user.getPhone());
        mTextViewAddress1.setText(user.getAddress1());
        mTextViewAddress2.setText(user.getAddress2());
        mTextViewCity.setText(user.getCity());
        mTextViewAmount.setText(userLocalStore.getamountsshop());
        Log.e("TAG Result", user.getPhone());
//        findViewById(R.id.settlement_history_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent mIntent = new Intent(MainActivity5.this, SettlementHistoryActivity.class);
//                mIntent.putExtra("PartnerCode", mTextViewPartnerCard.getText().toString());
//                startActivity(mIntent);
//            }
//        });
        mButtonSettlemrnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shop = mTextViewShopName.getText().toString();
                String PartnerCard = mTextViewPartnerCard.getText().toString();
                int settlementAmt = Integer.parseInt(mTextViewAmount.getText().toString());

                Log.e("points from main", settlementAmt + "'");

                User user1 = new User(shop, PartnerCard, settlementAmt);
                ServerRequest serverRequest = new ServerRequest(MainActivity5.this);
                serverRequest.storesettlementInBackground(user1, new GetUserCallBack() {
                    @Override
                    public void Done(User returedUser) {

                        userLocalStore.storeamount("0");
                        mTextViewAmount.setText("0");
                        //  Toast.makeText(getApplicationContext(),userLocalStore.getPointsshop(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    public void onBackPressed() {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_qrcode) {
            Intent intent = new Intent(MainActivity5.this, QRScanActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

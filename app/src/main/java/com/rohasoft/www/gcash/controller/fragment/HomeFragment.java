package com.rohasoft.www.gcash.controller.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.controller.CoupenAddActivity;
import com.rohasoft.www.gcash.controller.MainActivity5;
import com.rohasoft.www.gcash.controller.SettlementHistoryActivity;
import com.rohasoft.www.gcash.modal.GetUserCallBack;
import com.rohasoft.www.gcash.modal.ServerRequest;
import com.rohasoft.www.gcash.modal.User;
import com.rohasoft.www.gcash.modal.dataBase.UserLocalStore;

/**
 * Created by krish on 2/10/2018.
 */

public class HomeFragment extends Fragment {
    Toolbar mToolbar;
    TextView mTextViewShopName, mTextViewPartnerCard, mTextViewPhone, mTextViewAddress1, mTextViewAddress2, mTextViewCity, mTextViewAmount;

    public static final int REQUEST_CODE = 100;

    UserLocalStore userLocalStore;

    Button mButtonSettlemrnt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mTextViewShopName = (TextView) v.findViewById(R.id.shopname_textview);
        mTextViewPartnerCard = (TextView) v.findViewById(R.id.partner_card_textview);
        mTextViewPhone = (TextView) v.findViewById(R.id.phone_textview);
        mTextViewAddress1 = (TextView) v.findViewById(R.id.address1_textview);
        mTextViewAddress2 = (TextView) v.findViewById(R.id.address2_textview);
        mTextViewCity = (TextView) v.findViewById(R.id.city_textview);
        mTextViewAmount = (TextView) v.findViewById(R.id.ponits_text_view);
        mButtonSettlemrnt = (Button) v.findViewById(R.id.apply_settlement_btn);


        userLocalStore = new UserLocalStore(getActivity());
        final User user = userLocalStore.getLoggedUser();
        mTextViewShopName.setText(user.getShop());
        mTextViewPartnerCard.setText(user.getPartnerCode());
        mTextViewPhone.setText(user.getPhone());
        mTextViewAddress1.setText(user.getAddress1());
        mTextViewAddress2.setText(user.getAddress2());
        mTextViewCity.setText(user.getCity());
        mTextViewAmount.setText(userLocalStore.getamountsshop());
        Log.e("TAG Result", user.getPhone());
//        v.findViewById(R.id.settlement_history_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent mIntent = new Intent(getActivity(), SettlementHistoryActivity.class);
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
                if (settlementAmt != 0) {
                    Log.e("points from main", settlementAmt + "'");

                    User user1 = new User(shop, PartnerCard, settlementAmt);
                    ServerRequest serverRequest = new ServerRequest(getActivity());
                    serverRequest.storesettlementInBackground(user1, new GetUserCallBack() {
                        @Override
                        public void Done(User returedUser) {

                            userLocalStore.storeamount("0");
                            mTextViewAmount.setText("0");
                            //  Toast.makeText(getApplicationContext(),userLocalStore.getPointsshop(),Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {

                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setMessage("Settlement Amount Zero.");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.setCancelable(false);
                    builder.show();


                }

            }
        });


        return v;
    }
}

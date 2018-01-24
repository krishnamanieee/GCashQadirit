package com.rohasoft.www.gcash.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rohasoft.www.gcash.DataBase.UserLocalStore;
import com.rohasoft.www.gcash.Modal.GetSettlementCallBack;
import com.rohasoft.www.gcash.Modal.ServerRequest;
import com.rohasoft.www.gcash.Modal.Settlement;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.view.adapter.SettlementHistoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krish on 1/23/2018.
 */

public class SettlementHistoryActivity extends AppCompatActivity {
    List<Settlement> list;
    ListView listView;

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);
        ;
        UserLocalStore mUserLocalStore = new UserLocalStore(this);
        User mUser = mUserLocalStore.getLoggedUser();
        list = new ArrayList<>();
        arrayList = new ArrayList<>();
        User user = new User(getIntent().getStringExtra("PartnerCode"), "", "", "");

        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.settlementhistoryBackground(user, new GetSettlementCallBack() {
            @Override
            public void Done(Settlement returedSettlement) {
                if (returedSettlement == null) {
                    showErrorMessage();
                } else {
                    settlementHistory(returedSettlement);
                }
            }
        });
    }

    private void showErrorMessage() {
    }

    private void settlementHistory(Settlement settlement) {
        String s = settlement.getDueDate();
        try {
            JSONObject mJsonObject=new JSONObject(s);
            JSONArray mJsonArray=mJsonObject.getJSONArray("settlementHistory");
            SettlementHistoryAdapter mAdapter=new SettlementHistoryAdapter(SettlementHistoryActivity.this,mJsonArray);
            listView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    public void onBackPressed() {
       finish();
    }
}

package com.rohasoft.www.gcash.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.controller.SettlementHistoryActivity;
import com.rohasoft.www.gcash.modal.GetSettlementCallBack;
import com.rohasoft.www.gcash.modal.ServerRequest;
import com.rohasoft.www.gcash.modal.Settlement;
import com.rohasoft.www.gcash.modal.User;
import com.rohasoft.www.gcash.modal.dataBase.UserLocalStore;
import com.rohasoft.www.gcash.view.adapter.SettlementHistoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krish on 2/10/2018.
 */

public class SettlementHistoryFragment extends Fragment {
    List<Settlement> list;
    ListView listView;

    ArrayList<String> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settlement_history, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        ;
        UserLocalStore mUserLocalStore = new UserLocalStore(getActivity());
        User mUser = mUserLocalStore.getLoggedUser();
        list = new ArrayList<>();
        arrayList = new ArrayList<>();
        User user = new User(mUserLocalStore.getLoggedUser().getPartnerCode(), "", "", "");

        ServerRequest serverRequest = new ServerRequest(getActivity());
        serverRequest.settlementhistoryBackground(user, new GetSettlementCallBack() {
            @Override
            public void Done(Settlement returnedSettlement) {
                if (returnedSettlement == null) {
                    showErrorMessage();
                } else {
                    settlementHistory(returnedSettlement);
                }
            }
        });
        return v;
    }
    private void showErrorMessage() {
    }

    private void settlementHistory(Settlement settlement) {
        String s = settlement.getDueDate();
        try {
            JSONObject mJsonObject=new JSONObject(s);
            JSONArray mJsonArray=mJsonObject.getJSONArray("settlementHistory");
            SettlementHistoryAdapter mAdapter=new SettlementHistoryAdapter(getActivity(),mJsonArray);
            listView.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

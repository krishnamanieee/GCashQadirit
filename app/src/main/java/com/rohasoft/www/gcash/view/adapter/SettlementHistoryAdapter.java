package com.rohasoft.www.gcash.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rohasoft.www.gcash.Modal.Commons;
import com.rohasoft.www.gcash.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by krish on 1/24/2018.
 */

public class SettlementHistoryAdapter extends BaseAdapter {

    Context mContext;
    JSONArray mJsonArray;

    public SettlementHistoryAdapter(Context mContext, JSONArray mJsonArray) {
        this.mContext = mContext;
        this.mJsonArray = mJsonArray;
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView mDateTextView, mAmountTextView, mPayStatusTextView;
        if (view == null) {
            view = LayoutInflater.from(mContext).
                    inflate(R.layout.settlement_history_listview_item, viewGroup, false);
        }

        mDateTextView = (TextView) view.findViewById(R.id.txt_date);
        mAmountTextView = (TextView) view.findViewById(R.id.txt_payamt);
        mPayStatusTextView = (TextView) view.findViewById(R.id.txt_pay_status);

        try {
            if (mJsonArray.getJSONObject(i).has("date")) {
                mDateTextView.setText(Commons.getDate(mJsonArray.getJSONObject(i).getString("date")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (mJsonArray.getJSONObject(i).has("ponit")) {
                mAmountTextView.setText(mJsonArray.getJSONObject(i).getString("ponit"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (mJsonArray.getJSONObject(i).has("status")) {
                mPayStatusTextView.setText(mJsonArray.getJSONObject(i).getString("status"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}

package com.rohasoft.www.gcash.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.modal.Commons;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by krish on 2/3/2018.
 */

public class TransactionAdapter extends BaseAdapter {
    Context mContext;
    JSONArray mJsonArray;

    public TransactionAdapter(Context mContext, JSONArray mJsonArray) {
        this.mContext = mContext;
        this.mJsonArray = mJsonArray;
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void update(JSONArray mJsonArray) {
        this.mJsonArray = mJsonArray;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView mCustomerNameTextView, mCityTextView, mPhoneTextView, mDateTextView, mPointTextView;
        ImageView mImageView;
        if (view == null) {
            view = LayoutInflater.from(mContext).
                    inflate(R.layout.transaction_row, viewGroup, false);
        }
        mCustomerNameTextView = (TextView) view.findViewById(R.id.txt_cus_name);
        mCityTextView = (TextView) view.findViewById(R.id.txt_cus_city);
        mPhoneTextView = (TextView) view.findViewById(R.id.txt_cus_phone);
        mDateTextView = (TextView) view.findViewById(R.id.txt_cus_date);
        mPointTextView = (TextView) view.findViewById(R.id.txt_cus_point);


        try {
            if (mJsonArray.getJSONObject(i).has("customercard")) {
                mPhoneTextView.setText(mJsonArray.getJSONObject(i).getString("customercard"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (mJsonArray.getJSONObject(i).has("amount")) {
                if (mJsonArray.getJSONObject(i).getString("amount").equals("0")) {
                    if (mJsonArray.getJSONObject(i).has("reward"))
                        mPointTextView.setText("Reward: " + mJsonArray.getJSONObject(i).getString("reward"));
                } else {

                    mPointTextView.setText("Amount: " + mJsonArray.getJSONObject(i).getString("amount"));

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (mJsonArray.getJSONObject(i).has("date")) {
                mDateTextView.setText(Commons.getDate(mJsonArray.getJSONObject(i).getString("date")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (mJsonArray.getJSONObject(i).has("customerName")) {
                mCustomerNameTextView.setText(mJsonArray.getJSONObject(i).getString("customerName"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

}

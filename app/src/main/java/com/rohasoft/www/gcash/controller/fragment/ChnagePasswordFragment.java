package com.rohasoft.www.gcash.controller.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.controller.ChangePassword;
import com.rohasoft.www.gcash.controller.MainActivity;
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

public class ChnagePasswordFragment extends Fragment {
    EditText mNewPasswordEditText, mConfrimPasswordEditText;
    Button mDone;

    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_change_password, container, false);
        mNewPasswordEditText = (EditText) v.findViewById(R.id.new_password);
        mConfrimPasswordEditText = (EditText) v.findViewById(R.id.confrim_password);
        mDone = (Button) v.findViewById(R.id.change_password_done);

        userLocalStore = new UserLocalStore(getActivity());
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNewPassword = mNewPasswordEditText.getText().toString().trim();
                String strConfrimPassword = mConfrimPasswordEditText.getText().toString().trim();

                if (!strNewPassword.isEmpty()) {
                    if (strNewPassword.length() == 4) {
                        if (!strConfrimPassword.isEmpty()) {
                            if (strConfrimPassword.length() == 4) {
                                Settlement mSettlement=new Settlement(userLocalStore.getLoggedUser().getPartnerCode(),strNewPassword);
                                ServerRequest serverRequest = new ServerRequest(getActivity());
                                serverRequest.chnagePasswordBackground(mSettlement, new GetSettlementCallBack() {
                                    @Override
                                    public void Done(Settlement returedSettlement) {
                                        if (returedSettlement!=null){
                                            showErrorMessage("Update Password Successfully",true);

                                        }
                                    }
                                });
                            } else {
                                mConfrimPasswordEditText.setError("Check Password length should be 4");
                            }
                        } else {
                            mConfrimPasswordEditText.setError("Enter the Confrim Password");
                        }
                    } else {
                        mNewPasswordEditText.setError("Check Password length should be 4");
                    }
                } else {
                    mNewPasswordEditText.setError("Enter the New Password");
                }
            }
        });

        return v;
    }
    private void showErrorMessage(String message,final Boolean isTrue) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(isTrue)
                  getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        builder.show();


    }

}

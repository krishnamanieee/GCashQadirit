package com.rohasoft.www.gcash.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rohasoft.www.gcash.R;
import com.rohasoft.www.gcash.modal.GetSettlementCallBack;
import com.rohasoft.www.gcash.modal.GetUserCallBack;
import com.rohasoft.www.gcash.modal.ServerRequest;
import com.rohasoft.www.gcash.modal.Settlement;
import com.rohasoft.www.gcash.modal.User;
import com.rohasoft.www.gcash.modal.dataBase.UserLocalStore;

/**
 * Created by krish on 3/11/2018.
 */

public class ChangePassword extends AppCompatActivity {

    EditText mNewPasswordEditText, mConfrimPasswordEditText;
    Button mDone;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mNewPasswordEditText = (EditText) findViewById(R.id.new_password);
        mConfrimPasswordEditText = (EditText) findViewById(R.id.confrim_password);
        mDone = (Button) findViewById(R.id.change_password_done);

        userLocalStore = new UserLocalStore(ChangePassword.this);
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
                                ServerRequest serverRequest = new ServerRequest(ChangePassword.this);
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

    }
    private void showErrorMessage(String message,final Boolean isTrue) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ChangePassword.this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               if(isTrue)
                   finish();
            }
        });
        builder.show();


    }
}

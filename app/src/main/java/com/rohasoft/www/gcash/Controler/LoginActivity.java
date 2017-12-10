package com.rohasoft.www.gcash.Controler;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rohasoft.www.gcash.DataBase.UserLocalStore;
import com.rohasoft.www.gcash.Modal.GetUserCallBack;
import com.rohasoft.www.gcash.Modal.ServerRequest;
import com.rohasoft.www.gcash.Modal.User;
import com.rohasoft.www.gcash.R;


public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextEmail, mEditTextPassword;
    private Button mButtonLogin;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mEditTextEmail = (EditText) findViewById(R.id.login_email_edit_text);
        mEditTextPassword = (EditText) findViewById(R.id.login_password_edit_text);
        mButtonLogin = (Button) findViewById(R.id.login_login_button);
        userLocalStore=new UserLocalStore(this);
        
        logIn();




    }

    private void logIn() {

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mEditTextEmail.getText().toString().trim();
                String pass=mEditTextPassword.getText().toString();
                User user=new User(username,pass);
                authenticate(user);
            }
        });
    }

    private void authenticate(User user) {

        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void Done(User returedUser) {
                if (returedUser == null){
                    showErrorMessage();
                }else {
                    logUserIn(returedUser );
                }
            }
        });
    }

    private void logUserIn(User returedUser) {
        userLocalStore.storeUserData(returedUser);


        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


    private void showErrorMessage() {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Incorrect user details :(");
        builder.setPositiveButton("ok", null);
        builder.show();

    }

    @Override
    public void onStart() {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.CAMERA}, 0);


        }

        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {

            }
        }
    }

}

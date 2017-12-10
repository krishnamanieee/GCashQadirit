package com.rohasoft.www.gcash.Modal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class ServerRequest {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://app.qadirit.com/";
    Context mContext;

    public ServerRequest(Context context) {
        this.mContext=context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("processing");
        progressDialog.setMessage("Please wait...");
    }

    public void fetchUserDataInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callBack).execute();
    }

    public void fetchCardDataInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new FetchCardDataAsyncTask(user, callBack).execute();
    }

    public void sendOtpInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new sendOtpAsyncTask(user, callBack).execute();
    }

    private class FetchCardDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallBack getUserCallBack;

        public FetchCardDataAsyncTask(User user, GetUserCallBack getUserCallBack) {
            this.user = user;
            this.getUserCallBack = getUserCallBack;
        }

        @Override
        protected User doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("card", user.card));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "fetchcard.php");

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jobject = new JSONObject(result);

                if (jobject.length() == 0) {
                    returnedUser = null;
                } else {
                    String id = jobject.getString("id");
                    String name = jobject.getString("name");
                    String phone = jobject.getString("phone");
                    String city = jobject.getString("city");
                    String pincode = jobject.getString("pincode");
                    String card = jobject.getString("card");
                    String totallimit = jobject.getString("totallimit");

                        returnedUser = new User(id,name,card,phone,city,pincode,totallimit) ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User user) {
            progressDialog.dismiss();
            getUserCallBack.Done(user);
            super.onPostExecute(user);
        }
    }
    private class sendOtpAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallBack getUserCallBack;

        public sendOtpAsyncTask(User user, GetUserCallBack getUserCallBack) {
            this.user = user;
            this.getUserCallBack = getUserCallBack;
        }

        @Override
        protected User doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("otp", user.card));
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "otpconfrim.php");

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jobject = new JSONObject(result);

                if (jobject.length() == 0) {
                    returnedUser = null;
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User user) {
            progressDialog.dismiss();
            getUserCallBack.Done(user);
            super.onPostExecute(user);
        }
    }

    private class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallBack userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallBack userCallback) {

            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("email", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "newlogin.php");

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                Log.e("TAG Result",result);
                JSONObject jobject = new JSONObject(result);

                if (jobject.length() == 0) {
                    returnedUser = null;
                } else {
                    String shopId = jobject.getString("id");
                    String shop = jobject.getString("shop");
                    String username = jobject.getString("username");
                    String password = jobject.getString("password");
                    String phone = jobject.getString("phone");
                    String partnerCode = jobject.getString("partnercode");
                    String address1 = jobject.getString("address1");
                    String address2 = jobject.getString("address2");
                    returnedUser = new User(shopId,username, password, shop, phone,partnerCode,address1,address2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User user) {
            progressDialog.dismiss();
            userCallback.Done(user);
            super.onPostExecute(user);
        }
    }
}

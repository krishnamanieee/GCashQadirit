package com.rohasoft.www.gcash.Modal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.rohasoft.www.gcash.DataBase.UserLocalStore;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ayothi selvam on 12/7/2017.
 */

public class ServerRequest {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://admin.idusmarket.com/api/";
    Context mContext;

    public ServerRequest(Context context) {
        this.mContext = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("processing");
        progressDialog.setMessage("Please wait...");
    }

    public void fetchUserDataInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callBack).execute();
    }

    public void storePointInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new StorePointAsyncTask(user, callBack).execute();
    }

    public void storesettlementInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new StoresettlementAsyncTask(user, callBack).execute();
    }

    public void fetchCardDataInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new FetchCardDataAsyncTask(user, callBack).execute();
    }
    public void sendOtpInBackground(User user, GetUserCallBack callBack) {
        progressDialog.show();
        new sendOtpAsyncTask(user, callBack).execute();
    }
    public void settlementhistoryBackground(User settlement, GetSettlementCallBack callBack) {
        progressDialog.show();
        new settlementhistoryAsyncTask(settlement, callBack).execute();
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

            Log.e("input", dataToSend.toString());
            Log.e("input", post.getURI().toString());
            User returnedUser = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                Log.e("result", result);
                JSONObject jobject = new JSONObject(result);

                if (jobject.length() == 0) {
                    returnedUser = null;
                } else {
                    String id = jobject.getString("id");
                    String name = jobject.getString("name");
                    String phone = jobject.getString("phone1");
                    String city = jobject.getString("city");
                    String pincode = jobject.getString("pincode");
                    String card = jobject.getString("card");
                    String totallimit = jobject.getString("totallimit");
                    String reward = jobject.getString("reward");

                    returnedUser = new User(id, name, card, phone, city, pincode, totallimit, reward);
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

            dataToSend.add(new BasicNameValuePair("otp", user.otp));
            dataToSend.add(new BasicNameValuePair("phone", user.phone));
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "otp.php");

            Log.e("input", dataToSend.toString());
            Log.e("input", post.getURI().toString());
            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jobject = new JSONObject(result);

                Log.e("result", result);
                if (jobject.length() == 0) {
                    returnedUser = null;
                } else {
                    int i = Integer.parseInt(jobject.getString("invoice"));
                    UserLocalStore userLocalStore = new UserLocalStore(mContext);

                    returnedUser = new User(i);


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

    private class StorePointAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallBack getUserCallBack;

        public StorePointAsyncTask(User user, GetUserCallBack getUserCallBack) {
            this.user = user;
            this.getUserCallBack = getUserCallBack;
        }

        @Override
        protected User doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String date1 = dateFormat.format(date);

            dataToSend.add(new BasicNameValuePair("partnercode", user.partnerCode));
            dataToSend.add(new BasicNameValuePair("customercard", user.card));
            Log.e("db card", user.partnerCode + "==" + user.card);
            dataToSend.add(new BasicNameValuePair("invoice", user.invoice));
            dataToSend.add(new BasicNameValuePair("amount", user.amount));
            dataToSend.add(new BasicNameValuePair("reward", user.reward));
            dataToSend.add(new BasicNameValuePair("rewardPoint", user.rewardPoint));
            dataToSend.add(new BasicNameValuePair("date", date1));
            dataToSend.add(new BasicNameValuePair("totallimit", user.totallimit));
            dataToSend.add(new BasicNameValuePair("phone", user.phone));

            dataToSend.add(new BasicNameValuePair("partnerttoltal", String.valueOf(user.partnertToltal)));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "storepoint.php");
            Log.e("input", dataToSend.toString());
            Log.e("input", post.getURI().toString());

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();

                String result = EntityUtils.toString(entity);
                Log.e("repansone", result);
                JSONObject jobject = new JSONObject(result);

                if (jobject.length() == 0) {
                    returnedUser = null;
                } else {
                    returnedUser = new User(user.partnerCode);


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

    private class StoresettlementAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallBack getUserCallBack;

        public StoresettlementAsyncTask(User user, GetUserCallBack getUserCallBack) {
            this.user = user;
            this.getUserCallBack = getUserCallBack;
        }

        @Override
        protected User doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date date = new Date();
            String date1 = dateFormat.format(date);

            dataToSend.add(new BasicNameValuePair("partnercode", user.partnerCode));
            dataToSend.add(new BasicNameValuePair("shop", user.shop));

            dataToSend.add(new BasicNameValuePair("amount", String.valueOf(user.settlementAmt)));
            dataToSend.add(new BasicNameValuePair("date", date1));
            Log.e("db card", user.partnerCode + "==" + user.shop + user.settlementAmt);


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "settlement.php");

            Log.e("input", dataToSend.toString());
            Log.e("input", post.getURI().toString());
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
                    returnedUser = new User(user.partnerCode);


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

            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));
            Log.e("TAG", user.username + user.password);


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "newlogin.php");

            Log.e("input", dataToSend.toString());
            Log.e("input", post.getURI().toString());
            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                Log.e("TSG", result);

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
                    String city = jobject.getString("city");
                    String ponits = jobject.getString("point");
                    String pincode = jobject.getString("pincode");
                    returnedUser = new User(shop, ponits, partnerCode, phone, address1, address2, city, pincode, username);
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

    private class settlementhistoryAsyncTask extends AsyncTask<Void, Void, Settlement> {
        User settlement;
        GetSettlementCallBack userCallback;

        public settlementhistoryAsyncTask(User settlement, GetSettlementCallBack userCallback) {

            this.settlement = settlement;
            this.userCallback = userCallback;
        }

        @Override
        protected void onPostExecute(Settlement settlement) {
            progressDialog.dismiss();
            super.onPostExecute(settlement);
            userCallback.Done(settlement);
        }

        @Override
        protected Settlement doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("partnercode", settlement.partnerCode));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "settlement_history.php");

            Log.e("input", dataToSend.toString());
            Log.e("input", post.getURI().toString());
            Settlement returnedSettlement = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                Log.e("Response", result);

                JSONObject jobject = new JSONObject(result);

                if (jobject.length() == 0) {
                    returnedSettlement = null;
                } else {

                    returnedSettlement = new Settlement(jobject.toString(), "", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedSettlement;
        }


    }


}

package com.rohasoft.www.gcash.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScanActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private static final String URL_DATA = "http://admin.idusmarket.com/api/fetchdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }


    @Override
    public void handleResult(Result rawResult) {
        Intent intent=new Intent(getApplicationContext(),CoupenAddActivity.class);
        intent.putExtra("barcode", rawResult.getText());

        startActivity(intent);

       /* if (rawResult.toString().length() == 16){

           Intent intent = new Intent();
            intent.putExtra("barcode", rawResult.getText());
            setResult(RESULT_OK, intent);
            finish();
           Intent intent=new Intent(getApplicationContext(),CoupenAddActivity.class);
           intent.putExtra("barcode", rawResult.getText());

           startActivity(intent);


        }
        else {
          Toast.makeText(getApplicationContext(),rawResult.toString(),Toast.LENGTH_SHORT).show();
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Oops..:( ");
            builder.setMessage(" Invalid QR Code please try another code");
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();

                }
            });
            builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            });
            AlertDialog alert1 = builder.create();
            alert1.show();

        }

        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
*/
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }



}

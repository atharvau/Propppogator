package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view


        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        TextView et = new TextView(this);
        et.setText("Scan any ProperGator enabled advertisements .....\n Keep your hands steady!");
        et.setTextColor(Color.WHITE);
        et.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = 150;

        rootView.addView(et,params);

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
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {
        // Do something with the result here
        Log.v("kkkk", result.getContents()); // Prints scan results
        Log.v("uuuu", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        Intent i=new Intent(ScanActivity.this,ScrollingActivity.class);
        i.putExtra("UID",result.getContents().toString());
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();


        String s= databaseReference.child("Ad").child(result.getContents().toString()).push().getKey();

        databaseReference.child("Ad").child(result.getContents().toString()).child("Analytics").child(s).child("name").setValue(si.sname);
        databaseReference.child("Ad").child(result.getContents().toString()).child("Analytics").child(s).child("age").setValue(si.sage);
        databaseReference.child("Ad").child(result.getContents().toString()).child("Analytics").child(s).child("gender").setValue(si.sgender);
        databaseReference.child("Ad").child(result.getContents().toString()).child("Analytics").child(s).child("city").setValue(si.scity);

        startActivity(i);
        onBackPressed();

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}
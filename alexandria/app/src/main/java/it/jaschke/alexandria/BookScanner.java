package it.jaschke.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Jude Ben on 7/9/2015.
 */
public class BookScanner  extends ActionBarActivity implements ZXingScannerView.ResultHandler{

    //I am following a tutorial here https://github.com/dm77/barcodescanner

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);

         startCamera();


    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }


    public  void startCamera(){

        runOnUiThread(new Thread(new Runnable() {
            public void run() {

                mScannerView.startCamera();

            }
        }));
    }


    @Override
    public void handleResult(Result rawResult) {
        Intent scannerResultIntent = new Intent();
        scannerResultIntent.putExtra("ean_code", rawResult.getText());
        setResult(AddBook.mySCAN, scannerResultIntent);
        finish();
    }
}

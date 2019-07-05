package com.huangstudio.liteui_android;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.huangstudio.liteui.LSButton;
import com.huangstudio.liteui.dialog.SweetAlertDialog;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    LSButton mLSButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLSButton = findViewById(R.id.switch_button);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        TestLBSwitch();
    }

    // 异步switch
    private void TestLBSwitch(){
        mLSButton.setEnableToggleWait(true);
        mLSButton.setOnToggleWaitListener(new LSButton.ToggleWaitListener() {
            @Override
            public void toggleStart() {
                Log.i(TAG, "toggleStart: ");
                Random random = new Random();
                int n = random.nextInt(2);
                boolean result = n % 2 == 0;
                if (result) {
                    Log.i(TAG, "toggleStart: 等待3s");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLSButton.toggle();
                        }
                    }, 3000);
                }
            }

            @Override
            public void toggleTimeOut() {
                Log.i(TAG, "toggleTimeOut: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "通信失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button://普通dialog

                break;
            case R.id.button2://警告dialog
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.button3://禁止dialog
                break;
            case R.id.button4://等待dialog
                final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(true);
                pDialog.show();
                break;
            case R.id.button5://自定义dialog
                break;
                case xxxxxx
        }
    }

}

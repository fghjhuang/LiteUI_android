package com.huangstudio.liteui_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.huangstudio.liteui.LiteSwitch;
import com.huangstudio.liteui.LiteAlertDialog;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    LiteSwitch mLiteSwitch;
 //   ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();
  //  SynchronousQueue queue = new SynchronousQueue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLiteSwitch = findViewById(R.id.switch_button);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        TestLBSwitch();
    }

    // 异步switch
    private void TestLBSwitch(){
        mLiteSwitch.setEnableToggleWait(true);
        mLiteSwitch.setOnToggleWaitListener(new LiteSwitch.ToggleWaitListener() {
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
                            mLiteSwitch.toggle();
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
            case R.id.button7://普通dialog
                startActivity(new Intent(this,LiteDialogActivity.class));
                break;

            case R.id.button6:
                startActivity(new Intent(this,LiteCircleProgressActivity.class));
                break;
        }
    }

}

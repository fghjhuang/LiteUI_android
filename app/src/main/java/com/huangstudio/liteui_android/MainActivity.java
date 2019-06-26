package com.huangstudio.liteui_android;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.huangstudio.liteui.LSButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    LSButton mLSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLSButton = findViewById(R.id.switch_button);
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

    public void test(View v) {
        final ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**
                 * 通过这样一个监听事件，我们就可以获取
                 * 到ValueAnimator每一步所产生的值。
                 *
                 * 通过调用getAnimatedValue()获取到每个时间因子所产生的Value。
                 * */
                Integer value = (Integer) animation.getAnimatedValue();
                ((Button)findViewById(R.id.button)).setText(value + "");
            }
        });
        animator.start();
    }

}

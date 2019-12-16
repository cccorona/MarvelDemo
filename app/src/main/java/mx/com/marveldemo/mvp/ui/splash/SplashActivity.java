package mx.com.marveldemo.mvp.ui.splash;


import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import mx.com.marveldemo.R;
import mx.com.marveldemo.base.BaseActivity;
import mx.com.marveldemo.mvp.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {


    public static final String TAG = SplashActivity.class.getSimpleName();
    private static final long SPLASH_SCREEN_DELAY = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Thread.currentThread()
                        .setName(TAG);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}

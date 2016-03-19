package io.leftshift.weatherforecast.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.util.DateUtil;

/**
 * Created by Shahid on 13/3/2016.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!isTaskRoot()) {
            // This stops from opening again from the Splash screen when minimized
            finish();
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity if it has not been minimized
                if (!isFinishing()) {
                    Intent intent = new Intent(SplashActivity.this, LandingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}

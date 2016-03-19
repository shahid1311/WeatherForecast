package io.leftshift.weatherforecast.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.util.DateUtil;

/**
 * Created by Shahid on 3/13/2016.
 */
public class LandingActivity extends AppCompatActivity {

    private static final Logger logger = new Logger(LandingActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}

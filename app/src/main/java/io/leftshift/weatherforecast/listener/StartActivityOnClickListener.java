package io.leftshift.weatherforecast.listener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by shahid on 9/1/16.
 */
public class StartActivityOnClickListener implements View.OnClickListener {

    private Activity context;
    private Class<AppCompatActivity> newActivityClassName;
    private boolean finishCurrentActivity;

    public void StartActivityOnClickListener(Activity context,
                                             Class<AppCompatActivity> newActivityClassName,
                                             boolean finishCurrentActivity){
        this.context = context;
        this.newActivityClassName = newActivityClassName;
        this.finishCurrentActivity = finishCurrentActivity;
    }
    @Override
    public void onClick(View v) {
        Intent newActivityIntent = new Intent(context, newActivityClassName.getClass());
        context.startActivity(newActivityIntent);
        if (finishCurrentActivity)
            context.finish();
    }
}

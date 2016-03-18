package io.leftshift.weatherforecast.ui.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import io.leftshift.weatherforecast.logger.Logger;


public class CustomButton extends Button {
    private final Logger logger = new Logger(getClass().getName());

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(),
                "font/" + "HelveticaNeue-Regular.ttf");
        setTypeface(myTypeface);
    }

}

package io.leftshift.weatherforecast.ui.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import io.leftshift.weatherforecast.logger.Logger;


public class CustomTextView extends TextView {
    private final Logger logger = new Logger(getClass().getName());

    public CustomTextView(Context context) {
        this(context, null, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }



    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(),
                "font/" + "HelveticaNeue-Regular.ttf");
        setTypeface(myTypeface);
    }

}

package io.leftshift.weatherforecast.ui.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import io.leftshift.weatherforecast.logger.Logger;

public class CustomEditText extends EditText {
    private final Logger logger = new Logger(getClass().getName());

    public CustomEditText(Context context) {
        this(context, null, android.R.attr.editTextStyle);
    }


    public CustomEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(),
                "font/" + "HelveticaNeue-Regular.ttf");
        setTypeface(myTypeface);
    }

}

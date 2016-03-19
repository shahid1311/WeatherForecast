package io.leftshift.weatherforecast.ui.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.util.WeatherUtility;

/**
 * Created by shahid on 9/1/16.
 */
public class CircularPageIndicator implements ViewPager.OnPageChangeListener {

    private Context context;
    private LinearLayout pageIndicatorLinearLayout;

    public CircularPageIndicator(Context context, LinearLayout linearLayout) {
        this.context = context;
        this.pageIndicatorLinearLayout = linearLayout;
    }

    /**
     * @param numberOfPages
     * @param defaultIndicator
     * @return
     */
    public LinearLayout createPageIndicator(int numberOfPages, int defaultIndicator) {
        if (pageIndicatorLinearLayout != null) {
            for (int i = 0; i < numberOfPages; i++) {
                View view = new View(context);
                float width = WeatherUtility.convertDpToPixel(10, context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) width, (int) width);
                float margin = WeatherUtility.convertDpToPixel(5, context);
                layoutParams.setMargins((int) margin, (int) margin, (int) margin, (int) margin);
                view.setLayoutParams(layoutParams);

                if (i == defaultIndicator) {
                    setSelectedBackground(view);
                } else {
                    setDeFaultBackground(view);
                }
                pageIndicatorLinearLayout.addView(view);
            }
        }
        return pageIndicatorLinearLayout;
    }

    /**
     * Set the View Background to White to show the current page position
     *
     * @param view - View of which the background needs to be set
     */
    private void setSelectedBackground(View view) {
        if (view != null) {
            view.setBackgroundResource(R.drawable.rounded_white);
        }
    }

    /**
     * Set the View Background to transparent black 50 percent to show other pages
     *
     * @param view - View of which the background needs to be set
     */
    private void setDeFaultBackground(View view) {
        if (view != null) {
            view.setBackgroundResource(R.drawable.rounded_transparent_black);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Nothing to do here
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < pageIndicatorLinearLayout.getChildCount(); i++) {
            View childCircularView = pageIndicatorLinearLayout.getChildAt(i);
            if (i == position) {
                setSelectedBackground(childCircularView);
            } else {
                setDeFaultBackground(childCircularView);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Nothing to do here
    }
}

package io.leftshift.weatherforecast.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shahid on 3/19/2016.
 */
public class DateUtil {

    /**
     * This method returns 14 days from the current date in an arraylist of string in the following format
     * date format - dd MMM yyyy
     *
     * @return ArrayList<String> which contains 14 dates
     */
    public static ArrayList<String> getFutureDates() {
        ArrayList<String> dateList = new ArrayList<>();
        SimpleDateFormat df2 = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateList.add(df2.format(date));
        for (int i = 0; i < 13; i++) {
            cal.add(Calendar.DATE, 1); // add 10 days
            date = cal.getTime();
            dateList.add(df2.format(date));
        }

        return dateList;
    }
}

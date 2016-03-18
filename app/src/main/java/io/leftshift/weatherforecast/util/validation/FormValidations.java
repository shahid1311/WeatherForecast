package io.leftshift.weatherforecast.util.validation;

import android.text.TextUtils;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains Generic Validation for Form Fields
 * Created by shahid on 15/10/15.
 */
public class FormValidations {

    /**
     * This method is used to check if teh Edit Text or Text =View is null or is empty
     * @param view - EditText or TextView to check for empty
     * @return true of field is null or empty
     */
    public static boolean isFieldEmpty(TextView view) {
        return view == null || TextUtils.isEmpty(view.getText().toString().trim());
    }

    /**
     * This method checks for mobile number validation.
     * You can also use Android's phone match pattern described below
     * if you are accepting country code in one field itself
     * android.util.Patterns.PHONE.matcher(phone).matches();
     * @param view - EditText or TextView used for mobile number
     * @param mobileNoLimit - Limit for mobile number
     * @return
     */
    public static boolean isValidMobileNumber(TextView view, int mobileNoLimit) {
        String mobileNumber = view.getText().toString().trim();
        return TextUtils.isDigitsOnly(mobileNumber) && mobileNumber.length() == mobileNoLimit;
    }

    /**
     * This method checks for Email validation and uses Android's email id pattern by default
     * @param view - EditText or TextView used for Email id
     * @return
     */
    public static boolean isValidEmail(TextView view) {
        return !isFieldEmpty(view) && android.util.Patterns.EMAIL_ADDRESS.matcher(
                view.getText().toString().trim()).matches();
    }

}

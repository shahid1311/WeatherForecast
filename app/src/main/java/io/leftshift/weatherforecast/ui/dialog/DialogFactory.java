package io.leftshift.weatherforecast.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * This DialogFactory class is used return objects of different Custom Dialogs
 * Created by shahid on 7/5/15.
 */
public class DialogFactory {

    /**
     * This method return Instance of CustomProgressDialog.
     * @param context
     * @param message
     * @return
     */
    public static CustomProgressDialog createProgressDialog(Context context,
                                                      String message) {
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(context,message);
        return customProgressDialog;
    }


    /**
     * This method returns AlertDialog with a neutral button
     * @param context - Context of the current activity or fragment
     * @param titleText - Text to be displayed in the title
     * @param messageText - Text to be displayed in the dialog
     * @param neutralButtonText
     * @param neutralButtonClickListner
     * @return
     */
     public static AlertDialog createAlertDialogWithNeutralButton(Context context, String titleText, String messageText,
                 String neutralButtonText, DialogInterface.OnClickListener neutralButtonClickListner){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setMessage(messageText)
                .setCancelable(false)
                .setNeutralButton(neutralButtonText, neutralButtonClickListner);

        return  alertDialogBuilder.create();
    }

    /**
     * This method returns AlertDialog with a neutral button
     *
     * @param context                    - Context of the current activity or fragment
     * @param titleText                  - Text to be displayed in the title
     * @param messageText                - Text to be displayed in the dialog
     * @param positiveButtonText
     * @return
     */
    public static AlertDialog createAlertDialogWithPositiveButton(Context context, String titleText, String messageText,
                                                                  String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListner) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setMessage(messageText)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveButtonClickListner);

        return alertDialogBuilder.create();
    }


    /**
     * This method returns default AlertDialog with a Positive and Negative buttons
     * @param context - Context of the current activity or fragment
     * @param titleText - Text to be displayed in the title
     * @param messageText - Text to be displayed in the dialog
     * @param positiveButtonText - Text to be displayed on the positive button
     * @param negativeButtonText - Text to be displayed on the negative button
     * @param positiveButtonClickListner - Listener to be set to the positive button
     * @param negativeButtonClickListner - Listener to be set to the negative button
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithButtons(Context context, String titleText, String messageText,
                       String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener positiveButtonClickListner,
                       DialogInterface.OnClickListener negativeButtonClickListner){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog message
        alertDialogBuilder
                .setTitle(titleText)
                .setMessage(messageText)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, positiveButtonClickListner)
                .setNegativeButton(negativeButtonText, negativeButtonClickListner);

        return  alertDialogBuilder.create();
    }

    /**
     * This method sets a customView to the AlertDialog with positive and negative buttons
     * @param context - Context of the current activity or fragment
     * @param customView - View to be set in the Alert Dialog body
     * @param positiveButtonText - Text to be displayed on the positive button
     * @param negativeButtonText - Text to be displayed on the negative button
     * @param onPositiveButtonClickListner - Listener to be set to the positive button
     * @param onNegativeButtonClickListner - Listener to be set to the negative button
     * @return - Alert Dialog instance
     */
    public static AlertDialog createAlertDialogWithCustomView(Context context, View customView,
                                                           String positiveButtonText, String negativeButtonText,
                                                           DialogInterface.OnClickListener onPositiveButtonClickListner,
                                                           DialogInterface.OnClickListener onNegativeButtonClickListner){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog message
        alertDialogBuilder
                .setView(customView)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, onPositiveButtonClickListner)
                .setNegativeButton(negativeButtonText, onNegativeButtonClickListner);

        return  alertDialogBuilder.create();
    }


    /**
     * This method sets a customView to the AlertDialog without positive and negative buttons
     * @param context
     * @param customView
     * @return
     */
    public static AlertDialog createAlertDialogWithCustomViewNoButtons(Context context, View customView){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog message
        alertDialogBuilder
                .setView(customView)
                .setCancelable(false);

        return  alertDialogBuilder.create();
    }


    /**
     * This method sets a customView to the AlertDialog without positive and negative buttons
     * @param context
     * @param
     * @return
     */
    public static AlertDialog createSingleChoiceAlertDialog(Context context, ArrayList<String> itemList, int selectedItem,
                                                            String alertTitle, String positiveButton,
                                                            DialogInterface.OnClickListener dialogListener){

        String[] list = new String[itemList.size()];
        list = itemList.toArray(list);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog message
        alertDialogBuilder
                .setTitle(alertTitle)
                .setSingleChoiceItems(list, selectedItem, dialogListener);

        return  alertDialogBuilder.create();
    }

}

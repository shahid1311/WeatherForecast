package io.leftshift.weatherforecast.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * This Util class is used for displaying dialogs
 * Created by shahid on 7/5/15.
 */
public class DialogUtil {
    Activity mActivity;

    public DialogUtil(){
        //Default is required
    }

    public DialogUtil(Activity mActivity){
        this.mActivity = mActivity;
    }

    /**
     * This method displays the message passed in a toast dialog.
     * @param toastMessage - Message that needs to be displayed in the Toast
     * @param toastDuration -
     * @return
     */
    public void displayMessageInToast(final String toastMessage, final int toastDuration){
        if(mActivity!=null && !TextUtils.isEmpty(toastMessage)){
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivity, toastMessage, toastDuration).show();
                }
            });
        }
    }


/*    *//**
     * This method creates an AlertDialog to confirm delete contacts
     * @param contactName - Name to be displayed in the alert dialog
     * @param deleteContactListener - Listener which returns back the delete confirmation
     *//*
    public void showDeleteContactAlertDialog(String contactName
            , DialogInterface.OnClickListener deleteContactListener){
        if(mActivity!=null){
            AlertDialog alertDialog = DialogFactory.createAlertDialogWithButtons(mActivity,
                    mActivity.getString(R.string.delete_contact),
                    mActivity.getString(R.string.delet_contact_message, contactName),
                    mActivity.getString(R.string.delete),
                    mActivity.getString(R.string.cancel),
                    deleteContactListener,
                    getOkDialogOnClickListener());

            alertDialog.show();

            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            if(positiveButton!=null){
                positiveButton.setTextColor(mActivity.getResources()
                        .getColor(android.R.color.holo_red_dark));
            }
        }
    }


    *//**
     *
     * @param countryList
     * @param selectedCountryLocation
     *//*
    public void showCountryCodeDialog(ArrayList<String> countryList, int selectedCountryLocation,
                                      DialogInterface.OnClickListener dialogListener){
        if(mActivity!=null){
            AlertDialog alertDialog = DialogFactory.createSingleChoiceAlertDialog(mActivity,
                    countryList, selectedCountryLocation, mActivity.getString(R.string.select_country),
                    mActivity.getString(R.string.ok),  dialogListener);


            alertDialog.show();
        }
    }*/

    /**
     * This listener is for dismissing the dialog for Ok button
     * @return - DialogInterface.OnClickListener which dismissed the dialog in onClick
     */
    public DialogInterface.OnClickListener getOkDialogOnClickListener(){
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }
}

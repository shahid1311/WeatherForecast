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

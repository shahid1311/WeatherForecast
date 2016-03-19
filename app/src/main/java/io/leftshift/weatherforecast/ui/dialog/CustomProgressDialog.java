package io.leftshift.weatherforecast.ui.dialog;


import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

public class CustomProgressDialog extends ProgressDialog
{
    public CustomProgressDialog(Context context, String message) {
        super(context);
        this.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.setMessage(message);
        this.setCancelable(false);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * Close shown progress dialog
     */
    public void closeDialog() {
        if(isShowing()){
            this.dismiss();
            this.cancel();
        }
    }
}

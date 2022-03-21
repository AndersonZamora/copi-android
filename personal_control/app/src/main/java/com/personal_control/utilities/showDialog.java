package com.personal_control.utilities;

import android.app.ProgressDialog;
import android.content.Context;

import com.personal_control.R;

public class showDialog {
    private final Context mContext;
    private ProgressDialog mDialog;

    public showDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void viewDialog() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string._getting_location));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void dialogDismiss() {
        mDialog.dismiss();
    }
}

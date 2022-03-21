package com.personal_control.viewDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

import com.personal_control.R;

public class dialogView implements contractDialog.successfulDialog {

    private final Context mContext;
    private contractDialog.attachViewDialog attachViewDialog;

    public dialogView(Context mContext) {
        this.mContext = mContext;
    }

    public void messagePermissionLocation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.location);
        builder.setCancelable(false);
        builder.setMessage(R.string.point_work);
        builder.setPositiveButton(R.string.accept, (dialog, which) -> attachViewDialog.viewMessageAcceptLocation());
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            dialog.dismiss();
            attachViewDialog.viewMessageCancelLocation();
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.parseColor("#FF000000"));
    }

    public void messagePermissionCamera() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.camera);
        builder.setCancelable(false);
        builder.setMessage(R.string.scan_code);
        builder.setPositiveButton(R.string.accept, (dialog, which) -> attachViewDialog.viewMessageAcceptCamera());
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            dialog.dismiss();
            attachViewDialog.viewMessageCancelCamera();
        });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.parseColor("#FF000000"));
    }

    @Override
    public void attachView(contractDialog.attachViewDialog attachViewDialog) {
        this.attachViewDialog = attachViewDialog;
    }
}

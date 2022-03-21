package com.personal_control.utilities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.personal_control.view.viewWorking.working_view;


public class return_camera {

    private final Context mContext;

    public return_camera(Context mContext) {
        this.mContext = mContext;
    }

    public boolean checkSelPer() {
        return ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions(working_view view) {
        ActivityCompat.requestPermissions(view, new String[]{Manifest.permission.CAMERA}, 43);
    }
}

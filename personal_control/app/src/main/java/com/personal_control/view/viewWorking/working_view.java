package com.personal_control.view.viewWorking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.personal_control.R;
import com.personal_control.model.dataRegister;
import com.personal_control.utilities.return_camera;
import com.personal_control.utilities.return_location;
import com.personal_control.utilities.return_time;
import com.personal_control.utilities.showDialog;
import com.personal_control.view.viewMap.map_view;
import com.personal_control.viewDialog.contractDialog;
import com.personal_control.viewDialog.dialogView;

public class working_view extends AppCompatActivity implements contractWorking.viewWorking, contractDialog.attachViewDialog {

    private return_time mReturn_time;
    private return_location mReturn_location;
    private return_camera mReturn_camera;
    private String type;
    private String codeLocation;
    private showDialog mShowDialog;
    private dialogView mDialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_view);

        FusedLocationProviderClient mClient = LocationServices.getFusedLocationProviderClient(this);

        mReturn_location = new return_location(this, mClient);
        mReturn_location.set_working_view(this);
        mReturn_camera = new return_camera(getApplicationContext());

        mDialogView = new dialogView(this);
        mDialogView.attachView(this);
        mShowDialog = new showDialog(this);
        mReturn_time = new return_time(this);

        mReturn_location.checkGPS(this);

        Button button_entry = findViewById(R.id.button_entry);
        Button button_exit = findViewById(R.id.button_exit);

        button_entry.setOnClickListener(view -> permissions(getString(R.string.text_entry)));
        button_exit.setOnClickListener(view -> permissions(getString(R.string._exit)));
    }

    private void permissions(String typeRegister) {
        if (mReturn_location.checkSelPer()) {
            if (mReturn_camera.checkSelPer()) {
                type = "";
                type = typeRegister;
                to_register();
            } else {
                mReturn_camera.requestPermissions(this);
            }
        } else {
            mReturn_location.requestPermissions(this);
        }
    }

    @Override
    public void view_location(Double la, Double lo) {
        if (la != null | lo != null) {
            mShowDialog.dialogDismiss();
            assert la != null;
            assert lo != null;

            dataRegister dataRegister = new dataRegister();
            dataRegister.setCode(codeLocation);
            dataRegister.setLatitude(la);
            dataRegister.setLongitude(lo);
            dataRegister.setTypeRegister(type);
            dataRegister.setCheckTime(mReturn_time.time());

            DialogFragment fragment = new map_view(dataRegister);
            fragment.show(getSupportFragmentManager(), "mat_view");
        }
    }

    private void to_register() {
        IntentIntegrator mIntegrator = new IntentIntegrator(working_view.this);
        mIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        mIntegrator.setPrompt("Lector");
        mIntegrator.setCameraId(0);
        mIntegrator.setBeepEnabled(true);
        mIntegrator.setBarcodeImageEnabled(true);
        mIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult mResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (mResult != null) {
            if (mResult.getContents() == null) {
                Toast.makeText(this, "Lector Cancel", Toast.LENGTH_LONG).show();
            } else {
                codeLocation = mResult.getContents();
                mShowDialog.viewDialog();
                mReturn_location.getLocation();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == 101) {
            if (resultCode == RESULT_CANCELED) {
                mReturn_location.checkGPS(this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mReturn_camera.checkSelPer();
            } else {
                mDialogView.messagePermissionLocation();
            }
        }
        if (requestCode == 43) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("ok", "per");
            } else {
                mDialogView.messagePermissionCamera();
            }
        }
    }

    @Override
    public void viewMessageAcceptLocation() {
        mReturn_location.requestPermissions(this);
    }

    @Override
    public void viewMessageCancelLocation() {
        mDialogView.messagePermissionLocation();
    }

    @Override
    public void viewMessageAcceptCamera() {
        mReturn_camera.requestPermissions(this);
    }

    @Override
    public void viewMessageCancelCamera() {
        mDialogView.messagePermissionCamera();
    }

}
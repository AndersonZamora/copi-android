package com.personal_control.utilities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.personal_control.R;
import com.personal_control.view.viewWorking.contractWorking;
import com.personal_control.view.viewWorking.working_view;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class return_location implements contractWorking.workingView {

    private contractWorking.viewWorking mWorkingView;
    private final Context mContext;
    private final FusedLocationProviderClient mClient;

    public return_location(Context mContext, FusedLocationProviderClient mClient) {
        this.mContext = mContext;
        this.mClient = mClient;
    }

    public boolean checkSelPer() {
        return ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions(working_view view) {
        ActivityCompat.requestPermissions(view, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
    }

    @SuppressLint("MissingPermission")
    public void getLocation() {

        mClient.getLastLocation().addOnCompleteListener(task -> {
            Location mLocation = task.getResult();
            if (mLocation != null) {
                try {
                    Geocoder mGeocoder = new Geocoder(mContext, Locale.getDefault());
                    List<Address> addresses = mGeocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
                    mWorkingView.view_location(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void checkGPS(working_view view) {

        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder mBuilder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)
                .setAlwaysShow(true);

        Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(mContext.getApplicationContext())
                .checkLocationSettings(mBuilder.build());

        locationSettingsResponseTask.addOnCompleteListener(task -> {
            try {
                LocationSettingsResponse mResponse = task.getResult(ApiException.class);
                Log.e("response ", String.valueOf(mResponse));
            } catch (ApiException e) {
                if (e.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                    ResolvableApiException mException = (ResolvableApiException) e;
                    try {
                        mException.startResolutionForResult(view, 101);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
                if (e.getStatusCode() == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                    Toast.makeText(mContext, mContext.getString(R.string.setting_not_available), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void set_working_view(contractWorking.viewWorking mWorkingView) {
        this.mWorkingView = mWorkingView;
    }
}

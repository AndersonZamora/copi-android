package com.personal_control.view.viewMap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.personal_control.R;
import com.personal_control.model.dataRegister;

import java.util.Objects;

public class map_view extends DialogFragment implements OnMapReadyCallback {

    private final dataRegister dataRegister;

    public map_view(dataRegister dataRegister) {
        this.dataRegister = dataRegister;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        Button cancel_ = view.findViewById(R.id.cancel_);
        cancel_.setOnClickListener(view1 -> Objects.requireNonNull(getDialog()).dismiss());
        Button register_ = view.findViewById(R.id.register_);
        register_.setOnClickListener(view12 -> {

        });

        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        MarkerOptions markerOptions = new MarkerOptions();
        LatLng sydney = new LatLng(dataRegister.getLatitude(), dataRegister.getLongitude());
        markerOptions.position(sydney);
        markerOptions.title("MAP");
        googleMap.clear();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19));
        googleMap.addMarker(markerOptions);
    }
}
package com.personal_control.model;

public class dataRegister {

    private double latitude, longitude;
    private String nameLocation, checkTime, code, typeRegister;

    public dataRegister() {
    }

    public dataRegister(double latitude, double longitude, String nameLocation, String checkTime, String code, String typeRegister) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nameLocation = nameLocation;
        this.checkTime = checkTime;
        this.code = code;
        this.typeRegister = typeRegister;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeRegister() {
        return typeRegister;
    }

    public void setTypeRegister(String typeRegister) {
        this.typeRegister = typeRegister;
    }
}

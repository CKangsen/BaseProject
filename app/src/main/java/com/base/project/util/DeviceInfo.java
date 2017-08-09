/* Top Secret */
/* 绝密 TOP SECRET, COPYRIGHT © AFMOBI GROUP */
package com.base.project.util;

import android.text.TextUtils;

/**
 * 设备信息类
 * Created by Thinkpad on 2016/10/12.
 */

public class DeviceInfo {
    /**
     * 本地ip地址
     */
    private String ip;
    /**
     * Android设备唯一ID
     */
    private String androidID;
    private String imei;
    private String imsi;
    private String brand;
    private String ua;
    private String mcc;
    private String mnc;
    private String mver;
    /**
     * 当前版本名
     */
    private String cver;
    private String osVersion;
    /**
     * 屏幕分辨率
     */
    private int widthPixels;
    private int heightPixels;


    public DeviceInfo() {
        this.mcc ="";
        this.mnc = "";
        this.imsi = "";
        this.ua = "";
        this.mver = "";
        this.osVersion = "";
        this.widthPixels = 0;
        this.heightPixels = 0;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getAndroidID() {
        return androidID;
    }

    public void setAndroidID(String androidID) {
        this.androidID = androidID;
    }


    public String getImei() {
        if(TextUtils.isEmpty( imei)){
            if(!TextUtils.isEmpty(androidID)){
                return androidID;
            }
            return "no_imei";
        }
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        if(TextUtils.isEmpty( imsi)){
            return "no_sim_card";
        }
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getCver() {
        return cver;
    }

    public void setCver(String cver) {
        this.cver = cver;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getWidthPixels() {
        return widthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }

    public int getHeightPixels() {
        return heightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getMver() {
        return mver;
    }

    public void setMver(String mver) {
        this.mver = mver;
    }
}

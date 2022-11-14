package com.harry.offerwallapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Information {

    @SerializedName("app_name")
    @Expose
    private String appName;

    @SerializedName("appid")
    @Expose
    private Integer appid;

    @SerializedName("placement_id")
    @Expose
    private String placementId;

    @SerializedName("virtual_currency")
    @Expose
    private String virtualCurrency;

    @SerializedName("virtual_currency_sale_enabled")
    @Expose
    private Boolean virtualCurrencySaleEnabled;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("support_url")
    @Expose
    private String supportUrl;

    public Information(String appName, Integer appid, String placementId, String virtualCurrency, Boolean virtualCurrencySaleEnabled, String country, String language, String supportUrl) {
        this.appName = appName;
        this.appid = appid;
        this.placementId = placementId;
        this.virtualCurrency = virtualCurrency;
        this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
        this.country = country;
        this.language = language;
        this.supportUrl = supportUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public Boolean getVirtualCurrencySaleEnabled() {
        return virtualCurrencySaleEnabled;
    }

    public void setVirtualCurrencySaleEnabled(Boolean virtualCurrencySaleEnabled) {
        this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }
}

package com.harry.offerwallapp.model;

import static com.harry.offerwallapp.utils.Constant.APP_ID;
import static com.harry.offerwallapp.utils.Constant.HASH_KEY;
import static com.harry.offerwallapp.utils.Constant.IP;
import static com.harry.offerwallapp.utils.Constant.LOCALE;
import static com.harry.offerwallapp.utils.Constant.OFFER_TYPES;
import static com.harry.offerwallapp.utils.Constant.OS_VERSION;
import static com.harry.offerwallapp.utils.Constant.PAGE;
import static com.harry.offerwallapp.utils.Constant.PHONE_VERSION;
import static com.harry.offerwallapp.utils.Constant.TIMESTAMP;
import static com.harry.offerwallapp.utils.Constant.USER_ID;
import com.google.gson.annotations.SerializedName;
import com.harry.offerwallapp.utils.GenerateHashKey;
import java.util.HashMap;
import java.util.stream.Collectors;


interface OfferQueryRequest {

    HashMap<String, Object> asQueryMap();

    String formatQueryParams(HashMap<String, Object> params);
}

public class OfferRequest implements OfferQueryRequest {

    @SerializedName("appid")
    String appId;

    @SerializedName("ip")
    String ip;

    @SerializedName("locale")
    String locale;

    @SerializedName("offer_types")
    String offerTypes;

    @SerializedName("os_version")
    String osVersion;

    @SerializedName("page")
    String page;

    @SerializedName("phone_version")
    String phoneVersion;

    @SerializedName("timestamp")
    String timestamp;

    @SerializedName("uid")
    String uid;

    @SerializedName("hashKey")
    String hashKey;

    public OfferRequest(String appId, String ip, String locale, String offerTypes, String osVersion, String page, String phoneVersion, String timestamp, String uid, String hashKey) {
        this.appId = appId;
        this.ip = ip;
        this.locale = locale;
        this.offerTypes = offerTypes;
        this.osVersion = osVersion;
        this.page = page;
        this.phoneVersion = phoneVersion;
        this.timestamp = timestamp;
        this.uid = uid;
        this.hashKey = hashKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(String offerTypes) {
        this.offerTypes = offerTypes;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPhoneVersion() {
        return phoneVersion;
    }

    public void setPhoneVersion(String phoneVersion) {
        this.phoneVersion = phoneVersion;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    @Override
    public HashMap<String, Object> asQueryMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(APP_ID, getAppId());
        map.put(IP, getIp());
        map.put(LOCALE, getLocale());
        map.put(OFFER_TYPES, getOfferTypes());
        map.put(OS_VERSION, getOsVersion());
        map.put(PAGE, getPage());
        map.put(PHONE_VERSION, getPhoneVersion());
        map.put(TIMESTAMP, getTimestamp());
        map.put(USER_ID, getUid());
        map.put(HASH_KEY, getHashKey());
        return map;
    }

    @Override
    public String formatQueryParams(HashMap<String, Object> params) {
        String formattedParams = params.entrySet().stream()
                .map(p -> p.getKey() + "=" + p.getValue())
                .collect(Collectors.joining("&"));
        return GenerateHashKey.generate(formattedParams);
    }
}
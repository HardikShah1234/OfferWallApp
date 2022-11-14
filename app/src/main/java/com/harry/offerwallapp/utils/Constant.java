package com.harry.offerwallapp.utils;

/**
 * Definition of all the constants used in app.
 */
public class Constant {

    public static final String BASE_URL = "http://api.fyber.com/feed/v1/";
    public static final String X_Signature = "X-Sponsorpay-Response-Signature";
    public static final String Token = "token";
    public static final String APP_ID = "appid";
    public static final String USER_ID = "uid";
    public static final String LOCALE = "locale";
    public static final String OS_VERSION = "os_version";
    public static final String TIMESTAMP = "timestamp";
    public static final String HASH_KEY = "hashkey";
    public static final String IP = "ip";
    public static final String OFFER_TYPES = "offer_types";
    public static final String PAGE = "page";
    public static final String PHONE_VERSION = "phone_version";

    public static final String SPECIAL_CHAR_AMP = "&";
    public static final String SPECIAL_CHAR_EQUAL = "=";

    /**
     * [OfferRequest] constants.
     */
    public static String IP_ADD = "109.235.143.113";
    public static String LOCALE_VALUE = "DE";
    public static String OFFER_TYPES_VALUE = "112";
    public static String OS_VERSION_VALUE = String.valueOf(android.os.Build.VERSION.SDK_INT);
    public static String PAGE_NUMBER = "1";
    public static String PHONE_VERSION_VALUE = String.valueOf(android.os.Build.VERSION.RELEASE);
    public static String TIMESTAMP_VALUE = String.valueOf(System.currentTimeMillis() / 1000L);

    /**
     * Constant for Pagination.
     */
    public static Integer FIRST_PAGE = 1;
    public static Integer POST_PER_PAGE = 100;
}

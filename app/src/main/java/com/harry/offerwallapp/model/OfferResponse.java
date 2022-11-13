package com.harry.offerwallapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OfferResponse {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("pages")
    @Expose
    private int pages;

    @SerializedName("information")
    @Expose
    private Information information;

    @SerializedName("offers")
    @Expose
    private List<Offer> offers;

    public OfferResponse(String code, String message, int count, int pages, Information information,List<Offer> offers) {
        this.code = code;
        this.message = message;
        this.count = count;
        this.pages = pages;
        this.information = information;
        this.offers = offers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}

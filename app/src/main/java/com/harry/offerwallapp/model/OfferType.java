package com.harry.offerwallapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferType {

    @SerializedName("offer_type_id")
    @Expose
    private Integer offerTypeId;

    @SerializedName("readable")
    @Expose
    private String readable;

    public OfferType(Integer offerTypeId, String readable) {
        this.offerTypeId = offerTypeId;
        this.readable = readable;
    }

    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(Integer offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}

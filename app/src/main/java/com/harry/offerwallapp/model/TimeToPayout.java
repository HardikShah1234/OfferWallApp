package com.harry.offerwallapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeToPayout {

    @SerializedName("amount")
    @Expose
    private Integer amount;

    @SerializedName("readable")
    @Expose
    private String readable;

    public TimeToPayout(Integer amount, String readable) {
        this.amount = amount;
        this.readable = readable;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}

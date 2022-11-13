package com.harry.offerwallapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("lowres")
    @Expose
    private String lowres;

    @SerializedName("hires")
    @Expose
    private String hires;

    public Thumbnail(String lowres, String hires) {
        this.lowres = lowres;
        this.hires = hires;
    }

    public String getLowres() {
        return lowres;
    }

    public void setLowres(String lowres) {
        this.lowres = lowres;
    }

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }
}

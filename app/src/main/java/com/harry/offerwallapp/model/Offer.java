package com.harry.offerwallapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Offer {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("offer_id")
    @Expose
    private Integer offerId;

    @SerializedName("teaser")
    @Expose
    private String teaser;

    @SerializedName("required_actions")
    @Expose
    private String requiredActions;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("offer_types")
    @Expose
    private List<OfferType> offerTypes = null;

    @SerializedName("payout")
    @Expose
    private Integer payout;

    @SerializedName("time_to_payout")
    @Expose
    private TimeToPayout timeToPayout;

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    public Offer(String title, Integer offerId, String teaser, String requiredActions, String link, List<OfferType> offerTypes, Integer payout, TimeToPayout timeToPayout, Thumbnail thumbnail) {
        this.title = title;
        this.offerId = offerId;
        this.teaser = teaser;
        this.requiredActions = requiredActions;
        this.link = link;
        this.offerTypes = offerTypes;
        this.payout = payout;
        this.timeToPayout = timeToPayout;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(List<OfferType> offerTypes) {
        this.offerTypes = offerTypes;
    }

    public Integer getPayout() {
        return payout;
    }

    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    public TimeToPayout getTimeToPayout() {
        return timeToPayout;
    }

    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}

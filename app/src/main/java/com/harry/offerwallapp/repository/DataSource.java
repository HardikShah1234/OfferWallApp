package com.harry.offerwallapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.harry.offerwallapp.model.OfferResponse;
import com.harry.offerwallapp.network.FyberApi;
import com.harry.offerwallapp.utils.NetworkState;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DataSource {

    @Inject
    FyberApi fyberApi;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DataSource(FyberApi fyberApi, CompositeDisposable compositeDisposable) {
        this.fyberApi = fyberApi;
        this.compositeDisposable = compositeDisposable;
    }

    private static final MutableLiveData<NetworkState> _networkState = new MutableLiveData<NetworkState>();

    @NotNull
    public static LiveData<NetworkState> getNetworkState() {
        return (LiveData<NetworkState>) _networkState;
    }

    private static final MutableLiveData<OfferResponse> _offerDataResponse = new MutableLiveData<>();

    @NotNull
    public static LiveData<OfferResponse> getOfferResponse() {
        return (LiveData<OfferResponse>) _offerDataResponse;
    }

    public void fetchOffers(String signature, HashMap<String, Object> offerRequest) {
        _networkState.postValue(NetworkState.Companion.getLOADING());
        try {
            compositeDisposable.add(
                    fyberApi.getListOfOffers(signature, offerRequest).subscribeOn(Schedulers.io()).subscribe(offerResponse -> {
                        _offerDataResponse.postValue(offerResponse);
                        Log.d("Data Source Offer Request", String.valueOf(offerRequest));
                        _networkState.postValue(NetworkState.Companion.getLOADED());
                    }, error -> {
                        _networkState.postValue(NetworkState.Companion.getERROR());
                        Log.e("Data Source", error.getMessage());
                    })
            );
        } catch (Exception e) {
            _networkState.postValue(NetworkState.Companion.getERROR());
            Log.e("Not Proper", e.getMessage());
        }
    }
}


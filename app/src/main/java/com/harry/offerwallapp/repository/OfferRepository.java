package com.harry.offerwallapp.repository;

import androidx.lifecycle.LiveData;
import com.harry.offerwallapp.model.OfferRequest;
import com.harry.offerwallapp.model.OfferResponse;
import com.harry.offerwallapp.network.FyberApi;
import com.harry.offerwallapp.utils.NetworkState;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

public class OfferRepository {

    @Inject
    FyberApi fyberApi;

    public OfferRepository(FyberApi fyberApi) {
        this.fyberApi = fyberApi;
    }

    private DataSource dataSource;

    public LiveData<OfferResponse> fetchOffers(
            CompositeDisposable compositeDisposable,
            OfferRequest offerRequest,
            String signature
    ) {
        dataSource = new DataSource(fyberApi, compositeDisposable);
        dataSource.fetchOffers(signature, offerRequest.asQueryMap());
        return DataSource.getOfferResponse();
    }

    public LiveData<NetworkState> getNetworkState() {
        return DataSource.getNetworkState();
    }
}

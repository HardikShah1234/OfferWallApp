package com.harry.offerwallapp.viewModel;

import static com.harry.offerwallapp.utils.Constant.IP_ADD;
import static com.harry.offerwallapp.utils.Constant.LOCALE_VALUE;
import static com.harry.offerwallapp.utils.Constant.OFFER_TYPES_VALUE;
import static com.harry.offerwallapp.utils.Constant.OS_VERSION_VALUE;
import static com.harry.offerwallapp.utils.Constant.PAGE_NUMBER;
import static com.harry.offerwallapp.utils.Constant.PHONE_VERSION_VALUE;
import static com.harry.offerwallapp.utils.Constant.TIMESTAMP_VALUE;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.harry.offerwallapp.model.OfferRequest;
import com.harry.offerwallapp.model.OfferResponse;
import com.harry.offerwallapp.network.FyberApi;
import com.harry.offerwallapp.repository.OfferRepository;
import com.harry.offerwallapp.utils.GenerateHashKey;
import com.harry.offerwallapp.utils.NetworkState;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class OfferWallViewModel extends ViewModel {

    MutableLiveData<OfferResponse> liveData;
    MutableLiveData<NetworkState> networkStateMutableLiveData;
    OfferRepository offerRepository;
    OfferRequest offerRequest;
    String applicationId;
    String userId;
    String token;

    @Inject
    FyberApi fyberApi;

    @Inject
    public OfferWallViewModel(SavedStateHandle savedStateHandle) {
        liveData = new MutableLiveData<OfferResponse>();
        networkStateMutableLiveData = new MutableLiveData<NetworkState>();
        applicationId = savedStateHandle.get("appid");
        userId = savedStateHandle.get("uid");
        token = savedStateHandle.get("token");
        offerRequest = new OfferRequest(
                applicationId,
                IP_ADD,
                LOCALE_VALUE,
                OFFER_TYPES_VALUE,
                OS_VERSION_VALUE,
                PAGE_NUMBER,
                PHONE_VERSION_VALUE,
                TIMESTAMP_VALUE,
                userId,
                getHashString()
        );
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<OfferResponse> getLiveData() {
        offerRepository = new OfferRepository(fyberApi);
        Log.d("View Model Request params", String.valueOf(offerRequest));
        return (MutableLiveData<OfferResponse>) offerRepository.fetchOffers(
                compositeDisposable, offerRequest, token);
    }

    public MutableLiveData<NetworkState> getNetworkStateMutableLiveData() {
        return (MutableLiveData<NetworkState>) offerRepository.getNetworkState();
    }

    //Dynamic when passing arguments
    private String getHashString() {
        String allParams = "appid=".concat(applicationId)
                .concat("&").concat("ip=").concat(IP_ADD)
                .concat("&").concat("locale=").concat(LOCALE_VALUE)
                .concat("&").concat("offer_types=").concat(OFFER_TYPES_VALUE)
                .concat("&").concat("os_version=").concat(OS_VERSION_VALUE)
                .concat("&").concat("page=").concat(PAGE_NUMBER)
                .concat("&").concat("phone_version=").concat(PHONE_VERSION_VALUE)
                .concat("&").concat("timestamp=").concat(TIMESTAMP_VALUE)
                .concat("&").concat("uid=").concat(userId)
                .concat("&").concat(token);
        return GenerateHashKey.generate(allParams);
    }

    @Override
    public void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}


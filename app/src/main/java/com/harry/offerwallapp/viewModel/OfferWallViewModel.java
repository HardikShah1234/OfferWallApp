package com.harry.offerwallapp.viewModel;

import static com.harry.offerwallapp.utils.Constant.APP_ID;
import static com.harry.offerwallapp.utils.Constant.IP;
import static com.harry.offerwallapp.utils.Constant.IP_ADD;
import static com.harry.offerwallapp.utils.Constant.LOCALE;
import static com.harry.offerwallapp.utils.Constant.LOCALE_VALUE;
import static com.harry.offerwallapp.utils.Constant.OFFER_TYPES;
import static com.harry.offerwallapp.utils.Constant.OFFER_TYPES_VALUE;
import static com.harry.offerwallapp.utils.Constant.OS_VERSION;
import static com.harry.offerwallapp.utils.Constant.OS_VERSION_VALUE;
import static com.harry.offerwallapp.utils.Constant.PAGE;
import static com.harry.offerwallapp.utils.Constant.PHONE_VERSION;
import static com.harry.offerwallapp.utils.Constant.PHONE_VERSION_VALUE;
import static com.harry.offerwallapp.utils.Constant.SPECIAL_CHAR_AMP;
import static com.harry.offerwallapp.utils.Constant.SPECIAL_CHAR_EQUAL;
import static com.harry.offerwallapp.utils.Constant.TIMESTAMP;
import static com.harry.offerwallapp.utils.Constant.TIMESTAMP_VALUE;
import static com.harry.offerwallapp.utils.Constant.USER_ID;
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
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<OfferResponse> getLiveData(int pageNumber) {
        offerRepository = new OfferRepository(fyberApi);
        offerRequest = new OfferRequest(
                applicationId,
                IP_ADD,
                LOCALE_VALUE,
                OFFER_TYPES_VALUE,
                OS_VERSION_VALUE,
                String.valueOf(pageNumber),
                PHONE_VERSION_VALUE,
                TIMESTAMP_VALUE,
                userId,
                getHashString(String.valueOf(pageNumber))
        );
        return (MutableLiveData<OfferResponse>) offerRepository.fetchOffers(
                compositeDisposable, offerRequest, token);
    }

    public MutableLiveData<NetworkState> getNetworkStateMutableLiveData() {
        return (MutableLiveData<NetworkState>) offerRepository.getNetworkState();
    }

    private String getHashString(String pageNumber) {
        String apiRequestParams = APP_ID+"=".concat(applicationId)
                .concat(SPECIAL_CHAR_AMP).concat(IP+SPECIAL_CHAR_EQUAL).concat(IP_ADD)
                .concat(SPECIAL_CHAR_AMP).concat(LOCALE+SPECIAL_CHAR_EQUAL).concat(LOCALE_VALUE)
                .concat(SPECIAL_CHAR_AMP).concat(OFFER_TYPES+SPECIAL_CHAR_EQUAL).concat(OFFER_TYPES_VALUE)
                .concat(SPECIAL_CHAR_AMP).concat(OS_VERSION+SPECIAL_CHAR_EQUAL).concat(OS_VERSION_VALUE)
                .concat(SPECIAL_CHAR_AMP).concat(PAGE+SPECIAL_CHAR_EQUAL).concat(pageNumber)
                .concat(SPECIAL_CHAR_AMP).concat(PHONE_VERSION+SPECIAL_CHAR_EQUAL).concat(PHONE_VERSION_VALUE)
                .concat(SPECIAL_CHAR_AMP).concat(TIMESTAMP+SPECIAL_CHAR_EQUAL).concat(TIMESTAMP_VALUE)
                .concat(SPECIAL_CHAR_AMP).concat(USER_ID+SPECIAL_CHAR_EQUAL).concat(userId)
                .concat(SPECIAL_CHAR_AMP).concat(token);
        return GenerateHashKey.generate(apiRequestParams);
    }

    @Override
    public void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}


package com.harry.offerwallapp.network;

import static com.harry.offerwallapp.utils.Constant.X_Signature;
import com.harry.offerwallapp.model.OfferResponse;
import java.util.HashMap;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FyberApi {

    @GET("offers.json")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    Single<OfferResponse> getListOfOffers(
            @Header(X_Signature) String signature,
            @QueryMap HashMap<String, Object> query
    );
}




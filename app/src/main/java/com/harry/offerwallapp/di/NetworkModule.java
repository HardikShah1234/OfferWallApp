package com.harry.offerwallapp.di;

import static com.harry.offerwallapp.utils.Constant.BASE_URL;

import com.harry.offerwallapp.network.FyberApi;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public Interceptor provideInterceptor() {
        return chain -> {
            HttpUrl url = chain.request().url().newBuilder().build();
            Request request = chain.request().newBuilder().header("Connection", "Keep-Alive").url(url).build();
            return chain.proceed(request);
        };
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS).build();
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofitInstance(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder().baseUrl(BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient).build();
    }

    @Singleton
    @Provides
    public FyberApi provideFyberApi(Retrofit retrofit) {
        return retrofit.create(FyberApi.class);
    }
}

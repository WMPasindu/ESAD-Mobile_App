package com.esad.group.assignment.two.dev.network;

import com.esad.group.assignment.two.dev.interfaces.Api;
import com.esad.group.assignment.two.dev.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = AppConstants.BASE_URL;
    private static final int TIMEOUT = 20;
    private static final int TIMEOUT_LOGIN = 120;
    private static Retrofit RETROFIT_PLAIN_TEXT;
    private static Retrofit RETROFIT;
    private static Retrofit RETROFIT_LOGIN;

    public static Retrofit getRetrofitInstancePlainText() {
        if (RETROFIT_PLAIN_TEXT == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();

            RETROFIT_PLAIN_TEXT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return RETROFIT_PLAIN_TEXT;
    }

    public static Retrofit getRetrofitInstance() {
        if (RETROFIT == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();

            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return RETROFIT;
    }

    public static Retrofit getRetrofitInstanceLogin() {
        if (RETROFIT_LOGIN == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT_LOGIN, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT_LOGIN, TimeUnit.SECONDS)
                    .build();

            RETROFIT_LOGIN = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return RETROFIT_LOGIN;
    }
}

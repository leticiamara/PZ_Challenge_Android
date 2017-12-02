package com.leticia.pz_challenge_android.infrastucture.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leticia on 12/2/17.
 */

public class RetrofitService {

    private static final String BASE_URL = "http://pbmedia.pepblast.com/";

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}

package com.leticia.pz_challenge_android.infrastucture.service;

import com.leticia.pz_challenge_android.domain.model.Assets;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by leticia on 12/2/17.
 */

public interface AssetsAPI {

    @GET("pz_challenge/assets.json")
    Observable<Assets> getAssets();

    @Streaming
    @GET
    Observable<ResponseBody> downloadFileByUrl(@Url String url);
}

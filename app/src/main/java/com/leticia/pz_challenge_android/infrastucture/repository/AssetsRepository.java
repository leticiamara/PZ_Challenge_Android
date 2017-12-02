package com.leticia.pz_challenge_android.infrastucture.repository;

import com.leticia.pz_challenge_android.infrastucture.service.AssetsAPI;
import com.leticia.pz_challenge_android.infrastucture.service.RetrofitService;
import com.leticia.pz_challenge_android.domain.repository.IAssetsRepository;
import com.leticia.pz_challenge_android.domain.model.Assets;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by leticia on 12/2/17.
 */

public class AssetsRepository implements IAssetsRepository {

    @Override
    public Observable<Assets> getAssetsObservable() {
        Retrofit retrofit = RetrofitService.createRetrofit();
        AssetsAPI assetsAPI = retrofit.create(AssetsAPI.class);
        return assetsAPI.getAssets().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

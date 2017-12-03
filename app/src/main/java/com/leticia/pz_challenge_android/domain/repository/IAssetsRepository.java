package com.leticia.pz_challenge_android.domain.repository;

import com.leticia.pz_challenge_android.domain.model.Assets;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by leticia on 12/2/17.
 */

public interface IAssetsRepository {
    Observable<Assets> getAssetsObservable();

    Observable<ResponseBody> getDownloadFileObservable(String url);
}

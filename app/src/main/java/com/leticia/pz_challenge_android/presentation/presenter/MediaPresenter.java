package com.leticia.pz_challenge_android.presentation.presenter;

import com.leticia.pz_challenge_android.domain.repository.IAssetsRepository;
import com.leticia.pz_challenge_android.presentation.mvpView.MediaMvpView;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaPresenter implements IMediaPresenter {

    private IAssetsRepository assetsRepository;
    private MediaMvpView mvpView;

    public MediaPresenter(MediaMvpView mvpView, IAssetsRepository assetsRepository) {
        this.mvpView = mvpView;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public void loadAssetsList() {
        assetsRepository.getAssetsObservable().subscribe(assets -> mvpView.showAssetsList(assets),
                throwable -> mvpView.showErrorMessage(throwable.getMessage()));
    }
}

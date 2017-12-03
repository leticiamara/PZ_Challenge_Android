package com.leticia.pz_challenge_android.presentation.presenter;

import com.leticia.pz_challenge_android.domain.model.Assets;
import com.leticia.pz_challenge_android.domain.model.MediaItem;
import com.leticia.pz_challenge_android.domain.repository.IAssetsRepository;
import com.leticia.pz_challenge_android.presentation.mvpView.IMediaMvpView;
import com.squareup.picasso.Picasso;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaPresenter implements IMediaPresenter {

    private IAssetsRepository assetsRepository;
    private IMediaMvpView mvpView;

    public MediaPresenter(IMediaMvpView mvpView, IAssetsRepository assetsRepository) {
        this.mvpView = mvpView;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public void loadAssetsList() {
        assetsRepository.getAssetsObservable().subscribe((Assets assets) -> {
            mvpView.showAssetsList(assets);
        }, throwable -> mvpView.showErrorMessage(throwable.getMessage()));
    }
}

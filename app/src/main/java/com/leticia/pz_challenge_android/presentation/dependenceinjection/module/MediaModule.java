package com.leticia.pz_challenge_android.presentation.dependenceinjection.module;

import com.leticia.pz_challenge_android.domain.repository.IAssetsRepository;
import com.leticia.pz_challenge_android.infrastucture.repository.AssetsRepository;
import com.leticia.pz_challenge_android.presentation.mvpView.MediaMvpView;
import com.leticia.pz_challenge_android.presentation.presenter.IMediaPresenter;
import com.leticia.pz_challenge_android.presentation.presenter.MediaPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leticia on 12/2/17.
 */
@Module
public class MediaModule {

    private MediaMvpView mediaMvpView;

    public MediaModule(MediaMvpView mediaMvpView) {
        this.mediaMvpView = mediaMvpView;
    }

    @Provides
    public IMediaPresenter provideMediaPresenter(IAssetsRepository assetsRepository) {
        return new MediaPresenter(mediaMvpView, assetsRepository);
    }

    @Provides
    IAssetsRepository provideAssetsRepository() {
        return new AssetsRepository();
    }
}

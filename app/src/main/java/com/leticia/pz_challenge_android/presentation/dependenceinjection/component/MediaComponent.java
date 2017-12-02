package com.leticia.pz_challenge_android.presentation.dependenceinjection.component;

import com.leticia.pz_challenge_android.presentation.dependenceinjection.module.MediaModule;
import com.leticia.pz_challenge_android.presentation.presenter.IMediaPresenter;
import com.leticia.pz_challenge_android.presentation.view.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leticia on 12/2/17.
 */
@Singleton
@Component(modules = {MediaModule.class})
public interface MediaComponent {

    void inject(MainActivity activity);

    IMediaPresenter getMediaPresenter();
}

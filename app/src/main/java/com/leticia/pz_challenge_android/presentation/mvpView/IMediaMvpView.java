package com.leticia.pz_challenge_android.presentation.mvpView;

import android.content.Context;
import android.widget.ImageView;

import com.leticia.pz_challenge_android.domain.model.Assets;

/**
 * Created by leticia on 12/2/17.
 */

public interface IMediaMvpView {
    void showAssetsList(Assets assets);

    void showErrorMessage(String message);

    Context getContext();

    ImageView getMediaImageView();

    void showVideoScreen(String videoPath, String audioPath);

    void updateVideoPath(String videoPath, int position);

    void updateAudioPath(String audioPath, int position);

    void startProgress();

    void finishProgress();
}

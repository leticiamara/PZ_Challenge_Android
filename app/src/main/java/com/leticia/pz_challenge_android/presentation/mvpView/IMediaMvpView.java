package com.leticia.pz_challenge_android.presentation.mvpView;

import android.content.Context;
import android.widget.ImageView;

import com.leticia.pz_challenge_android.domain.model.Assets;
import com.squareup.picasso.Target;

/**
 * Created by leticia on 12/2/17.
 */

public interface IMediaMvpView {
    void showAssetsList(Assets assets);

    void showErrorMessage(String message);

    Context getContext();

    ImageView getMediaImageView();
}

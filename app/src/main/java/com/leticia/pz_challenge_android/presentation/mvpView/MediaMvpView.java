package com.leticia.pz_challenge_android.presentation.mvpView;

import com.leticia.pz_challenge_android.domain.model.Assets;

/**
 * Created by leticia on 12/2/17.
 */

public interface MediaMvpView {
    void showAssetsList(Assets assets);

    void showErrorMessage(String message);
}

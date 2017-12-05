package com.leticia.pz_challenge_android.presentation.presenter;

import com.leticia.pz_challenge_android.domain.model.MediaItem;

/**
 * Created by leticia on 12/2/17.
 */

public interface IMediaPresenter {
    void loadAssetsList();

    void downloadData(MediaItem mediaItem, int position);
}

package com.leticia.pz_challenge_android.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by leticia on 12/2/17.
 */

public class Assets {

    private String assetsLocation;
    @SerializedName("objects")
    private List<MediaItem> mediaItems;

    public String getAssetsLocation() {
        return assetsLocation;
    }

    public void setAssetsLocation(String assetsLocation) {
        this.assetsLocation = assetsLocation;
    }

    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }
}

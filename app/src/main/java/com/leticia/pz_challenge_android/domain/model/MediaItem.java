package com.leticia.pz_challenge_android.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaItem {

    private String name;
    @SerializedName("bg")
    private String videoBackgroundName;
    @SerializedName("im")
    private String imageName;
    @SerializedName("sg")
    private String audioName;
    @SerializedName("txts")
    private List<TextItem> textItems;

    private String videoStoredPath;
    private String audioStorePath;
    private DownloadStatus downloadStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoBackgroundName() {
        return videoBackgroundName;
    }

    public void setVideoBackgroundName(String videoBackgroundName) {
        this.videoBackgroundName = videoBackgroundName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public List<TextItem> getTextItems() {
        return textItems;
    }

    public void setTextItems(List<TextItem> textItems) {
        this.textItems = textItems;
    }

    public String getVideoStoredPath() {
        return videoStoredPath;
    }

    public void setVideoStoredPath(String videoStoredPath) {
        this.videoStoredPath = videoStoredPath;
    }

    public String getAudioStorePath() {
        return audioStorePath;
    }

    public void setAudioStorePath(String audioStorePath) {
        this.audioStorePath = audioStorePath;
    }

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}

package com.leticia.pz_challenge_android.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaItem {

    private String name;
    @SerializedName("bg")
    private String videoBackground;
    @SerializedName("im")
    private String image;
    @SerializedName("sg")
    private String audio;
    @SerializedName("txts")
    private List<TextItem> textItems;
    private String videoStoredPath;
    private String audioStorePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoBackground() {
        return videoBackground;
    }

    public void setVideoBackground(String videoBackground) {
        this.videoBackground = videoBackground;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
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
}

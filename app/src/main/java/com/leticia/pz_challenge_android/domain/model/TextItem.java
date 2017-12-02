package com.leticia.pz_challenge_android.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leticia on 12/2/17.
 */

public class TextItem {

    @SerializedName("txt")
    private String text;
    private double time;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}

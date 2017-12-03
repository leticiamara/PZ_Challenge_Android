package com.leticia.pz_challenge_android.infrastucture;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by leticia on 12/3/17.
 */

public class SharedPreferencesManager {

    private static final String ASSETS_LOCATION_KEY = "assets-location-key";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveAssetsLocation(String assetsLocation) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ASSETS_LOCATION_KEY, assetsLocation);
        editor.apply();
    }

    public String getAssetsLocation(){
        return sharedPreferences.getString(ASSETS_LOCATION_KEY, "");
    }
}

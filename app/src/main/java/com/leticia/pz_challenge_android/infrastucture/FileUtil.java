package com.leticia.pz_challenge_android.infrastucture;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by leticia on 12/3/17.
 */

public class FileUtil {

    public static final String SEPARATOR = "/";
    private static final String MEDIA_DIR_PATH = Environment.getExternalStorageDirectory().getPath()
            + "/PZ_Challenge_Android/MediaFiles/";

    private static File getMediaFolder() {
        File mediaFolder = new File(MEDIA_DIR_PATH);
        if (!mediaFolder.exists()) {
            mediaFolder.mkdirs();
        }
        return mediaFolder;
    }

    public static File saveFileToDisk(String fileName, BufferedSource bufferedSource) throws IOException {
        File destinationFile = new File(getMediaFolder(), fileName);
        BufferedSink bufferedSink = Okio.buffer(Okio.sink(destinationFile));
        bufferedSink.writeAll(bufferedSource);
        bufferedSink.close();
        return destinationFile;
    }
}

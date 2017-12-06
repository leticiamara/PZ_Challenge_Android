package com.leticia.pz_challenge_android.presentation.view.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.leticia.pz_challenge_android.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.leticia.pz_challenge_android.presentation.view.activities.MainActivity.AUDIO_PATH;
import static com.leticia.pz_challenge_android.presentation.view.activities.MainActivity.VIDEO_PATH;

public class PlayerActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            String videoPath = getIntent().getStringExtra(VIDEO_PATH);
            String audioPath = getIntent().getStringExtra(AUDIO_PATH);
            Uri uri = Uri.parse(videoPath);
            setupVideoView(uri);
            startPlayAudio(audioPath);
        }
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        videoView.stopPlayback();
        super.onDestroy();
    }

    public void startPlayAudio(String path){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            videoView.stopPlayback();
            finish();
        });
    }

    private void setupVideoView(Uri uri) {
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        videoView.start();
    }
}

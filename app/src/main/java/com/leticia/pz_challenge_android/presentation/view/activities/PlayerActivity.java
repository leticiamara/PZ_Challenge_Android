package com.leticia.pz_challenge_android.presentation.view.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.leticia.pz_challenge_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.leticia.pz_challenge_android.presentation.view.activities.MainActivity.VIDEO_PATH;

public class PlayerActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        String path = getIntent().getStringExtra(VIDEO_PATH);
        Uri uri = Uri.parse(path);

        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
        videoView.start();
    }
}

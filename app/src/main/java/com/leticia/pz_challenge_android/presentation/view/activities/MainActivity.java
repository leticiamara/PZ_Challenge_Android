package com.leticia.pz_challenge_android.presentation.view.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.leticia.pz_challenge_android.R;
import com.leticia.pz_challenge_android.domain.model.Assets;
import com.leticia.pz_challenge_android.domain.model.DownloadStatus;
import com.leticia.pz_challenge_android.domain.model.MediaItem;
import com.leticia.pz_challenge_android.presentation.dependenceinjection.component.DaggerMediaComponent;
import com.leticia.pz_challenge_android.presentation.dependenceinjection.module.MediaModule;
import com.leticia.pz_challenge_android.presentation.mvpView.IMediaMvpView;
import com.leticia.pz_challenge_android.presentation.presenter.IMediaPresenter;
import com.leticia.pz_challenge_android.presentation.view.adapter.MediaAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMediaMvpView,
        MediaAdapter.OnDownloadClickedListener {

    public static final String VIDEO_PATH = "video-path";
    public static final String AUDIO_PATH = "audio-path";
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 9991;
    @BindView(R.id.media_list)
    RecyclerView mediaList;
    private MediaAdapter mediaAdapter;

    @Inject
    IMediaPresenter presenter;
    private MediaItem currentMediaItem;
    private int currentAdapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupDependenceInjection();
        setupRecyclerView();

        presenter.loadAssetsList();
    }

    @Override
    public void showAssetsList(Assets assets) {
        mediaAdapter.setAssetList(assets.getMediaItems(), assets.getAssetsLocation());
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ImageView getMediaImageView() {
        return null;
    }

    @Override
    public void showVideoScreen(String videoPath, String audioPath) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(VIDEO_PATH, videoPath);
        intent.putExtra(AUDIO_PATH, audioPath);
        startActivity(intent);
    }

    @Override
    public void updateVideoPath(String absolutePath, int position) {
        mediaAdapter.updateVideoPath(absolutePath, position);
    }

    @Override
    public void updateAudioPath(String audioPath, int position) {
        mediaAdapter.updateAudioPath(audioPath, position);
    }

    @Override
    public void startProgress(int position) {
        mediaAdapter.updateDownloadStatus(DownloadStatus.STARTED, position);
    }

    @Override
    public void finishProgress(boolean error, int position) {
        if (error) {
            mediaAdapter.updateDownloadStatus(DownloadStatus.ERROR, position);
        } else {
            mediaAdapter.updateDownloadStatus(DownloadStatus.COMPLETED, position);
        }
    }

    @Override
    public void onDownloadClicked(int adapterPosition) {
        MediaItem mediaItem = mediaAdapter.getMediaItem(adapterPosition);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
            currentMediaItem = mediaItem;
            currentAdapterPosition = adapterPosition;
        } else {
            presenter.downloadData(mediaItem, adapterPosition);
        }
    }

    @Override
    public void onPlayClicked(int adapterPosition) {
        MediaItem mediaItem = mediaAdapter.getMediaItem(adapterPosition);
        showVideoScreen(mediaItem.getVideoStoredPath(), mediaItem.getAudioStorePath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.downloadData(currentMediaItem, currentAdapterPosition);
                } else {
                    Toast.makeText(this, R.string.msg_app_needs_permission, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void setupDependenceInjection() {
        DaggerMediaComponent
                .builder()
                .mediaModule(new MediaModule(this))
                .build()
                .inject(this);
    }

    private void setupRecyclerView() {
        mediaList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mediaList.setLayoutManager(layoutManager);
        mediaAdapter = new MediaAdapter(this);
        mediaList.setAdapter(mediaAdapter);
    }
}

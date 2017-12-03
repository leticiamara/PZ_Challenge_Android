package com.leticia.pz_challenge_android.presentation.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.leticia.pz_challenge_android.R;
import com.leticia.pz_challenge_android.domain.model.Assets;
import com.leticia.pz_challenge_android.presentation.dependenceinjection.component.DaggerMediaComponent;
import com.leticia.pz_challenge_android.presentation.dependenceinjection.module.MediaModule;
import com.leticia.pz_challenge_android.presentation.mvpView.IMediaMvpView;
import com.leticia.pz_challenge_android.presentation.presenter.IMediaPresenter;
import com.leticia.pz_challenge_android.presentation.view.adapter.MediaAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMediaMvpView {

    @BindView(R.id.media_list)
    RecyclerView mediaList;
    private MediaAdapter mediaAdapter;

    @Inject
    IMediaPresenter presenter;

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

        mediaAdapter = new MediaAdapter();
        mediaList.setAdapter(mediaAdapter);
    }
}

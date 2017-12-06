package com.leticia.pz_challenge_android.presentation.view.viewholder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leticia.pz_challenge_android.R;
import com.leticia.pz_challenge_android.presentation.view.adapter.MediaAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leticia on 12/5/17.
 */

public class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txt_name)
    TextView viewName;
    @BindView(R.id.image_background)
    ImageView imageBackground;
    @BindView(R.id.btn_download)
    FloatingActionButton mBtnDownload;
    @BindView(R.id.progress_download)
    ProgressBar mProgressDownload;

    private final MediaAdapter.OnDownloadClickedListener onDownloadClickedListener;
    private boolean isPlay;

    public MediaViewHolder(View itemView, MediaAdapter.OnDownloadClickedListener onDownloadClickedListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.onDownloadClickedListener = onDownloadClickedListener;
        mBtnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isPlay) {
            onDownloadClickedListener.onPlayClicked(getAdapterPosition());
        } else {
            onDownloadClickedListener.onDownloadClicked(getAdapterPosition());
        }
    }

    public void onDownloadStarted() {
        mProgressDownload.setVisibility(View.VISIBLE);
        mBtnDownload.setEnabled(false);
    }

    public void onDownloadFinishedWithSuccess() {
        mProgressDownload.setVisibility(View.INVISIBLE);
        mBtnDownload.setEnabled(true);
    }

    public void onDownloadFinishedWithError() {
        mProgressDownload.setVisibility(View.INVISIBLE);
        mBtnDownload.setEnabled(true);
    }

    public TextView getViewName() {
        return viewName;
    }

    public ImageView getImageBackground() {
        return imageBackground;
    }

    public FloatingActionButton getmBtnDownload() {
        return mBtnDownload;
    }

    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

}

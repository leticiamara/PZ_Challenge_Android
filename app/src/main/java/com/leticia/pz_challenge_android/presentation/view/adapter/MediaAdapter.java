package com.leticia.pz_challenge_android.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leticia.pz_challenge_android.R;
import com.leticia.pz_challenge_android.domain.model.DownloadStatus;
import com.leticia.pz_challenge_android.domain.model.MediaItem;
import com.leticia.pz_challenge_android.presentation.view.viewholder.MediaViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaAdapter extends RecyclerView.Adapter<MediaViewHolder> {

    private List<MediaItem> mediaItems;
    private String assetsLocation;
    private OnDownloadClickedListener onDownloadClickedListener;

    public MediaAdapter(OnDownloadClickedListener onDownloadClickedListener) {
        this.onDownloadClickedListener = onDownloadClickedListener;
        mediaItems = new ArrayList<>();
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media, parent, false);
        return new MediaViewHolder(view, onDownloadClickedListener);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        holder.getViewName().setText(mediaItem.getName());
        if (mediaItem.getDownloadStatus() != null) {
            if (mediaItem.getDownloadStatus() == DownloadStatus.STARTED) {
                holder.onDownloadStarted();
            } else if (mediaItem.getDownloadStatus() == DownloadStatus.COMPLETED) {
                holder.onDownloadFinishedWithSuccess();
            } else {
                holder.onDownloadFinishedWithError();
            }
        }
        changeBtnIconIfIsPlay(holder, mediaItem);
        loadMediaThumbnail(holder, mediaItem);
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    public void setAssetList(List<MediaItem> assets, String assetsLocation) {
        this.mediaItems = assets;
        this.assetsLocation = assetsLocation;
        notifyDataSetChanged();
    }

    public MediaItem getMediaItem(int adapterPosition) {
        return mediaItems.get(adapterPosition);
    }

    public void updateVideoPath(String videoPath, int position) {
        mediaItems.get(position).setVideoStoredPath(videoPath);
        notifyItemChanged(position);
    }

    public void updateAudioPath(String audioPath, int position) {
        mediaItems.get(position).setAudioStorePath(audioPath);
        notifyItemChanged(position);
    }

    public void updateDownloadStatus(DownloadStatus downloadStatus, int position) {
        mediaItems.get(position).setDownloadStatus(downloadStatus);
        notifyItemChanged(position);
    }

    private void changeBtnIconIfIsPlay(MediaViewHolder holder, MediaItem mediaItem) {
        if (mediaItem.getVideoStoredPath() != null && mediaItem.getAudioStorePath() != null
                && !mediaItem.getVideoStoredPath().isEmpty() && !mediaItem.getAudioStorePath().isEmpty()) {
            holder.getmBtnDownload().setImageDrawable(holder.itemView.getResources()
                    .getDrawable(R.drawable.ic_play_arrow_white_24dp));
            holder.setIsPlay(true);
        } else {
            holder.getmBtnDownload().setImageDrawable(holder.itemView.getResources()
                    .getDrawable(R.drawable.ic_file_download_white_24dp));
            holder.setIsPlay(false);
        }
    }

    private void loadMediaThumbnail(MediaViewHolder holder, MediaItem mediaItem) {
        Picasso.with(holder.itemView.getContext())
                .load(assetsLocation + "/" + mediaItem.getImageName())
                .into(holder.getImageBackground());
    }

    public interface OnDownloadClickedListener {
        void onDownloadClicked(int adapterPosition);

        void onPlayClicked(int adapterPosition);
    }
}

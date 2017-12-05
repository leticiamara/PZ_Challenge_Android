package com.leticia.pz_challenge_android.presentation.view.adapter;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leticia.pz_challenge_android.R;
import com.leticia.pz_challenge_android.domain.model.MediaItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.AssetViewHolder> {

    private List<MediaItem> mediaItems;
    private String assetsLocation;
    private OnDownloadListener onDownloadListener;

    public MediaAdapter(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
        mediaItems = new ArrayList<>();
    }

    @Override
    public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media, parent, false);
        return new AssetViewHolder(view, onDownloadListener);
    }

    @Override
    public void onBindViewHolder(AssetViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        holder.getViewName().setText(mediaItem.getName());

        Picasso.with(holder.itemView.getContext())
                .load(assetsLocation + "/" + mediaItem.getImage())
                .into(holder.getImageBackground());
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

    public interface OnDownloadListener {
        void onDownloadClicked(int adapterPosition);
    }

    class AssetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_name)
        TextView viewName;
        @BindView(R.id.image_background)
        ImageView imageBackground;
        @BindView(R.id.btn_download)
        FloatingActionButton btnDownload;

        private final OnDownloadListener onDownloadListener;

        AssetViewHolder(View itemView, OnDownloadListener onDownloadListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.onDownloadListener = onDownloadListener;
            btnDownload.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDownloadListener.onDownloadClicked(getAdapterPosition());
            btnDownload.setClickable(false);
            btnDownload.setBackgroundTintList(ColorStateList.valueOf(view.getResources().getColor(R.color.colorButtonDisabled)));
        }

        TextView getViewName() {
            return viewName;
        }

        ImageView getImageBackground() {
            return imageBackground;
        }
    }
}

package com.leticia.pz_challenge_android.presentation.view.adapter;

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

/**
 * Created by leticia on 12/2/17.
 */

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.AssetViewHolder> {

    private List<MediaItem> mediaItems;
    private String assetsLocation;

    public MediaAdapter() {
        mediaItems = new ArrayList<>();
    }

    @Override
    public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media, parent, false);
        return new AssetViewHolder(view);
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

    class AssetViewHolder extends RecyclerView.ViewHolder {

        private TextView viewName;
        private ImageView imageBackground;

        AssetViewHolder(View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.txt_name);
            imageBackground = itemView.findViewById(R.id.image_background);
        }

        TextView getViewName() {
            return viewName;
        }

        public ImageView getImageBackground() {
            return imageBackground;
        }
    }
}

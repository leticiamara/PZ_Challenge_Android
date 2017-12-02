package com.leticia.pz_challenge_android.presentation.view.activities.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leticia.pz_challenge_android.R;
import com.leticia.pz_challenge_android.domain.model.MediaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leticia on 12/2/17.
 */

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.AssetViewHolder> {

    private List<MediaItem> assets;

    public MediaAdapter() {
        assets = new ArrayList<>();
    }

    @Override
    public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media, parent, false);
        return new AssetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AssetViewHolder holder, int position) {
        MediaItem mediaItem = assets.get(position);
        holder.getViewName().setText(mediaItem.getName());
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }

    public void setAssetList(List<MediaItem> assets) {
        this.assets = assets;
        notifyDataSetChanged();
    }

    class AssetViewHolder extends RecyclerView.ViewHolder {

        private TextView viewName;

        AssetViewHolder(View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.txt_name);
        }

        TextView getViewName() {
            return viewName;
        }
    }
}

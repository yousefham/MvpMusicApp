package com.yosoftware.musicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.core.General.OnItemClickListener;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;

import java.util.ArrayList;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList> playLists;
    private OnItemClickListener<PlayList> onItemClickListener;

    public PlayListAdapter(Context context, ArrayList<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false);
        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener<PlayList> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playLists.get(position);
        holder.playList_name.setText(playList.getPlayListName());
        if (playList.getMusic() != null && playList.getMusic().size() > 0) {
            holder.songCount.setText(playList.getMusic().size() + "");
        } else {
            holder.songCount.setText("0 Song");

        }
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView playList_name, songCount;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playList_name = (TextView) itemView.findViewById(R.id.playList_name);
            songCount = (TextView) itemView.findViewById(R.id.song_count);
            layout = (ConstraintLayout) itemView.findViewById(R.id.playlistItem);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(itemView, getAdapterPosition(), playLists.get(getAdapterPosition()));
                    }
                }
            });
        }




    }
}

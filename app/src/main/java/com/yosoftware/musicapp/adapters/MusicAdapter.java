package com.yosoftware.musicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.features.all_music.data.model.Music;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    Context context;
    ArrayList<Music> musicList;
    String type;
    int playListId;
    Gson gson;

    public MusicAdapter(Context context, ArrayList<Music> musicList) {
        this.context = context;
        this.musicList = musicList;
    }
    public MusicAdapter(Context context, ArrayList<Music> musicList, String type, int playListId) {
        this.context = context;
        this.musicList = musicList;
        this.type = type;
        this.playListId = playListId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_item, parent, false);
        gson = new GsonBuilder().serializeNulls().create();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.title.setText(music.getTitle());
        holder.number.setText(new StringBuilder().append(position + 1).append("").toString());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, number;
        ImageView dots, addToPlayList;
        ConstraintLayout music_item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.music_name);
            number = (TextView) itemView.findViewById(R.id.number);
            music_item = (ConstraintLayout) itemView.findViewById(R.id.music_item);
            dots = (ImageView) itemView.findViewById(R.id.dots);
            addToPlayList = (ImageView) itemView.findViewById(R.id.add_to_playlist);
            if (type != null && type.equals("playlist")) {
                addToPlayList.setVisibility(View.VISIBLE);
                dots.setVisibility(View.GONE);
            } else {
                addToPlayList.setVisibility(View.GONE);
                dots.setVisibility(View.VISIBLE);
            }
            music_item.setOnClickListener(this::onClick);
            dots.setOnClickListener(this::onClick);
            addToPlayList.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.music_item:
//                    Music music = musicList.get(getAdapterPosition());
////                    Intent intent = new Intent(context, Player.class);
////                    intent.putExtra("title", music.getTitle());
////                    intent.putExtra("artist", music.getArtist());
////                    intent.putExtra("duration", music.getDuration());
////                    intent.putExtra("url", music.getData());
////                    intent.putExtra("musicData", gson.toJson(music));
//                    context.startActivity(intent);
                    break;
                case R.id.dots:
                    showSheetDialog();
                    break;
                case R.id.add_to_playlist:
                    Select();
                    break;
            }

        }

        private void Select() {
            title.setTextColor(Color.parseColor("#D8D8D8"));
            number.setTextColor(Color.parseColor("#D8D8D8"));
            Music music = new Music();
            music.setPath(musicList.get(getAdapterPosition()).getData());
            music.setPlay_list_id(playListId);
            music.setTitle(musicList.get(getAdapterPosition()).getTitle());
            music.setArtist(musicList.get(getAdapterPosition()).getArtist());
            music.setDuration(musicList.get(getAdapterPosition()).getDuration());
           // DataBaseHelper.with(context).addMusic(music, context);
        }

        private void showSheetDialog() {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.song_settings_sheet);
            //TODO: define all specification of sheet
            bottomSheetDialog.show();
        }
    }
}

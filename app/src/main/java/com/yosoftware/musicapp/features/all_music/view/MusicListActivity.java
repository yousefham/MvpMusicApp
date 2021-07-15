package com.yosoftware.musicapp.features.all_music.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.adapters.MusicAdapter;
import com.yosoftware.musicapp.databinding.ActivityMusicListBinding;
import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.all_music.presenter.AllMusicPresenter;

import java.util.ArrayList;

public class MusicListActivity extends AppCompatActivity implements AllMusicView {

    ArrayList<Music> musicList;
    AllMusicPresenter presenter;
    ActivityMusicListBinding binding;
    MusicAdapter adapter;
    Intent receiver;
    String path;
    int playListId;
    boolean isPlayList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initIntent();
        presenter = new AllMusicPresenter(this, this, "");
        init();
        presenter.getMusicList();
    }

    private void initIntent() {
        receiver = getIntent();
        if (receiver.hasExtra("path")) {
            path = receiver.getStringExtra("path");
        } else {
            isPlayList = true;
            playListId = receiver.getIntExtra("PlayListId", 0);
        }
        if (receiver.hasExtra("title")) {
            binding.folderTitle.setText(receiver.getStringExtra("title"));
        } else {
            binding.folderTitle.setText("Select Songs");
        }

    }

    void init() {
        musicList = new ArrayList<>();
        if (isPlayList) {
            adapter = new MusicAdapter(this, musicList, "playlist", playListId);
        } else {
            adapter = new MusicAdapter(this, musicList);
        }

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMusicListUpdate(ArrayList<Music> music) {
        musicList.clear();
        musicList.addAll(music);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (isPlayList) {
            Intent intent = new Intent();
            intent.putExtra("count", 1);
            setResult(RESULT_OK, intent);
        }
        super.onBackPressed();

    }
}
package com.yosoftware.musicapp.features.playlist.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import com.google.gson.Gson;
import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.adapters.MusicAdapter;
import com.yosoftware.musicapp.databinding.ActivityPlayListDetailsBinding;
import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.playlist.presenter.PlayListDetailsPresenter;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;

import java.util.ArrayList;

public class PlayListDetails extends AppCompatActivity implements PlayListActivityView {

    PlayListDetailsPresenter presenter;
    ActivityPlayListDetailsBinding binding;
    MusicAdapter adapter;
    Intent intent;
    ArrayList<Music> musicList;
    int playListId;
    PlayList playList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayListDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent = getIntent();
        if (intent.hasExtra("playList")) {
            playList = new Gson().fromJson(intent.getStringExtra("playList"), PlayList.class);
            playListId = playList.getId();
        }
        presenter = new PlayListDetailsPresenter(this, this, playListId);
        init();
        presenter.getMusicList();
    }

    void init() {
        musicList = new ArrayList<>();
        adapter = new MusicAdapter(this, musicList, "playlist", playListId);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.playListName.setText(playList.getPlayListName());
        binding.durationTime.setText(playList.getDuration());
    }

    void addMusic() {
//        Intent intent = new Intent(this, MusicList.class);
//        intent.putExtra("PlayListId", playListId);
//        startActivity(intent);
    }


    void deletePlayList() {
        presenter.deletePlayList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                deletePlayList();
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onMusicListUpdate(ArrayList<Music> music) {
        if (music.size() == 0 && music == null) {
            binding.addSong.setVisibility(View.VISIBLE);
            binding.addSongPlusImg.setVisibility(View.VISIBLE);
        } else {
            binding.addSong.setVisibility(View.GONE);
            binding.addSongPlusImg.setVisibility(View.GONE);
            musicList.clear();
            musicList.addAll(music);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPlayListDeleteSuccess() {
        Intent intent = new Intent();
        startActivityForResult(intent, 1);
        finish();
    }

    @Override
    public void onPlayListDeleteError() {
        Toast.makeText(this, "Something happened when try to delete, try again!", Toast.LENGTH_SHORT).show();
    }


}
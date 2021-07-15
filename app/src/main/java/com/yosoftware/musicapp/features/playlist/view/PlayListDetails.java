package com.yosoftware.musicapp.features.playlist.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.adapters.MusicAdapter;
import com.yosoftware.musicapp.databinding.ActivityPlayListDetailsBinding;
import com.yosoftware.musicapp.databinding.AddPlayListBinding;
import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.all_music.view.MusicListActivity;
import com.yosoftware.musicapp.features.playlist.presenter.PlayListDetailsPresenter;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
        adapter = new MusicAdapter(this, musicList);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.playListName.setText(playList.getPlayListName());
        binding.durationTime.setText(playList.getDuration());
        binding.addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMusic();
            }
        });
        binding.addSongPlusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMusic();
            }
        });
        Toolbar toolbar = binding.tvUp;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    void addMusic() {
        Intent intent = new Intent(this, MusicListActivity.class);
        intent.putExtra("PlayListId", playListId);
        startActivityForResult(intent, 1);
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
            case R.id.edit:
                EditPlaylistName();
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private void EditPlaylistName() {
        Dialog dialog = new Dialog(this);
        AddPlayListBinding editPlayListName = AddPlayListBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(editPlayListName.getRoot());
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editPlayListName.title.setText("Edit Playlist Name");
        editPlayListName.createPlayList.setText("Save");
        editPlayListName.playListName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editPlayListName.createPlayList.setAlpha(1);
                editPlayListName.createPlayList.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editPlayListName.createPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playListName = editPlayListName.playListName.getText().toString();
                presenter.editPlayListName(playListName);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onMusicListUpdate(ArrayList<Music> music) {
        if (music.size() == 0) {
            binding.addSong.setVisibility(View.VISIBLE);
        } else {
            binding.addSong.setVisibility(View.GONE);
            musicList.clear();
            musicList.addAll(music);
            binding.songCount.setText(new StringBuilder().append(musicList.size()).append(" Song").toString());
            binding.playAllTv.setText(new StringBuilder().append("Play All (").append(musicList.size()).append(")").toString());
            int duration = 0;
            for (Music music1 : musicList) {
                duration = duration + Integer.parseInt(music1.getDuration());
            }
            int millis = Integer.parseInt(String.valueOf(duration));
            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
            long seconds = (millis / 1000) % 60;
            binding.durationTime.setText(new StringBuilder().append(minutes).append(":").append(seconds).toString());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPlayListDeleteSuccess() {
        Intent intent1 = new Intent();
        intent1.putExtra("playlist", new Gson().toJson(playList, PlayList.class));
        setResult(RESULT_OK, intent1);
        finish();
    }

    @Override
    public void onPlayListDeleteError() {
        Toast.makeText(this, "Something happened when try to delete, try again!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlayListNameEditedSuccessfully(String newName) {
        binding.playListName.setText(newName);
    }


    @Override
    public void onPlayListNameEditedGetError() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                presenter.getMusicList();
            }
        }
    }
}
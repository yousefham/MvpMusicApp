package com.yosoftware.musicapp.features.player.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.databinding.ActivityPlayerBinding;
import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.player.presenter.PlayerPresenter;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Player extends AppCompatActivity implements PlayerView {

    ActivityPlayerBinding binding;
    PlayerPresenter presenter;
    Intent intent;
    Music currentMusic;
    boolean isUserEvent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent = getIntent();
        currentMusic = new Gson().fromJson(intent.getStringExtra("music"), Music.class);
        File musicFile = new File(currentMusic.getData());
        presenter = new PlayerPresenter(this, this, Objects.requireNonNull(musicFile.getParentFile()).getPath(), currentMusic);
        initButtons();
    }

    private void initButtons() {
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.playOrPause();
            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.nextMusic();
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUserEvent = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.seekTo(seekBar.getProgress());
                isUserEvent = false;

            }
        });
    }


    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void previous() {

    }

    @Override
    public void next() {

    }

    @Override
    public void repeat() {

    }

    @Override
    public void selectSong(Music current) {
        binding.musicName.setText(current.getDisplayName());
        int millis = Integer.parseInt(current.getDuration());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = (millis / 1000) % 60;
        binding.durationTime.setText(new StringBuilder().append(minutes).append(":").append(seconds).toString());
        binding.musicArtist.setText(current.getArtist());
    }

    @Override
    public void shuffle() {

    }

    @Override
    public void playOne() {

    }

    @Override
    public void seekTO(int seek) {

    }

    @Override
    public void onSeekChange(int position, int current) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isUserEvent) {
                    binding.seekBar.setProgress(position);
                    int millis = current;
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
                    long seconds = (millis / 1000) % 60;
                    binding.seekTime.setText(new StringBuilder().append(minutes).append(":").append(seconds).toString());
                }
            }
        });
    }


    @Override
    public void onPlayStateChange(int state) {
        switch (state) {
            case PLAY_STATE_PLAY:
                binding.play.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                break;
            case PLAY_STATE_PAUSE:
            case PLAY_STATE_STOP:
                binding.play.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                break;
        }
    }
}
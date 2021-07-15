package com.yosoftware.musicapp.features.player.presenter;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;

import com.yosoftware.musicapp.core.General.PlayerControl;
import com.yosoftware.musicapp.core.Utils.MusicUtils;
import com.yosoftware.musicapp.core.datasources.DataBaseHelper;
import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.player.view.PlayerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerPresenter extends Binder implements PlayerControl {

    Activity activity;
    PlayerView view;
    String folderPath;
    Music currentMusic;
    MediaPlayer player;
    SeekTimeTask seekTimeTask;
    Timer timer;
    int currentState = PLAY_STATE_STOP;
    ArrayList<Music> allMusic;
    String songPath;

    public PlayerPresenter(Activity activity, PlayerView view, String folderPath, Music music) {
        this.activity = activity;
        this.view = view;
        this.folderPath = folderPath;
        initMedia();
        pickMusic(music);
        getAllMusicInPath();
    }


    void initMedia() {
        if (player == null) {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
    }

    public void seekTo(int position) {
        if (player != null) {
            int tarSeek = (int) (position * 1.0f / 100 * player.getDuration());
            player.seekTo(tarSeek);
            view.seekTO(tarSeek);
        }
    }

    void getAllMusicInPath() {
        allMusic = MusicUtils.getMusicList(folderPath, activity);
        for (Music mm : allMusic) {
            if (mm.getData().contains(currentMusic.getData())) {
                System.out.println("Music " + mm.toString());
            }
        }
    }


    public void nextMusic() {
        if (currentMusic != null) {
            int currentIndex = allMusic.indexOf(currentMusic);
            Music music = allMusic.get(currentIndex + 1);
            currentMusic = music;
            currentState = PLAY_STATE_STOP;
            stopTimer();
            pickMusic(music);
            view.selectSong(music);
        }
    }


    public void playOrPause() {
        if (currentState == PLAY_STATE_STOP) {
            if (player != null) {
                try {
                    player.reset();
                    player.setDataSource(currentMusic.getData());
                    player.prepare();
                    player.start();
                    startTimer();
                    view.play();
                    currentState = PLAY_STATE_PLAY;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (currentState == PLAY_STATE_PLAY) {
            if (player != null) {
                player.pause();
                currentState = PLAY_STATE_PAUSE;
                stopTimer();
                view.stop();
            }
        } else if (currentState == PLAY_STATE_PAUSE) {
            if (player != null) {
                player.start();
                currentState = PLAY_STATE_PLAY;
                startTimer();
                view.play();
            }
        }
        view.onPlayStateChange(currentState);
    }

    void pickMusic(Music music) {
        currentMusic = music;
        playOrPause();
        view.selectSong(music);
    }

    void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        if (seekTimeTask == null) {
            seekTimeTask = new SeekTimeTask();
        }
        timer.schedule(seekTimeTask, 0, 100);
    }

    void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (seekTimeTask != null) {
            seekTimeTask.cancel();
            seekTimeTask = null;
        }

    }


    private class SeekTimeTask extends TimerTask {

        @Override
        public void run() {
            if (player != null && view != null) {
                int position = player.getCurrentPosition();
                int currentPosition = (int) (position * 1.0f / player.getDuration() * 100);
                view.onSeekChange(currentPosition, position);

            }
        }
    }
}

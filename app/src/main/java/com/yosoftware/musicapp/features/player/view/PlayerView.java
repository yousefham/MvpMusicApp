package com.yosoftware.musicapp.features.player.view;

import com.yosoftware.musicapp.features.all_music.data.model.Music;

public interface PlayerView {
    public static final int PLAY_STATE_PLAY = 1;    //Play
    public static final int PLAY_STATE_PAUSE = 2;     //time out
    public static final int PLAY_STATE_STOP = 3;

    void play();

    void stop();

    void previous();

    void next();

    void repeat();

    void selectSong(Music current);

    void shuffle();

    void playOne();

    void seekTO(int seek);

    void onSeekChange(int position, int current);

    void onPlayStateChange(int state);
}

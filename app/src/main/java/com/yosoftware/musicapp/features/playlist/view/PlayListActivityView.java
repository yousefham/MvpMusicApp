package com.yosoftware.musicapp.features.playlist.view;

import com.yosoftware.musicapp.features.all_music.data.model.Music;

import java.util.ArrayList;

public interface PlayListActivityView {
    void onMusicListUpdate(ArrayList<Music> music);

    void onPlayListDeleteSuccess();

    void onPlayListDeleteError();
}

package com.yosoftware.musicapp.features.all_music.view;

import com.yosoftware.musicapp.features.all_music.data.model.Music;

import java.util.ArrayList;

public interface AllMusicView {

    void onMusicListUpdate(ArrayList<Music> music);
}

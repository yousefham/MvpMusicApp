package com.yosoftware.musicapp.features.all_music.presenter;

import android.app.Activity;

import com.yosoftware.musicapp.core.Utils.MusicUtils;
import com.yosoftware.musicapp.features.all_music.view.AllMusicFragmentView;

public class AllMusicPresenter {

    Activity activity;
    AllMusicFragmentView view;
    String path;

    public AllMusicPresenter(Activity activity, AllMusicFragmentView view, String path) {
        this.activity = activity;
        this.view = view;
        this.path = path;
    }

    public void getMusicList() {
        view.onMusicListUpdate(MusicUtils.getMusicList(path, activity));
    }
}

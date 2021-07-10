package com.yosoftware.musicapp.features.playlists.presenter;

import android.app.Activity;

import com.yosoftware.musicapp.core.datasources.DataBaseHelper;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;
import com.yosoftware.musicapp.features.playlists.view.PlaylistsFragmentView;

public class PlayListPresenter {
    Activity activity;
    PlaylistsFragmentView view;

    public PlayListPresenter(Activity activity, PlaylistsFragmentView view) {
        this.activity = activity;
        this.view = view;
    }

    public void getPlayLists() {
        view.onPlaylistUpdate(DataBaseHelper.with(activity).getPlayLists());
    }

    public void addPlayList(PlayList playList) {
        DataBaseHelper.with(activity).addPlayList(playList);
        getPlayLists();
    }
}

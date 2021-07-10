package com.yosoftware.musicapp.features.playlist.presenter;

import android.app.Activity;

import com.yosoftware.musicapp.core.datasources.DataBaseHelper;
import com.yosoftware.musicapp.features.playlist.view.PlayListActivityView;

public class PlayListDetailsPresenter {
    Activity activity;
    PlayListActivityView view;
    int playListId;

    public PlayListDetailsPresenter(Activity activity, PlayListActivityView view, int playListId) {
        this.activity = activity;
        this.view = view;
        this.playListId = playListId;
    }

    public void getMusicList() {
        view.onMusicListUpdate(DataBaseHelper.with(activity).getMusic(playListId));
    }

    public void deletePlayList() {
        boolean status = DataBaseHelper.with(activity).DeletePlayList(playListId, activity);
        if (status) {
            view.onPlayListDeleteSuccess();
        } else {
            view.onPlayListDeleteError();
        }
    }
}

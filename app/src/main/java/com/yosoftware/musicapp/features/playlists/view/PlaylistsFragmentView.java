package com.yosoftware.musicapp.features.playlists.view;

import com.yosoftware.musicapp.features.playlists.data.model.PlayList;

import java.util.ArrayList;

public interface PlaylistsFragmentView {

    void onPlaylistUpdate(ArrayList<PlayList> playLists);
}

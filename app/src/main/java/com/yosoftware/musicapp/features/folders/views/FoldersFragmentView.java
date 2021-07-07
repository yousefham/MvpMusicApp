package com.yosoftware.musicapp.features.folders.views;

import com.yosoftware.musicapp.features.folders.data.model.Folder;

import java.util.ArrayList;

public interface FoldersFragmentView {

    void onFoldersListUpdate(ArrayList<Folder> folders);
}

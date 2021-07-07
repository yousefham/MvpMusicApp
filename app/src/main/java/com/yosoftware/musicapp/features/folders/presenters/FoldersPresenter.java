package com.yosoftware.musicapp.features.folders.presenters;

import android.app.Activity;

import com.yosoftware.musicapp.core.datasources.StorageHelper;
import com.yosoftware.musicapp.features.folders.views.FoldersFragmentView;

public class FoldersPresenter {
    Activity activity;
    FoldersFragmentView mView;

    public FoldersPresenter(Activity activity, FoldersFragmentView mView) {
        this.activity = activity;
        this.mView = mView;
    }

    public void getFolders() {
        mView.onFoldersListUpdate(StorageHelper.getFolders(activity));
    }


}

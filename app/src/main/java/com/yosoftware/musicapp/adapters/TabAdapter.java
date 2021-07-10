package com.yosoftware.musicapp.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yosoftware.musicapp.features.all_music.view.AllMusicFragment;
import com.yosoftware.musicapp.features.folders.views.FoldersFragment;
import com.yosoftware.musicapp.features.playlists.view.PlayListFragment;

public class TabAdapter extends FragmentPagerAdapter {
    Context context;
    int tabsCount;

    public TabAdapter(@NonNull FragmentManager fm, Context context, int tabsCount) {
        super(fm);
        this.context = context;
        this.tabsCount = tabsCount;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FoldersFragment();
            case 1:
                return new AllMusicFragment();
            case 2:
                return new PlayListFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}

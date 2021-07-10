package com.yosoftware.musicapp.core.Utils;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;

import com.yosoftware.musicapp.features.all_music.data.model.Music;

import java.util.ArrayList;

public class MusicUtils {

    public static ArrayList<Music> getMusicList(String path, Activity context) {
        ArrayList<Music> _music = new ArrayList<>();
        String selection = "";
        if (path.isEmpty()) {
            selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        } else {
            selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 AND " + MediaStore.Audio.Media.DATA + " LIKE '" + path + "/%'";
        }

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };
        Cursor cursor = context.managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);


        while (cursor.moveToNext()) {
            _music.add(new Music(cursor.getString(0), cursor.getString(2),
                    cursor.getString(1), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5)));
        }
        return _music;

    }
}

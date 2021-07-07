package com.yosoftware.musicapp.core.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.Arrays;

public class FolderUtils {
    public static String[] extension = {".aac", ".mp3", ".wav", ".ogg", ".midi", ".3gp", ".mp4", ".m4a", ".amr", ".flac"};

    public static int getAudioFileCount(String dirPath, Activity activity) {
        String selection = MediaStore.Audio.Media.DATA + " like ?";
        String[] projection = {MediaStore.Audio.Media.DATA};
        String[] selectionArgs = {dirPath + "%"};
        Cursor cursor = activity.managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);
        return cursor.getCount();
    }

    @SuppressLint("NewApi")
    public static boolean checkIfFileHasExtension(String s, String[] extn) {
        return Arrays.stream(extn).anyMatch(entry -> s.endsWith(entry));
    }
}

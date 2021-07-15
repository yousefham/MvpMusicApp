package com.yosoftware.musicapp.core.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MUSIC";
    public static final int version = 1;
    public static final String PLAYLISTS = "PLAYLISTS";
    public static final String MUSIC = "MUSIC";
    SQLiteDatabase db;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    public static DataBaseHelper with(Context context) {
        return new DataBaseHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CIV_query;
        CIV_query = "CREATE TABLE " + PLAYLISTS + "(PLAYLISTNAME TEXT,DURATION TEXT,CREATEATDATE TEXT)";
        db.execSQL(CIV_query);
//        "CREATE TABLE \"MUSIC\" (\n" +
//                "\t\"MUSIC_NAME\"\t TEXT ,\n" +
//                "\t\" PATH \"\tTEXT\n" +
//                ")"
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPlayList(PlayList playList) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PLAYLISTNAME", playList.getPlayListName());
        values.put("DURATION", playList.getDuration());
        values.put("CREATEATDATE", playList.getCreateAtDate());
        database.insert(PLAYLISTS, null, values);
        database.close();
    }

    public boolean EditPlayListInfo(PlayList playList) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PLAYLISTNAME", playList.getPlayListName());
        int status = database.update(PLAYLISTS, values, "ID=" + playList.getId(), null);
        database.close();
        if (status == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DeletePlayList(int Id, Context context) {
        db = this.getWritableDatabase();
        return db.delete(PLAYLISTS, "ID=" + Id, null) > 0;

    }

    public void addMusic(Music music, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MUSIC_NAME", music.getTitle());
        values.put("PATH", music.getPath());
        values.put("PLAYLIST_ID", music.getPlay_list_id());
        values.put("ARTIST", music.getArtist());
        values.put("DURATION", music.getDuration());
        db.insert(MUSIC, null, values);
        db.close();
        Toast.makeText(context, "Added To PlayList", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<Music> getMusic(int playlist_id) {
        db = this.getWritableDatabase();
        ArrayList<Music> music = new ArrayList<>();
        String query = "SELECT * FROM " + MUSIC + " WHERE PLAYLIST_ID = " + playlist_id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Music musicItem = new Music();
                musicItem.setTitle(cursor.getString(0));
                musicItem.setPath(cursor.getString(1));
                musicItem.setPlay_list_id(cursor.getInt(2));
                musicItem.setArtist(cursor.getString(3));
                musicItem.setDuration(cursor.getString(4));
                music.add(musicItem);
            } while (cursor.moveToNext());
        }

        return music;
    }

    public ArrayList<PlayList> getPlayLists() {
        db = this.getWritableDatabase();
        ArrayList<PlayList> playLists = new ArrayList<>();
        String query = "SELECT * FROM " + PLAYLISTS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PlayList playList = new PlayList();
                playList.setId(cursor.getInt(0));
                playList.setPlayListName(cursor.getString(1));
                playList.setDuration(cursor.getString(2));
                playList.setCreateAtDate(cursor.getString(3));
                playList.setMusic(getMusic(cursor.getInt(0)));
                playLists.add(playList);
            } while (cursor.moveToNext());
        }
        return playLists;
    }


}

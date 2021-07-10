package com.yosoftware.musicapp.features.playlists.data.model;

import com.yosoftware.musicapp.features.all_music.data.model.Music;

import java.util.ArrayList;

public class PlayList {
    int Id;
    String playListName;
    String duration;
    String createAtDate;
    ArrayList<Music> music;

    public PlayList() {
    }

    public PlayList(int id, String playListName) {
        Id = id;
        this.playListName = playListName;
    }

    public PlayList(int id, String playListName, String duration, String createAtDate) {
        Id = id;
        this.playListName = playListName;
        this.duration = duration;
        this.createAtDate = createAtDate;
    }

    public PlayList(String playListName, String duration, String createAtDate) {
        this.playListName = playListName;
        this.duration = duration;
        this.createAtDate = createAtDate;
    }



    public PlayList(String playListName, String duration, String createAtDate, ArrayList<Music> music) {
        this.playListName = playListName;
        this.duration = duration;
        this.createAtDate = createAtDate;
        this.music = music;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(String createAtDate) {
        this.createAtDate = createAtDate;
    }

    public ArrayList<Music> getMusic() {
        return music;
    }

    public void setMusic(ArrayList<Music> music) {
        this.music = music;
    }
}

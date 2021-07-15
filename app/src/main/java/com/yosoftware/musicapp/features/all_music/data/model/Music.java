package com.yosoftware.musicapp.features.all_music.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Music {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("play_list_id")
    @Expose
    private int play_list_id;


    public Music() {
    }

    public Music(String id, String title, String artist, String data, String displayName, String duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.data = data;
        this.displayName = displayName;
        this.duration = duration;
    }

    public Music(String id, String title, String artist, String data, String displayName, String duration, String path, int play_list_id) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.data = data;
        this.displayName = displayName;
        this.duration = duration;
        this.path = path;
        this.play_list_id = play_list_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPlay_list_id() {
        return play_list_id;
    }

    public void setPlay_list_id(int play_list_id) {
        this.play_list_id = play_list_id;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", data='" + data + '\'' +
                ", displayName='" + displayName + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

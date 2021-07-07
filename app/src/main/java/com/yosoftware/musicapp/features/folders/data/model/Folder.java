package com.yosoftware.musicapp.features.folders.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Folder {
    @SerializedName("folderName")
    @Expose
    private String folderName;
    @SerializedName("folderPath")
    @Expose
    private String folderPath;


    public Folder(String folderName, String folderPath) {
        this.folderName = folderName;
        this.folderPath = folderPath;
    }

    public Folder() {
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
}

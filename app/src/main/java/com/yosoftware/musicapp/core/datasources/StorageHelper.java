package com.yosoftware.musicapp.core.datasources;

import android.app.Activity;
import android.os.Environment;

import com.yosoftware.musicapp.core.Utils.AudioFilter;
import com.yosoftware.musicapp.core.Utils.FolderUtils;
import com.yosoftware.musicapp.features.folders.data.model.Folder;

import java.io.File;
import java.util.ArrayList;

public class StorageHelper {

    public static ArrayList<Folder> getFolders( Activity activity) {
        String root_sd = Environment.getExternalStorageDirectory().toString();
        ArrayList<Folder> dataSet = new ArrayList<>();
        File list[] = null;
        File file = new File(root_sd);
        list = file.listFiles(new AudioFilter());
        int counter = 0;
        for (int i = 0; i < list.length; i++) {
            String name = list[i].getName();
            int count = FolderUtils.getAudioFileCount(list[i].getAbsolutePath(), activity);
            if (count > 0 &&
                    !name.contains("Android") &&
                    !name.contains("Notifications") &&
                    !name.contains("Alarms") &&
                    !name.contains("Ringtones")) {
                if (list[i].isDirectory()) {
                    File list1[] = list[i].listFiles(new AudioFilter());
                    for (File ff : list1) {
                        if (ff.isDirectory()) {
                            int count1 = FolderUtils.getAudioFileCount(ff.getAbsolutePath(), activity);
                            if (count1 > 0) {
                                File list3[] = ff.listFiles(new AudioFilter());
                                Folder folder = null;
                                for (File fff : list3) {
                                    if (fff.isDirectory()) {
                                        int count2 = FolderUtils.getAudioFileCount(fff.getAbsolutePath(), activity);
                                        if (count2 > 0) {
                                            dataSet.add(new Folder(fff.getName(), fff.getPath()));
                                        }
                                    } else if (FolderUtils.checkIfFileHasExtension(fff.getName(), FolderUtils.extension)) {
                                        if (counter == 0) {
                                            folder = new Folder(fff.getParentFile().getName(), fff.getParentFile().getPath());
                                            counter++;
                                            dataSet.add(folder);
                                        } else if (folder != null && fff.getParentFile().getName().contains(folder.getFolderName())) {
                                            //System.out.println("Audio2 " + fff.getParentFile().getName());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return dataSet;
    }
}

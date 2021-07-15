package com.yosoftware.musicapp.features.all_music.view;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yosoftware.musicapp.adapters.MusicAdapter;
import com.yosoftware.musicapp.databinding.FragmentAllMusicBinding;
import com.yosoftware.musicapp.features.all_music.data.model.Music;
import com.yosoftware.musicapp.features.all_music.presenter.AllMusicPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AllMusic extends Fragment implements AllMusicView {
    AllMusicPresenter presenter;
    FragmentAllMusicBinding binding;
    ArrayList<Music> music_list;
    MusicAdapter adapter;

    public AllMusic() {
        // Required empty public constructor
    }


    public static AllMusic newInstance() {
        AllMusic fragment = new AllMusic();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllMusicBinding.inflate(inflater, container, false);
        presenter = new AllMusicPresenter(getActivity(), this, "");
        init();
        presenter.getMusicList();
        return binding.getRoot();
    }

    void init() {
        music_list = new ArrayList<>();
        adapter = new MusicAdapter(getContext(), music_list);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onMusicListUpdate(ArrayList<Music> music) {
        music_list.clear();
        music_list.addAll(music);
        Collections.sort(music_list, new Comparator<Music>() {
            @Override
            public int compare(Music o1, Music o2) {
                return o1.getDisplayName().compareToIgnoreCase(o2.getDisplayName());
            }
        });
        Collections.reverse(music_list);
        adapter.notifyDataSetChanged();
    }
}
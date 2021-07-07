package com.yosoftware.musicapp.features.folders.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.adapters.FoldersAdapter;
import com.yosoftware.musicapp.databinding.FolderItemBinding;
import com.yosoftware.musicapp.databinding.FragmentFoldersBinding;
import com.yosoftware.musicapp.features.folders.data.model.Folder;
import com.yosoftware.musicapp.features.folders.presenters.FoldersPresenter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoldersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoldersFragment extends Fragment implements FoldersFragmentView {
    FoldersPresenter presenter;
    FoldersAdapter adapter;
    ArrayList<Folder> folders;

    FragmentFoldersBinding binding;

    public FoldersFragment() {
        // Required empty public constructor
    }


    public static FoldersFragment newInstance() {
        FoldersFragment fragment = new FoldersFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoldersBinding.inflate(inflater, container, false);
        presenter = new FoldersPresenter(getActivity(), this);
        init();
        presenter.getFolders();
        return binding.getRoot();
    }

    void init() {
        folders = new ArrayList<>();
        adapter = new FoldersAdapter(getContext(), folders);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onFoldersListUpdate(ArrayList<Folder> folders) {
        folders.clear();
        folders.addAll(folders);
        Log.d("sizeFolder", folders.size() + "");
        adapter.notifyDataSetChanged();
    }
}
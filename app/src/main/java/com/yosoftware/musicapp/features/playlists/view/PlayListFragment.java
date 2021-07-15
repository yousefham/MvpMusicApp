package com.yosoftware.musicapp.features.playlists.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.adapters.PlayListAdapter;
import com.yosoftware.musicapp.core.General.OnItemClickListener;
import com.yosoftware.musicapp.core.Utils.PlayListUtils;
import com.yosoftware.musicapp.databinding.AddPlayListBinding;
import com.yosoftware.musicapp.databinding.FragmentPlayListBinding;
import com.yosoftware.musicapp.features.playlist.view.PlayListDetails;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;
import com.yosoftware.musicapp.features.playlists.presenter.PlayListPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class PlayListFragment extends Fragment implements PlaylistsFragmentView {


    PlayListPresenter presenter;
    FragmentPlayListBinding binding;
    ArrayList<PlayList> playListArrayList;
    PlayListAdapter adapter;


    public PlayListFragment() {
        // Required empty public constructor
    }


    public static PlayListFragment newInstance(String param1, String param2) {
        PlayListFragment fragment = new PlayListFragment();
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
        binding = FragmentPlayListBinding.inflate(inflater, container, false);
        presenter = new PlayListPresenter(getActivity(), this);
        init();
        presenter.getPlayLists();
        binding.addPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlayList();
            }
        });
        return binding.getRoot();
    }

    void init() {
        playListArrayList = new ArrayList<>();
        adapter = new PlayListAdapter(getContext(), playListArrayList);
        binding.recyclerView.setAdapter(adapter);
        int numOfCard = PlayListUtils.calculateNoOfColumns(getContext(), 159);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numOfCard));
        adapter.setOnItemClickListener(new OnItemClickListener<PlayList>() {
            @Override
            public void onItemClick(View view, int position, PlayList item) {
                Intent intent = new Intent(getContext(), PlayListDetails.class);
                intent.putExtra("playList", new Gson().toJson(item));
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public void onPlaylistUpdate(ArrayList<PlayList> playLists) {
        playListArrayList.clear();
        playListArrayList.addAll(playLists);
        adapter.notifyDataSetChanged();
    }

    private void AddPlayList() {
        Dialog dialog = new Dialog(getContext());
        AddPlayListBinding addPlayListBinding = AddPlayListBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(addPlayListBinding.getRoot());
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        addPlayListBinding.playListName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addPlayListBinding.createPlayList.setAlpha(1);
                addPlayListBinding.createPlayList.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addPlayListBinding.createPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playListName = addPlayListBinding.playListName.getText().toString();
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String dateTime = df.format(date);
                PlayList playList = new PlayList(playListName, "0", dateTime);
                presenter.addPlayList(playList);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                PlayList deleted = new Gson().fromJson(data.getStringExtra("playlist"), PlayList.class);
                playListArrayList.remove(deleted);
                adapter.notifyDataSetChanged();
                presenter.getPlayLists();
            }
        }

    }
}
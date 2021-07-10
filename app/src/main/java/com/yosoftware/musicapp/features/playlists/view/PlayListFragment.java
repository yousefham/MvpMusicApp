package com.yosoftware.musicapp.features.playlists.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

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
import com.yosoftware.musicapp.databinding.FragmentPlayListBinding;
import com.yosoftware.musicapp.features.playlist.view.PlayListDetails;
import com.yosoftware.musicapp.features.playlists.data.model.PlayList;
import com.yosoftware.musicapp.features.playlists.presenter.PlayListPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PlayListFragment extends Fragment implements PlaylistsFragmentView {


    PlayListPresenter presenter;
    FragmentPlayListBinding binding;
    ArrayList<PlayList> playListArrayList;
    PlayListAdapter adapter;
    Dialog dialog;

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
                startActivity(intent);
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
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_play_list);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText play_list_name = (EditText) dialog.findViewById(R.id.play_list_name);
        Button create = (Button) dialog.findViewById(R.id.create_play_list);
        play_list_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                create.setAlpha(1);
                create.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playListName = play_list_name.getText().toString();
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
}
package com.yosoftware.musicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.features.folders.data.model.Folder;

import java.util.ArrayList;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.ViewHolder>{
    Context context;
    ArrayList<Folder> folders;


    public FoldersAdapter(Context context, ArrayList<Folder> folders) {
        this.context = context;
        this.folders = folders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.folder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Folder folder = folders.get(position);
        holder.folder_name.setText(folder.getFolderName());
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView folder_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folder_name = (TextView) itemView.findViewById(R.id.folder_name);
            itemView.setOnClickListener(this::onClick);

        }


        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, MusicList.class);
//            intent.putExtra("path", folders.get(getAdapterPosition()).getFolderPath());
//            intent.putExtra("title", folders.get(getAdapterPosition()).getFolderName());
//            context.startActivity(intent);
        }
    }
}

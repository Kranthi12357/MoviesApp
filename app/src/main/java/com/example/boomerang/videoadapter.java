package com.example.boomerang;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class videoadapter extends RecyclerView.Adapter<videoadapter.ViewHolder> {

    ArrayList<videos> videoss;
    Context context;

    final ListItemClickListner listItemClickListner;

    public interface ListItemClickListner{
            void clicklistners(String key);
    }


    public videoadapter(Context context, ListItemClickListner listItemClickListner){
        this.context = context;;
        this.listItemClickListner = listItemClickListner;

    }
    public void setdata(ArrayList<videos> videos){
        this.videoss = videos;
        notifyDataSetChanged();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String key = videoss.get(getAdapterPosition()).key;
            listItemClickListner.clicklistners(key);
        }
    }
    @NonNull
    @Override
    public videoadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull videoadapter.ViewHolder holder, int position) {

        videos video = videoss.get(position);
        Picasso.with(context)
        .load("https://img.youtube.com/vi/" + video.key+ "/hqdefault.jpg")
        .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(videoss == null){
            return  0;
        }
        else {
            return videoss.size();
        }
    }


}

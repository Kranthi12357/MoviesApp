package com.example.boomerang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.ViewHolder> {

    List<MovieEntity> movieEntities;
    Context context;
    public favouriteAdapter(Context context){
        this.context = context;
    }
    public void setdata(List<MovieEntity> movieEntities){
        this.movieEntities = movieEntities;
        notifyDataSetChanged();
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieEntity movie = movieEntities.get(position);
        Picasso.with(context)
                .load(movie.getImage())
                .into(holder.imageView);
            holder.textView.setText(String.valueOf(movie.getId()));

    }

    @Override
    public int getItemCount() {
        if(movieEntities == null){
            return  0;
        }
        else {
            return movieEntities.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textfav);
            imageView = itemView.findViewById(R.id.favimages);
        }
    }
}

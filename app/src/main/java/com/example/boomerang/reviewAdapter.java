package com.example.boomerang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.ViewHolder>{

    ArrayList<reviewlist> reviewlists;

    public void setdata(ArrayList<reviewlist> reviewlists){
        this.reviewlists = reviewlists;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public reviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewlistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reviewAdapter.ViewHolder holder, int position) {
            reviewlist r = reviewlists.get(position);
            holder.textView.setText(r.author);
            holder.textView1.setText(r.content);
    }

    @Override
    public int getItemCount() {
       if(reviewlists == null){
           return  0;
       }
       else {
           return reviewlists.size();
       }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView,textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.author);
            textView1 = itemView.findViewById(R.id.contents);

        }
    }
}

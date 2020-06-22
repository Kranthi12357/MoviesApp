package com.example.boomerang;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    final Context context;
    ArrayList<data> datas;
     private final ListItemClickListner mOnClickListener ;

    public interface ListItemClickListner{
        void onListemItemClick(String image,String release , Double avg,int id);
    }
    public Adapter(Context context,ListItemClickListner listItemClickListner){
        mOnClickListener = listItemClickListner;
        this.context = context;
    }
    public void setdata(ArrayList<data> d){
        this.datas = d;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        data d = datas.get(position);
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+d.image)
                .into(holder.imageView);
        holder.textView1.setText(String.valueOf(d.vote_average));
       // holder.textView2.setText(d.title);
        holder.textView.setText(d.release_date);
        Log.e("hello",d.image);
    }
    @Override
    public int getItemCount() {
        if(null == datas) {
            return  0;
        }else {
            return datas.size();
        }
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      final  TextView textView,textView1;
        ImageView imageView;
        public ViewHolder(View itemview){
            super(itemview);
            textView = itemview.findViewById(R.id.date);
            textView1 = itemview.findViewById(R.id.rating);
            //textView2 = (TextView)itemview.findViewById(R.id.name);
            imageView = itemview.findViewById(R.id.tumbnail);
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           String image = datas.get(getAdapterPosition()).image;
           String date = datas.get(getAdapterPosition()).release_date;
           Double avg = datas.get(getAdapterPosition()).vote_average;
           int id = datas.get(getAdapterPosition()).id;
           Log.e("id",String.valueOf(id));

            mOnClickListener.onListemItemClick(image,date,avg,id);
        }
    }


}

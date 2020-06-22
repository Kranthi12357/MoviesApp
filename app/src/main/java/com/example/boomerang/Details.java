package com.example.boomerang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends Fragment implements videoadapter.ListItemClickListner{
    int id;
    AppDatabase appDatabase;
    float var = 0;
    String image;
    videoadapter vd;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager ;
    Context context;
    FragmentManager fragmentManager;
    String api_key = "a3a1faa12cc290423ccec02780e2caad";
    String url = "https://api.themoviedb.org/3/movie/";
    ImageView imageView,imageViews;
    TextView textView,textView1,textView2;
    CircularProgressBar circularProgressBar;
    public Details(int id, String image, Context context,FragmentManager fragmentManager){
        this.id = id;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.image = image;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("id",String.valueOf(id));
        appDatabase = AppDatabase.getInstance(context);
        View root = inflater.inflate(R.layout.movies,container,false);
        imageView = root.findViewById(R.id.imageview);
        imageViews = root.findViewById(R.id.imageviews);
        textView = root.findViewById(R.id.textView);
         circularProgressBar = root.findViewById(R.id.circularProgressBar);
        textView1 = root.findViewById(R.id.overview);
         textView2 = root.findViewById(R.id.textView3);
        ImageButton imageButton = root.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutor.getInstance().getDiksIO().execute(
                        new Runnable() {
                            @Override
                            public void run() {
                                MovieEntity movieEntity = new MovieEntity(id,"https://image.tmdb.org/t/p/w500/"+image);
                                appDatabase.movieDao().insert(movieEntity);
                                Log.e("value","inserted");


                            }
                        }
                );


            }
        });
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+image)
                .into(imageView);
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+image)
                .into(imageViews);

        apiinterface app = servicebuilder.BuildServie(apiinterface.class);
        Call<MovieDetails> requestcall = app.movieDetailsCall(id,api_key);

        requestcall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(response.isSuccessful()){
                    textView.setText(response.body().title);
                    textView1.setText(response.body().overview);
                    var = (float)(response.body().vote_average)*10;
                    circular();
                    int budget = response.body().budget;
                    if(budget != 0){
                        textView2.setVisibility(View.VISIBLE);
                        textView2.setText("$" + budget);
                    }
                    else{
                        textView2.setVisibility(View.INVISIBLE);
                    }

                }
            }
            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.e("failed","failde");
            }
        });

        videos(root);
        review(root);
        return root;
    }

    public void circular(){
        circularProgressBar.setProgress((var));
        circularProgressBar.setBackgroundColor(10);
        circularProgressBar.setProgressWithAnimation(var, (long) 1000);
        circularProgressBar.setProgressMax(200f);
        circularProgressBar.setProgressBarColor(Color.GREEN);
        circularProgressBar.setProgressBarColorStart(Color.GREEN);
        circularProgressBar.setProgressBarColorEnd(Color.GREEN);
        circularProgressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);
        circularProgressBar.setBackgroundProgressBarColor(Color.GREEN);
        circularProgressBar.setBackgroundProgressBarColorStart(Color.GREEN);
        circularProgressBar.setBackgroundProgressBarColorEnd(Color.GREEN);
        circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);
        circularProgressBar.setProgressBarWidth(7f);
        circularProgressBar.setBackgroundProgressBarWidth(3f);
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);
    }

    public void videos(View root){
        recyclerView = root.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        vd = new videoadapter(context, this);
        recyclerView.setAdapter(vd);
        apiinterface api = servicebuilder.BuildServie(apiinterface.class);

        Call<VideoList> request = api.videolist(id,api_key);

        request.enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                if(response.isSuccessful()){
                   ArrayList<videos> v =  response.body().results;
                   vd.setdata(v);

                }
            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {

            }
        });


    }

    @Override
    public void clicklistners(String key) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+key));
        startActivity(intent);
    }
    public void  review(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.recyclereviewss);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        reviewAdapter ra = new reviewAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ra);

        apiinterface api = servicebuilder.BuildServie(apiinterface.class);

        Call<review> request = api.reviewslist(id,api_key);

        request.enqueue(new Callback<review>() {
            @Override
            public void onResponse(Call<review> call, Response<review> response) {
                assert response.body() != null;
                ArrayList<reviewlist> reviewss=  response.body().results;
               ra.setdata(reviewss);
            }

            @Override
            public void onFailure(Call<review> call, Throwable t) {

            }
        });
    }
    }


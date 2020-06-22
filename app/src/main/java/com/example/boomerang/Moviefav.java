package com.example.boomerang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class Moviefav extends AppCompatActivity {
    Context context;
    AppDatabase appDatabase;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    favouriteAdapter recycleViewFavourites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviefav);

        recyclerView = findViewById(R.id.recyclefav);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(context,2);
        recycleViewFavourites = new favouriteAdapter(context);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recycleViewFavourites);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getMvmovie().observe(this, movieEntities -> {
            recycleViewFavourites.setdata(movieEntities);
        });
    }
}
package com.example.boomerang;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface MovieDao {

    @Insert
     void insert(MovieEntity movieEntity);

    @Query("SELECT * FROM MovieEntity ")
    LiveData<List<MovieEntity>> getmovies();


}

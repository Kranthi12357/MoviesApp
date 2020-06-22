package com.example.boomerang;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel  {

    private LiveData<List<MovieEntity>> mvmovie;
    public ViewModel(@NonNull Application application) {
        super(application);

        mvmovie = AppDatabase.getInstance(getApplication()).movieDao().getmovies();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<MovieEntity>> getMvmovie() {
        return mvmovie;
    }
}

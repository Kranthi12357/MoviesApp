package com.example.boomerang;

import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

public class AppExecutor  {

    private  Executor diksIO;
    private static AppExecutor instance;
    private  Executor mainThread;

    public AppExecutor(ExecutorService io, Executor diksIO, Executor mainThread){
        this.diksIO = diksIO;
        this.mainThread = mainThread;


    }

    public static AppExecutor getInstance(){
        if(instance == null) {
            synchronized (new Object()) {
                instance = new AppExecutor(Executors.newSingleThreadExecutor()
                        , Executors.newFixedThreadPool(4),
                        new MainThreadexecutor());
            }
        }
        return instance;
    }

    public Executor getDiksIO() {
        return diksIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }
    public static class MainThreadexecutor implements Executor{

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
                mainThreadHandler.post(command);
        }
    }
}

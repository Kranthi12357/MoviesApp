package com.example.boomerang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String movieurl = "https://api.themoviedb.org/3/trending/movie/week?api_key=a3a1faa12cc290423ccec02780e2caad";
   public static RecyclerView recyclerView;
   GridLayoutManager gridLayoutManager;
   public   Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.relativelayout);
        adapter = new Adapter(MainActivity.this);
        gridLayoutManager  = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        load();
    }
    public void load(){
        new network(adapter).execute(movieurl);
    }
    public static class  network extends AsyncTask<String,Void, ArrayList<data>> {
       final Adapter adapter;
        public network(Adapter adapter){
            this.adapter = adapter;
        }
        @Override
        protected ArrayList<data> doInBackground(String... strings) {
            if(strings.length == 0){
                return  null;
            }
            try {
                String url = strings[0];
                Log.e("yeah ","entered");
                Uri basuri = Uri.parse(url);
                Uri.Builder builder = basuri.buildUpon();
                return javaapi.fetch(builder.toString());
            }
            catch (Exception e){
                return null;
            }
        }
        @Override
        protected void onPostExecute(ArrayList<data> data) {
            super.onPostExecute(data);
            adapter.setdata(data);
            recyclerView.setHasFixedSize(true);
        }
    }
}
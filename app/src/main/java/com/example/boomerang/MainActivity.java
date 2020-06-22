package com.example.boomerang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.ListItemClickListner{

    public static final String movieurl = "https://api.themoviedb.org/3/trending/movie/week?api_key=a3a1faa12cc290423ccec02780e2caad";
    public static final String popularurl = "https://api.themoviedb.org/3/movie/popular?api_key=a3a1faa12cc290423ccec02780e2caad";
    public static final String toprated = "https://api.themoviedb.org/3/movie/top_rated?api_key=a3a1faa12cc290423ccec02780e2caad";
    public static RecyclerView recyclerView;
   GridLayoutManager gridLayoutManager;
   public Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.relativelayout);
        adapter = new Adapter(MainActivity.this,  this);
        gridLayoutManager  = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        load(popularurl);
    }
    public void load(String movieurl){
        new network(adapter).execute(movieurl);
    }

    @Override
    public void onListemItemClick(String image,String release,Double avg,int id) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("image_url",image);
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.top_rated){
            load(toprated);
            return true;
        }
        if(item.getItemId() == R.id.trending){
            load(movieurl);
            return true;
        }
        if(item.getItemId() == R.id.popular){
            load(popularurl);

            return true;
        }

        if(item.getItemId() == R.id.favourites){
            Intent intent = new Intent(this,Moviefav.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.boomerang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        ViewPager viewpager = findViewById(R.id.viewpager);

        int id = 0;
        String image = null;
        if (getIntent().hasExtra("id")){
            id = getIntent().getIntExtra("id",0);
            image = getIntent().getStringExtra("image_url");
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),this,1,id,image,getApplicationContext());
        viewpager.setAdapter(fragmentAdapter);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewpager);
    }
}
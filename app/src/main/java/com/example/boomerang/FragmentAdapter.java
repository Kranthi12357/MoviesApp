package com.example.boomerang;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    int nooftabs;
    int id;
    String image;
    Resources resources;
    FragmentManager fragmentManager;
    Context context;
    public FragmentAdapter(FragmentManager fragmentManager, AppCompatActivity appCompatActivity,int nooftabs,int id,String image,Context context) {
        super(fragmentManager);
        this.nooftabs = nooftabs;
        this.id = id;
        this.fragmentManager = fragmentManager;
        this.image = image;
        this.context = context;
        resources = appCompatActivity.getResources();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new Details(id,image,context,fragmentManager);
            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0 : return "Movie";

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return nooftabs;
    }
}

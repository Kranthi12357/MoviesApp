package com.example.boomerang;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovieEntity")
public class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int Movie_id;
    public String image;

    public MovieEntity(int ide, String s) {
        Movie_id = ide;
        image = s;
    }

    public MovieEntity(){

    }

    public int getId() {
        return id;
    }

    public int getMovie_id() {
        return Movie_id;
    }
    public String getImage(){
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMovie_id(int movie_id){
        this.Movie_id = movie_id;
    }
    public void setImage(String image){
        this.image = image;
    }
}

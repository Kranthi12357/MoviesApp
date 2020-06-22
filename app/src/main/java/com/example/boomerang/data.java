package com.example.boomerang;

public class data {
   final String image;
   final String release_date;
    final int id;
    final Double vote_average;

    public data(String image,String release_date,Double vote_average,int id){
        this.image = image;
        this.id = id;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }
}

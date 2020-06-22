package com.example.boomerang;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiinterface {

    @GET(value = "{movie_id}/reviews")
    Call<review> reviewslist(@Path("movie_id") int id,@Query("api_key") String key);

    @GET(value = "{movie_id}")
     Call<MovieDetails> movieDetailsCall(@Path("movie_id") int movie_id,@Query("api_key") String key);

    @GET(value = "{movie_id}/videos")
    Call<VideoList> videolist(@Path("movie_id") int id,@Query("api_key") String key);
}

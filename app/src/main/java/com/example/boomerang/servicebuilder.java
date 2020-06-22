package com.example.boomerang;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class servicebuilder {

    public static String url = "https://api.themoviedb.org/3/movie/";

   public static HttpLoggingInterceptor interceptor =  new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static OkHttpClient.Builder okhttp = new OkHttpClient.Builder().addInterceptor(interceptor);

    public static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(okhttp.build());

    public static Retrofit retrofit = builder.build();

    public static  <T> T BuildServie(Class<T> ServiceType){
        return retrofit.create(ServiceType);
    }

}

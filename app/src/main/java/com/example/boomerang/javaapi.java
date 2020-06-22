package com.example.boomerang;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class javaapi {
    public static ArrayList<data> fetch(String requesturl){
        URL url = CreateUrl(requesturl);
        String jsonresponse = null;
        try {
            jsonresponse = makehttprequest(url);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
        ArrayList<data> datas = extract(jsonresponse);
        return datas;
    }
    public static ArrayList<data> extract(String json){
        if(TextUtils.isEmpty(json)){
            return null;

        }
        ArrayList<data>datas = new ArrayList<data>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray =  jsonObject.getJSONArray("results");
            for(int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String title = jsonObject1.getString("original_title");
                int id = jsonObject1.getInt("id");
                Double avg = jsonObject1.getDouble("vote_average");
                String image = jsonObject1.getString("poster_path");
                String date = jsonObject1.getString("release_date");
                datas.add(new data(image,date,avg,id));
            }
        }
        catch (Exception e){

        }
            return datas;
    }
    public static String makehttprequest(URL url) throws IOException {
        // If the URL is null, then return early.
        String jsonResponse = null;
        if (url == null) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e("hello", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("helloi", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    public static String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static URL CreateUrl(String requesturl){
        URL url = null;
        try{
             url = new URL(requesturl);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

}

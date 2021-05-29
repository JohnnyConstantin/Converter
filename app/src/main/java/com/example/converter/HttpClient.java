package com.example.converter;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient extends AsyncTask<String, String, String> {
    private String urlString = "https://converter-se-course.herokuapp.com";

    public String get(String location){
        try {
            URL url = new URL(urlString + location);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();

            return convertStreamToString(in);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String post(String location, String data){

        OutputStream out = null;

        try {
            URL url = new URL(urlString + location);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());


            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();

            os.close();
            return convertStreamToString(in);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected String doInBackground(String... strings) {
        if(strings[0].equals("POST")) {
            return this.post(strings[1], strings[2]);
        }
        else if (strings[0].equals("GET")){
            return this.get(strings[1]);
        }
        return null;
    }

    public String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}

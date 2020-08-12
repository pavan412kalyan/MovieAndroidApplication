package com.example.tejav.recyclerview;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchmoviedetails extends AsyncTask<String,String,String> {


    String Title;
    String Runtime;
    String genre;
    String Director;
    String Actors;
    String imdbRating;
    String language;
    String plot;
    String writer;
    String awards;
    String surl;
    String data;

    public fetchmoviedetails(String surl) {
        this.surl = surl;
    }


    @Override
    protected String doInBackground(String... voids) {
        try {
            URL url = new URL(surl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;

            }
            data = data.substring(4, data.length() - 4);
            JSONObject jsonObject = new JSONObject(data);
            Title=jsonObject.getString("Title");
            Runtime=jsonObject.getString("Runtime");
            genre=jsonObject.getString("Genre");
            Actors=jsonObject.getString("Actors");
            imdbRating=jsonObject.getString("imdbRating");
            Director=jsonObject.getString("Director");
            writer=jsonObject.getString("Writer");
            plot=jsonObject.getString("Plot");
            awards=jsonObject.getString("Awards");
            language=jsonObject.getString("Language");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        Subactivity.imdb.setText("\n\nIMDB:\n"+imdbRating);
        Subactivity.title.setText(Title);
        Subactivity.runtime.setText("\n\nRUNTIME\n"+Runtime);
        Subactivity.actors.setText("\n\nCAST\n"+Actors);
        Subactivity.director.setText("\n\nDIRECTOR\n"+Director);;
        Subactivity.writer.setText("\n\nWRITERS\n"+writer);
        Subactivity.plot.setText("\n\nPLOT\n"+plot);
        Subactivity.awards.setText("\n\nAWARDS\n"+awards);
        Subactivity.language.setText("\n\nLANGUAGE\n"+language);
        Subactivity.genre.setText("\n\nGENRES\n"+genre);


    }
}
package com.example.tejav.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import  java.io.*;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Subactivity extends AppCompatActivity {

  private   ImageView imageView;
  public static TextView title,runtime,genre,director,actors,imdb,language,plot,writer,
    awards;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);
        imageView=(ImageView)findViewById(R.id.Poster);
        title=(TextView)findViewById(R.id.Title);
        runtime=(TextView)findViewById(R.id.Runtime);
        genre=(TextView)findViewById(R.id.Genre);
        language=(TextView)findViewById(R.id.Language);
        director=(TextView)findViewById(R.id.Director);
        plot=(TextView)findViewById(R.id.Plot);
        writer=(TextView)findViewById(R.id.Writer);
        actors=(TextView)findViewById(R.id.Actors);
        awards=(TextView)findViewById(R.id.Awards);
        imdb=(TextView)findViewById(R.id.Imdb);




        Intent intent = getIntent();
     String posterurl = intent.getStringExtra("posterurl");
        String title = intent.getStringExtra("title");
        String searchurl="http://www.omdbapi.com/?apikey=d31af0a6&t="+title;
        fetchmoviedetails fetchmoviedetails = new fetchmoviedetails(searchurl);
        fetchmoviedetails.execute();
        Picasso.get().load(posterurl).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).into(imageView);



    }





}

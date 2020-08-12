package com.example.tejav.recyclerview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import android.support.v7.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener {

    private  static  String url= "https://api.themoviedb.org/3/movie/now_playing?api_key=974f6af2ff282991134b3f8fb68fb936";
    private  String searchurl="https://api.themoviedb.org/3/search/movie?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&query=";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
   private SwipeRefreshLayout swipe;
    String userInput="";   //for in on query text change
   int type=0;
    String api="974f6af2ff282991134b3f8fb68fb936";
    String imdbid="tt0111161";


    String mnowplaying="https://api.themoviedb.org/3/movie/now_playing?api_key="+api+"&language=en-US&page=1";
   String popuparmovies="https://api.themoviedb.org/3/movie/popular?api_key="+api+"&language=en-US&page=1";
   String topratedmovies="https://api.themoviedb.org/3/movie/top_rated?api_key="+api+"&language=en-US&page=1";
   String upcomingmovie="https://api.themoviedb.org/3/movie/upcoming?api_key="+api+"&language=en-US&page=1";
  // String topimdb="https://api.themoviedb.org/3/find/"+imdbid+"?api_key="+api+"&language=en-US&external_source=imdb_id";
    int pageno=1;
  String imdblist= "https://api.themoviedb.org/4/list/634?page="+pageno+"api_key="+api;


    //connecton




private List<listitems> listitems1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        swipe=(SwipeRefreshLayout)findViewById(R.id.swipeRefresh) ;
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listitems1.clear();
                loadrecyclerview(url);

            }
        });
        recyclerView.setHasFixedSize(true);
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems1 = new ArrayList<>();

  loadrecyclerview(url);


    }

    private  void loadrecyclerview(String url){
        final ProgressDialog progressDialog= new ProgressDialog(this);
progressDialog.setMessage("Loading  data....");
progressDialog.show();
        if(userInput.length()>0) {
            progressDialog.dismiss();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(swipe.isRefreshing())
                {
                    swipe.setRefreshing(false);
                }



                try {
                    JSONObject jsonObject = new JSONObject(response);
                   JSONArray results = null;
                    if(jsonObject.has("results"))
                    {
                        results= jsonObject.getJSONArray("results");

                    }

                   // JSONArray results= jsonObject.getJSONArray("results");
               /*    if(jsonObject.has("movie_results"))
                   {
                        results= jsonObject.getJSONArray("movie_results");
                   }
                   */
                    for (int i =0;i<results.length();i++)
                    {
                        JSONObject movie=results.getJSONObject(i);

                     listitems items = new listitems(movie.getString("title"),movie.getString("overview"),movie.getString("poster_path"),movie.getString("release_date"),movie.getDouble("vote_average"),movie.getString("backdrop_path"));

                     listitems1.add(items);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new Listadapter(listitems1,getApplicationContext());
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
//tooool bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {





        getMenuInflater().inflate(R.menu.menulayout,menu);

        MenuItem menuItem =menu.findItem(R.id.search_bar);
        SearchView searchView =(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        ////tv

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.imdbtop :
            {

                    listitems1.clear();
                    //imdblist= "https://api.themoviedb.org/4/list/634?page="+pageno+"api_key="+api;
            for (int i=0;i<13;i++) {
                imdblist= "https://api.themoviedb.org/4/list/634?page="+pageno+"&api_key="+api;

                loadrecyclerview(imdblist);
                pageno+=1;

            }
            pageno=1;
                        return true;






            }

            case  R.id.tv:

            {
                if (item.getItemId()==R.id.tv)
                {
                    Toast.makeText(getApplicationContext(),"TV section",Toast.LENGTH_LONG).show();
                    Intent tvintent = new Intent(this,tvshow.class);
                    startActivity(tvintent);
                    return  true;
                    //searchurl="https://api.themoviedb.org/3/search/tv?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1&query=";
                }
            }
            case  R.id.mnowplaying :{
               listitems1.clear();
               loadrecyclerview(mnowplaying);
                return  true;
            }
            case  R.id.popularMovies :
            {
                listitems1.clear();
                loadrecyclerview(popuparmovies);
              return  true;
            }
            case  R.id.topratedmovie :
            {

                listitems1.clear();
                loadrecyclerview(topratedmovies);
                return  true;
            }
            case  R.id.upcomingmovie :
        {

            listitems1.clear();
            loadrecyclerview(upcomingmovie);
            return  true;
        }



        }




      /*  if (item.getItemId()==R.id.tv)
        {
            Toast.makeText(getApplicationContext(),"clivked",Toast.LENGTH_LONG).show();
            Intent tvintent = new Intent(this,tvshow.class);
            startActivity(tvintent);
            return  true;
            //searchurl="https://api.themoviedb.org/3/search/tv?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1&query=";
        }
        */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {


            String userInput =query.toLowerCase();
        String urlsearch=searchurl+userInput;
        //Toast.makeText(getApplicationContext(),urlsearch,Toast.LENGTH_SHORT).show();
        listitems1.clear();
        loadrecyclerview(urlsearch);
       // link=searchurl;
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {

         userInput =newText.toLowerCase();
        String urlsearch=searchurl+userInput;
        //Toast.makeText(getApplicationContext(),urlsearch,Toast.LENGTH_SHORT).show();
      if(userInput.length()>0) {
          listitems1.clear();
          loadrecyclerview(urlsearch);
      }


        return false;
    }

    //////fortvsearch bar



}

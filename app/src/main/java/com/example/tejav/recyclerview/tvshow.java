package com.example.tejav.recyclerview;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class tvshow extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    private RecyclerView tvrecycler;
    String api="974f6af2ff282991134b3f8fb68fb936";
  String tvsearchurl="https://api.themoviedb.org/3/search/tv?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1&query=";
String populartv= "https://api.themoviedb.org/3/tv/top_rated?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1";
   String tvsearch="https://api.themoviedb.org/3/tv/airing_today?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1";
String getlatest="https://api.themoviedb.org/3/tv/latest?api_key="+api+"&language=en-US";
String airingtoday="https://api.themoviedb.org/3/tv/airing_today?api_key="+api+"&language=en-US&page=1";
String tvonair="https://api.themoviedb.org/3/tv/on_the_air?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1";
String popular="https://api.themoviedb.org/3/tv/popular?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1";
String toprated="https://api.themoviedb.org/3/tv/top_rated?api_key=974f6af2ff282991134b3f8fb68fb936&language=en-US&page=1";
String teluguimdb="https://www.jasonbase.com/things/rX8b.json";

//Global variable
 // public   int teluguimdbclicked ;
   private  RecyclerView.Adapter adapter;
String userInput ="";
private SwipeRefreshLayout swipetv;

private List<tvmodel> tvmodelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow);
        tvrecycler=(RecyclerView)findViewById(R.id.tvrecyclerview);
        tvrecycler.setHasFixedSize(true);
        tvrecycler.setLayoutManager(new LinearLayoutManager(this));
        tvmodelList = new ArrayList<>();
        loadtvrecycler(tvsearch);


        swipetv=(SwipeRefreshLayout)findViewById(R.id.swipetv);
swipetv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        tvmodelList.clear();
        loadtvrecycler(tvsearch);
    }
});

    }

    private  void loadtvrecycler(String url) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading  data....");
        progressDialog.show();
          if(userInput.length()>0) {
            //  Toast.makeText(getApplicationContext(),userInput.length(),Toast.LENGTH_SHORT).show();

              progressDialog.dismiss();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                  if(swipetv.isRefreshing())
                 {
                  swipetv.setRefreshing(false);
                }


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("results");
//                    Toast.makeText(getApplicationContext(),results.length(),Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movie = results.getJSONObject(i);
                        /*     public tvmodel(String orginal_name, int vote_average, String poster_path, String first_air_date, String overview, String original_language) {
                         */
                        tvmodel items = new tvmodel(movie.getString("original_name"), movie.getDouble("vote_average"), movie.getString("poster_path"), movie.getString("first_air_date"), movie.getString("overview"), movie.getString("original_language"));

                        tvmodelList.add(items);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new Tvadapter(tvmodelList,getApplicationContext());
                tvrecycler.setAdapter(adapter);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
       // teluguimdbclicked=0;

    }


  ////////////////////////////toobar in tv show


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main2,menu);

        MenuItem menuItem =menu.findItem(R.id.searchtv);
        SearchView searchView =(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case  R.id.movieintent :{
                Intent moviemove = new Intent(this,MainActivity.class);
                startActivity(moviemove);

            }

            case R.id.teluguimdb:
            {
              //  teluguimdbclicked=1;
                tvmodelList.clear();
                loadtvrecycler("https://www.jasonbase.com/things/rX8b.json");
                 int bottom=tvrecycler.getBottom();
tvrecycler.getLayoutManager().smoothScrollToPosition(tvrecycler,new RecyclerView.State(),tvrecycler.getAdapter().getItemCount());
                Toast.makeText(getApplicationContext(), ""+bottom, Toast.LENGTH_SHORT).show();

                return true;

            }
           case  R.id.airingtoday : {
               tvmodelList.clear();
               loadtvrecycler(airingtoday);
               return  true;
           }

            case R.id.tvonair : {
                tvmodelList.clear();
                loadtvrecycler(tvonair);
                return  true;
            }
                case R.id.popular : {
                    tvmodelList.clear();
                    loadtvrecycler(popular);
                    return  true;
                }
            case R.id.toprated : {
                tvmodelList.clear();
                loadtvrecycler(toprated);
                return  true;
            }
            case R.id.getlatest : {
                tvmodelList.clear();
                loadtvrecycler(getlatest);
                return  true;
            }



        }


     /*   if(item.getItemId()==R.id.populartvshows) {
            tvmodelList.clear();
            loadtvrecycler(populartv);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        String userInput =query.toLowerCase();
        String urlsearch=tvsearchurl+userInput;
       // Toast.makeText(getApplicationContext(),urlsearch,Toast.LENGTH_SHORT).show();
        tvmodelList.clear();
        loadtvrecycler(urlsearch);
        // link=searchurl;
        return false;    }

    @Override
    public boolean onQueryTextChange(String newText) {

         userInput =newText.toLowerCase();
        String urlsearch=tvsearchurl+userInput;
       // Toast.makeText(getApplicationContext(),urlsearch,Toast.LENGTH_SHORT).show();
        if(userInput.length()>0) {
            tvmodelList.clear();
            loadtvrecycler(urlsearch);
        }


        return false;    }


    }




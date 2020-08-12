package com.example.tejav.recyclerview;

public class tvmodel {


/*
*
      "original_name": "Game of Thrones",
      "id": 1399,
      "name": "Game of Thrones",
      "vote_count": 4596,
      "vote_average": 8.18,
      "poster_path": "\/gwPSoYUHAKmdyVywgLpKKA4BjRr.jpg",
      "first_air_date": "2011-04-17",
      "popularity": 59.685954,
      "genre_ids": [
        10765,
        18,
        10759
      ],
      "original_language": "en",
      "backdrop_path": "\/gX8SYlnL9ZznfZwEH4KJUePBFUM.jpg",
      "overview": "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
      "origin_country": [
        "US"
* */


  private   String orginal_name;
  private   double vote_average;
  private   String poster_path;
 private    String first_air_date;
 private    String Overview;
  private   String original_language  ;
  //only for telugu imdb post from url

    public tvmodel(String orginal_name, double vote_average, String poster_path, String first_air_date, String overview, String original_language) {
        this.orginal_name = orginal_name;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date;
        this.Overview = overview;
        this.original_language = original_language;
    }





    public String getOrginal_name() {
        return orginal_name;
    }

    public void setOrginal_name(String orginal_name) {
        this.orginal_name = orginal_name;
    }

    public double getVote_average() {
        return vote_average/2;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {

       /* int teluguimdbclicked =new tvshow().teluguimdbclicked;
String telugu=""+teluguimdbclicked;
        Log.d("telugu",telugu);
        System.out.print("clicked==="+teluguimdbclicked);
        if(teluguimdbclicked==0)
            return  poster_path;
            */
        return "https://image.tmdb.org/t/p/w500"+poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
}

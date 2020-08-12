package com.example.tejav.recyclerview;

public class listitems {
    private String head;
    private String desc;
    private String imageurl;
    private String date;
    private  double rating;
    private  String posterurl;

    public listitems(String head, String desc, String imageurl, String date, double rating, String posterurl) {
        this.head = head;
        this.desc = desc;
        this.imageurl = imageurl;
        this.date = date;
        this.rating = rating;
        this.posterurl = posterurl;
    }

    public String getPosterurl() {

        return "https://image.tmdb.org/t/p/w500"+posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRating() {
        return rating/2;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return "Overview:\n"+desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getImageurl() {
        return "https://image.tmdb.org/t/p/w500"+imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }



}

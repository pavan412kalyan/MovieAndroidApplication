package com.example.tejav.recyclerview;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Listadapter extends RecyclerView.Adapter<Listadapter.ViewHolder>{

    List<listitems> listItems;
    private Context context;

    public Listadapter(List<listitems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout,parent,false);
        return  new ViewHolder(v);


    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        listitems listItem=listItems.get(position);
        holder.textViewhead.setText(listItem.getHead());
        final String title=listItem.getHead();
        holder.textViewdesc.setText(listItem.getDesc());
        holder.ratingBar.setRating((float) listItem.getRating());
        final double myrate= listItem.getRating();
        holder.date.setText(listItem.getDate());
        final String posterurl=listItem.getPosterurl();


        Picasso.get()
                .load(listItem.getImageurl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.no_poster)
                .into(holder.image);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent =new Intent(context.getApplicationContext(),Subactivity.class);
              intent.putExtra("posterurl",posterurl);
              intent.putExtra("title",title);
                context.getApplicationContext().startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{


        public TextView textViewhead;
        public  TextView textViewdesc;
        public ImageView image;
        public  TextView date;
        public RatingBar ratingBar;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);


            textViewhead=(TextView)itemView.findViewById(R.id.heading);
            textViewdesc=(TextView)itemView.findViewById(R.id.desc);
           image=(ImageView)itemView.findViewById(R.id.image);
           date=(TextView)itemView.findViewById(R.id.date);
           ratingBar=(RatingBar)itemView.findViewById(R.id.rating);
linearLayout=(LinearLayout)itemView.findViewById(R.id.linearlayout);

        }
    }

}

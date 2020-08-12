package com.example.tejav.recyclerview;

import android.content.Context;
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

import com.squareup.picasso.Picasso;

import java.util.List;

public class Tvadapter extends RecyclerView.Adapter<Tvadapter.ViewHolder> {

    List<tvmodel> tvmodelList;
    private Context context;

    public Tvadapter(List<tvmodel> tvmodelList, Context context) {
        this.tvmodelList = tvmodelList;
        this.context = context;
    }



    @NonNull
    @Override
    public Tvadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tvlistlayout,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull Tvadapter.ViewHolder holder, int position) {

       tvmodel tvmodel= tvmodelList.get(position) ;
       holder.tvhead.setText(tvmodel.getOrginal_name());
        holder.textViewdesc.setText(tvmodel.getOverview());
        holder.ratingBar.setRating((float) tvmodel.getVote_average());
        final double myrate= tvmodel.getVote_average();
        holder.date.setText(tvmodel.getFirst_air_date());
        final String posterurl=tvmodel.getPoster_path();
        Picasso.get()
                .load(tvmodel.getPoster_path())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.no_poster)
                .into(holder.image);




    }




        @Override
    public int getItemCount() {
        return tvmodelList.size();
    }


    public class  ViewHolder extends RecyclerView.ViewHolder{




        public TextView tvhead;
        public  TextView textViewdesc;
        public ImageView image;
        public  TextView date;
        public RatingBar ratingBar;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);



            tvhead=(TextView)itemView.findViewById(R.id.tvheading);
            textViewdesc=(TextView)itemView.findViewById(R.id.tvdesc);
            date=(TextView)itemView.findViewById(R.id.tvdate);
            image=(ImageView)itemView.findViewById(R.id.tvimage);
            ratingBar=(RatingBar)itemView.findViewById(R.id.tvrating);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearlayout);
        }






    }
}

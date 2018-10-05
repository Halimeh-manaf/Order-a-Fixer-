package com.example.manafnajdathalimah.firebase2;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Values_orders> moviesList;
    public Context mContext;
    DatabaseReference database;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button b2, b3;
        public TextView title, year, genre;


        public MyViewHolder(View view) {
            super(view);
            //initialize buttons and TextViews
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            b2 = (Button) view.findViewById(R.id.btnedit);
            b3 = (Button) view.findViewById(R.id.btndel);
        }
    }

    //constructor
    public MovieAdapter(Context mContext, List<Values_orders> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set layout to itemView using Layout inflater
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Values_orders movie = moviesList.get(position);
        holder.title.setText(movie.getRequest_user_email());
        holder.genre.setText(movie.getRequest_user_name());
        holder.year.setText(movie.getRequest_user_id());

        //Click listener of button delete
        holder.b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //point databaseReference to Movies
                database = FirebaseDatabase.getInstance().getReference().child("orders");
                //remove value of child movie.getKey()
                database.child(movie.getKey()).setValue(null);
                //remove item from list
                moviesList.remove(position);
                //notofy datachanged to adapter
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, moviesList.size());
                Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        //Click listener of Button Edit
        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, EditContent.class);
                //pass data though intent using puExtra
                i.putExtra("name", movie.getRequest_user_email());
                i.putExtra("genre", movie.getRequest_user_name());
                i.putExtra("year", movie.getRequest_user_id());
                i.putExtra("key", movie.getKey());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
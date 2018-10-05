package com.example.manafnajdathalimah.firebase2;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
  import android.os.Bundle;
  import android.support.v7.app.AppCompatActivity;
  import android.support.v7.widget.DefaultItemAnimator;
  import android.support.v7.widget.LinearLayoutManager;
  import android.support.v7.widget.RecyclerView;
  import android.support.v7.widget.Toolbar;
  import android.view.MotionEvent;
  import android.view.View;
  import android.widget.Button;

  import com.google.firebase.crash.FirebaseCrash;
  import com.google.firebase.database.DataSnapshot;
  import com.google.firebase.database.DatabaseError;
  import com.google.firebase.database.DatabaseReference;
  import com.google.firebase.database.FirebaseDatabase;
  import com.google.firebase.database.ValueEventListener;

  import java.util.ArrayList;
  import java.util.List;

public class List_orders_Activity extends AppCompatActivity {
        private List<Values_orders> movieList = new ArrayList<>();
        private RecyclerView recyclerView;
        private MovieAdapter mAdapter;
        DatabaseReference database;
    Toolbar toolbar;
        Button b1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_orders_);

             toolbar = (Toolbar) findViewById(R.id.toolbar1);
            toolbar.setTitle("UNIANDROID.COM");

            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar2);
            toolbar1.setTitle(R.string.app_name);

            //initialized button and recyclerview
            b1 = (Button) findViewById(R.id.btnadd);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            //Click listener of ADD NEW button
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), AddContent.class);
                    startActivity(i);
                }
            });

            //initialize Adapter
            mAdapter = new MovieAdapter(getApplicationContext(), movieList);
            //set layout manager to recycler view
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //set Adapter to recycler view
            recyclerView.setAdapter(mAdapter);
            FirebaseCrash.log("App Started");
            prepareMovieData();
        }

        @Override
        protected void onResume() {
            super.onResume();
            prepareMovieData();
        }

        //method to fetch data from firebase and add to RecyclerView
        private void prepareMovieData() {
            movieList.clear();
            //pint database reference to Movies
            database = FirebaseDatabase.getInstance().getReference().child("orders");

            //ValueEvent Listener --> executes everytime a datachange in Movies node in firebase occurs
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //clear list
                    movieList.clear();
                    //loop items
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        if (postSnapshot.getKey().toString().equals("counter")) {
                            //String x2 = childDataSnapshot.getValue().toString();
                            //Log.i("This is the string", x2);
                        } else {
                            //get value from postSnapshot to Movies object
                            Values_orders m = postSnapshot.getValue(Values_orders.class);
                            Values_orders movie = new Values_orders(m.getRequest_user_name(), m.getRequest_user_address(), m.getRequest_user_id(), postSnapshot.getKey());
                            //add movie object to list used in recycler
                            movieList.add(movie);
                            //notify data changed to recyclerView
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
package com.example.manafnajdathalimah.firebase2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class List_fixersActivity extends AppCompatActivity {

    ListView fixers;
    ArrayList<String> fixersinfo = new ArrayList<>();
    ArrayAdapter adapter;


    public void show_key_value(){

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = mFirebaseDatabase.getReference("orders");
        try {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                        String x = childDataSnapshot.getKey();
                        Log.i("this is the key", x); //displays the key for the node
                        if (childDataSnapshot.getKey().toString().equals("orders_counter")) {
                            //String x2 = childDataSnapshot.getValue().toString();
                            //Log.i("This is the string", x2);
                        } else {
                            Log.i("inside if cond", x); //displays the key for the node
                            String y = dataSnapshot.child(x).child("request_order_number").getValue().toString();
                            Log.i("this is the value", y);   //gives the value for given keyname
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fixers);

        fixers = findViewById(R.id.list_of_fixers);
        adapter = new ArrayAdapter(List_fixersActivity.this, android.R.layout.simple_list_item_1, fixersinfo);
        fixers.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Fixers").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                try {

                    String fullname = dataSnapshot.child("fixer_fullname").getValue().toString();
                    fixersinfo.add(fullname);
                    adapter.notifyDataSetChanged();

                    // To add all the other info
                   /*  String email = dataSnapshot.child("fixer_email").getValue().toString();
                    fixersinfo.add(email);
                    adapter.notifyDataSetChanged();
                    */

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        fixers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
              //  String value = (String) adapter.getItemAtPosition(position);
            Map<String,String> list;
            /*list.put("from","");
            list.put("type_of_service","");
            list.put("address","");
            list.put("mobile_no","");
            list.put("time","");
            list.put("image_url","");
            */
            FirebaseDatabase.getInstance().getReference().child("Users");
            }
        });
    }}

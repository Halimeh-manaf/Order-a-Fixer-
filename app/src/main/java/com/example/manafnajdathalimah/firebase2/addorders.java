package com.example.manafnajdathalimah.firebase2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class addorders extends AppCompatActivity implements
        View.OnClickListener, AdapterView.OnItemSelectedListener {

    public String user_id;
    public int counter;
    public Spinner spinner;
    public static final String[] paths = {"Plumber", "Electrician", "Technician"};
    DatabaseReference mDatabase;
    EditText request_adress;
    EditText mobile_no;
    EditText time;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String service_type;



   /* public void increase_order_number() {             //get a reference to order_number

        mDatabase = FirebaseDatabase.getInstance().getReference().child("order_number");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot != null) {
                    final order_num cou = new order_num();
                    String x = dataSnapshot.child("order_number").getValue().toString();
                    //String x = "0";
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("order_number");
                    // int cond = 1;
                    //while (x != null) {
                    // if (cond != 0){
                    //  for (int i = 0; i<1;i++){
                    counter = Integer.parseInt(x);
                    cou.c = Integer.parseInt(x);
                    Log.i("Counter value originally", Integer.toString(cou.c));
                    cou.c++;
                    Log.i("Counter value after ++ ", Integer.toString(cou.c));

                    x = String.valueOf(counter);
                    // counter = Integer.valueOf(x);
                    HashMap<String, String> request_map = new HashMap<String, String>();
                    request_map.put("order_number", Integer.toString(cou.c));
                    //request_map.put("order_number", x = ""+counter);
                    //Log.i("INSIDE WHILE LOOP",Integer.toString(counter));
                    // Log.i("INSIDE WHILE LOOP",x);
                    mDatabase.setValue(request_map);
                    // break;
                    //  cond = 0;
                    //  }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Log.i("STRING X outside test", Integer.toString(cou.c));

    }

    */


    public void test(View view) {             //generate order counter folder.

        mDatabase = FirebaseDatabase.getInstance().getReference().child("orders").child("counter");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot != null) {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("orders").child("counter");
                    //HashMap<String, String> request_map = new HashMap<String, String>();
                   // request_map.put("order_number", "0");
                    String order_counter = "0";
                    //Values_counter val = new Values_counter();
                    Values_counter.setOrder_counter(order_counter);
                    mDatabase.setValue(Values_counter.getOrder_counter());
                    Log.i("counter",Values_counter.getOrder_counter().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Log.i("counter",Values_counter.getOrder_counter().toString());   // doesn't work cause of asynchr. problem

    }


    public void next(View View){

        //get a reference to user_id
        mDatabase = FirebaseDatabase.getInstance().getReference().child("orders");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Values_counter.setOrder_counter((String) dataSnapshot.child("counter").getValue());
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("orders").child("counter");
                    int x = Integer.parseInt(Values_counter.getOrder_counter());
                    x++;
                    Values_counter.setOrder_counter(String.valueOf(x));
                    Log.i("msg is here",Values_counter.getOrder_counter());

                   // HashMap<String, String> counter_map = new HashMap<String, String>();
                    //counter_map.put("order_number", Integer.toString(counter));
                    mDatabase.setValue(Values_counter.getOrder_counter());
                   // mDatabase = FirebaseDatabase.getInstance().getReference().child("orders").child(mAuth.getCurrentUser().getDisplayName() + "_" + Values_counter.getOrder_counter());
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("orders");

                    Values_orders new_order = new Values_orders(mAuth.getCurrentUser().getDisplayName().toString(),Values_counter.getOrder_counter(),mAuth.getCurrentUser().getUid().toString(),mAuth.getCurrentUser().getEmail().toString(),request_adress.getText().toString(),mobile_no.getText().toString(),time.getText().toString(),getIntent().getStringExtra("image_URL"),service_type);
                    mDatabase.push().setValue(new_order);

                    // mDatabase.setValue(new_order);

                    /*
                   //HashMap<String, String> request_map = new HashMap<String, String>();
                    request_map.put("request_user_name",mAuth.getCurrentUser().getDisplayName().toString());
                    request_map.put("request_order_number", Integer.toString(counter));
                    request_map.put("request_user_id", mAuth.getCurrentUser().getUid().toString());
                    request_map.put("request_user_email", mAuth.getCurrentUser().getEmail().toString());
                    request_map.put("request_user_address", request_adress.getText().toString());
                    request_map.put("request_user_mobile_no", mobile_no.getText().toString());
                    request_map.put("request_user_preferred_time", time.getText().toString());
                    request_map.put("request_user_damage_image", getIntent().getStringExtra("image_URL"));
                    request_map.put("request_user_service_type", service_type);
                    mDatabase.setValue(request_map);
                    */
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        // startActivity(new Intent(this,nextpage.class));
    }

    public void add_image(View view){
        try {
            Intent intent = new Intent(this, image_activity.class);
            intent.putExtra("user_id",getIntent().getStringExtra("user_id"));
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addorders);
        request_adress = findViewById(R.id.request_adress);
        mobile_no = findViewById(R.id.request_mobile_no);
        time = findViewById(R.id.request_time);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(addorders.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                service_type = "Plumber";
                Log.i("choice","Plumber");

                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                service_type = "Electrician";
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                service_type = "Technician";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.addorder_1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.add_image){
            Intent intent = new Intent(this,image_activity.class);
            startActivity(intent);

        } else if (item.getItemId()== R.id.list_fixer) {
            Intent intent = new Intent(this, List_orders_Activity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}


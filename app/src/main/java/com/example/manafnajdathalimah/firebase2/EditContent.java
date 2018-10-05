package com.example.manafnajdathalimah.firebase2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditContent extends AppCompatActivity {
    EditText et1, et2, et3;
    Button b1;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("UNIANDROID.COM");

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar1.setTitle(R.string.app_name);

        //point database reference to Movies in Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("orders");

        //initialize edittext and button
        et1 = (EditText) findViewById(R.id.etmov);
        et2 = (EditText) findViewById(R.id.etgnr);
        et3 = (EditText) findViewById(R.id.etyr);
        b1 = (Button) findViewById(R.id.btnedit);

        //getExtra is used to fetch data passed through intent
        String title = getIntent().getStringExtra("name");
        String genre = getIntent().getStringExtra("genre");
        String year = getIntent().getStringExtra("year");
        final String key = getIntent().getStringExtra("key");

        //show values in Edittexts
        et1.setText(title);
        et2.setText(genre);
        et3.setText(year);

        //click Listener of EDIT Button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("orders");
                            //add etails to MovieDetails object
                            Values_orders m = new Values_orders(mAuth.getCurrentUser().getDisplayName().toString(),et2.getText().toString(),mAuth.getCurrentUser().getUid().toString());
                            mDatabase.child(key).setValue(m);
                            Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
                            // finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
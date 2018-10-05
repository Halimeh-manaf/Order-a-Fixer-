package com.example.manafnajdathalimah.firebase2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddContent extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    Button b1;
    EditText e1, e2, e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("UNIANDROID.COM");

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar1.setTitle(R.string.app_name);

        //point databasereference to Movies


        //Initalize Editexts and button
        e1 = (EditText) findViewById(R.id.etmov);
        e2 = (EditText) findViewById(R.id.etgnr);
        e3 = (EditText) findViewById(R.id.etyr);
        b1 = (Button) findViewById(R.id.btnok);

        //Clicklistener of ADD button
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
                            Values_orders m = new Values_orders(mAuth.getCurrentUser().getDisplayName().toString(),e2.getText().toString(),mAuth.getCurrentUser().getUid().toString());
                            mDatabase.push().setValue(m);
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






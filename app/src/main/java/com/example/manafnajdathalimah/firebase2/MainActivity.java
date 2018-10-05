package com.example.manafnajdathalimah.firebase2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public EditText email;
    public EditText pass;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public void login (View view) {
       try {
           mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(MainActivity.this, "Authentication succeed.",
                                       Toast.LENGTH_SHORT).show();
                               // Sign in success, update UI with the signed-in user's information
                               next();
                           } else {
                               // If sign in fails, display a message to the user.
                               Toast.makeText(MainActivity.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                           }
                           // ...
                       }
                   });
       } catch (Exception e){
           e.printStackTrace();
       }

    }

    public void signup (View view){
        try {
            Intent intent = new Intent(this,SignupActivity.class);
            startActivity(intent);

 /*

            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
                    */

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signupfixer (View view) {
        try {
            Intent intent = new Intent(this, SignupfixerActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next (){
        try {
            Intent intent = new Intent(this,orders2.class);
            startActivity(intent);
        } catch (Exception e){

        }
        //move to next activity
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);

        if (mAuth.getCurrentUser() !=null){
            next();
        }
    }
}

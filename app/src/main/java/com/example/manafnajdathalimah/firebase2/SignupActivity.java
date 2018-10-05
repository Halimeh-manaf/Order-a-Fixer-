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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    EditText user_email;
    EditText user_id;
    EditText user_fullname;
    EditText user_no;
    EditText user_pass;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference mDatabase;

    public void next (){
        try {
            Intent intent = new Intent(this,orders2.class);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void signup (View view) {
        try {
            mAuth.createUserWithEmailAndPassword(user_email.getText().toString(), user_pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(task.getResult().getUser().getUid());
                                Values_user new_user = new Values_user(user_email.getText().toString(),user_id.getText().toString(),user_fullname.getText().toString(),user_no.getText().toString());
                                // to save extra user info in DB.
                               /* HashMap<String, String> userMap = new HashMap<String, String>();
                                userMap.put("email", user_email.getText().toString());
                                userMap.put("user_name", user_id.getText().toString()); // add if condition : must not contain '.', '#', '$', '[', or ']'
                                userMap.put("fullname", user_fullname.getText().toString());
                                userMap.put("number", user_no.getText().toString());
                                //userMap.put("user_pass", user_pass.getText().toString());
                                */
                                mDatabase.setValue(new_user);

                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user2 = mAuth.getCurrentUser();
                                Toast.makeText(SignupActivity.this, "Registeration succeeded.",
                                        Toast.LENGTH_SHORT).show();

                                user2 = task.getResult().getUser();
                                Task<Void> updateTask = user2.updateProfile(
                                        new UserProfileChangeRequest
                                                .Builder()
                                                .setDisplayName(user_id.getText().toString()).build());


                                next();
                            } else {
                                // If sign in fails, display a message to the user.
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                Toast.makeText(SignupActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("LoginActivity", "Failed Registration", e);

                            }

                            // ...
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user_email = findViewById(R.id.fixer_email);
        user_id = findViewById(R.id.fixer_id);
        user_fullname = findViewById(R.id.fixer_name);
        user_no = findViewById(R.id.fixer_no);
        user_pass = findViewById(R.id.fixer_pass);

    }
}

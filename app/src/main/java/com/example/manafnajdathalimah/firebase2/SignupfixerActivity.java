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

public class SignupfixerActivity extends AppCompatActivity {

    EditText fixer_email;
    EditText fixer_id;
    EditText fixer_fullname;
    EditText fixer_no;
    EditText fixer_pass;
    EditText fixer_type;
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

    public void signupfixer (View view) {
        try {
            mAuth.createUserWithEmailAndPassword(fixer_email.getText().toString(), fixer_pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Fixers").child(task.getResult().getUser().getUid());
                                // to save extra user info in DB.
                                HashMap<String, String> userMap = new HashMap<String, String>();
                                userMap.put("fixer_email", fixer_email.getText().toString());
                                userMap.put("fixer_name", fixer_id.getText().toString());
                                userMap.put("fixer_fullname", fixer_fullname.getText().toString());
                                userMap.put("fixer_no", fixer_no.getText().toString());
                                userMap.put("fixer_type", fixer_type.getText().toString());

                                //userMap.put("user_pass", user_pass.getText().toString());

                                mDatabase.setValue(userMap);

                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user2 = mAuth.getCurrentUser();
                                Toast.makeText(SignupfixerActivity.this, "Registeration succeeded.",
                                        Toast.LENGTH_SHORT).show();

                                user2 = task.getResult().getUser();
                                Task<Void> updateTask = user2.updateProfile(
                                        new UserProfileChangeRequest
                                                .Builder()
                                                .setDisplayName(fixer_id.getText().toString()).build());

                                next();
                            } else {
                                // If sign in fails, display a message to the user.
                                FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                Toast.makeText(SignupfixerActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_signupfixer);

        fixer_email = findViewById(R.id.fixer_email);
        fixer_id = findViewById(R.id.fixer_id);
        fixer_fullname = findViewById(R.id.fixer_name);
        fixer_no = findViewById(R.id.fixer_no);
        fixer_pass = findViewById(R.id.fixer_pass);
        fixer_type = findViewById(R.id.fixer_type);

    }
}

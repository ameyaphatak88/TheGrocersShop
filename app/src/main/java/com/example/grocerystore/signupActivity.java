package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText user,pass, email;
    ImageView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signup = findViewById(R.id.signup);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        email = findViewById(R.id.email);

        Intent intent = getIntent();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String password = pass.getText().toString();
                String username = user.getText().toString();

                if(username.isEmpty()) {
                    user.setError("username is required");
                    user.requestFocus();
                    return;
                }

                if(Email.isEmpty()) {
                    email.setError("E-Mail is required");
                    email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    email.setError("Please enter valid e-mail");
                    email.requestFocus();
                    return;
                }

                if(password.isEmpty()) {
                    pass.setError("Password Required");
                    pass.requestFocus();
                    return;
                }

                if(password.length() < 6) {
                    pass.setError("Min Password length should be 6 chars");
                    pass.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(username, Email);

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(signupActivity.this, "User registered Successfully", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(signupActivity.this, "Failed to Register!, Please try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(signupActivity.this, "Failed to Register!, Please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
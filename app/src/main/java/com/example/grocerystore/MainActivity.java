package com.example.grocerystore;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText username, password;
    ImageView login;
    TextView signup;
    ImageView adminlogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signup = findViewById(R.id.signup2);
        login = findViewById(R.id.login);

        adminlogin = findViewById(R.id.adminbutton);

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, adminlogin.class));
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, signupActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.isEmpty()) {
            username.setError("username is required");
            username.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            username.setError("Valid Email Required");
            username.requestFocus();
            return;
        }

        if(pass.isEmpty()) {
            password.setError("password required");
            password.requestFocus();
            return;
        }

        if(pass.length() < 6) {
            password.setError("Password should not be less than 6 chars");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String userID = mAuth.getCurrentUser().getUid();
                    Log.e("", "onComplete: " + userID);
                    Intent intent1 = new Intent(MainActivity.this, FirstActivity.class);
                    intent1.putExtra("userID", userID);
                    startActivity(intent1);
                }else {
                    Toast.makeText(MainActivity.this, "Failed to login, try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onBackPressed() {
        Toast.makeText(this, "Please Login or Sign-up", Toast.LENGTH_LONG).show();
        return;
    }

}
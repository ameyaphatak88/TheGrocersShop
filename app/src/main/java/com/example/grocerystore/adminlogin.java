package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {

    EditText userName, Password;
    ImageView adminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        userName = findViewById(R.id.adminusername);
        Password = findViewById(R.id.adminpassword);

        adminLogin = findViewById(R.id.adminlogin2);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString().trim();
                String pass = Password.getText().toString();

                if(user.equalsIgnoreCase("admin")) {
                    if(pass.equals("admin")) {
                        startActivity(new Intent(adminlogin.this, customerdetails.class));
                    }
                    else {
                        Toast.makeText(adminlogin.this,"Incorrect username or password", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(adminlogin.this,"Incorrect username or password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onBackPressed() {
        startActivity(new Intent(adminlogin.this, MainActivity.class));
    }

}
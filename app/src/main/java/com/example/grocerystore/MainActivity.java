package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView search;
    ImageView strawberryButton, mangoButton, broccoliButton, searchButton;
    ImageView wheat, rice, milk, eggs, oil, sugar;
    String passOnToNextActivity="";
    Button checkoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.search_text);
        searchButton = findViewById(R.id.search);
        strawberryButton = findViewById(R.id.straw);
        mangoButton = findViewById(R.id.man);
        broccoliButton = findViewById(R.id.bro);
        wheat = findViewById(R.id.imageView7);
        rice = findViewById(R.id.imageView8);
        milk = findViewById(R.id.imageView9);
        eggs = findViewById(R.id.eggs);
        oil = findViewById(R.id.imageView12);
        sugar = findViewById(R.id.sugar);
        checkoutbutton =  (Button) findViewById(R.id.button2);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = search.getText().toString();
                if(text.equals("")) {

                }
                else {
                    if(text.equals("Strawberry") || text.equals("strawberry")) {
                      passOnToNextActivity="Strawberry";
                    }
                    else if(text.equals("Mango") || text.equals("mango")) {
                        passOnToNextActivity="Mango";
                    }
                    else if(text.equals("Broccoli") || text.equals("broccoli")) {
                        passOnToNextActivity="Broccoli";
                    }
                    else if(text.equals("Wheat") || text.equals("wheat")) {
                        passOnToNextActivity="Wheat";
                    }
                    else if(text.equals("Rice") || text.equals("rice")) {
                        passOnToNextActivity="Rice";
                    }
                    else if(text.equals("Milk") || text.equals("milk")) {
                        passOnToNextActivity="Milk";
                    }
                    else if(text.equals("Eggs") || text.equals("eggs")) {
                        passOnToNextActivity="Eggs";
                    }
                    else if(text.equals("Oil") || text.equals("oil")) {
                        passOnToNextActivity="Oil";
                    }
                    else if(text.equals("Sugar") || text.equals("sugar")) {
                        passOnToNextActivity="Sugar";
                    }
                    else {
                        passOnToNextActivity="";
                        return;
                    }
                    goToSecondActivity();
                }
            }
        });

        checkoutbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                //passOnToNextActivity="Strawberry";
                goToCheckOutActivity();
            }
        });


        strawberryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity="Strawberry";
                goToSecondActivity();
            }
        });
        mangoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity="Mango";
                goToSecondActivity();
            }
        });
        broccoliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Broccoli";
                goToSecondActivity();
            }
        });
        wheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Wheat";
                goToSecondActivity();
            }
        });
        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Rice";
                goToSecondActivity();
            }
        });
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Milk";
                goToSecondActivity();
            }
        });
        eggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Eggs";
                goToSecondActivity();
            }
        });
        oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Oil";
                goToSecondActivity();
            }
        });
        sugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOnToNextActivity = "Sugar";
                goToSecondActivity();
            }
        });



    }

    public void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class).putExtra("passed", passOnToNextActivity);
        startActivity(intent);
    }

    public void goToCheckOutActivity(){
        Intent intent = new Intent(this, CheckoutActivity.class).putExtra("passed", passOnToNextActivity);
        startActivity(intent);
    }

}
package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    TextView productName, productDesc, cost, totalCost, quantity;
    ImageView displayImage, increment, decrement, cart;
    int count=1;
    int productPrice = 0;
    String product="";
    String userID="";

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        productName = findViewById(R.id.productName);
        productDesc = findViewById(R.id.productDesc);
        cost = findViewById(R.id.cost);
        totalCost = findViewById(R.id.totalCost);
        quantity = findViewById(R.id.quantity);

        displayImage = findViewById(R.id.displayImage);
        cart = findViewById(R.id.addToCart);

        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);

        Intent intent = getIntent();
        product = intent.getExtras().getString("passed");
        userID = intent.getExtras().getString("UserID");

        Log.e("", "userid:- " + userID);

        decideProduct(product);
        totalCost.setText("₹" + Integer.toString(productPrice));

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                quantity.setText(Integer.toString(count));
                int tot = count*productPrice;
                totalCost.setText("₹" + Integer.toString(tot));
                if(count>=1) {
                    cart.setVisibility(View.VISIBLE);
                }
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count < 0) {
                    count=0;
                }
                quantity.setText(Integer.toString(count));
                int tot = count*productPrice;
                totalCost.setText("₹" + Integer.toString(tot));
                if(count<=0) {
                    cart.setVisibility(View.INVISIBLE);
                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Products");
                String userid = userID;
                String productName = product;
                int quantity = count;
                products Products = new products(userid, productName, quantity, "PENDING");
                reference.child(userid).child(productName).setValue(Products).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SecondActivity.this, "Inserted Successfully", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(SecondActivity.this, "Failed to Insert!, Please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    void decideProduct(String product) {
        if(product.equals("Mango")) {
            productName.setText("Indian Mango");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mango));
            productDesc.setText("The 'Alphonso' mango is a named mango cultivar that originated in India. The pulp of Alphonso is much more delicious, and its skin is thin but firm. Its seed is also smaller as compared to the other mango cultivars, so there is more pulp to enjoy the great taste.");
            cost.setText("₹ 350/Kg");
            productPrice=350;
        }
        else if(product.equals("Strawberry")) {
            productName.setText("Strawberry");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.straw));
            productDesc.setText("Strawberries are bright red, juicy, and sweet.They’re an excellent source of vitamin C and manganese and also contain decent amounts of folate.Strawberries are very rich in antioxidants and plant compounds, which may have benefits for heart health and blood sugar control");
            cost.setText("₹ 140/Kg");
            productPrice=140;
        }
        else if(product.equals("Broccoli")) {
            productName.setText("Broccoli");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bro));
            productDesc.setText("Broccoli is an edible green plant in the cabbage family whose large flowering head, stalk and small associated leaves are eaten as a vegetable.");
            cost.setText("₹ 60/Kg");
            productPrice=60;
        }
        else if(product.equals("Wheat")) {
            productName.setText("Wheat");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheat));
            productDesc.setText("Eat our delicious wheat everyday, it contains all the goodness of sun-rippened wheat in its most delicious form - keeps your fit and fill at the same time.");
            cost.setText("₹ 20/Kg");
            productPrice=20;
        }
        else if(product.equals("Rice")) {
            productName.setText("Rice");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rice));
            productDesc.setText("Get the best quality rice at home from your favourite shop. Our rice is of the best-best quality which makes you love every grain of it.");
            cost.setText("₹ 80/Kg");
            productPrice=80;
        }
        else if(product.equals("Milk")) {
            productName.setText("Milk");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.milk));
            productDesc.setText("Get the best quality milk delivered at home. Get the best full cream milk in town with the goodness of calcium with no added preservatives.");
            cost.setText("₹ 40/L");
            productPrice=40;
        }
        else if(product.equals("Sugar")) {
            productName.setText("Sugar");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.sugar));
            productDesc.setText("Get the best Sugar in town at your favourite store, best finely-graded sugar in town with the same taste.");
            cost.setText("₹ 40/Kg");
            productPrice=40;
        }
        else if(product.equals("Eggs")) {
            productName.setText("Eggs");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.eggs));
            productDesc.setText("Eggs are rich in vitamin-D, protein and what not! get the best organic eggs in town for the lowest price available.");
            cost.setText("₹ 50/Dozen");
            productPrice=50;
        }
        else if(product.equals("Oil")) {
            productName.setText("Oil");
            displayImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.oil));
            productDesc.setText("Get the purest oil across all brands. This oil is lowest in fat and bad cholesterol. This oil promotes taste and keeps you healthy.");
            cost.setText("₹ 100/L");
            productPrice=100;
        }
    }
}
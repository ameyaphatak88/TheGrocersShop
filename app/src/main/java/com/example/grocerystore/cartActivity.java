package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cartActivity extends AppCompatActivity {
    DatabaseReference reference;

    String userID;
    ListView listView;
    FirebaseListAdapter adapter;
    ImageView checkout;
    TextView totalCostPrint;
    products pro;
    int totalCost=0;

    ArrayList<products>p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = findViewById(R.id.listView);
        checkout = findViewById(R.id.checkout);
        totalCostPrint = findViewById(R.id.printCost);

        Intent intent = getIntent();
        userID = intent.getExtras().getString("userID");

        //FirebaseListOptions<products> options = new FirebaseListOptions<products>.Builder<products>();

        ArrayList <ProductList> arrayList_ = new ArrayList<>();
        p = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference().child("Products").child(userID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    int imageSrc=0;
                    String pr = snapshot1.child("productName").getValue().toString();
                    String qty = snapshot1.child("productQty").getValue().toString();
                    int quant = Integer.parseInt(qty);
                    String sta = snapshot1.child("status").getValue().toString();

                    if(pr.equals("Broccoli") && sta.equals("PENDING")) {
                        totalCost += (quant*60);
                        imageSrc = R.drawable.bro;
                    }
                    else if(pr.equals("Mango") && sta.equals("PENDING")) {
                        totalCost += (quant*350);
                        imageSrc = R.drawable.mango;
                    }
                    else if(pr.equals("Strawberry") && sta.equals("PENDING")) {
                        totalCost += (quant*140);
                        imageSrc = R.drawable.strawberry;
                    }
                    else if(pr.equals("Wheat") && sta.equals("PENDING")) {
                        totalCost += (quant*20);
                        imageSrc = R.drawable.wheat;
                    }
                    else if(pr.equals("Rice") && sta.equals("PENDING")) {
                        totalCost += (quant*80);
                        imageSrc = R.drawable.r;
                    }
                    else if(pr.equals("Sugar") && sta.equals("PENDING")) {
                        totalCost += (quant*40);
                        imageSrc = R.drawable.sugar;
                    }
                    else if(pr.equals("Eggs") && sta.equals("PENDING")) {
                        totalCost += (quant*50);
                        imageSrc = R.drawable.eggs;
                    }
                    else if(pr.equals("Milk") && sta.equals("PENDING")) {
                        totalCost += (quant*40);
                        imageSrc = R.drawable.milk;
                    }
                    else if(pr.equals("Oil") && sta.equals("PENDING")) {
                        totalCost += (quant*100);
                        imageSrc = R.drawable.oil;
                    }
                    ProductList pro = new ProductList(pr,quant,imageSrc);
                    products p1 = new products(userID, pr, quant, "PAID");
                    if(sta.equals("PENDING"))
                    {
                        p.add(p1);
                        arrayList_.add(pro);
                    }

                }
                Log.e("", "onDataChange: "+arrayList_.size() );
                totalCostPrint.setText("₹ " + Integer.toString(totalCost));
                PersonListAdapter adapter = new PersonListAdapter(cartActivity.this, R.layout.list_item, arrayList_);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.e("", "onCreate: " + arrayList_.size() );

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<p.size(); i++) {
                    products p3 = p.get(i);
                    reference.child(p3.productName).setValue(p3);
                }
                if(totalCost!=0) {
                    Intent ci = new Intent(cartActivity.this, CheckoutActivity.class);
                    ci.putExtra("userID", userID);
                    startActivity(ci);
                }
                else {
                    Toast.makeText(cartActivity.this, "Nothing to checkout for", Toast.LENGTH_LONG).show();
                    Intent ci2 = new Intent(cartActivity.this, FirstActivity.class);
                    ci2.putExtra("userID", userID);
                    startActivity(ci2);
                }
            }
        });
    }
}
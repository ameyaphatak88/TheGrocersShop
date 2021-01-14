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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customerItemDetails extends AppCompatActivity {
    DatabaseReference reference;

    String userID;
    ListView listView;
    FirebaseListAdapter adapter;
    ImageView deliver, backButton;
    products pro;

    int flag;

    ArrayList<products>p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_item_details);
        listView = findViewById(R.id.listViewOrder);
        deliver = findViewById(R.id.deliverButton);

        flag=0;

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
                    if(sta.equals("PENDING")) {
                        flag=1;
                    }
                    if(pr.equals("Broccoli") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.bro;
                    }
                    else if(pr.equals("Mango") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.mango;
                    }
                    else if(pr.equals("Strawberry") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.strawberry;
                    }
                    else if(pr.equals("Wheat") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.wheat;
                    }
                    else if(pr.equals("Rice") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.r;
                    }
                    else if(pr.equals("Sugar") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.sugar;
                    }
                    else if(pr.equals("Eggs") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.eggs;
                    }
                    else if(pr.equals("Milk") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.milk;
                    }
                    else if(pr.equals("Oil") && sta.equals("PENDING")) {
                        imageSrc = R.drawable.oil;
                    }
                    ProductList pro = new ProductList(pr,quant,imageSrc);
                    products p1 = new products(userID, pr, quant, sta);
                    p.add(p1);
                    arrayList_.add(pro);

                }
                customerItemAdapter adapter = new customerItemAdapter(customerItemDetails.this, R.layout.customer_list_item, arrayList_);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1) {
                    Toast.makeText(customerItemDetails.this, "Not all items have been paid for.", Toast.LENGTH_LONG).show();
                }
                else {
                    DatabaseReference custReference = FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);
                    custReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(customerItemDetails.this, customerdetails.class));
                                Toast.makeText(customerItemDetails.this, "Items marked as delivered", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(customerItemDetails.this, customerdetails.class));
    }

}
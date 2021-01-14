package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customerdetails extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseListAdapter adapter;
    ListView listView;

    ArrayList<UserHelperCustomer> u;
    ArrayList<String>userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetails);


        listView = findViewById(R.id.listViewCustomer);

        u = new ArrayList<UserHelperCustomer>();
        userid = new ArrayList<String>();

        reference = FirebaseDatabase.getInstance().getReference().child("Customers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String userID = snapshot1.getKey();
                    String address = snapshot1.child("addr").getValue().toString();
                    String email = snapshot1.child("email").getValue().toString();
                    String phone = snapshot1.child("phone").getValue().toString();
                    String name = snapshot1.child("name").getValue().toString();
                    String mode_payment = snapshot1.child("mode_payment").getValue().toString();

                    UserHelperCustomer userHelperCustomer = new UserHelperCustomer(name, email, phone, address, mode_payment);

                    userid.add(userID);
                    u.add(userHelperCustomer);

                }

                customerListAdapter adapter = new customerListAdapter(customerdetails.this, R.layout.customer_list, u);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userIdToBePassedOn = userid.get(position);
                Intent intent = new Intent(customerdetails.this, customerItemDetails.class).putExtra("userID", userIdToBePassedOn);
                startActivity(intent);
            }
        });


    }

    public void onBackPressed() {
        startActivity(new Intent(customerdetails.this, adminlogin.class));
    }


}
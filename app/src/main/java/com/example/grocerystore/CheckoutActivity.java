/*package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }
}*/

package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CheckoutActivity extends AppCompatActivity {

    EditText name, e_mail, phone_no, addr;
    Spinner spinner;
    String[] items = new String[] {"Cash", "Credit Card", "Debit Card", "Net-Banking", "UPI"};
    ImageView submitButton;
    String userID;
    String MODEPAYMENT;


    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        name = findViewById(R.id.editTextTextPersonName); //done
        e_mail = findViewById(R.id.editTextTextEmailAddress); //done
        phone_no = findViewById(R.id.editTextPhone2); // done
        addr = findViewById(R.id.editTextTextPostalAddress); //done
        spinner = findViewById(R.id.dropdownPayment);
        submitButton = findViewById(R.id.button); //done

        Intent intent = getIntent();
        String bd = intent.getExtras().getString("userID");
        userID=bd;

        spinner = (Spinner)findViewById(R.id.dropdownPayment);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(CheckoutActivity.this,
                android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>parent, View v, int position, long id) {
                switch(position) {
                    case 0 : {
                        MODEPAYMENT="Cash";
                        break;
                    }
                    case 1 : {
                        MODEPAYMENT="Credit Card";
                        break;
                    }
                    case 2 : {
                        MODEPAYMENT="Debit Card";
                        break;
                    }
                    case 3 : {
                        MODEPAYMENT="Net Banking";
                        break;
                    }
                    case 4 : {
                        MODEPAYMENT="UPI";
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Customers");


                String NAME = name.getText().toString();
                String EMAIL = e_mail.getText().toString();
                String PHONE = phone_no.getText().toString();
                String ADDR = addr.getText().toString();

                UserHelperCustomer hc = new UserHelperCustomer(NAME, EMAIL, PHONE, ADDR, MODEPAYMENT);



                /*FirebaseDatabase.getInstance().getReference("Customers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(bd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CheckoutActivity.this, "Thank you for using GrocersStore!!", Toast.LENGTH_LONG).show();
                    }
                });*/

                reference.child(bd).setValue(hc);

                gotostartpage();
            }

            //startActivity(new Intent(MainActivity.this, FirstActivity.class));

        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
            }
        });

        e_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_mail.setText("");
            }
        });

        phone_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_no.setText("");
            }
        });

        addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addr.setText("");
            }
        });
    }

    public void gotostartpage() {
        Toast.makeText(this, "Checkout Successful", Toast.LENGTH_LONG).show();
        Log.e("", "gotostartpage: " + userID);
        Intent intent2 = new Intent(this, FirstActivity.class).putExtra("userID", userID);
        startActivity(intent2);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please complete checkout first.", Toast.LENGTH_LONG).show();
        return;
    }

}


/*
submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();

                String NAME = name.getText().toString();
                String EMAIL = e_mail.getText().toString();
                String PHONE = phone_no.getText().toString();
                String ADDR = addr.getText().toString();

                if(NAME.isEmpty()) {
                    name.setError("username is required");
                    name.requestFocus();
                    return;
                }

                if(EMAIL.isEmpty()) {
                    e_mail.setError("username is required");
                    e_mail.requestFocus();
                    return;
                }

                if(PHONE.isEmpty()) {
                    phone_no.setError("username is required");
                    phone_no.requestFocus();
                    return;
                }

                if(ADDR.isEmpty()) {
                    addr.setError("username is required");
                    addr.requestFocus();
                    return;
                }

               // Log.d("aa","qq");

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("aa","qq");
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("CustomerDetails");

                        UserHelperCustomer hc = new UserHelperCustomer(NAME, EMAIL, PHONE, ADDR);

                        reference.child(PHONE).setValue(hc);

                        //reference.setValue("First data");
                    }
                });


            }
        });
 */



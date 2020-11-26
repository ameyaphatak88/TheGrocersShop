package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity{
    String passOnToNextActivity = "";

    TextView name, e_mail, phone_no, mode_payment, addr;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkoutactivity);

        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        name = findViewById(R.id.editTextTextPersonName);
        e_mail = findViewById(R.id.editTextTextPersonName3);
        phone_no = findViewById(R.id.editTextPhone2);
        addr = findViewById(R.id.editTextTextPostalAddress);

        submitButton = (Button) findViewById(R.id.button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v){


                String s_name = name.getText().toString();
                String s_email = e_mail.getText().toString();
                String s_phoneno= phone_no.getText().toString();
                String s_addr = addr.getText().toString();

                helper.insertData(s_name, s_email, s_phoneno, s_addr, database);

                //---------------------

               /* Cursor cursor = database.rawQuery("SELECT NAME, EMAIL FROM PRODUCTS", new String[]{});

                if(cursor != null)
                {
                    cursor.moveToFirst();
                }

                StringBuilder builder = new StringBuilder();

                do{
                    String namee = cursor.getString(0);
                    String emaill = cursor.getString(1);

                    builder.append("NAME - " + namee + " PRICE - " + emaill);
                }
                while(cursor.moveToNext());

                TextView textView = findViewById(R.id.textView4);
                textView.setText(builder.toString());*/
                //-----------------------
            }
            });
    }

    /*public void goToFirstPage() {
        Intent intent = new Intent(this, MainActivity.class).putExtra("passed", passOnToNextActivity);
        startActivity(intent);
    }

    public void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class).putExtra("passed", passOnToNextActivity);
        startActivity(intent);
    }*/

}
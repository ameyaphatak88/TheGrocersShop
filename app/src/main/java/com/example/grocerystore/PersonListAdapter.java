package com.example.grocerystore;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/14/2017.
 */

public class PersonListAdapter extends ArrayAdapter<ProductList> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public PersonListAdapter(Context context, int resource, ArrayList<ProductList> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        int qty = getItem(position).getQty();
        int img = getItem(position).getImage();
        int cost=0;

        ProductList product = new ProductList(name,qty,img);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            ImageView productimage = (ImageView) convertView.findViewById(R.id.imageViewList);
            TextView productqty = (TextView) convertView.findViewById(R.id.textView2);
            TextView productstatus = (TextView) convertView.findViewById(R.id.textView3);
            TextView productTotal = (TextView) convertView.findViewById(R.id.textView4);

        if(name.equals("Broccoli")) {
            productimage.setImageResource(R.drawable.bro);
            cost=60;
        }
        else if(name.equals("Mango")) {
            productimage.setImageResource(R.drawable.mango);
            cost=350;
        }
        else if(name.equals("Strawberry")) {
            productimage.setImageResource(R.drawable.straw);
            cost=140;
        }
        else if(name.equals("Wheat")) {
            productimage.setImageResource(R.drawable.wheat);
            cost=20;
        }
        else if(name.equals("Rice")) {
            productimage.setImageResource(R.drawable.rice);
            cost=80;
        }
        else if(name.equals("Sugar")) {
            productimage.setImageResource(R.drawable.sugar);
            cost=40;
        }
        else if(name.equals("Eggs")) {
            productimage.setImageResource(R.drawable.eggs);
            cost=50;
        }
        else if(name.equals("Milk")) {
            productimage.setImageResource(R.drawable.milk);
            cost=40;
        }
        else if(name.equals("Oil")) {
            productimage.setImageResource(R.drawable.oil);
            cost=100;
        }
        productqty.setText(product.getName());
        productstatus.setText("quantity : " + Integer.toString(product.getQty()));
        String finText = "Total Cost : " + Integer.toString(cost) + " * " + Integer.toString(product.getQty()) + " = " + Integer.toString(product.getQty()*cost);
        productTotal.setText(finText);
        return convertView;
    }
}
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

public class customerItemAdapter extends ArrayAdapter<ProductList> {

    private static final String TAG = "customerItemAdapter";

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
    public customerItemAdapter(Context context, int resource, ArrayList<ProductList> objects) {
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

        ProductList product = new ProductList(name,qty,img);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView productimage = (ImageView) convertView.findViewById(R.id.customerItemImage);
        TextView productName = (TextView) convertView.findViewById(R.id.customerItemName);
        TextView productQty = (TextView) convertView.findViewById(R.id.customerItemQuantity);

        if(name.equals("Broccoli")) {
            productimage.setImageResource(R.drawable.bro);
        }
        else if(name.equals("Mango")) {
            productimage.setImageResource(R.drawable.mango);
        }
        else if(name.equals("Strawberry")) {
            productimage.setImageResource(R.drawable.straw);
        }
        else if(name.equals("Wheat")) {
            productimage.setImageResource(R.drawable.wheat);
        }
        else if(name.equals("Rice")) {
            productimage.setImageResource(R.drawable.rice);
        }
        else if(name.equals("Sugar")) {
            productimage.setImageResource(R.drawable.sugar);
        }
        else if(name.equals("Eggs")) {
            productimage.setImageResource(R.drawable.eggs);
        }
        else if(name.equals("Milk")) {
            productimage.setImageResource(R.drawable.milk);
        }
        else if(name.equals("Oil")) {
            productimage.setImageResource(R.drawable.oil);
        }
        productName.setText(product.getName());
        productQty.setText("quantity : " + Integer.toString(product.getQty()));
        return convertView;
    }
}
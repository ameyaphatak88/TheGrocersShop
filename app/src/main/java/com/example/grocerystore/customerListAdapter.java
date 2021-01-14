package com.example.grocerystore;


import android.content.Context;
import android.util.Log;
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

public class customerListAdapter extends ArrayAdapter<UserHelperCustomer> {

    private static final String TAG = "customerListAdapter";

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
    public customerListAdapter(Context context, int resource, ArrayList<UserHelperCustomer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        String addr = getItem(position).getAddr();
        String email = getItem(position).getEmail();
        String phone = getItem(position).getPhone();
        String mode_payment = getItem(position).getMode_payment();

        UserHelperCustomer user = new UserHelperCustomer(name,email,phone,addr,mode_payment);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nameText = (TextView) convertView.findViewById(R.id.customerName);
        TextView address = (TextView) convertView.findViewById(R.id.customerAddress);
        TextView phoneText = (TextView) convertView.findViewById(R.id.customerPhone);
        TextView emailText = (TextView) convertView.findViewById(R.id.customerEmail);

        nameText.setText("Name : " + name);
        address.setText("Address : " + addr);
        phoneText.setText("Phone Number : " + phone);
        emailText.setText("E-Mail ID : " + email);

        return convertView;
    }
}
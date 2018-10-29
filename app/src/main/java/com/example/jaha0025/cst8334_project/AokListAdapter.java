package com.example.jaha0025.cst8334_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AokListAdapter extends ArrayAdapter<ActsofK> {

    private  static final String TAG = "AokListAdaptor";

    private Context mContext;
    int mResource;

    public AokListAdapter(Context context, int resource, ArrayList<ActsofK> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       String id = getItem(position).getId();
       String aokTitle= getItem(position).getAokTitle();

       ActsofK aok = new ActsofK(id, aokTitle);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvid = convertView.findViewById(R.id.text_Title);
        TextView tvaoks = convertView.findViewById(R.id.aok);


        tvid.setText(id);
        tvaoks.setText(aokTitle);

        return  convertView;
    }
}

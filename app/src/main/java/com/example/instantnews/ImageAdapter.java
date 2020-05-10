package com.example.instantnews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Pair<String,Integer>> items;
    public ImageAdapter(Context context, ArrayList<Pair<String,Integer>> data) {
        this.mContext=context;
        items=data;
        Log.e("cat size: ",""+data.size());
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1=view;
        if (view1==null)
        {
            view1=LayoutInflater.from(mContext).inflate(R.layout.grid_display,viewGroup,false);
        }


        final Pair<String,Integer> item=items.get(i);
        ImageView img=view1.findViewById(R.id.grid_image);
        TextView txt=view1.findViewById(R.id.grid_text);
        img.setImageResource(item.second);
        txt.setText(item.first);
        Log.e("Category :",item.first);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Hi boys",Toast.LENGTH_SHORT).show();
                Intent categoryIntent=new Intent(mContext,MainActivity.class);
                categoryIntent.putExtra("category",item.first);

            }
        });



        return view1;
    }

}

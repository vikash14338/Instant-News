package com.example.instantnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsCategory extends AppCompatActivity {
    private ArrayList<Pair<String,Integer>> gridData;
    private ImageAdapter imageAdapter;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);
        gridData=new ArrayList<Pair<String, Integer>>();
        addDataToGridData();
        mGridView=findViewById(R.id.gridview);
        imageAdapter=new ImageAdapter(gridData);
        mGridView.setAdapter(imageAdapter);


    }
    private void addDataToGridData() {
        gridData.add(new Pair<String, Integer>("General",R.raw.general));
        gridData.add(new Pair<String, Integer>("Sports",R.raw.sports));
        gridData.add(new Pair<String, Integer>("Business",R.raw.business));
        gridData.add(new Pair<String, Integer>("Entertainment",R.raw.entertainment));
        gridData.add(new Pair<String, Integer>("Education",R.raw.education));
        gridData.add(new Pair<String, Integer>("Health",R.raw.health));
        gridData.add(new Pair<String, Integer>("Science",R.raw.science));
        gridData.add(new Pair<String, Integer>("Technology",R.raw.technology));

    }
    public class ImageAdapter extends BaseAdapter{

        ArrayList<Pair<String,Integer>> items;
        public ImageAdapter( ArrayList<Pair<String,Integer>> data) {

            items=data;
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
                //inflate the Layout where you want to display the data in grid manner
                view1= LayoutInflater.from(getApplicationContext()).inflate(R.layout.grid_display,viewGroup,false);
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
                   // Toast.makeText(getApplicationContext(),"Hi boys",Toast.LENGTH_SHORT).show();
                    Intent categoryIntent=new Intent(getApplicationContext(),MainActivity.class);
                    categoryIntent.putExtra("category",(item.first).toLowerCase());
                    startActivity(categoryIntent);
                    finish();

                }
            });



            return view1;
        }
    }


}

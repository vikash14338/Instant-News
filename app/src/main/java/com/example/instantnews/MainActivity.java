package com.example.instantnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private VerticalViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<NewsModel> newsData;
    private FloatingActionButton titlePicker;
    private String URI_DATA_EVERYTHING="https://newsapi.org/v2/everything?language=en&pageSize=100&apiKey=433b04a31b7149aa85029ec1b6a302ae&sortBy=popularity";
    private String URI_DATA="https://newsapi.org/v2/top-headlines?language=en&apiKey=433b04a31b7149aa85029ec1b6a302ae";
    private String API_KEY="433b04a31b7149aa85029ec1b6a302ae";
    private String Category="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // title picker allow to choose what type of news you want show
        titlePicker=findViewById(R.id.fb_header);
        newsData=new ArrayList<NewsModel>();
        viewPager=findViewById(R.id.vertical_view_pager);
        Intent catIntent=getIntent();
        Category=catIntent.getStringExtra("category");

        if (!TextUtils.isEmpty(Category)) {
            //Load data if this intent is invoked by category activity
            loadJson(URI_DATA_EVERYTHING+"&q="+Category);
        }else{
            //if this is start activity(Not invoked by Category Activity)
            loadJson(URI_DATA);
        }

//        Log.e("url: ", URI_DATA);
//
//        Log.e("size: ",""+newsData.size());

        titlePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,NewsCategory.class));
            }
        });

        //Allow user to know details about the news
        viewPager.setOnItemClickListener(new VerticalViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                NewsModel item=newsData.get(position);
                Intent webIntent=new Intent(MainActivity.this,NewsDetails.class);
                webIntent.putExtra("url",item.getContentUrl());
                Log.e("url: ",item.getContentUrl());
                startActivity(webIntent);

                Toast.makeText(MainActivity.this,"Hi there "+item.contentUrl,Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    //Adding Search option ,allow user to search news of their own choice
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.category_menu,menu);
        SearchManager searchManager= (SearchManager) getSystemService(SEARCH_SERVICE);
        final SearchView mSearchView= (SearchView) menu.findItem(R.id.menu_search).getActionView();
        MenuItem searchItem=menu.findItem(R.id.menu_search);
        mSearchView.setQueryHint("Search news");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length()>2)
                {
                    String query="&q="+s;
                    loadJson(URI_DATA_EVERYTHING+query);

                }
                mSearchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //loadJson(URI_DATA+"&query="+s);
                return false;
            }
        });

        searchItem.getIcon().setVisible(false,false);
        loadJson(URI_DATA);

        return true;
    }

    // Load data from give url
    private void loadJson(String uri) {
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!newsData.isEmpty())
                    newsData.clear();
                ArrayList<NewsModel> latestData=new ArrayList<>();

                try {
                    JSONObject newObject=new JSONObject(response);
                    JSONArray jsonArray=newObject.getJSONArray("articles");
                    Log.e("Array :",""+jsonArray.length());
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject obj=jsonArray.getJSONObject(i);
                        NewsModel item=new NewsModel(obj.getString("urlToImage"),obj.getString("content"),
                                obj.getString("title"),obj.getString("url"));
                        newsData.add(item);
                    }
                    if(newsData.isEmpty())
                    {
                        Toast.makeText(MainActivity.this,"No News Available on "+Category,Toast.LENGTH_SHORT).show();
                        Log.e("url: ",URI_DATA.substring(0,URI_DATA.indexOf("&category")));
                        loadJson(URI_DATA.substring(0,URI_DATA.indexOf("&category")));
                    }else{
                        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),newsData);
                        viewPager.setAdapter(viewPagerAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Load Error","error while loading");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }


}

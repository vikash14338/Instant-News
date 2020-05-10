package com.example.instantnews;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    ArrayList<NewsModel> items;
    public ViewPagerAdapter(FragmentManager fm, ArrayList<NewsModel> items) {
        super(fm);
        this.items=items;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ChildFragment child=new ChildFragment();
        Bundle bundle=new Bundle();
        NewsModel item=items.get(position);
        bundle.putString("title",item.title);
        bundle.putString("content",item.content);
        bundle.putString("contentUrl",item.contentUrl);
        bundle.putString("imageUrl",item.imageUrl);
        child.setArguments(bundle);
        return child;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}

package com.example.instantnews;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ChildFragment extends Fragment {

    ImageView imageView;
    TextView tvTitle,tvContent;

    public ChildFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_child,container,false);
        tvTitle=view.findViewById(R.id.newsTitle);
        tvContent=view.findViewById(R.id.newsContent);
        imageView=view.findViewById(R.id.newsImage);
        Bundle bundle=getArguments();
        tvTitle.setText(bundle.getString("title"));
        tvContent.setText(bundle.getString("content"));
        if (bundle.getString("imageUrl")!=null)
            Picasso.get().load(Uri.parse(bundle.getString("imageUrl"))).into(imageView);


        return view;
    }
}

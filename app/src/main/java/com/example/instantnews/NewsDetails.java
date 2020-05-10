package com.example.instantnews;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetails extends AppCompatActivity {

    private WebView displayContent;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Intent webIntent=getIntent();
        url=webIntent.getStringExtra("url");
        displayContent=findViewById(R.id.webView);


        displayContent.setWebChromeClient(new WebChromeClient());
        displayContent.getSettings().setLoadsImagesAutomatically(true);
        displayContent.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        displayContent.loadUrl(url);
    }

//    private class MyBrowser extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            view.loadUrl(url);
//            return true;
//        }
//    }
}

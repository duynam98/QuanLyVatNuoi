package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;

public class WebActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://nguoichannuoi.vn/");
    }

    private void initView() {
        webview = findViewById(R.id.webview);
    }
}

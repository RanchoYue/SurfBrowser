package com.yue.surfbrowser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class WebActivity extends AppCompatActivity {

    public static final String TAG = "yue_";
    private LocalHttpServer server;

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);
        WebView webView = findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // 启用JavaScript[1,6](@ref)
        settings.setAllowFileAccess(true);
        settings.setAllowUniversalAccessFromFileURLs(true); // 允许跨域请求[4](@ref)

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webView.loadUrl("http://localhost:8080/index.html");

            }
        });

        // 启动本地服务器（端口设为8080）
        server = new LocalHttpServer(8080, this);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (server != null) {
            server.stop(); // 停止服务器
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");

    }

}
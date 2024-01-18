package com.yue.surfbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.SearchView;

public class MainActivity extends Activity {

    private String mURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.findViewById(androidx.appcompat.R.id.search_plate).setBackground(null);
        searchView.findViewById(androidx.appcompat.R.id.submit_area).setBackground(null);
        searchView.setQuery("", true); // 设置输入的文本为空
        searchView.setIconified(false); // 展开 SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 搜索提交时的操作
                searchUrl();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 搜索框中文本改变时的操作
                mURL = newText;
                return false;
            }
        });
    }

    private void searchUrl() {
        Intent intent = new Intent(MainActivity.this, Html5Activity.class);
        if (!TextUtils.isEmpty(mURL)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", mURL);
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }
}

package com.example.philippe.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.philippe.testapp.utils.ViewManager.ViewUtils;


public class TestActivity extends AppCompatActivity {

    public static final String TEST_STRING = "ViewUtilsTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void showActionBar(View v)
    {
        if (getSupportActionBar().isShowing())
            ViewUtils.showActionBar(this, false);
        else
            ViewUtils.showActionBar(this, true);
    }

    public void setImageView(View v)
    {
        ViewUtils.setImageView(this, R.id.activity_test_imageview, R.mipmap.ic_launcher);
    }

    public void loadImageView(View v)
    {
        ViewUtils.loadImageView(this, R.id.activity_test_imageview, "https://commons.wikimedia.org/wiki/File:Neolitsea_sericea_kz2.jpg");
    }

    public void setTextView(View v)
    {
        ViewUtils.setTextView(this, R.id.activity_test_textview, TEST_STRING);
    }

    public void setOnClickListener(View v)
    {
        ViewUtils.setOnClickListener(this, R.id.activity_test_imageview, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void setVisibility(View v)
    {
        ViewUtils.setVisibility(this, R.id.activity_test_imageview, View.VISIBLE);
    }
}

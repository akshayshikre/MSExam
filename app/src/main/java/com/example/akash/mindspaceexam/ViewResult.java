package com.example.akash.mindspaceexam;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));

    }
}

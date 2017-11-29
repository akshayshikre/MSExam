package com.example.akash.mindspaceexam;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    String passwordSetByMe = "";
    String timerSetByMe = "";
    Button b1, b2, b3, b4, b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));


        b1 = (Button) findViewById(R.id.btnextquestion);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnextquestion:
               Intent in =new Intent(this,CreateExamActivity.class);
                startActivity(in);
                break;

        }
    }





}

package com.example.akash.mindspaceexam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //	ActionBar bar = getActionBar();
        //	bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));


        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                AlertDialog.Builder a_builder = new AlertDialog.Builder(StartActivity.this);
                a_builder.setTitle("Authentication");

                View dialogBox = getLayoutInflater().inflate(
                        R.layout.custom_dialog, null);

                a_builder.setView(dialogBox);

                final EditText box = (EditText) dialogBox.findViewById(R.id.editText2);

                a_builder.setPositiveButton("Login",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                if (box.getText().toString().equals("admin")) {
                                    arg0.dismiss();
//                                    Intent i = new Intent(StartActivity.this, AdminActivity.class);
                                    Intent i = new Intent(StartActivity.this, AdminActivity.class);
                                    startActivity(i);
                                } else {
                                    arg0.dismiss();
                                }
                            }
                        });

                a_builder.setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        });

                AlertDialog alert = a_builder.create();
                alert.show();
            }
        });
        //registerForContextMenu(btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.button2:
                Intent i = new Intent(StartActivity.this, StartQuizActivity.class);
               // Intent i = new Intent(StartActivity.this, FormActivity.class);
                startActivity(i);

                break;
        }


    }

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add("Login");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
		a_builder.setTitle("Authentication");

		View dialogBox = getLayoutInflater().inflate(
				R.layout.custom_dialog, null);

		a_builder.setView(dialogBox);

		final EditText box = (EditText) dialogBox.findViewById(R.id.editText2);

		a_builder.setPositiveButton("Login",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						if (box.getText().toString().equals("admin")) {
							arg0.dismiss();
							//Intent i = new Intent(SplashActivity.this, AdminActivity.class);
                            Intent i = new Intent(StartActivity.this, AdminActivity.class);
							startActivity(i);
						} else {
							arg0.dismiss();
						}
					}
				});

		a_builder.setNegativeButton("Back",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});

		AlertDialog alert = a_builder.create();
		alert.show();
		return super.onContextItemSelected(item);
	}

}

package com.example.akash.mindspaceexam;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.akash.mindspaceexam.SplashActivity.serverip;
import static com.example.akash.mindspaceexam.StartQuizActivity.gradea;
import static com.example.akash.mindspaceexam.StartQuizActivity.gradeb;
import static com.example.akash.mindspaceexam.StartQuizActivity.gradec;
import static com.example.akash.mindspaceexam.StartQuizActivity.graded;
import static com.example.akash.mindspaceexam.StartQuizActivity.marksprquest;
import static com.example.akash.mindspaceexam.StartQuizActivity.mono;
import static com.example.akash.mindspaceexam.StartQuizActivity.name;
import static com.example.akash.mindspaceexam.StartQuizActivity.psubject;

public class ResultActivity extends AppCompatActivity {

    String link = "http://"+serverip+"/mindspaceexam/uploadResult.php";

    LinearLayout results;
    int marks;
    TextView label,tname;
    AlertDialog.Builder builder;
    AlertDialog alert;
    int timeTaken,ga,gb,gc,gd;
    String startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));


        label = (TextView) findViewById(R.id.textView1);
        tname = (TextView) findViewById(R.id.textView5);
        marks = getIntent().getIntExtra("marks", -1);
        marks=marks*Integer.parseInt(marksprquest);
        timeTaken = getIntent().getIntExtra("timeTaken", -1);
        startTime = getIntent().getStringExtra("startTime");
        label.setText("You scored " + marks + " marks\nin " + timeTaken + " seconds");
        results = (LinearLayout) findViewById(R.id.results);
        ga=Integer.parseInt(gradea);
        gb=Integer.parseInt(gradeb);
        gc=Integer.parseInt(gradec);
        gd=Integer.parseInt(graded);
        if(marks>=ga) tname.setText(name+" you got Grade A");
            else if(marks>=gb&&marks<ga) tname.setText(name+" you got Grade B");
               else if(marks>=gb&&marks<gc) tname.setText(name+" you got Grade C");
                   else tname.setText(name+" you got Grade D");

        uploadInformation();
    }

    public void uploadInformation() {
        MyTask mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... arg0) {

            try {

                SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String timing = time.format(new Date()).toString();
                String day = date.format(new Date()).toString();
                Log.e("resultactivity",name+"--"+mono+"--"+psubject+"--"+timing+"--"+day+"--"+timeTaken+"--"+startTime);
                String data = URLEncoder.encode("score", "UTF-8") + "="
                        + URLEncoder.encode( String.valueOf(marks), "UTF-8");
                data += "&" + URLEncoder.encode("name", "UTF-8") + "="
                        + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("number", "UTF-8") + "="
                        + URLEncoder.encode(mono, "UTF-8");
                data += "&" + URLEncoder.encode("subject", "UTF-8") + "="
                        + URLEncoder.encode(psubject, "UTF-8");
                data += "&" + URLEncoder.encode("timing", "UTF-8") + "="
                        + URLEncoder.encode(timing, "UTF-8");
                data += "&" + URLEncoder.encode("day", "UTF-8") + "="
                        + URLEncoder.encode(day, "UTF-8");
                data += "&" + URLEncoder.encode("timeTaken", "UTF-8") + "="
                        + URLEncoder.encode(timeTaken + " seconds", "UTF-8");
                data += "&" + URLEncoder.encode("startTime", "UTF-8") + "="
                        + URLEncoder.encode(startTime, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(
                        conn.getOutputStream());

                wr.write(data);
                wr.flush();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ResultActivity.this,result,Toast.LENGTH_LONG).show();
            results.setVisibility(View.INVISIBLE);
        }
    }
}

package com.example.akash.mindspaceexam;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.example.akash.mindspaceexam.SplashActivity.serverip;

public class CreateExamActivity extends AppCompatActivity {
    Button bcreate;
    EditText edclass, edbranch, edsubject, edtopic, edtypeexam, ednoofquest, edmarksprquest, edexamcode, edexamdate,
            edexamduration, edremark, edgradea, edgradeb, edgradec, edgraded, edpassword;
    public String cclass, cbranch, csubject, ctopic, ctypeexam, cnoofquest, cmarksprquest, cexamcode, cexamdate,
            cexamduration, cremark, cgradea, cgradeb, cgradec, cgraded, cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        bcreate = (Button) findViewById(R.id.btnsubmit);
        edclass = (EditText) findViewById(R.id.edclass);
        edbranch = (EditText) findViewById(R.id.edbranch);
        edsubject = (EditText) findViewById(R.id.edsubject);
        edtopic = (EditText) findViewById(R.id.edtopic);
        edtypeexam = (EditText) findViewById(R.id.edtypeexam);
        ednoofquest = (EditText) findViewById(R.id.ednoofquest);
        edmarksprquest = (EditText) findViewById(R.id.edmarksprquest);
        edexamcode = (EditText) findViewById(R.id.edexamcode);
        edexamdate = (EditText) findViewById(R.id.edexamdate);
        edexamduration = (EditText) findViewById(R.id.edexamduration);
        edremark = (EditText) findViewById(R.id.edremark);
        edgradea = (EditText) findViewById(R.id.edgradea);
        edgradeb = (EditText) findViewById(R.id.edgradeb);
        edgradec = (EditText) findViewById(R.id.edgradec);
        edgraded = (EditText) findViewById(R.id.edgraded);
        edpassword = (EditText) findViewById(R.id.edpassword);
        bcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cclass = edclass.getText().toString();
                cbranch = edbranch.getText().toString();
                csubject = edsubject.getText().toString();
                ctopic = edtopic.getText().toString();
                ctypeexam = edtypeexam.getText().toString();
                cnoofquest = ednoofquest.getText().toString();
                cmarksprquest = edmarksprquest.getText().toString();
                cexamcode = edexamcode.getText().toString();
                cexamdate = edexamdate.getText().toString();
                cexamduration = edexamduration.getText().toString();
                cremark = edremark.getText().toString();
                cgradea = edgradea.getText().toString();
                cgradeb = edgradeb.getText().toString();
                cgradec = edgradec.getText().toString();
                cgraded = edgraded.getText().toString();
                cpassword = edpassword.getText().toString();
                new  AddExamCode().execute();
            }
        });
    }
    class AddExamCode extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            String link, data;
            try {

                link = "http://" + serverip + "/mindspaceexam/addexam.php";

                data = URLEncoder.encode("class", "UTF-8") + "="
                        + URLEncoder.encode(cclass, "UTF-8");
                data += "&" + URLEncoder.encode("branch", "UTF-8") + "="
                        + URLEncoder.encode(cbranch, "UTF-8");
                data += "&" + URLEncoder.encode("subject", "UTF-8") + "="
                        + URLEncoder.encode(csubject, "UTF-8");
                data += "&" + URLEncoder.encode("topic", "UTF-8") + "="
                        + URLEncoder.encode(ctopic, "UTF-8");
                data += "&" + URLEncoder.encode("typeofexam", "UTF-8") + "="
                        + URLEncoder.encode(ctypeexam, "UTF-8");
                data += "&" + URLEncoder.encode("noofquest", "UTF-8") + "="
                        + URLEncoder.encode(cnoofquest, "UTF-8");
                data += "&" + URLEncoder.encode("marksprquest", "UTF-8") + "="
                        + URLEncoder.encode(cmarksprquest, "UTF-8");
                data += "&" + URLEncoder.encode("examcode", "UTF-8") + "="
                        + URLEncoder.encode(cexamcode, "UTF-8");
                data += "&" + URLEncoder.encode("examdate", "UTF-8") + "="
                        + URLEncoder.encode(cexamdate, "UTF-8");
                data += "&" + URLEncoder.encode("examduration", "UTF-8") + "="
                        + URLEncoder.encode(cexamduration, "UTF-8");
                data += "&" + URLEncoder.encode("remark", "UTF-8") + "="
                        + URLEncoder.encode(cremark, "UTF-8");
                data += "&" + URLEncoder.encode("gradea", "UTF-8") + "="
                        + URLEncoder.encode(cgradea, "UTF-8");
                data += "&" + URLEncoder.encode("gradeb", "UTF-8") + "="
                        + URLEncoder.encode(cgradeb, "UTF-8");
                data += "&" + URLEncoder.encode("gradec", "UTF-8") + "="
                        + URLEncoder.encode(cgradec, "UTF-8");
                data += "&" + URLEncoder.encode("graded", "UTF-8") + "="
                        + URLEncoder.encode(cgraded, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(cpassword, "UTF-8");
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
                Log.e("error",sb.toString());
                return sb.toString();
            } catch (Exception e) {
                Log.e("error",e.getMessage());
                return new String(
                        "Something went wrong!\nPlease try again later.");
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(CreateExamActivity.this,s,Toast.LENGTH_SHORT).show();
            if(s.equals("Exam Created Successfully")) {
             Intent in=new Intent(CreateExamActivity.this,AddQuestionsActivity.class);
                in.putExtra("examcode",cexamcode);
                in.putExtra("noofq",cnoofquest);
                startActivity(in);
                finish();
            }
            else  Toast.makeText(CreateExamActivity.this,"not created",Toast.LENGTH_SHORT).show();
        }
    }






}

package com.example.akash.mindspaceexam;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.example.akash.mindspaceexam.SplashActivity.serverip;

public class StartQuizActivity extends AppCompatActivity {
    DatabaseHelper helper;
    EditText edname,edmono;
    Spinner spbranch,spclass,spsubject,sptopic,sptype,spexam;
    Button start;
    ArrayList<String> branchlist = new ArrayList<String>(),
            classlist = new ArrayList<String>(),
            subjectlist = new ArrayList<String>(),
            topiclist = new ArrayList<String>(),
            typelist = new ArrayList<String>(),
            examlist = new ArrayList<String>();
    ArrayAdapter<String> branchadapter,classadapter,subjectadapter,topicadapter,typeadapter,examadapter;
    String selectedbranch,selectedclass,selectedsubject,selectedtopic,selectedtype,selectedexam;
    String tclasss="",tbranch="",tsubject="",ttopic="",ttypeofexam="",tnoofquest="",
            tmarksprquest="",texamdate="",texamduration="",tremark="",tgradea="",
            tgradeb="",tgradec="",tgraded="",tpassword="",texamcode="";
    ArrayList<question> tquestionlist=new ArrayList<question>();
    public static String noofquest,marksprquest,examdate,examduration,remark,gradea
    ,gradeb,gradec,graded,password,name,mono,psubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);
        spbranch = (Spinner) findViewById(R.id.spbranchname);
        spclass = (Spinner) findViewById(R.id.spclassname);
        spsubject = (Spinner) findViewById(R.id.spsubjectname);
        sptopic = (Spinner) findViewById(R.id.sptopicname);
        sptype = (Spinner) findViewById(R.id.sptypeofexam);
        spexam = (Spinner) findViewById(R.id.spexamcode);
        start = (Button) findViewById(R.id.btstartqz);
        edname=(EditText)findViewById(R.id.editText);
        edmono=(EditText)findViewById(R.id.editText10);
        spbranch.setEnabled(false);
        spclass.setEnabled(false);
        spsubject.setEnabled(false);
        sptopic.setEnabled(false);
        sptype.setEnabled(false);
        spexam.setEnabled(false);
        start.setEnabled(false);
        helper = new DatabaseHelper(this, "quiz_database", null, 1000);
        new getclasscode().execute();

        spclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spclass.getSelectedItem().toString().equals("--")){
                    selectedclass=spclass.getSelectedItem().toString();
                    Log.i("selectedclass",selectedclass);
                    spbranch.setEnabled(true);
                    spsubject.setEnabled(false);
                    sptopic.setEnabled(false);
                    sptype.setEnabled(false);
                    spexam.setEnabled(false);
                    start.setEnabled(false);
                    selectedclass=spclass.getSelectedItem().toString();
                    new getbranchcode().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spbranch.getSelectedItem().toString().equals("--")){
                    spsubject.setEnabled(true);
                    sptopic.setEnabled(false);
                    sptype.setEnabled(false);
                    spexam.setEnabled(false);
                    start.setEnabled(false);
                    selectedbranch=spbranch.getSelectedItem().toString();
                    new getsubjectcode().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spsubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spsubject.getSelectedItem().toString().equals("--")){
                    sptopic.setEnabled(true);
                    sptype.setEnabled(false);
                    spexam.setEnabled(false);
                    start.setEnabled(false);
                    selectedsubject=spsubject.getSelectedItem().toString();
                    new gettopiccode().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sptopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!sptopic.getSelectedItem().toString().equals("--")){
                    sptype.setEnabled(true);
                    spexam.setEnabled(false);
                    start.setEnabled(false);
                    selectedtopic=sptopic.getSelectedItem().toString();
                    new gettypecode().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!sptype.getSelectedItem().toString().equals("--")){
                    spexam.setEnabled(true);
                    start.setEnabled(false);
                    selectedtype=sptype.getSelectedItem().toString();
                    new getexamcode().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spexam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spexam.getSelectedItem().toString().equals("--")){
                    start.setEnabled(true);
                    selectedexam=spexam.getSelectedItem().toString();
                   // new getclasscode().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetQuestions().execute();
            }
        });
    }


    class getclasscode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/getclasses.php";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                classlist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            classlist.clear();
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    classlist.add(json_obj.getString("class"));
                }
                for(int i = 0; i< classlist.size(); i++) {
                    Log.i("details--",classlist.get(i));
                }
                Set<String> hs = new HashSet<>();
                hs.addAll(classlist);
                classlist.clear();
                classlist.add("--");
                classlist.addAll(hs);
                for(int i = 0; i< classlist.size(); i++) {
                    Log.i("afterhashsetdetails--",classlist.get(i));
                }
                classadapter = new ArrayAdapter<String>(StartQuizActivity.this, android.R.layout.simple_spinner_item, classlist);
                classadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spclass.setAdapter(classadapter);
                spclass.setEnabled(true);
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                classlist.clear();
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class getbranchcode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.i("doinback","start");
            Log.i("selectedclass",selectedclass);
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/getbranches.php";
                data  = URLEncoder.encode("class", "UTF-8") + "=" +
                        URLEncoder.encode(selectedclass, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                branchlist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            branchlist.clear();
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    branchlist.add(json_obj.getString("branch"));
                }
                for(int i = 0; i< branchlist.size(); i++) {
                    Log.i("detailsbranch--",branchlist.get(i));
                }
                Set<String> hs = new HashSet<>();
                hs.addAll(branchlist);
                branchlist.clear();
                branchlist.add("--");
                branchlist.addAll(hs);
                for(int i = 0; i< branchlist.size(); i++) {
                    Log.i("hashsetdetailsbranch--",branchlist.get(i));
                }
                branchadapter = new ArrayAdapter<String>(StartQuizActivity.this, android.R.layout.simple_spinner_item, branchlist);
                branchadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spbranch.setAdapter(branchadapter);
                spbranch.setEnabled(true);
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                branchlist.clear();
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class getsubjectcode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/getsubjects.php";

                data  = URLEncoder.encode("class", "UTF-8") + "=" +
                        URLEncoder.encode(selectedclass, "UTF-8");
                data  +="&" + URLEncoder.encode("branch", "UTF-8") + "=" +
                        URLEncoder.encode(selectedbranch, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                subjectlist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            subjectlist.clear();
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    subjectlist.add(json_obj.getString("subject"));
                }
                for(int i = 0; i< subjectlist.size(); i++) {
                    Log.i("details--",subjectlist.get(i));
                }
                Set<String> hs = new HashSet<>();
                hs.addAll(subjectlist);
                subjectlist.clear();
                subjectlist.add("--");
                subjectlist.addAll(hs);
                for(int i = 0; i< subjectlist.size(); i++) {
                    Log.i("afterhashsetdetails--",subjectlist.get(i));
                }
                subjectadapter = new ArrayAdapter<String>(StartQuizActivity.this, android.R.layout.simple_spinner_item, subjectlist);
                subjectadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spsubject.setAdapter(subjectadapter);
                spsubject.setEnabled(true);
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                subjectlist.clear();
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class gettopiccode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/gettopics.php";

                data  = URLEncoder.encode("class", "UTF-8") + "=" +
                        URLEncoder.encode(selectedclass, "UTF-8");
                data  +="&" + URLEncoder.encode("branch", "UTF-8") + "=" +
                        URLEncoder.encode(selectedbranch, "UTF-8");
                data  +="&" + URLEncoder.encode("subject", "UTF-8") + "=" +
                        URLEncoder.encode(selectedsubject, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                topiclist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            topiclist.clear();
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    topiclist.add(json_obj.getString("topic"));
                }
                for(int i = 0; i< topiclist.size(); i++) {
                    Log.i("details--",topiclist.get(i));
                }
                Set<String> hs = new HashSet<>();
                hs.addAll(topiclist);
                topiclist.clear();
                topiclist.add("--");
                topiclist.addAll(hs);
                for(int i = 0; i< topiclist.size(); i++) {
                    Log.i("afterhashsetdetails--",topiclist.get(i));
                }
                topicadapter = new ArrayAdapter<String>(StartQuizActivity.this, android.R.layout.simple_spinner_item, topiclist);
                topicadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sptopic.setAdapter(topicadapter);
                sptopic.setEnabled(true);
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                topiclist.clear();
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class gettypecode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/gettypes.php";

                data  = URLEncoder.encode("class", "UTF-8") + "=" +
                        URLEncoder.encode(selectedclass, "UTF-8");
                data  +="&" + URLEncoder.encode("branch", "UTF-8") + "=" +
                        URLEncoder.encode(selectedbranch, "UTF-8");
                data  +="&" + URLEncoder.encode("subject", "UTF-8") + "=" +
                        URLEncoder.encode(selectedsubject, "UTF-8");
                data  +="&" + URLEncoder.encode("topic", "UTF-8") + "=" +
                        URLEncoder.encode(selectedtopic, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                typelist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            typelist.clear();
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    typelist.add(json_obj.getString("typeofexam"));
                }
                for(int i = 0; i< typelist.size(); i++) {
                    Log.i("details--",typelist.get(i));
                }
                Set<String> hs = new HashSet<>();
                hs.addAll(typelist);
                typelist.clear();
                typelist.add("--");
                typelist.addAll(hs);
                for(int i = 0; i< typelist.size(); i++) {
                    Log.i("afterhashsetdetails--",typelist.get(i));
                }
                typeadapter = new ArrayAdapter<String>(StartQuizActivity.this, android.R.layout.simple_spinner_item, typelist);
                typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sptype.setAdapter(typeadapter);
                sptype.setEnabled(true);
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                typelist.clear();
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class getexamcode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/getexamcodes.php";

                data  = URLEncoder.encode("class", "UTF-8") + "=" +
                        URLEncoder.encode(selectedclass, "UTF-8");
                data  +="&" + URLEncoder.encode("branch", "UTF-8") + "=" +
                        URLEncoder.encode(selectedbranch, "UTF-8");
                data  +="&" + URLEncoder.encode("subject", "UTF-8") + "=" +
                        URLEncoder.encode(selectedsubject, "UTF-8");
                data  +="&" + URLEncoder.encode("topic", "UTF-8") + "=" +
                        URLEncoder.encode(selectedtopic, "UTF-8");
                data  +="&" + URLEncoder.encode("typeofexam", "UTF-8") + "=" +
                        URLEncoder.encode(selectedtype, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                examlist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            examlist.clear();
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_obj = jsonArray.getJSONObject(i);
                    examlist.add(json_obj.getString("examcode"));
                }
                for(int i = 0; i< examlist.size(); i++) {
                    Log.i("details--",examlist.get(i));
                }
                Set<String> hs = new HashSet<>();
                hs.addAll(examlist);
                examlist.clear();
                examlist.add("--");
                examlist.addAll(hs);
                for(int i = 0; i< examlist.size(); i++) {
                    Log.i("afterhashsetdetails--",examlist.get(i));
                }
                examadapter = new ArrayAdapter<String>(StartQuizActivity.this, android.R.layout.simple_spinner_item, examlist);
                examadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spexam.setAdapter(examadapter);
                spexam.setEnabled(true);
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                examlist.clear();
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class getexamdetailscode extends AsyncTask<Void, Void, String> {
       /* @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pb.setProgress(pb.getProgress()+1);
        }*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="",data="";
            try{
                link="http://"+serverip+"/mindspaceexam/getexamdetails.php";

                data  = URLEncoder.encode("class", "UTF-8") + "=" +
                        URLEncoder.encode(selectedclass, "UTF-8");
                data  +="&" + URLEncoder.encode("branch", "UTF-8") + "=" +
                        URLEncoder.encode(selectedbranch, "UTF-8");
                data  +="&" + URLEncoder.encode("subject", "UTF-8") + "=" +
                        URLEncoder.encode(selectedsubject, "UTF-8");
                data  +="&" + URLEncoder.encode("topic", "UTF-8") + "=" +
                        URLEncoder.encode(selectedtopic, "UTF-8");
                data  +="&" + URLEncoder.encode("typeofexam", "UTF-8") + "=" +
                        URLEncoder.encode(selectedtype, "UTF-8");
                data  +="&" + URLEncoder.encode("examcode", "UTF-8") + "=" +
                        URLEncoder.encode(selectedexam, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null){
                    sb.append(line);
                    break;
                }
                Log.i("doinback",sb.toString());
                return sb.toString();
            } catch(Exception e){
                Log.i("doinback",new String("Exception: " + e.getMessage()));
                examlist.clear();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String res) {
            Log.i("onpost","in");
            Log.i("onpost",res);
            try {
                JSONObject job=new JSONObject(res);
                JSONArray jsonArray = job.getJSONArray("result");
                if (jsonArray.length() == 1) {
                    JSONObject json_obj = jsonArray.getJSONObject(0);
                    noofquest = json_obj.getString("noofquest");
                    marksprquest = json_obj.getString("marksprquest");
                    examdate = json_obj.getString("examdate");
                    examduration = json_obj.getString("examduration");
                    remark = json_obj.getString("remark");
                    gradea = json_obj.getString("gradea");
                    gradeb = json_obj.getString("gradeb");
                    gradec = json_obj.getString("gradec");
                    graded = json_obj.getString("graded");
                    password = json_obj.getString("password");

                    SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
                    String startTime = time.format(new Date()).toString();

                    Intent i = new Intent(StartQuizActivity.this,
                            QuizActivity.class);
                    i.putExtra("startTime", startTime);
                    name= String.valueOf(edname.getText());
                    mono= String.valueOf(edmono.getText());
                    psubject=selectedsubject;
                    startActivity(i);
                    finish();
                }
                //spin.setAdapter(cityAdapter);
                //pb.setVisibility(View.GONE);
            } catch (Exception e){
                //pb.setVisibility(View.GONE);
                e.printStackTrace();
            }

        }
    }

    class GetQuestions extends AsyncTask<String, Void, String> {

        String myJSON, result;
        String tableName = "";

        @Override
        protected String doInBackground(String... params) {

            try {
                String link = "http://"+serverip+"/mindspaceexam/getquest.php";

                String data = URLEncoder.encode("examcode", "UTF-8") + "="
                        + URLEncoder.encode(selectedexam, "UTF-8");

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

                System.out.println("monitor: " + sb.toString());

                result = sb.toString();
                return sb.toString();
            } catch (Exception e) {
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            myJSON = result;
            System.out.println(myJSON);

            try {
                JSONObject obj = new JSONObject(myJSON);
                JSONArray array = obj.getJSONArray("result");
                JSONObject innerObj;
                helper.clearQuestions();
                for (int i = 0; i < array.length(); i++) {
                    innerObj = new JSONObject(array.get(i).toString());
                    helper.addQuestions(i,
                            innerObj.getString("question"),
                            innerObj.getString("optionA"),
                            innerObj.getString("optionB"),
                            innerObj.getString("optionC"),
                            innerObj.getString("optionD"),
                            innerObj.getString("correctAnswer"));
                }
                if (helper.getnumberOfQuestions() > 0) {

             new getexamdetailscode().execute();
                } else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

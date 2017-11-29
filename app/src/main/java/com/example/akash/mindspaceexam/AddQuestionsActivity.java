package com.example.akash.mindspaceexam;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.example.akash.mindspaceexam.SplashActivity.serverip;

public class AddQuestionsActivity extends AppCompatActivity {
    int i=1;
    EditText qBox;
    EditText aBox;
    EditText bBox;
    EditText cBox;
    EditText dBox;
    Spinner answer;
    Button add;
    TextView qno;
    String question, optionA, optionB, optionC, optionD, correctAnswer,
            examcode, noofq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f9ea0")));

        examcode=getIntent().getStringExtra("examcode")
        ;
        noofq=getIntent().getStringExtra("noofq");
        qBox = (EditText) findViewById(R.id.editText1);
        aBox = (EditText) findViewById(R.id.editText2);
        bBox = (EditText) findViewById(R.id.editText3);
        cBox = (EditText) findViewById(R.id.editText4);
        dBox = (EditText) findViewById(R.id.editText5);
        answer = (Spinner) findViewById(R.id.spinner);
        qno=(TextView) findViewById(R.id.textView);
        add=(Button)findViewById(R.id.button6);
        qno.setText("Question :-"+String.valueOf(i)+"/"+noofq);
    add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        question=qBox.getText().toString();
        optionA=aBox.getText().toString();
        optionB=bBox.getText().toString();
        optionC=cBox.getText().toString();
        optionD=dBox.getText().toString();
        if(answer.getSelectedItem().toString().equals("Option A"))
            correctAnswer=optionA;
        else if(answer.getSelectedItem().toString().equals("Option B"))
            correctAnswer=optionB;
        else if(answer.getSelectedItem().toString().equals("Option C"))
            correctAnswer=optionC;
        else if(answer.getSelectedItem().toString().equals("Option D"))
            correctAnswer=optionD;
        new AddQuestionCode().execute();
    }
});
    }




    class AddQuestionCode extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {

            try {
                String link = "http://"+serverip+"/mindspaceexam/addquestion.php";

                String data = URLEncoder.encode("question", "UTF-8") + "="
                        + URLEncoder.encode(question, "UTF-8");
                data += "&" + URLEncoder.encode("optionA", "UTF-8") + "="
                        + URLEncoder.encode(optionA, "UTF-8");
                data += "&" + URLEncoder.encode("optionB", "UTF-8") + "="
                        + URLEncoder.encode(optionB, "UTF-8");
                data += "&" + URLEncoder.encode("optionC", "UTF-8") + "="
                        + URLEncoder.encode(optionC, "UTF-8");
                data += "&" + URLEncoder.encode("optionD", "UTF-8") + "="
                        + URLEncoder.encode(optionD, "UTF-8");
                data += "&" + URLEncoder.encode("correctAnswer", "UTF-8") + "="
                        + URLEncoder.encode(correctAnswer, "UTF-8");
                data += "&" + URLEncoder.encode("examcode", "UTF-8") + "="
                        + URLEncoder.encode(examcode, "UTF-8");

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
                    //break;
                }
                System.out.println("Response: " + sb.toString());
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(AddQuestionsActivity.this,result, Toast.LENGTH_SHORT).show();
            if(result.equals("Question Added Successfully")) {
                qBox.setText("");
                aBox.setText("");
                bBox.setText("");
                cBox.setText("");
                dBox.setText("");
                i++;
                qno.setText("Question :-"+i+"/"+noofq);
                Log.i("noofq",i+"/"+noofq);
                if(i==(Integer.parseInt(noofq)+1)) finish();
            }
        }
    }
}

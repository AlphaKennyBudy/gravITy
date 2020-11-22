package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    int counter;
    int countQ;
    String ques;
    String[] questions;
    int find = 0;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    TextView tv;
    RadioButton[] rbs;
    int ind = 6;
    String ans;
    ArrayList<String> res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        startTest();
    }
    public void startTest(){
        Log.d("TestActivity", "I was created!");
        Bundle arg = getIntent().getExtras();
        res = arg.getStringArrayList("res");
        counter = Integer.parseInt(arg.get("count").toString());
        countQ = Integer.parseInt(arg.get("countQ").toString());
        ques = arg.get("question").toString();
        questions = ques.split("\n");
        Log.d("TestActivity", Arrays.toString(questions));
        tv= findViewById(R.id.question);
         rb1 = findViewById(R.id.radio_a);
         rb2 = findViewById(R.id.radio_b);
         rb3 = findViewById(R.id.radio_c);
         rb4 = findViewById(R.id.radio_d);
         rb5 = findViewById(R.id.radio_e);
        rbs = new RadioButton[]{rb1, rb2, rb3, rb4, rb5};
        //for (int i = 0; i<questions.length-5;){
        ans = questions[1];
        String[] q = {questions[1],questions[2],questions[3],questions[4], questions[5]};
        tv.setText(questions[0].substring(questions[0].indexOf(">")+1));
        List<String> var = Arrays.asList(q);
        Collections.shuffle(var);
        var.toArray(q);
        rb1.setText(q[0].substring(q[0].indexOf(">")+1));
        rb2.setText(q[1].substring(q[0].indexOf(">")+1));
        rb3.setText(q[2].substring(q[0].indexOf(">")+1));
        rb4.setText(q[3].substring(q[0].indexOf(">")+1));
        rb5.setText(q[4].substring(q[0].indexOf(">")+1));
        //}
        int asked=1;
        MaterialButton imgN = findViewById(R.id.imgnext);
        imgN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newQues();
            }
        });
    }
    public void newQues(){

        for (RadioButton rb : rbs){
            System.out.println(ans+ "   " + rb.getText());
            if (rb.isChecked() && rb.getText().equals(ans.substring(ans.indexOf(">")+1))){
                find++;
            }
        }
        for (RadioButton rb :rbs){
            rb.setChecked(false);
        }
        if (ind>=countQ*6){
            Intent intent = new Intent(this, ResultActivitySecond.class);
            intent.putExtra("point", find);
            intent.putExtra("asked", countQ);
            intent.putExtra("ques", ques);
            intent.putStringArrayListExtra("res",res);
            startActivity(intent);
        }
        ans = questions[(ind+1)%questions.length];
        String[] q = {questions[(ind+1)%questions.length],questions[(ind+2)%questions.length],questions[(ind+3)%questions.length],questions[(ind+4)%questions.length], questions[(ind+5)%questions.length]};
        Log.d("TestActivity: 102", Arrays.toString(q));
        Log.d("TestActivity: 103", questions.length +" - " + questions[0]);
        tv.setText(questions[ind%questions.length].substring(questions[0].indexOf(">")+1));
        List<String> var = Arrays.asList(q);
        Collections.shuffle(var);
        var.toArray(q);
        for (int j = 0; j<5; j++){
            rbs[j].setText(q[j].substring(q[0].indexOf(">")+1));
        }
        ind+=6;
        System.out.println(find);
    }
}
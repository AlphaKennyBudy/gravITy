package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class SolveQuestionsActivity extends AppCompatActivity {
    String ques;
    int counter=0;
    Spinner spinner;
    ArrayList<String> res = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_questions);
        Bundle arg = getIntent().getExtras();
        ques = arg.get("question").toString();
        res = arg.getStringArrayList("res");
        spinner = findViewById(R.id.ques);
        for (int i = 0; i<ques.length()-9;i++){
            if (ques.substring(i, i+10).equals("<question>"))
                counter++;
        }
        String[] countQ = {counter/10+"", counter/5+"", counter/2+"", counter+""};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, countQ);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        ImageButton ib = findViewById(R.id.imageBtn1);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAct();
            }
        });
    }
    public void testAct(){
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("question", ques);
        intent.putExtra("count", counter);
        int ind = Integer.parseInt(spinner.getSelectedItem().toString());
        intent.putExtra("countQ", ind);
        intent.putStringArrayListExtra("res", res);
        startActivity(intent);
    }
}
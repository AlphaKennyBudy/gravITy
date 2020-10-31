package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ResultActivitySecond extends AppCompatActivity {

    int find;
    int asked;
    TextView tv;
    String q;
    ArrayList<String> res;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        Bundle arg = getIntent().getExtras();
        find = Integer.parseInt(arg.get("point").toString());
        asked = Integer.parseInt(arg.get("asked").toString());
        q=arg.get("ques").toString();
        res = arg.getStringArrayList("res");
        tv=findViewById(R.id.result);
        tv.setText(find + "/" + asked);
        ImageButton imgB = findViewById(R.id.imgBtnHome);
        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });
        ImageView img2 = findViewById(R.id.imageView);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSpinPage();
            }
        });
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        res.add(df.format(new Date()) +"        " + find+" / " +asked);
        System.out.println(res.get(0));
    }
    public  void homePage(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putStringArrayListExtra("res",res);
        startActivity(intent);
    }
    public  void goSpinPage(){
        Intent intent = new Intent(this, SolveQuestionsActivity.class);
        intent.putExtra("question", q);
        startActivity(intent);
    }
}
package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {
    int find;
    int asked;
    TextView tv;
    String q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle arg = getIntent().getExtras();
        find = Integer.parseInt(arg.get("point").toString());
        asked = Integer.parseInt(arg.get("asked").toString());
        q=arg.get("ques").toString();
        tv.setText(find + "/" + asked);
        MaterialButton imgB = findViewById(R.id.imgBtnHome);
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

    }
    public  void homePage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public  void goSpinPage(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("question", q);
        startActivity(intent);
    }
}
package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private static final int FILE_PICKER_REQUEST_CODE = 10;
    Intent myFileIntent;
    FileReader fr;
    int count = 1;
    String questions = "";
    ArrayList<String> res = new ArrayList<>();
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle arg = getIntent().getExtras();
        if (arg != null)
            res = arg.getStringArrayList("res");
        ImageButton imgRes = findViewById(R.id.imgBtnRes);
        ed = findViewById(R.id.editTextTextMultiLine);
        imgRes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goResults();
            }
        });
        ImageButton btn = findViewById(R.id.imageBtn3);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                readFile_();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readFile();
                } else {
                    // show a msg to user
                }
            }
        }
    }

    public void readFile() {
        System.out.println("-------------------------------------------");
        File f = new File(Environment.getExternalStorageDirectory(), "questions.txt");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String et;
            while ((et = bf.readLine()) != null) {
                System.out.println(et);
                /*if (et.substring(0, et.indexOf(">")+1).equals("<question>")) {
                    String substring1 = et.substring(et.indexOf(">")+1);
                    questions+="/n"+count+ substring1 +"\n";
                } else {
                    String substring = et.substring(et.indexOf(">")+1);
                    questions+= substring +"\n";
                }*/
            }
            System.out.println(questions);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readFile_() {
        String et = ed.getText().toString();

        Intent intent = new Intent(this, SolveQuestionsActivity.class);

        intent.putExtra("question", et);
        intent.putStringArrayListExtra("res", res);
        startActivity(intent);
    }

    public void goResults() {
        Intent intent = new Intent(this, RecentActivity.class);
        intent.putStringArrayListExtra("res", res);
        startActivity(intent);
    }

    public void selectFile(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, FILE_PICKER_REQUEST_CODE);
        }

        new MaterialFilePicker()
                .withActivity(this)
                .withCloseMenu(true)
                .withHiddenFiles(true)
                .withFilter(Pattern.compile(".*\\.txt$"))
                .withFilterDirectories(false)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
            readTxt(filePath);
        }
    }

    private void readTxt(String path) {
        BufferedReader br = null;

        try {

            String sCurrentLine;
            String text = "";

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                text += sCurrentLine+"\n";
            }

            ed.setText(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
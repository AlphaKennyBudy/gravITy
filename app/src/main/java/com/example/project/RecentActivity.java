package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RecentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        Bundle arg = getIntent().getExtras();
        res = arg.getStringArrayList("res");
        ListView lv = findViewById(R.id.listView);
        String[] dev = new String[res.size()];
        res.toArray(dev);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dev));
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
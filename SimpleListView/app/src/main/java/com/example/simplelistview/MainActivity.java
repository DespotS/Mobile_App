package com.example.simplelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] months  = {"January", "February", "March", "June", "July","August","September", "October", "November", "December"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_view_entry, R.id.entry_text_view, months);

        ListView listView = findViewById(R.id.my_list_view);
        listView.setAdapter(adapter);
    }
}
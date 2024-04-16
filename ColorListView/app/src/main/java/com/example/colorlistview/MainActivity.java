package com.example.colorlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorEntry[] colors = { new ColorEntry("black", Color.BLACK), new ColorEntry("red",Color.RED),
                 new ColorEntry("green", Color.GREEN), new ColorEntry("blue", Color.BLUE),
                 new ColorEntry("magenta", Color.MAGENTA), new ColorEntry("yellow", Color.YELLOW)
        };
        ColorListAdapter adapter = new ColorListAdapter(colors);
        ListView listView = findViewById(R.id.my_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent detailsIntent = new Intent(MainActivity.this, ColorDetailsActivity.class);

            ColorEntry entry = colors[position];
             detailsIntent.putExtra("colorName", entry.name);
             detailsIntent.putExtra("colorCode", entry.colorCode);
              startActivity(detailsIntent);


            }
        });
    }
}
package com.example.spiningwheel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView display;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DB = new DBHelper(this);
    ListView listView = findViewById(R.id.list_item);
    String[] amount = new String[] {"50 Birr", "100 Birr"};
    List<String> Players_list = new ArrayList<String>(Arrays.asList(amount));
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Players_list);
    listView.setAdapter(arrayAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          if(position==0) {
              Intent intent = new Intent(getApplicationContext(), FiftyBirr.class);
              DB.playgame(intent.getStringExtra("username"), "50");
              startActivity(intent);
          }
          else{
            Intent intent = new Intent(getApplicationContext(), HundredBirr.class);
              DB.playgame(intent.getStringExtra("username"), "100");
            startActivity(intent);
          }
        }
    });
    }
}

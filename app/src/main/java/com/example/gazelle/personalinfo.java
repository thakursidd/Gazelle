package com.example.gazelle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

ArrayList<String> list;

        list = new ArrayList<String>();

public class personalinfo extends AppCompatActivity {
    public static String ranch_name_full = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
    }
    public void gohome(View view) {
        Intent intent = new Intent(this, home.class);
        EditText e1 = (EditText)findViewById(R.id.ranch_name2);
        ranch_name_full = (e1.getText().toString());
        startActivity(intent);
    }
    public void entercoordinates(View view) {
        EditText e1 = (EditText)findViewById(R.id.enter_coordinates);
         = (e1.getText().toString());
        startActivity(intent);
    }
}
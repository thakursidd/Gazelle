package com.example.gazelle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gazelle.personalinfo.empty_class;
import static com.example.gazelle.personalinfo.evi_values;
import static com.example.gazelle.personalinfo.farm_size;
import static com.example.gazelle.personalinfo.global_class_num;
import static com.example.gazelle.personalinfo.global_class_size;
import static com.example.gazelle.personalinfo.global_points_num;
import static com.example.gazelle.personalinfo.habsize;
import static com.example.gazelle.personalinfo.xpoints;
import static com.example.gazelle.personalinfo.ypoints;

import android.os.Bundle;

import android.view.View;

import android.widget.Toast;

import java.util.ArrayList;

public class all_areas extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_areas);

        Integer size = global_class_num;
        System.out.println("size" + size);
        ArrayList<String> arraylist = new ArrayList<>();

        int iplus = 0;
        int class_val = -1;
        for (int i = 0; i < size; i++) {
            iplus = i+1;
            class_val++;
                for (int j = 0; j < empty_class.size(); j++) {
                    if (i == empty_class.get(j)) {
                        class_val++;
                        arraylist.add("Empty Class: " + (class_val-1) + " EVI Range: " + evi_values.get(class_val-1) + " Class Area: 0");
                        System.out.println("Empty class: " + class_val);
                    }
                }
                System.out.println("EVI: " + evi_values.get(class_val) + "class: " + class_val);
            System.out.println("habsize " + habsize.get(i) + "i" + i);
            arraylist.add("Class: " + class_val + " EVI Range: " + evi_values.get(class_val) + " Class Area: " + habsize.get(i) + " m^2");
            System.out.println(arraylist.get(i));
        }
        arraylist.add("Total Ranch Area: " + farm_size + " km^2");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.all_areas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, arraylist);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
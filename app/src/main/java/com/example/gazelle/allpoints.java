package com.example.gazelle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gazelle.personalinfo.empty_class;
import static com.example.gazelle.personalinfo.evi_values;
import static com.example.gazelle.personalinfo.global_class_size;
import static com.example.gazelle.personalinfo.global_points_num;
import static com.example.gazelle.personalinfo.habsize;
import static com.example.gazelle.personalinfo.xpoints;
import static com.example.gazelle.personalinfo.ypoints;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class allpoints extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpoints);

        Integer size = global_points_num;
        System.out.println("size" + size);
        ArrayList<String> arraylist = new ArrayList<>();
        int k = -1;
        int class_val = -1;
        for (int i = 0; i < size; i++) {
            if (i % global_class_size == 0) {
                k++;
                class_val++;
                System.out.println("Module 0" + k + class_val);
                for (int j = 0; j < empty_class.size(); j++) {
                    if (class_val == empty_class.get(j)) {
                        class_val++;
                        arraylist.add("Empty Class: " + (class_val-1) + " EVI Range: " + evi_values.get(class_val-1) + " Class Area: 0 km^2");
                        System.out.println("Empty class" + class_val);
                    }
                }
            }
            arraylist.add(xpoints.get(i).toString() + ", " + ypoints.get(i).toString() + " Class: " + class_val + " EVI Range: " + evi_values.get(class_val) + " Class Area: " + habsize.get(k) + " m^2");
        System.out.println(arraylist.get(i));
    }
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.allpoints);
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

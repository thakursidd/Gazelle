package com.example.gazelle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gazelle.grass.grass_hab;
import static com.example.gazelle.shrub.tree_height;
import static com.example.gazelle.shrub.can_height;
import static com.example.gazelle.shrub.crown_rad;


import static com.example.gazelle.personalinfo.empty_class;
import static com.example.gazelle.personalinfo.evi_values;
import static com.example.gazelle.personalinfo.global_class_size;
import static com.example.gazelle.personalinfo.global_points_num;
import static com.example.gazelle.personalinfo.habsize;
import static com.example.gazelle.personalinfo.xpoints;
import static com.example.gazelle.calc.file_name;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class prevcalc extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevcalc);
        String tempString = null;
        int i = 0;
        int flag = 0;
        int size = 0;
        String dir = prevcalc.this.getFilesDir().getAbsolutePath();
        while(flag != 1) {
            System.out.println("in While Flag != 1");
            dir = dir + "/config" + i + ".txt";
            System.out.println("dir" + dir);
            File file = new File(dir);
            if (file.exists()) {
                flag = 0;
                size++;
                file_name = size + 1;
                System.out.println("file exists" + size + " " + file_name);

            }
            else {
                flag = 1;
                System.out.println("file does not exist" + size + " " + file_name);
            }
            i++;
        }
        ArrayList <String> arraylist = new ArrayList<>();
        for(i = 0; i < size; i++)
        {
            tempString = readFromFile(i, this);
            try {
                JSONObject json = new JSONObject(tempString);
                Integer id = Integer.valueOf(String.valueOf(json.getJSONObject("ID:")));
                String time = String.valueOf(json.getJSONObject("Time:"));
                arraylist.add("ID: "+ id.toString() + ", Time: " + time.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(arraylist);

        // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.prevcalc);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(this, arraylist);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

        }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        sendtocalc(readFromFile(position, this));
    }
    public void sendtocalc(String tempString)
    {
        JSONObject json = null;
        try {
            json = new JSONObject(tempString);
            Integer id = Integer.valueOf(String.valueOf(json.getJSONObject("ID:")));
            String time = String.valueOf(json.getJSONObject("Time:"));
            JSONArray grassarr = json.getJSONArray("Grass:");
            JSONArray treeheightarr = json.getJSONArray("Tree Height:");
            JSONArray canheightarr = json.getJSONArray("Canopy Height:");
            JSONArray crownradarr = json.getJSONArray("Crown Radius:");
            for (int i = 0; i < grassarr.length(); i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.println("grass: " + grassarr.getJSONArray(i).get(0).toString());
                        grass_hab[i][0] = (Double.parseDouble(grassarr.getJSONArray(i).get(0).toString()));
                        grass_hab[i][1] = (Double.parseDouble(grassarr.getJSONArray(i).get(1).toString()));
                        grass_hab[i][2] = (Double.parseDouble(grassarr.getJSONArray(i).get(2).toString()));
                        grass_hab[i][3] = (Double.parseDouble(grassarr.getJSONArray(i).get(3).toString()));
                    }
                }
            for (int i = 0; i < treeheightarr.length(); i++) {
                for (int j = 0; j < treeheightarr.getJSONArray(i).length(); j++) {
                    System.out.println("treeheight: beginning:  " + treeheightarr.getJSONArray(i).get(j).toString());
                    tree_height[i][j] = (Double.parseDouble(treeheightarr.getJSONArray(i).get(j).toString()));
                    can_height[i][j] = (Double.parseDouble(canheightarr.getJSONArray(i).get(j).toString()));
                    crown_rad[i][j] = (Double.parseDouble(crownradarr.getJSONArray(i).get(j).toString()));
                    System.out.println("treeheight: beginning:  " + treeheightarr.getJSONArray(i).get(j).toString());
                    System.out.println("treeheight:" + tree_height[i][j]);
                    System.out.println("canheight:" + can_height[i][j]);
                    System.out.println("crownrad:" + crown_rad[i][j]);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String readFromFile(Integer num, Context context) {
        String ret = "";
        String tempfilename = "config" + num + ".txt";
        System.out.println("Reading from file: " + tempfilename);

        try {
            InputStream inputStream = null;
                inputStream = context.openFileInput(tempfilename);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            return("Error");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            return("Error");
        }

        return ret;
    }

}


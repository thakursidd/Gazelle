package com.example.gazelle;


import static com.example.gazelle.calc.file_name;
import static com.example.gazelle.personalinfo.empty_class;
import static com.example.gazelle.personalinfo.evi_values;
import static com.example.gazelle.personalinfo.global_points_num;
import static com.example.gazelle.personalinfo.global_satellite_picture;
import static com.example.gazelle.personalinfo.habsize;
import static com.example.gazelle.personalinfo.global_class_num;
import static com.example.gazelle.personalinfo.xpoints;
import static com.example.gazelle.personalinfo.ypoints;
import static com.example.gazelle.personalinfo.farm_size;
import static com.example.gazelle.personalinfo.ranch_name_full;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class home extends AppCompatActivity {

    private VideoView mVideoView;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        /*if (flag == 0) {
            String dir = home.this.getFilesDir().getAbsolutePath();
            dir = dir + "/satdata.txt";
            System.out.println("dir" + dir);
            File file = new File(dir);
            if (file.exists()) {
                System.out.println("file exists");
                file.delete();
                //readsatdata(readFromFile(this));
            } else {
                System.out.println("file does not exist");
            }
            flag = 1;
        }*/
    }


    /**
     * Called when the user taps the Send button
     */
    public void startMain(View view) {
        Intent intent = new Intent(this, Hub.class);
        startActivity(intent);
    }

    public void editinfo(View view) {
        Intent intent = new Intent(this, personalinfo.class);
        startActivity(intent);
    }

    public void prevcalc(View view) {
        Intent intent = new Intent(this, prevcalc.class);
        startActivity(intent);
    }

    public void readsatdata(String tempString) {
        JSONObject jsonObject = null;
;
        try {
            jsonObject = new JSONObject(tempString);
            JSONArray satpoints_x = jsonObject.getJSONArray("xpoints:");
            JSONArray satpoints_y = jsonObject.getJSONArray("ypoints:");
            JSONArray habsizes = jsonObject.getJSONArray("habsizes:");
            JSONArray evivalues = jsonObject.getJSONArray("evivalues:");
            JSONArray emptyclass = jsonObject.getJSONArray("emptyclass:");

            JSONObject ranchname = jsonObject.getJSONObject("ranchname:");
            JSONObject farmsize = jsonObject.getJSONObject("farmsize:");
            JSONObject satimage = jsonObject.getJSONObject("satimage:");
            JSONObject classnum = jsonObject.getJSONObject("classnum:");
            JSONObject pointsnum = jsonObject.getJSONObject("pointsnum:");

            for (int i = 0; i < satpoints_x.length(); i++) {
                System.out.println("xpoints: " + Double.parseDouble(xpoints.get(i).toString()));
                xpoints.add(Double.parseDouble(satpoints_x.get(i).toString()));
                System.out.println("ypoints: " + Double.parseDouble(ypoints.get(i).toString()));
                ypoints.add(Double.parseDouble(satpoints_y.get(i).toString()));
            }
            for (int i = 0; i < habsizes.length(); i++) {
                System.out.println("habsize: " + Double.parseDouble(habsizes.get(i).toString()));
                habsize.add(Double.parseDouble(habsizes.get(i).toString()));
            }
            for (int i = 0; i < evivalues.length(); i++) {
                System.out.println("evivalues: " + Double.parseDouble(evivalues.get(i).toString()));
                evi_values.add(evivalues.get(i).toString());
            }
            for (int i = 0; i < emptyclass.length(); i++) {
                System.out.println("emptyclass: " + Double.parseDouble(emptyclass.get(i).toString()));
                empty_class.add(Integer.valueOf((emptyclass.get(i).toString())));
            }

            ranch_name_full = ranchname.toString();
            farm_size = (Double.parseDouble(farmsize.toString()));
            global_class_num = (Integer.valueOf(classnum.toString()));
            global_points_num = (Integer.valueOf(pointsnum.toString()));

            byte [] encodeByte = Base64.decode(satimage.toString(),Base64.DEFAULT);
            global_satellite_picture = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
        public String readFromFile (Context context){
            String ret = "";
            String tempfilename = "satdata.txt";
            try {
                InputStream inputStream = null;
                inputStream = context.openFileInput(tempfilename);
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((receiveString = bufferedReader.readLine()) != null) {
                        stringBuilder.append("\n").append(receiveString);
                    }

                    inputStream.close();
                    ret = stringBuilder.toString();
                }
            } catch (FileNotFoundException e) {
                Log.e("login activity", "File not found: " + e.toString());
                return ("Error");
            } catch (IOException e) {
                Log.e("login activity", "Can not read file: " + e.toString());
                return ("Error");
            }

            return ret;
        }
    }


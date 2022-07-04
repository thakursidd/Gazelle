package com.example.gazelle;

import static com.example.gazelle.personalinfo.global_points_num;
import static com.example.gazelle.personalinfo.habsize;
import static com.example.gazelle.personalinfo.global_class_size;
import static com.example.gazelle.personalinfo.global_class_num;
import static com.example.gazelle.personalinfo.xpoints;

import static com.example.gazelle.grass.grass_hab;
import static com.example.gazelle.personalinfo.farm_size;

import static com.example.gazelle.personalinfo.ranch_name_full;
import static com.example.gazelle.shrub.can_height;
import static com.example.gazelle.shrub.crown_rad;
import static com.example.gazelle.shrub.tree_height;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class calc extends AppCompatActivity
    implements View.OnClickListener {

    public double DM = 0;
    public double AU = 0;
    public double GU = 0;
    public double f = 0.35;
    public double cattle = 11.25;
    public double wildebeest = 4.5;
    public int d = 180;
    public double cattlecap = 0;
    public double goatcap = 0;
    public double sheepcap = 0;
    public double kuducap = 0;
    public double donkeycap = 0;
    public double impalacap = 0;
    public double wildebeestcap = 0;
    public double springbokcap = 0;
    public ArrayList<SeekBar> mSeekbars = new ArrayList<SeekBar>();
    public ArrayList<Double> values = new ArrayList<Double>();
    public static Integer file_name = 0;

    public double kg_tot = 0;

    public double BAU = 0;
    public double tot_b_vol = 0;
    double cf = 0.05;
    //EDIT CONSTANTS
    double grass_area_measurement = 1;
    double woody_area_measurement = 40; //40 for 20m, 200 for 100m transect
    double BAU_const = 1500;

    double maxau = 0;
    double maxbau = 0;
    double maxmaxau = 0;
    double maxmaxbau = 0;

    public static int animalnum = 8;
    public static double[][] agb = new double[][]{{0.9, 0.1}, {0.04, 0.16}, {0.1, 0.1}, {0.1, 0.8}, {0.7, 0}, {0.1, 0.25}, {0.43, 0.03}, {0.12, 0.07}};
    public static double[] animals = new double[animalnum];
    public int[] animalloc = new int[]{(R.id.cattle_data), (R.id.goat_data), (R.id.sheep_data), (R.id.kudu_data), (R.id.donkey_data), (R.id.impala_data), (R.id.wildebeest_data), (R.id.springbok_data)};
    public static int[] lock_array = new int[8];

    JSONArray arr = new JSONArray();


    double p = 0;

    private Button cb;
    private Button gb;
    private Button sb;
    private Button kb;
    private Button db;
    private Button ib;
    private Button wb;
    private Button spb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        mSeekbars.add((SeekBar) findViewById(R.id.cattlebar));
        mSeekbars.add((SeekBar) findViewById(R.id.goatbar));
        mSeekbars.add((SeekBar) findViewById(R.id.sheepbar));
        mSeekbars.add((SeekBar) findViewById(R.id.kudubar));
        mSeekbars.add((SeekBar) findViewById(R.id.donkeybar));
        mSeekbars.add((SeekBar) findViewById(R.id.impalabar));
        mSeekbars.add((SeekBar) findViewById(R.id.wildebeestbar));
        mSeekbars.add((SeekBar) findViewById(R.id.springbokbar));
        cb = findViewById(R.id.tc);
        cb.setOnClickListener(this);
        gb = findViewById(R.id.tg);
        gb.setOnClickListener(this);
        sb = findViewById(R.id.ts);
        sb.setOnClickListener(this);
        kb = findViewById(R.id.tk);
        kb.setOnClickListener(this);
        db = findViewById(R.id.td);
        db.setOnClickListener(this);
        ib = findViewById(R.id.ti);
        ib.setOnClickListener(this);
        wb = findViewById(R.id.tw);
        wb.setOnClickListener(this);
        spb = findViewById(R.id.tsp);
        spb.setOnClickListener(this);
        for (int i = 0; i < 8; i++) {
            lock_array[i] = 0;
        }
        TextView t1 = (TextView) findViewById(R.id.ranch_name2);
        t1.setText(ranch_name_full);
        System.out.println("1");
        TextView h1 = (TextView) findViewById(R.id.habitatnumfinal);
        h1.setText("# Habitats: " + global_class_num);
        System.out.println("1");
        TextView f1 = (TextView) findViewById(R.id.farm_size_display);
        System.out.println("1");
        f1.setText("Farm size: " + Double.toString(rounder(farm_size, 2)) + "m^2");
        System.out.println("1");
        grasscalc();
        System.out.println("2");
        capcalc(0, 0, 0, 0);
        System.out.println("3");
        woodycalc();
        System.out.println("finishedwoody");


        TextView t2 = (TextView) findViewById(R.id.kg);
        t2.setText(Double.toString(rounder(kg_tot, 2)) + " kg");
        System.out.println("3");

        TextView t3 = (TextView) findViewById(R.id.au);
        t3.setText(Double.toString(rounder(AU, 2)) + " AU/ha/year");
        System.out.println("4");

        TextView t4 = (TextView) findViewById(R.id.woody_vol);
        t4.setText(Double.toString(rounder(tot_b_vol, 2)) + " m^3");
        System.out.println("3");

        TextView t5 = (TextView) findViewById(R.id.bau);
        t5.setText(Double.toString(rounder(BAU, 2)) + " BAU/ha/year");
        System.out.println("4");
        //capcalc(0,0,0);
        System.out.println("5");
        displayMax();
        //System.out.println(saveSeekbarValues());
        System.out.println("5.5");
        System.out.println("5.7");
        System.out.println("6");
        onRadioButtonSelected();
        System.out.println("hi2");
        run(0);
        for (int i = 0; i < mSeekbars.size(); i++) {
            System.out.println("running func");
            SeekBar seek = mSeekbars.get(i);
            System.out.println("I = " + i);
            int finalI = i;
            seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                                @Override
                                                public void onStopTrackingTouch(SeekBar seekBar) {
                                                    // TODO Auto-generated method stub
                                                }

                                                @Override
                                                public void onStartTrackingTouch(SeekBar seekBar) {
                                                    // TODO Auto-generated method stub
                                                }

                                                @Override
                                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                    // TODO Auto-generated method stub
                                                    System.out.println("in loop" + finalI);
                                                    p = progress * 0.01 * animals[finalI];
                                                    System.out.println("inside" + p);
                                                    saveSeekbarValues(1, finalI, p);
                                                    recalc(finalI);
                                                    displayFunc(0, 0, 0);
                                                    System.out.println(values);

                                                }
                                            }
            );
        }
    }

    public void run(int p) {
        saveSeekbarValues(0, 0, p);
        displayFunc(0, 0, p);
    }

    public void savedata(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());

        System.out.println("Saving Calc Data... ");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("ID:", file_name);
            jsonObject.put("Time:", currentDateandTime);
            JSONArray grass = new JSONArray();
            JSONArray treeheight = new JSONArray();
            JSONArray canheight = new JSONArray();
            JSONArray crownrad = new JSONArray();
            System.out.println(jsonObject);
            for (int i = 0; i < global_points_num; i++) {
                for (int j = 0; j < 4; j++) {
                    grass.put(grass_hab[i][j]);
                }
            }

            for (int i = 0; i < global_points_num; i++) {
                for (int j = 0; j < tree_height[i].length; j++) {
                    treeheight.put(tree_height[i][j]);
                    canheight.put(can_height[i][j]);
                    crownrad.put(crown_rad[i][j]);
                }
            }
            jsonObject.put("Grass:", grass);
            jsonObject.put("Tree Height:", treeheight);
            jsonObject.put("Canopy Height:", canheight);
            jsonObject.put("Crown Radius:", crownrad);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String tempString = jsonObject.toString();
        System.out.println(tempString);
        writeToFile(tempString, this);

    }

    // To enable seekbars
    private void onRadioButtonSelected() {
        for (SeekBar seekbar : mSeekbars) {
            seekbar.setEnabled(true);
            System.out.println("hi");
        }
    }

    // To enable seekbars
    private void disableseekbars() {
        for (int i = 0; i < animalnum; i++) {
            if (lock_array[i] == 1) {
                mSeekbars.get(i).setEnabled(false);
                System.out.println("off" + i);
            }
        }
    }

    // To get seekbar values
    public ArrayList<Double> saveSeekbarValues(int flag, int i, double p) {
        if ((flag == 0) || (values.size() == 0)) {
            for (SeekBar seekbar : mSeekbars) {
                values.add((double) seekbar.getProgress());
            }
        }
        if (flag == 1) {
            values.set(i, (double) p);
        }
        System.out.println("hi");
        return values;
    }

    public void displayMax() {
        TextView a1 = (TextView) findViewById(R.id.cattle_out2);
        a1.setText(Double.toString(rounder(animals[0], 2)));
        TextView a2 = (TextView) findViewById(R.id.goatout);
        a2.setText(Double.toString(rounder(animals[1], 2)));
        TextView a3 = (TextView) findViewById(R.id.sheepout);
        a3.setText(Double.toString(rounder(animals[2], 2)));
        TextView a4 = (TextView) findViewById(R.id.kuduout);
        a4.setText(Double.toString(rounder(animals[3], 2)));
        TextView a5 = (TextView) findViewById(R.id.donkeyout);
        a5.setText(Double.toString(rounder(animals[4], 2)));
        TextView a6 = (TextView) findViewById(R.id.impala_out);
        a6.setText(Double.toString(rounder(animals[5], 2)));
        TextView a7 = (TextView) findViewById(R.id.wildebeest_out);
        a7.setText(Double.toString(rounder(animals[6], 2)));
        TextView a8 = (TextView) findViewById(R.id.springbok_out);
        a8.setText(Double.toString(rounder(animals[7], 2)));
    }

    public void displayFunc(int flag, int j, double p) {
        if (flag == 0) {
            for (int i = 0; i < animalnum; i++) {
                TextView a1 = (TextView) findViewById(animalloc[i]);
                System.out.println("here" + a1 + " space " + animals[i]);
                String string1 = rounder(values.get(i), 1) + "/" + Double.toString(rounder(animals[i], 2));
                System.out.println(string1);
                a1.setText(string1);
            }
        }
        if (flag == 1) {
            TextView a1 = (TextView) findViewById(animalloc[j]);
            System.out.println("here" + a1 + " space " + animals[j]);
            String string1 = Double.toString(rounder(p, 1)) + "/" + Double.toString(rounder(animals[j], 2));
            System.out.println(string1);
            a1.setText(string1);
        }
    }

    public void recalc(int sel) {
        int flag = 0;
        int[] temparr = new int[8];
        int k = 0;
        maxau = maxmaxau;
        maxbau = maxmaxbau;
        maxau = maxau - agb[sel][0] * values.get(sel);
        maxbau = maxbau - agb[sel][1] * values.get(sel);
        if (maxau <= 0) {
            maxau = 0;
        }
        if (maxbau <= 0) {
            maxbau = 0;
        }
        for (int i = 0; i < animalnum; i++) {
            flag = 0;
            if (lock_array[i] == 1) {
                System.out.println("lockedobject" + i);
                maxau = maxau - agb[i][0] * values.get(i);
                maxbau = maxbau - agb[i][1] * values.get(i);
                if (maxau <= 0) {
                    maxau = 0;
                }
                if (maxbau <= 0) {
                    maxbau = 0;
                }
                flag = 1;
            }

            if (i == sel) {
                flag = 1;
            }
            if (flag != 1) {
                temparr[k] = i;
                System.out.println("i=" + i + "k=" + k);
                k++;
            }
        }

        for (int i = 0; i < temparr.length - 1; i++) {
            capcalc(1, maxau, maxbau, temparr[i]);
            System.out.println(i);
            System.out.println("************************" + temparr[i] + "********************* " + maxau
                    + "*************************" + maxbau + "*********");
        }
    }

    public void capcalc(int flag, double maxau, double maxbau, int animaltemp) {
        if (flag == 1) {
            double tempvar1 = 0;
            double tempvar2 = 0;
            if (agb[animaltemp][0] != 0 && agb[animaltemp][1] != 0) {
                tempvar1 = maxau / agb[animaltemp][0];
                System.out.println("maxau" + maxau + "tempvar1" + tempvar1);
                tempvar2 = maxbau / agb[animaltemp][1];
                System.out.println("maxbau" + maxbau + "tempvar2" + tempvar2);
            } else if (agb[animaltemp][0] == 0) {
                tempvar1 = 0;
                tempvar2 = maxbau / agb[animaltemp][1];

            } else if (agb[animaltemp][1] == 0) {
                tempvar2 = 0;
                tempvar1 = maxau / agb[animaltemp][0];

            }

            animals[animaltemp] = rounder(tempvar1 + tempvar2, 3);
            System.out.println("************" + animals[animaltemp] + "***********");

        } else {
            maxau = AU * farm_size; //TODO: not that accurate bc we calculate AU for average of farm rather than each specific habitat
            maxbau = BAU * farm_size;
            maxmaxau = maxau;
            maxmaxbau = maxbau;
            double tempvar1 = 0;
            double tempvar2 = 0;
            for (int i = 0; i < animalnum; i++) {
                if (agb[i][0] != 0 && agb[i][1] != 0) {
                    tempvar1 = maxau / agb[i][0];
                    System.out.println("maxau" + maxau + "tempvar1" + tempvar1);
                    tempvar2 = maxbau / agb[i][1];
                    System.out.println("maxbau" + maxbau + "tempvar2" + tempvar2);
                } else if (agb[i][0] == 0) {
                    tempvar1 = 0;
                    tempvar2 = maxbau / agb[i][1];

                } else if (agb[i][1] == 0) {
                    tempvar2 = 0;
                    tempvar1 = maxau / agb[i][0];

                }

                animals[i] = rounder(tempvar1 + tempvar2, 3);

                System.out.println(animals[i]);

            }

        }
    }

    public void grasscalc() {
        double avg1 = 0;

        for (int i = 0; i < global_class_num; i++) {
            for (int k = 0; k < global_class_size; k++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("i" + i);
                    System.out.println("j" + j);
                    System.out.println("k" + k);
                    //System.out.println("grasshab0:" + grass_hab[0][0]);
                    // System.out.println("grasshab0:" + grass_hab[0][1]);
                    // System.out.println("grasshab0:" + grass_hab[0][2]);
                    // System.out.println("grasshab0:" + grass_hab[0][3]);
                    // System.out.println("grasshab1:" + grass_hab[1][0]);
                    // System.out.println("grasshab1:" + grass_hab[1][1]);
                    //   System.out.println("grasshab1:" + grass_hab[1][0]);
                    //  System.out.println("grasshab1:" + grass_hab[1][1]);
                    System.out.println((grass_hab[(i * global_class_size) + k][j]));
                    avg1 = (grass_hab[(i * global_class_size) + k][j]) + avg1;
                    System.out.println("avg" + avg1);
                }
            }
            System.out.println(habsize.get(i) + ", " + grass_area_measurement + ", " + global_class_size);
            kg_tot = (habsize.get(i) * (avg1 / (grass_area_measurement * 4 * global_class_size))) + kg_tot;
            System.out.println("kg" + kg_tot);
            avg1 = 0;

        }
        System.out.println("beb");
        if (farm_size <= 0) {
            farm_size = 1.0;
        }
        double temp_kg_tot = kg_tot / farm_size;
        DM = rounder(temp_kg_tot, 1);
        System.out.println("DM" + DM);
        AU = 1.0 / ((d / ((DM * f) / cattle))); //TODO CALC FOR LARGE AU FAILS SOMETIMES
        AU = rounder(AU, 3);
        System.out.println("AU" + AU);
        GU = 1.0 / ((d / ((DM * f) / wildebeest)));
        GU = rounder(GU, 3);

    }

    public void woodycalc() {
        double rad = 0;
        double b_vol = 0;
        double b_vol_avg = 0;
        for (int i = 0; i < global_class_num; i++) {
            for (int k = 0; k < global_class_size; k++) {
                for (int j = 0; j < tree_height[0].length; j++) {
                    if (tree_height[(i * global_class_size) + k][j] > 0) {
                        System.out.println(i);
                        System.out.println(j);
                        System.out.println(tree_height[(i * global_class_size) + k][j]);
                        System.out.println(can_height[(i * global_class_size) + k][j]);
                        System.out.println(crown_rad[(i * global_class_size) + k][j]);
                        rad = (((((tree_height[(i * global_class_size) + k][j]) - (can_height[(i * global_class_size) + k][j])) / 2.0) + (crown_rad[(i * global_class_size) + k][j])) / 2.0);
                        System.out.println("rad" + rad);
                        b_vol = (((22 / 7.0) * (Math.pow((2 - can_height[(i * global_class_size) + k][j]), 2)) / 3.0) * ((3 * (rad)) - (2 - can_height[(i * global_class_size) + k][j])));
                        System.out.println(b_vol);
                        b_vol_avg = b_vol_avg + b_vol;
                    }
                }
            }
            tot_b_vol = (habsize.get(i) * (b_vol_avg / (woody_area_measurement * global_class_size))) + tot_b_vol;
            System.out.println(tot_b_vol);
            b_vol_avg = 0;
        }
        double tot_b_vol_hec = tot_b_vol / farm_size;
        System.out.println(tot_b_vol_hec);
        double BU = tot_b_vol_hec * 2;
        BU = BU * cf;
        System.out.println(BU);
        BAU = BU / BAU_const;
        BAU = rounder(BAU, 3);
        tot_b_vol = rounder(tot_b_vol, 3);
        System.out.println(tot_b_vol);
        System.out.println(BAU);


    }

    public static double rounder(double d, int r) {
        double scale = Math.pow(10, r);
        return (Math.round(d * scale) / scale);
    }
    /*public void lockcattle() {
        int flag = 0;
        for(int i = 0; i < lock_array.length; i++)
        {
            if(lock_array[i] == 0)
            {
                flag = 1;
            }
        }
        if(flag == 0) {
            lock_array[lock_array.length] = 0;
        }
    }*/

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
        switch (view.getId()) {
            case R.id.tc:
                lock_array[0] = 1 ^ lock_array[0];
                System.out.println("lockcattle" + lock_array[0]);
                break;
            case R.id.tg:
                lock_array[1] = 1 ^ lock_array[1];
                System.out.println("lockgoat" + lock_array[1]);
                break;

            case R.id.ts:
                lock_array[2] = 1 ^ lock_array[2];
                System.out.println("locksheep" + lock_array[2]);
                break;

            case R.id.tk:
                lock_array[3] = 1 ^ lock_array[3];
                System.out.println("lockkudu" + lock_array[3]);
                break;

            case R.id.td:
                lock_array[4] = 1 ^ lock_array[4];
                System.out.println("lockdonkey" + lock_array[4]);
                break;

            case R.id.ti:
                lock_array[5] = 1 ^ lock_array[5];
                break;

            case R.id.tw:
                lock_array[6] = 1 ^ lock_array[6];
                break;

            case R.id.tsp:
                lock_array[7] = 1 ^ lock_array[7];
                break;

        }
        onRadioButtonSelected();
        disableseekbars();
    }

    private void writeToFile(String data, Context context) {
        try {
            String file_name_temp = "config" + file_name + ".txt";
            System.out.println("Writing to file: " + file_name_temp);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file_name_temp, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
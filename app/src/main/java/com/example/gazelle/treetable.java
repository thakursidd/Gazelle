package com.example.gazelle;

import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class treetable extends AppCompatActivity {
    public static int treenum = 5;
    public static String[] tree_height = new String[40];
    public static String[] can_height = new String[40];
    public static String[] crown_rad = new String[40];
    public static String[] brow_ed = new String[40];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treetable);
        init();
    }

    public void addtree(View view){
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        treenum++;
        TableRow tbrow = new TableRow(this);
        TextView t1v = new TextView(this);
        t1v.setText("" + treenum);
        t1v.setTextColor(WHITE);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);
        EditText t2v = new EditText(this);
        tree_height[treenum] = String.valueOf(t2v);
        t2v.setText(" ");
        System.out.println(t2v);
        t2v.setTextColor(WHITE);
        t2v.setGravity(Gravity.CENTER);
        tbrow.addView(t2v);
        EditText t3v = new EditText(this);
        can_height[treenum] = String.valueOf(t3v);
        t3v.setText(" ");
        t3v.setTextColor(WHITE);
        t3v.setGravity(Gravity.CENTER);
        tbrow.addView(t3v);
        EditText t4v = new EditText(this);
        crown_rad[treenum] = String.valueOf(t4v);
        t4v.setText(" ");
        t4v.setTextColor(WHITE);
        t4v.setGravity(Gravity.CENTER);
        tbrow.addView(t4v);
        EditText t5v = new EditText(this);
        brow_ed[treenum] = String.valueOf(t5v);
        t5v.setText(" ");
        t5v.setTextColor(WHITE);
        t5v.setGravity(Gravity.CENTER);
        tbrow.addView(t5v);
        stk.addView(tbrow);
    }
    /** Called when the user taps the Send button */
    public void finished3(View view) {
        Intent intent = new Intent(this, shrub.class);
        startActivity(intent);
        //for(int j = 0; j < treenum; j++){
            //tree_height[j] = Integer.parseInt(t2v.getText().toString());



        treenum = 5;
    }
    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("Tree.No ");
        tv0.setTextColor(WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Tree Height ");
        tv1.setTextColor(WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Canopy Height ");
        tv2.setTextColor(WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Crown Radius ");
        tv3.setTextColor(WHITE);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" Broswer Edible? ");
        tv4.setTextColor(WHITE);
        tbrow0.addView(tv4);
        stk.addView(tbrow0);
        for (int i = 0; i < treenum; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            EditText t2v = new EditText(this);
            tree_height[i] = String.valueOf(t2v);
            t2v.setText(" ");
            System.out.println(t2v);
            t2v.setTextColor(WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            EditText t3v = new EditText(this);
            can_height[i] = String.valueOf(t3v);
            t3v.setText(" ");
            t3v.setTextColor(WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            EditText t4v = new EditText(this);
            crown_rad[i] = String.valueOf(t4v);
            t4v.setText(" ");
            t4v.setTextColor(WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            EditText t5v = new EditText(this);
            brow_ed[i] = String.valueOf(t5v);
            t5v.setText(" ");
            t5v.setTextColor(WHITE);
            t5v.setGravity(Gravity.CENTER);
            tbrow.addView(t5v);
            stk.addView(tbrow);
        }

    }

}
package com.example.gazelle;

import static com.example.gazelle.MainActivity.numhab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class shrub extends AppCompatActivity {
    public static int[][] tree_height = new int[4][12];
    public static int[][] can_height = new int[4][12];
    public static int[][] crown_rad = new int[4][12];
    public static int[][] ed_brows = new int[4][12];
    public static int treenum = 0;
    public static int zonenum = 1;
    public static int habnum = 1;
    public int display_treenum = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrub);
        treenum = 0;
        zonenum = 1;
        habnum = 1;
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setEnabled(false);
        if(numhab == 1)
        {
            Button btn3 = (Button) findViewById(R.id.button13);
            btn3.setEnabled(false);
            Button btn4 = (Button) findViewById(R.id.button2);
            btn4.setEnabled(true);
        }
        TextView e7 = (TextView) findViewById(R.id.tree_hab);
        e7.setText("Habitat " + habnum + " /" + numhab);
    }

    /**12
     * Called when the user taps the Send button
     */
    public void finished2(View view) {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        EditText e2 = (EditText) findViewById(R.id.tree_data_2);
        EditText e3 = (EditText) findViewById(R.id.tree_data_3);
        EditText e4 = (EditText) findViewById(R.id.tree_data_4);
        readData();
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }

    /**
     * Called when the user taps the Send button
     */
    public void nexttree(View view) {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        EditText e2 = (EditText) findViewById(R.id.tree_data_2);
        EditText e3 = (EditText) findViewById(R.id.tree_data_3);
        EditText e4 = (EditText) findViewById(R.id.tree_data_4);
        readData();
        treenum++;
        display_treenum++;
        TextView e5 = (TextView) findViewById(R.id.tree_num);
        e5.setText("Tree " + display_treenum);
        e1.setText(" ");
        e2.setText(" ");
        e3.setText(" ");
        e4.setText(" ");
    }

    /**
     * Called when the user taps the Send button
     */
    public void nextzone(View view) {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        EditText e2 = (EditText) findViewById(R.id.tree_data_2);
        EditText e3 = (EditText) findViewById(R.id.tree_data_3);
        EditText e4 = (EditText) findViewById(R.id.tree_data_4);
        readData();
        treenum++;
        display_treenum = 1;
        zonenum++;

        TextView e5 = (TextView) findViewById(R.id.tree_num);
        e5.setText("Tree " + display_treenum);
        TextView e6 = (TextView) findViewById(R.id.tree_zone);
        e6.setText("Zone " + zonenum);

        e1.setText(" ");
        e2.setText(" ");
        e3.setText(" ");
        e4.setText(" ");
    }

    /**
     * Called when the user taps the Send button
     */
    public void nexthab(View view) {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        EditText e2 = (EditText) findViewById(R.id.tree_data_2);
        EditText e3 = (EditText) findViewById(R.id.tree_data_3);
        EditText e4 = (EditText) findViewById(R.id.tree_data_4);
        readData();

        treenum = 0;
        display_treenum = 1;
        zonenum = 1;
        habnum++;

        TextView e5 = (TextView) findViewById(R.id.tree_num);
        e5.setText("Tree " + display_treenum);
        TextView e6 = (TextView) findViewById(R.id.tree_zone);
        e6.setText("Zone " + zonenum);
        TextView e7 = (TextView) findViewById(R.id.tree_hab);
        e7.setText("Habitat " + habnum + " /" + numhab);
        if(habnum >= numhab)
        {
            Button btn2 = (Button) findViewById(R.id.button13);
            btn2.setEnabled(false);
            Button btn3 = (Button) findViewById(R.id.button2);
            btn3.setEnabled(true);
        }

        e1.setText(" ");
        e2.setText(" ");
        e3.setText(" ");
        e4.setText(" ");
    }

    public void readData() {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        EditText e2 = (EditText) findViewById(R.id.tree_data_2);
        EditText e3 = (EditText) findViewById(R.id.tree_data_3);
        EditText e4 = (EditText) findViewById(R.id.tree_data_4);
        tree_height[habnum-1][treenum] = tryParse(e1.getText().toString());
        can_height[habnum-1][treenum] = tryParse(e2.getText().toString());
        crown_rad[habnum-1][treenum] = tryParse(e3.getText().toString());
        ed_brows[habnum-1][treenum] = tryParse(e4.getText().toString());

    }
    public int tryParse(Object obj) {
        int retVal;
        try {
            retVal = Integer.parseInt((String) obj);
        } catch (NumberFormatException nfe) {
            retVal = 0; // or null if that is your preference
        }
        return retVal;
    }
}



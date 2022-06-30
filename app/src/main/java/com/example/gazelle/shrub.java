package com.example.gazelle;

import static com.example.gazelle.MainActivity.global_point_counter;
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
    public static double[][] tree_height = new double[25][30];
    public static double[][] can_height = new double[25][30];
    public static double[][] crown_rad = new double[25][30];
    public static double[][] ed_brows = new double[25][30];
    public static int treenum = 0;
    public static int zonenum = 1;
    public static int habnum = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrub);
        treenum = 0;
        TextView t1 = (TextView)findViewById(R.id.shrub_point_info);
        int tempint = global_point_counter+1;
        t1.setText("Point: " + tempint);
    }

    /**12
     * Called when the user taps the Send button
     */
    public void finished2(View view) {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        Double tempDouble = tryParse(e1.getText().toString());
        if(tempDouble > 0) {
            readData(global_point_counter);
        }
        treenum = 0;
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }


    public void nexttree(View view) {
        readData(global_point_counter);
        int display_treenum = treenum+1;
        TextView e5 = (TextView) findViewById(R.id.tree_num);
        e5.setText("Tree/Shrub: " + display_treenum);

    }


    public void readData(int x) {
        EditText e1 = (EditText) findViewById(R.id.tree_data_1);
        EditText e2 = (EditText) findViewById(R.id.tree_data_2);
        EditText e3 = (EditText) findViewById(R.id.tree_data_3);
        EditText e4 = (EditText) findViewById(R.id.tree_data_4);
        tree_height[x][treenum] = tryParse(e1.getText().toString());
        can_height[x][treenum] = tryParse(e2.getText().toString());
        crown_rad[x][treenum] = tryParse(e3.getText().toString());
        ed_brows[x][treenum] = tryParse(e4.getText().toString());
        treenum++;
        e1.setText(" ");
        e2.setText(" ");
        e3.setText(" ");
        e4.setText(" ");

    }
    public Double tryParse(Object obj) {
        double retVal;
        try {
            retVal = Double.parseDouble((String) obj);
        } catch (NumberFormatException nfe) {
            retVal = 0; // or null if that is your preference
        }
        return retVal;
    }
}



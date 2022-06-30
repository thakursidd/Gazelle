package com.example.gazelle;

import static com.example.gazelle.MainActivity.global_point_counter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class grass extends AppCompatActivity {
    public static String EXTRA_MESSAGE = "";
    public static double[][] grass_hab = new double[20][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grass);
        TextView t1 = (TextView)findViewById(R.id.grass_point_info);
        int tempint = global_point_counter+1;
        t1.setText("Point: " + tempint);
    }

    /** Called when the user taps the finished button */
    public void finished(View view) {
        savevalues(global_point_counter);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void savevalues(int x)
    {
        EditText e1 = (EditText)findViewById(R.id.grass_data1);
        EditText e2 = (EditText)findViewById(R.id.grass_data2);
        EditText e3 = (EditText)findViewById(R.id.grass_data3);
        EditText e4 = (EditText)findViewById(R.id.grass_data4);

        grass_hab[x][0] = tryParse(e1.getText().toString());
        grass_hab[x][1] = tryParse(e2.getText().toString());
        grass_hab[x][2] = tryParse(e3.getText().toString());
        grass_hab[x][3] = tryParse(e4.getText().toString());


        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
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








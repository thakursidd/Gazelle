package com.example.gazelle;

//import static com.example.gazelle.shrub.EXTRA_MESSAGE2;

import static com.example.gazelle.MainActivity.numhab;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class grass extends AppCompatActivity {
    public static String EXTRA_MESSAGE = "";
    public static int[][] grass_hab = new int[5][4];
    public static int counter = 1;
    int x = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grass);
        counter = 1;
        Button btn = (Button) findViewBy''Id(R.id.grass_next1);
        btn.setEnabled(true);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setEnabled(false);
        if(numhab == 1)
        {
            Button btn3 = (Button) findViewById(R.id.grass_next1);
            btn3.setEnabled(false);
            Button btn4 = (Button) findViewById(R.id.button2);
            btn4.setEnabled(true);
        }
        TextView e7 = (TextView) findViewById(R.id.grass_hab);
        e7.setText("Habitat " + counter + " /"+ numhab);
    }

    /** Called when the user taps the finished button */
    public void finished(View view) {
        savevalues(0);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void grassSave(View view){
        savevalues(1);
    }
    public void savevalues(int x)
    {
        EditText e1 = (EditText)findViewById(R.id.grass_data1);
        EditText e2 = (EditText)findViewById(R.id.grass_data2);
        EditText e3 = (EditText)findViewById(R.id.grass_data3);
        EditText e4 = (EditText)findViewById(R.id.grass_data4);

        grass_hab[counter-1][0] = tryParse(e1.getText().toString());
        grass_hab[counter-1][1] = tryParse(e2.getText().toString());
        grass_hab[counter-1][2] = tryParse(e3.getText().toString());
        grass_hab[counter-1][3] = tryParse(e4.getText().toString());

        System.out.println(counter);

        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        System.out.println("help2");

        if(x==1) {
            System.out.println("help3");
            counter++;
            TextView e7 = (TextView) findViewById(R.id.grass_hab);
            e7.setText("Habitat " + counter + " /"+ numhab);
        }
        if(counter >= numhab)
        {
            Button btn = (Button) findViewById(R.id.grass_next1);
            btn.setEnabled(false);
            Button btn2 = (Button) findViewById(R.id.button2);
            btn2.setEnabled(true);
        }
        System.out.println(grass_hab[2][0]);
        System.out.println(grass_hab[2][1]);
        System.out.println(grass_hab[2][2]);
        System.out.println(grass_hab[2][3]);



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








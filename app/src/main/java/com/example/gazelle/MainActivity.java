package com.example.gazelle;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import static com.example.gazelle.personalinfo.xpoints;
import static com.example.gazelle.personalinfo.ypoints;


    public class MainActivity extends AppCompatActivity {
        public static int numhab = 0;
        public static int global_point_counter = 0;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView e2 = (TextView)findViewById(R.id.hab_num_output);
        //int temp1 = numhab + 1;
        //e2.setText("Habitat #" + temp1);
        //EditText e3 = (EditText)findViewById(R.id.farm_size);
        //e3.setText(farm_size + " hectares");

            if((global_point_counter) != xpoints.size())
            {
                Button btn3 = (Button) findViewById(R.id.button5);
                btn3.setEnabled(false);
                Button btn6 = (Button) findViewById(R.id.previous_point);
                btn6.setEnabled(false);
            }
            if(xpoints.size() > 0) {
                Button btn4 = (Button) findViewById(R.id.button);
                btn4.setEnabled(true);
                Button btn0 = (Button) findViewById(R.id.button3);
                btn0.setEnabled(true);
                Button btn5 = (Button) findViewById(R.id.next_point_button);
                btn5.setEnabled(true);
                TextView e1 = (TextView) findViewById(R.id.coord_num);
                int tempint = global_point_counter + 1;
                e1.setText("Points: " + tempint + " /" + xpoints.size());
                TextView e2 = (TextView) findViewById(R.id.coord_disp);
                e2.setText(xpoints.get(global_point_counter) + ", " + ypoints.get(global_point_counter));
            }
            if(xpoints.size() == 1)
            {
                Button btn5 = (Button) findViewById(R.id.next_point_button);
                btn5.setEnabled(false);
                Button btn1 = (Button) findViewById(R.id.button5);
                btn1.setEnabled(true);
            }
            else
            {
                Button btn3 = (Button) findViewById(R.id.button5);
                btn3.setEnabled(false);
                Button btn4 = (Button) findViewById(R.id.button);
                btn4.setEnabled(false);
                Button btn0 = (Button) findViewById(R.id.button3);
                btn0.setEnabled(false);
                Button btn5 = (Button) findViewById(R.id.next_point_button);
                btn5.setEnabled(false);
                Button btn6 = (Button) findViewById(R.id.previous_point);
                btn6.setEnabled(false);
                TextView e1 = (TextView) findViewById(R.id.coord_num);
                e1.setText("No coordinates entered.");
                TextView e2 = (TextView) findViewById(R.id.coord_disp);
                e2.setText("Please edit personal info");
            }
    }

    /** Called when the user taps the Send button */
    public void sendtoGrass(View view) {
        Intent intent = new Intent(this, grass.class);
        startActivity(intent);
    }
    public void sendtoShrub(View view) {
        Intent intent2 = new Intent(this, shrub.class);
        startActivity(intent2);
    }

    public void sendtoCalc(View view) {
        Intent intent4 = new Intent(this, calc.class);
        //EditText e1 = (EditText)findViewById(R.id.farm_size);
        //farm_size = tryParse(e1.getText().toString());
        startActivity(intent4);
    }
    /*public void savehabsize(View view)
    {
        EditText e1 = (EditText)findViewById(R.id.habitatsizenum);
        String tempvar = e1.getText().toString();
        habsize[numhab] = tryParse(tempvar);
        farm_size = farm_size + habsize[numhab];
        System.out.println(farm_size);
        numhab++;
        e1.setText("");
        TextView e2 = (TextView)findViewById(R.id.hab_num_output);
        int temp1 = numhab + 1;
        e2.setText("Habitat #" + temp1);
        EditText e3 = (EditText)findViewById(R.id.farm_size);
        e3.setText(farm_size + " m^2");
    }*/

    public void next_point(View view)
    {
        if((global_point_counter+1) < xpoints.size()){
            Button btn6 = (Button) findViewById(R.id.previous_point);
            btn6.setEnabled(true);
            global_point_counter++;
            TextView e1 = (TextView) findViewById(R.id.coord_num);
            int tempint = global_point_counter+1;
            e1.setText("Points: " + tempint + " /" + xpoints.size());
            TextView e2 = (TextView) findViewById(R.id.coord_disp);
            e2.setText(xpoints.get(global_point_counter) + ", " + ypoints.get(global_point_counter));
        }
        if((global_point_counter+1) == xpoints.size())
        {
            Button btn3 = (Button) findViewById(R.id.button5);
            btn3.setEnabled(true);
            Button btn6 = (Button) findViewById(R.id.next_point_button);
            btn6.setEnabled(false);
        }

    }
        public void previous_point(View view)
        {
            if(global_point_counter != 0) {
                Button btn6 = (Button) findViewById(R.id.next_point_button);
                btn6.setEnabled(true);
                global_point_counter--;
                TextView e1 = (TextView) findViewById(R.id.coord_num);
                e1.setText("Points: " + global_point_counter + 1 + " /" + xpoints.size());
                TextView e2 = (TextView) findViewById(R.id.coord_disp);
                e2.setText(xpoints.get(global_point_counter) + ", " + ypoints.get(global_point_counter));
                if ((global_point_counter+1) != xpoints.size()) {
                    Button btn3 = (Button) findViewById(R.id.button5);
                    btn3.setEnabled(false);
                }
                if(global_point_counter == 0)
                {
                    Button btn5 = (Button) findViewById(R.id.previous_point);
                    btn5.setEnabled(false);
                }
            }
        }

        public void all_points(View view) {

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
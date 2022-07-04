package com.example.gazelle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.gazelle.personalinfo.empty_class;
import static com.example.gazelle.personalinfo.evi_values;
import static com.example.gazelle.personalinfo.global_class_num;
import static com.example.gazelle.personalinfo.global_points_num;
import static com.example.gazelle.personalinfo.global_satellite_picture;
import static com.example.gazelle.personalinfo.xpoints;
import static com.example.gazelle.personalinfo.ypoints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
        public static int numhab = 0;
        public static int global_point_counter = 0;
        ImageView image;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView e2 = (TextView)findViewById(R.id.hab_num_output);
        //int temp1 = numhab + 1;
        //e2.setText("Habitat #" + temp1);
        //EditText e3 = (EditText)findViewById(R.id.farm_size);
        //e3.setText(farm_size + " hectares");
            displayevi();
            TextView h1 = (TextView) findViewById(R.id.habnum);
            h1.setText("# Habitats: " + global_class_num);
            image = (ImageView) findViewById(R.id.image);
            System.out.println(global_satellite_picture);
            image.setImageBitmap(global_satellite_picture);

            System.out.println("global total points:" + global_points_num);
            System.out.println("global current point counter" + global_point_counter);

            if((global_point_counter+1) != global_points_num)
            {
                Button btn3 = (Button) findViewById(R.id.button5);
                btn3.setEnabled(false);
                Button btn6 = (Button) findViewById(R.id.previous_point);
                btn6.setEnabled(false);
                Button btn5 = (Button) findViewById(R.id.next_point_button);
                btn5.setEnabled(true);
            }
            if((global_point_counter+1) == global_points_num)
            {
                Button btn3 = (Button) findViewById(R.id.button5);
                btn3.setEnabled(true);
                Button btn6 = (Button) findViewById(R.id.previous_point);
                btn6.setEnabled(false);
                Button btn5 = (Button) findViewById(R.id.next_point_button);
                btn5.setEnabled(false);
            }
            if(xpoints.size() > 0) {
                Button btn4 = (Button) findViewById(R.id.button);
                btn4.setEnabled(true);
                Button btn0 = (Button) findViewById(R.id.button3);
                btn0.setEnabled(true);

                TextView e1 = (TextView) findViewById(R.id.coord_num);
                int tempint = global_point_counter + 1;
                e1.setText("Points: " + tempint + " /" + xpoints.size());
                TextView e2 = (TextView) findViewById(R.id.x_coord_disp);
                TextView e3 = (TextView) findViewById(R.id.y_coord_disp);
                e2.setText(String.valueOf(xpoints.get(global_point_counter)));
                e3.setText(String.valueOf(ypoints.get(global_point_counter)));
            }
            if(global_points_num == 1)
            {
                Button btn5 = (Button) findViewById(R.id.next_point_button);
                btn5.setEnabled(false);
                Button btn1 = (Button) findViewById(R.id.button5);
                btn1.setEnabled(true);
            }
            if(global_points_num == 0)
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
                TextView e2 = (TextView) findViewById(R.id.x_coord_disp);
                e2.setText("Please edit personal info");
                TextView e3 = (TextView) findViewById(R.id.y_coord_disp);
                e3.setText("Return to main screen using back arrow");
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
        public void displayevi() {
            TextView e1 = (TextView) findViewById(R.id.evi1);
            TextView e2 = (TextView) findViewById(R.id.evi2);
            TextView e3 = (TextView) findViewById(R.id.evi3);
            TextView e4 = (TextView) findViewById(R.id.evi4);
            TextView e5 = (TextView) findViewById(R.id.evi5);
            ArrayList<TextView> values = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));
            for(int i =0; i < global_class_num; i++)
            {
                values.get(i).setText(evi_values.get(i));
                }
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
            System.out.println(global_point_counter);
            TextView e1 = (TextView) findViewById(R.id.coord_num);
            int tempint = global_point_counter+1;
            e1.setText("Points: " + tempint + " /" + xpoints.size());
            TextView e2 = (TextView) findViewById(R.id.x_coord_disp);
            TextView e3 = (TextView) findViewById(R.id.y_coord_disp);
            e2.setText(String.valueOf(xpoints.get(global_point_counter)));
            e3.setText(String.valueOf(ypoints.get(global_point_counter)));
            System.out.println(String.valueOf(xpoints.get(global_point_counter)));
            System.out.println(String.valueOf(ypoints.get(global_point_counter)));

        }
        if((global_point_counter+1) == xpoints.size())
        {
            Button btn3 = (Button) findViewById(R.id.button5);
            btn3.setEnabled(true);
            Button btn6 = (Button) findViewById(R.id.next_point_button);
            btn6.setEnabled(false);
        }

        System.out.println("global total points:" + global_points_num);
        System.out.println("global current point counter" + global_point_counter);
    }
        public void previous_point(View view)
        {
            if(global_point_counter != 0) {
                Button btn6 = (Button) findViewById(R.id.next_point_button);
                btn6.setEnabled(true);
                global_point_counter = global_point_counter-1;
                System.out.println(global_point_counter);
                TextView e1 = (TextView) findViewById(R.id.coord_num);
                int tempint = global_point_counter+1;
                e1.setText("Points: " + tempint + " /" + xpoints.size());
                TextView e2 = (TextView) findViewById(R.id.x_coord_disp);
                TextView e3 = (TextView) findViewById(R.id.y_coord_disp);
                e2.setText(String.valueOf(xpoints.get(global_point_counter)));
                e3.setText(String.valueOf(ypoints.get(global_point_counter)));
                System.out.println(String.valueOf(xpoints.get(global_point_counter)));
                System.out.println(String.valueOf(ypoints.get(global_point_counter)));

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

            System.out.println("global total points:" + global_points_num);
            System.out.println("global current point counter" + global_point_counter);
        }

        public void all_points(View view) {
            Intent intent = new Intent(this, allpoints.class);
            startActivity(intent);
        }
        public void all_areas(View view) {
            Intent intent = new Intent(this, all_areas.class);
            startActivity(intent);
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
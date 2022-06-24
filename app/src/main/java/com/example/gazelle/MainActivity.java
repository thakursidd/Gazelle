package com.example.gazelle;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
public static int farm_size = 0;
public static int[] habsize = new int[10];
public static int numhab = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView e2 = (TextView)findViewById(R.id.hab_num_output);
        int temp1 = numhab + 1;
        e2.setText("Habitat #" + temp1);
        EditText e3 = (EditText)findViewById(R.id.farm_size);
        e3.setText(farm_size + " hectares");
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
        EditText e1 = (EditText)findViewById(R.id.farm_size);
        farm_size = tryParse(e1.getText().toString());
        startActivity(intent4);
    }
    public void savehabsize(View view)
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
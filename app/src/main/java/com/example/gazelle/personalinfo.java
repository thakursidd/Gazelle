package com.example.gazelle;


import static com.example.gazelle.grass.grass_hab;
import static com.example.gazelle.shrub.can_height;
import static com.example.gazelle.shrub.crown_rad;
import static com.example.gazelle.shrub.tree_height;
import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class personalinfo extends AppCompatActivity {
    public static String ranch_name_full = "";
    String myUrl = "http://34.93.225.182:5000/";
    ArrayList<Double> xcords = new ArrayList<Double>();
    ArrayList<Double> ycords = new ArrayList<Double>();
    public static ArrayList<Integer> empty_class = new ArrayList<Integer>();
    public static ArrayList<Double> xpoints = new ArrayList<Double>();
    public static ArrayList<Double> ypoints = new ArrayList<Double>();
    public static ArrayList<Double> habsize = new ArrayList<Double>();
    public static ArrayList<String> evi_values = new ArrayList<>();
    public static Bitmap global_satellite_picture;
    public static Integer global_points_num = 0;
    MyRecyclerViewAdapter adapter;


    public static String satellite_url = null;
    public static Double farm_size = 0.0;
    public static int global_class_size = 5;
    public static int global_class_num = 0;
    ProgressDialog progressDialog;
    Integer x = 0;
    int index_no;
    private boolean progressEnable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);

        printlist();

    }
public void printlist()
{
    if(xcords.size() > 0) {
        Integer size = xcords.size();
        System.out.println("size" + size);
        ArrayList<String> arraylist = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            arraylist.add(xcords.get(i).toString() + ", " + ycords.get(i).toString());
            System.out.println(arraylist.get(i));
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.listcoords);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, arraylist);
        recyclerView.setAdapter(adapter);
    }
}
    public void gohome(View view) {
        Intent intent = new Intent(this, home.class);
        EditText e1 = (EditText) findViewById(R.id.ranch_name2);
        ranch_name_full = (e1.getText().toString());
        startActivity(intent);
    }

    public void entercoordinates(View view) {
        EditText e1 = (EditText) findViewById(R.id.enter_coordinates);
        String tempString = e1.getText().toString();
        System.out.println(tempString);
        if(!tempString.isEmpty()) {
            System.out.println("in");
            String[] parts = tempString.split(",");
            System.out.println(parts[0]);
            System.out.println(parts[1]);
            xcords.add(Double.parseDouble(parts[0]));
            ycords.add(Double.parseDouble(parts[1]));
            System.out.println(xcords.get(x));
            System.out.println(ycords.get(x));
            x++;
            e1.setText("");
            if (x == 20) {
                e1.setText("Maximum Points Reached");
                Button btn = (Button) findViewById(R.id.coordinates_button);
                btn.setEnabled(false);
            }
            printlist();

        }
    }

    public String convert_points() {
        String tempString = "{\"list\" : [";
        tempString = tempString + xcords.get(0) + ", " + ycords.get(0);
        for (int i = 1; i < xcords.size(); i++) {
            tempString = tempString + ", " + xcords.get(i) + ", " + ycords.get(i); //{"list" : ["When was Barack Obama's birthday?", "What is 10 plus 5 equal to?"]}
        }
        tempString = tempString + "]}";
        return tempString;
    }

    public void parse_data(JSONObject json) throws JSONException {
        System.out.println(json.getJSONArray("output").get(0).toString());
        System.out.println(json.getJSONArray("output").get(1).toString());
        satellite_url = json.getJSONArray("output").get(0).toString();
        JSONArray arr = json.getJSONArray("output").getJSONArray(1);
        int k = 0;
        for (int i = 0; i < arr.length(); i++) {
            if (Double.parseDouble(arr.getJSONArray(i).get(2).toString()) != 0){
            for (int j = 0; j < global_class_size; j++) {
                    System.out.println("xpoint" + (arr.getJSONArray(i).getJSONArray(1).getJSONArray(j).get(0).toString()));
                    System.out.println("ypoint" + (arr.getJSONArray(i).getJSONArray(1).getJSONArray(j).get(1).toString()));
                    xpoints.add(Double.parseDouble(arr.getJSONArray(i).getJSONArray(1).getJSONArray(j).get(0).toString()));
                    ypoints.add(Double.parseDouble(arr.getJSONArray(i).getJSONArray(1).getJSONArray(j).get(1).toString()));
                    System.out.println(xpoints.size());

            }
                habsize.add((Double.parseDouble(arr.getJSONArray(i).get(2).toString())) * 1000);
                farm_size = habsize.get(k) + farm_size;
                System.out.println(("habsize" + habsize.get(k) + "farmsize" + farm_size));
                k++;
            }
            else
            {
                empty_class.add(i);
                System.out.println("empty: " + empty_class.get(i));
            }
            if(i != 0 || i != global_class_num) {
                String tempString = null;
                tempString = arr.getJSONArray(i).get(0).toString();
                String[] parts = tempString.split(" - ");
                String part1 = parts[0];
                String part2 = parts[1];
                tempString = part1.substring(0, Math.min(part1.length(), 3)) + " - " + part2.substring(0, Math.min(part2.length(), 3));
                evi_values.add(tempString);
                System.out.println("evi: " + evi_values.get(i));
            }
            else
            {
                evi_values.add(arr.getJSONArray(i).get(0).toString());
                System.out.println("evi: " + evi_values.get(i));

            }

        }
        global_class_num = k;

    }

    public void load_points(View view) {
        if (xcords.size() != 0) {
            printlist();
            System.out.println("call api");

            //send points to backend api
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            System.out.println("8");
            myAsyncTasks.execute();
            System.out.println(xpoints.size());
            System.out.println("api done");
        }
    }

    public void call_api(String request) throws IOException {
        System.out.println("in api");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        System.out.println("1");
        String URL = myUrl;
        System.out.println("2");
        System.out.println("4");
        final String requestBody = request; //"{\"list\" : [\"When was Barack Obama's birthday?\", \"What is 10 plus 5 equal to?\"]}"; //jsonBody.toString();
        System.out.println("5");

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("6");
                Log.i("VOLLEY", response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    String json = null;
                    try {
                        json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("dick" + json);
                    ////////////
                    JSONObject data_obj = null;
                    try {
                        data_obj = new JSONObject(json);
                        parse_data(data_obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //////
                    String imageURL = satellite_url;
                    System.out.println("imageURL" + imageURL);
                    Bitmap bitmap = null;
                    try {
                        // Download Image from URL
                        InputStream input = new java.net.URL(imageURL).openStream();
                        // Decode Bitmap
                        bitmap = BitmapFactory.decodeStream(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    global_satellite_picture = bitmap;
                    // dismiss the progress dialog after receiving data from API
                    // can get more details such as response.headers
                }
                savedata();
                global_points_num = xpoints.size();
                System.out.println("total size" + global_points_num);
                progressDialog.dismiss();
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 100000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 1000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(stringRequest);
    }


    public class MyAsyncTasks extends AsyncTask<String, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(personalinfo.this);
            progressDialog.setMessage("Processing results");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                call_api(convert_points());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {

        }
    }
    public void all_points(View view) {
        Intent intent = new Intent(this, allpoints.class);
        startActivity(intent);
    }
    public void test(View view) {
        xcords = new ArrayList<Double>(Arrays.asList(-26.1492311, -26.0911285, -26.1405004, -26.2091982));
        ycords = new ArrayList<Double>(Arrays.asList(21.8925734, 21.8280708, 21.7831315, 21.8646191));
        load_points(view);
        Switch s1 = (Switch) findViewById(R.id.switch1);
        s1.setChecked(false);
    }
    public void savedata() {
        System.out.println("Saving Sat Data... ");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONArray satpoints_x = new JSONArray();
            JSONArray satpoints_y = new JSONArray();
            JSONArray habsizes = new JSONArray();
            JSONArray evivalues = new JSONArray();
            JSONArray emptyclass = new JSONArray();
            System.out.println(jsonObject);
            for (int i = 0; i < xpoints.size(); i++) {
                    satpoints_x.put(xpoints.get(i));
                    satpoints_y.put(ypoints.get((i)));
            }
            for (int i = 0; i < habsize.size(); i++) {
                habsizes.put(habsize.get(i));
            }
            for (int i = 0; i < evi_values.size(); i++) {
                evivalues.put(evi_values.get(i));
            }
            for (int i = 0; i < empty_class.size(); i++) {
                emptyclass.put(empty_class.get(i));
            }


            jsonObject.put("xpoints:", satpoints_x);
            jsonObject.put("ypointst:", satpoints_y);
            jsonObject.put("habsizes:", habsizes);
            jsonObject.put("evivalues:", evivalues);
            jsonObject.put("emptyclass:", emptyclass);
            jsonObject.put("ranchname:", ranch_name_full);
            jsonObject.put("farmsize:", farm_size);
            jsonObject.put("satimage:", global_satellite_picture);
            jsonObject.put("classnum:", global_class_num);
            jsonObject.put("pointsnum:", global_points_num);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String tempString = jsonObject.toString();
        System.out.println(tempString);
        writeToFile(tempString, this);
    }
    private void writeToFile(String data, Context context) {
        try {
            String file_name_temp = "satdata.txt";
            System.out.println("Writing to file: " + file_name_temp);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file_name_temp, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}





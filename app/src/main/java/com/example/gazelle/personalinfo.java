package com.example.gazelle;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class personalinfo extends AppCompatActivity {
    public static String ranch_name_full = "";
    String myUrl = "http://34.93.225.182:5000/";
    ArrayList<Double> xcords = new ArrayList<Double>();
    ArrayList<Double> ycords = new ArrayList<Double>();
    public static ArrayList<Double> xpoints = new ArrayList<Double>();
    public static ArrayList<Double> ypoints = new ArrayList<Double>();

    public static int farm_size = 0;
    public static int[] habsize = new int[5];
    public static int global_class_size = 0;
    public static int global_class_num = 0;
    ProgressDialog progressDialog;
    Integer x = 0;
    int index_no;
    private boolean progressEnable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        xpoints.add(1.0);
        ypoints.add(1.0);

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


    public void load_points(View view) throws IOException, InterruptedException {
        int currentPosition = 0;
        int total = 25;
        if (xcords.size() != 0) {

            //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            //progressBar.setProgress(currentPosition);
            System.out.println("call api");

            //send points to backend api
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            System.out.println("8");
            myAsyncTasks.execute();

            System.out.println("api done");

            //post xcords + ycords
            //wait for results from api
            global_class_size = 5;
            global_class_num = 5;
            farm_size = 0; //read in farmsize
            habsize[0] = 0;
            habsize[1] = 0;
            habsize[2] = 0;
            habsize[3] = 0;
            habsize[4] = 0;

            String tempvar = ""; //read in from api
            tempvar = "-21.5463,4.63728";
            while (!tempvar.equals("0")) {
                String[] parts = tempvar.split(",");
                xpoints.add(Double.parseDouble(parts[0]));
                ypoints.add(Double.parseDouble(parts[0]));
                tempvar = "0";
            }
            TextView e1 = (TextView) findViewById(R.id.points_loaded);
            e1.setText(xpoints.size() + " Points Loaded");


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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(data_obj.get("output"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //////

                    // can get more details such as response.headers
                }
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
            progressDialog.setMessage("processing results");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                call_api(convert_points());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {

            // dismiss the progress dialog after receiving data from API
            progressDialog.dismiss();
        }
    }
}




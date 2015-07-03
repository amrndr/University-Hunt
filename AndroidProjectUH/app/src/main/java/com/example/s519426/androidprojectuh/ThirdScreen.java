package com.example.s519426.androidprojectuh;

/**
 * Created by Amerender Singh, Giridharan Srinivasaraj, Mounika Thangella.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;


public class ThirdScreen extends Activity {

    public String UNIVERSITY_SELECTED="";

    public String THIRD_SCREEN_URL = "";

    public int SUCCESS;



    ArrayList<University> univItems = new ArrayList<University>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_third_screen);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        THIRD_SCREEN_URL = intent.getStringExtra("thirdscreenurl");

        Log.e("ThirdScreen", THIRD_SCREEN_URL);

        new JSONParse().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.third_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class MyArrayAdapter extends ArrayAdapter<University> {


        public MyArrayAdapter(Context context, int resource, int textViewID, List<University> data) {

            super(context, resource, textViewID, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ImageView imageView = (ImageView) view.findViewById(R.id.univIV);
            TextView textView = (TextView) view.findViewById(R.id.univTV);
            textView.setTextColor(Color.WHITE);
            University u = this.getItem(position);
            imageView.setImageBitmap(u.getPic());
            textView.setText(u.getName());


            return view;
        }
    }


    protected class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(ThirdScreen.this);
            pDialog.setMessage("Hunting Universities ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
//            Bitmap myBitmap = null;
            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(THIRD_SCREEN_URL);


            try {

                SUCCESS = json.getInt("success");

                if (SUCCESS == 1)

                {
                    JSONArray universities = json.getJSONArray("university");

                    for (int i = 0; i < universities.length(); i++) {

                        Bitmap myBitmap = null;

                        JSONObject univ = universities.getJSONObject(i);


                        URL url = null;
                        try {
                            url = new URL(univ.getString("image"));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection connection = null;
                        try {
                            connection = (HttpURLConnection) url.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        connection.setDoInput(true);
                        try {
                            connection.connect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        InputStream input = null;
                        try {
                            input = connection.getInputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myBitmap = BitmapFactory.decodeStream(input);


                        University u = new University(univ.getString("name"), myBitmap);


                        univItems.add(u);


                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String json) {



            if (SUCCESS == 1) {

                ArrayAdapter<University> univAA = new MyArrayAdapter(getApplicationContext(), R.layout.univ_row, R.id.univTV, univItems);
                final ListView univLV = (ListView) findViewById(R.id.univLV);
                univLV.setAdapter(univAA);

                univLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                        Toast.makeText(getApplicationContext(), ""+ adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT ).show();

                        UNIVERSITY_SELECTED =  adapterView.getItemAtPosition(i).toString();


//                        Toast.makeText(getApplicationContext(), ""+UNIVERSITY_SELECTED, Toast.LENGTH_SHORT ).show();

                        Intent intent = new Intent(getApplicationContext(), FourthScreen.class);
                        intent.putExtra("universityvalues",UNIVERSITY_SELECTED);
                        startActivity(intent);

                    }
                });






            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThirdScreen.this);


                builder.setMessage("No university found. Search with another criteria.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }

                        });


                AlertDialog dialog = builder.create();


                dialog.show();
            }

            pDialog.dismiss();

        }




    }

}
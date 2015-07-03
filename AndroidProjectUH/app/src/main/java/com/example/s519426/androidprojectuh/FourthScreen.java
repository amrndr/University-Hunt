package com.example.s519426.androidprojectuh;

/**
 * Created by Amerender Singh, Giridharan Srinivasaraj, Mounika Thangella.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FourthScreen extends Activity {

    public String NAME="";

    public String UNIVERSITY_SELECTED="";

    public String PROGRAM="";

    public String COUNTRY_SELECTED="";
    public String REGION_SELECTED="";
    public int TUITION_FEE=0;
    public int GRE=0;

    public double IELTS=0;

    public int TOEFL=0;

    public double GPA=0;

    public String DEADLINE="";

    public String CONTACT="";



    public String URL="";



    TextView nameTV, deadlineTV, contactTV, websiteTV, programTV, countryTV, regionTV, greTV, toeflTV, ieltsTV, gpaTV, tuitionTV;


    public int SUCCESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fourth_screen);

        Intent intent = getIntent();
        UNIVERSITY_SELECTED = intent.getStringExtra("universityvalues");
        UNIVERSITY_SELECTED = UNIVERSITY_SELECTED.replace(" ", "%20");
        nameTV = (TextView) findViewById(R.id.nameTV);
        deadlineTV = (TextView) findViewById(R.id.deadlineTV);
        contactTV = (TextView) findViewById(R.id.contactTV);
        websiteTV = (TextView) findViewById(R.id.websiteTV);

        programTV = (TextView) findViewById(R.id.programTV1);
        countryTV = (TextView) findViewById(R.id.countryTV1);
        regionTV = (TextView) findViewById(R.id.regionTV1);
        greTV = (TextView) findViewById(R.id.greTV1);
        toeflTV = (TextView) findViewById(R.id.toeflTV1);
        ieltsTV = (TextView) findViewById(R.id.ieltsTV1);
        gpaTV = (TextView) findViewById(R.id.gpaTV1);
        tuitionTV = (TextView) findViewById(R.id.tuitionTV1);




        new JSONParse().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fourth_screen, menu);
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




    protected class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(FourthScreen.this);
            pDialog.setMessage("Hunting Universities ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
//            Bitmap myBitmap = null;
            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl("http://192.168.0.21/webservice/final_json1.php?name=%22"+UNIVERSITY_SELECTED+"%22");




            try {

                SUCCESS = json.getInt("success");

                if (SUCCESS == 1)

                {


                    JSONArray universities = json.getJSONArray("university");

                    for (int i = 0; i < universities.length(); i++) {



                        JSONObject univ = universities.getJSONObject(i);

                        NAME = univ.getString("name");

                        PROGRAM = univ.getString("program");

                        COUNTRY_SELECTED = univ.getString("country");
                        REGION_SELECTED = univ.getString("region");

                        TUITION_FEE = univ.getInt("tuition");



                        GRE = univ.getInt("gre");

                        IELTS = univ.getDouble("ielts");

                        TOEFL = univ.getInt("toefl");

                        GPA = univ.getDouble("gpa");

                        DEADLINE = univ.getString("deadline");

                        CONTACT = univ.getString("contact");



                        URL = univ.getString("website");





                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String json) {

            SecondScreen second = new SecondScreen();



            if (SUCCESS == 1) {



                nameTV.setText(NAME);
                programTV.setText(""+"M.S.");
                countryTV.setText(COUNTRY_SELECTED);
                regionTV.setText(REGION_SELECTED);

                if(GRE != 0) {
                    greTV.setText("" + GRE);
                }

                else
                {
                    greTV.setText("" + "N/A");
                }

                if(TOEFL != 0) {
                    toeflTV.setText("" + TOEFL);
                }

                else
                {
                    toeflTV.setText("" + "N/A");
                }
                ieltsTV.setText(""+ IELTS);
                gpaTV.setText(""+GPA);
                tuitionTV.setText(""+TUITION_FEE);
                deadlineTV.setText(DEADLINE);
                contactTV.setText(CONTACT);
                websiteTV.setText(URL);



            } else {

            }

            pDialog.dismiss();

        }




    }


}

package com.example.s519426.androidprojectuh;

/**
 * Created by Amerender Singh, Giridharan Srinivasaraj, Mounika Thangella.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SecondScreen extends Activity {

    public String THIRD_SCREEN_URL="";

    public String COUNTRY_SELECTED="";
    public String PROGRAM="";

    public String REGION_SELECTED="";
    public int TUITION_FEE_MIN=0;
    public int TUITION_FEE_MAX=1000000;
    public int GRE_MIN=0;
    public int GRE_MAX=340;
    public int IELTS_MIN=0;
    public int IELTS_MAX=9;
    public int TOEFL_MIN=0;
    public int TOEFL_MAX=120;
    public int GPA_MIN=0;
    public int GPA_MAX=4;

    Spinner spinner1;
    Spinner spinner2;

    EditText tuitionFeeMin, tuitionFeeMax, greMin, greMax, ieltsMin, ieltsMax, toeflMin, toeflMax, gpaMin, gpaMax;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second_screen);

        Intent intent = getIntent();
        COUNTRY_SELECTED = intent.getStringExtra("countryvalues");

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.Program,android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        if(COUNTRY_SELECTED.equals("AUSTRALIA")) {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.AUS, android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
        }

        if(COUNTRY_SELECTED.equals("CANADA")) {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.CANADA, android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
        }

        if(COUNTRY_SELECTED.equals("UK")) {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.UK, android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
        }

        if(COUNTRY_SELECTED.equals("USA")) {
            ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.USA, android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
        }


        PROGRAM =  spinner1.getSelectedItem().toString();


                {
                    PROGRAM="%25";
                }

//        REGION_SELECTED =  spinner2.getSelectedItem().toString();
//
//        if(REGION_SELECTED.equals("ANY"))
//        {
//            REGION_SELECTED="%25";
//        }

//        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                PROGRAM = (String) adapterView.getItemAtPosition(i);
//
//                if(PROGRAM.equals("ANY"))
//                {
//                    PROGRAM="%25";
//                }
//
//            }
//        });


//        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                REGION_SELECTED = (String) adapterView.getItemAtPosition(i);
//
//                if(REGION_SELECTED.equals("ANY"))
//                {
//                    REGION_SELECTED="%25";
//                }
//
//            }
//        });

        tuitionFeeMin = (EditText) findViewById(R.id.tfET1);
        tuitionFeeMax = (EditText) findViewById(R.id.tfET2);
        greMin = (EditText) findViewById(R.id.greET1);
        greMax = (EditText) findViewById(R.id.greET2);
        ieltsMin = (EditText) findViewById(R.id.ieltsET1);
        ieltsMax = (EditText) findViewById(R.id.ieltsET2);
        toeflMin = (EditText) findViewById(R.id.toeflET1);
        toeflMax = (EditText) findViewById(R.id.toeflET2);
        gpaMin = (EditText) findViewById(R.id.gpaET1);
        gpaMax = (EditText) findViewById(R.id.gpaET2);









    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_screen, menu);
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

    public void huntBTN(View view)
    {

        if(!tuitionFeeMin.getText().toString().isEmpty()) {

            TUITION_FEE_MIN = Integer.parseInt(tuitionFeeMin.getText().toString());
        }


        if(!tuitionFeeMax.getText().toString().isEmpty()) {
            TUITION_FEE_MAX = Integer.parseInt(tuitionFeeMax.getText().toString());
        }

        if(!greMin.getText().toString().isEmpty()) {
            GRE_MIN = Integer.parseInt(greMin.getText().toString());
        }

        if(!greMax.getText().toString().isEmpty()) {
            GRE_MAX = Integer.parseInt(greMax.getText().toString());
        }


        if(!ieltsMin.getText().toString().isEmpty()) {
            IELTS_MIN = Integer.parseInt(ieltsMin.getText().toString());
        }


        if(!ieltsMax.getText().toString().isEmpty()) {
            IELTS_MAX = Integer.parseInt(ieltsMax.getText().toString());
        }

        if(!toeflMin.getText().toString().isEmpty()) {
            TOEFL_MIN = Integer.parseInt(toeflMin.getText().toString());
        }

        if(!toeflMax.getText().toString().isEmpty()) {
            TOEFL_MAX = Integer.parseInt(toeflMax.getText().toString());
        }

        if(!gpaMin.getText().toString().isEmpty()) {
            GPA_MIN = Integer.parseInt(gpaMin.getText().toString());
        }

        if(!gpaMax.getText().toString().isEmpty()) {
            GPA_MAX = Integer.parseInt(gpaMax.getText().toString());
        }

        REGION_SELECTED =  spinner2.getSelectedItem().toString();

        if(REGION_SELECTED.equals("ANY"))
        {
            REGION_SELECTED="%25";
        }










        THIRD_SCREEN_URL = "http://192.168.0.21/webservice/final_json.php?t_fees_min="+TUITION_FEE_MIN+"&t_fees_max="+TUITION_FEE_MAX+"&ielts_min="+IELTS_MIN+"&ielts_max="+IELTS_MAX+"&toefl_min="+TOEFL_MIN+"&toefl_max="+TOEFL_MAX+"&gpa_min="+GPA_MIN+"&gpa_max="+GPA_MAX+"&country=%22"+COUNTRY_SELECTED+"%22&course=%22"+PROGRAM+"%22&state=%22"+REGION_SELECTED+"%22&gre_min="+GRE_MIN+"&gre_max="+GRE_MAX+"";



        Log.e("THIRD_SCREEN_URL", THIRD_SCREEN_URL);


        Intent intent = new Intent(this, ThirdScreen.class);
        intent.putExtra("thirdscreenurl",THIRD_SCREEN_URL);
        startActivity(intent);

    }



}

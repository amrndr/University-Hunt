package com.example.s519426.androidprojectuh;

/**
 * Created by Amerender Singh, Giridharan Srinivasaraj, Mounika Thangella.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    public String COUNTRY_SELECTED="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        String[] title = {"AUSTRALIA", "CANADA", "UNITED KINGDOM", "UNITED STATES OF AMERICA"};
        int[] images = {R.drawable.australia,R.drawable.canada,R.drawable.uk,R.drawable.usa_128};

        ArrayList<CountryRow> countryItems = new ArrayList<CountryRow>();

        countryItems.add(new CountryRow(title[0], images[0]));
        countryItems.add(new CountryRow(title[1], images[1]));
        countryItems.add(new CountryRow(title[2], images[2]));
        countryItems.add(new CountryRow(title[3], images[3]));


        ArrayAdapter<CountryRow> countryAA = new MyArrayAdapter(this, R.layout.country_row, R.id.countryTV, countryItems);
        ListView countryLV = (ListView) findViewById(R.id.countrylistView);
        countryLV.setAdapter(countryAA);

        countryLV.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String countryselected="";
        switch (i) {
            case 0:
                countryselected = "AUSTRALIA";
                break;
            case 1:
                countryselected = "CANADA";
                break;
            case 2:
                countryselected = "UK";
                break;
            case 3:
                countryselected = "USA";
                break;
        }
        COUNTRY_SELECTED=countryselected;
        Intent intent = new Intent(this, SecondScreen.class);
        intent.putExtra("countryvalues",COUNTRY_SELECTED);
        startActivity(intent);
    }


    class MyArrayAdapter extends ArrayAdapter<CountryRow> {


        public MyArrayAdapter(Context context, int resource, int textViewID, List<CountryRow> data) {

            super(context, resource, textViewID, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ImageView imageView = (ImageView) view.findViewById(R.id.countryIV);
            TextView textView = (TextView) view.findViewById(R.id.countryTV);
            CountryRow  c = this.getItem(position);
            imageView.setImageResource(c.getImage());
            textView.setText(c.getTitle());
            return view;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}

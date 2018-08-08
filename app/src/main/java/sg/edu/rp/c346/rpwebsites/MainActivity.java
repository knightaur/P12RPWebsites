package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = sharedPref.edit();
        int pos = spncat.getSelectedItemPosition();
        int pos1 = spnsubCat.getSelectedItemPosition();
        preEdit.putInt("spinnerSelection", pos);
        preEdit.putInt("spinnerSelection2", pos1);
        preEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preEdit = sharedPref.edit();
        spncat.setSelection(sharedPref.getInt("spinnerSelection", 0));
        spnsubCat.setSelection(sharedPref.getInt("spinnerSelection2",0));
    }

    Spinner spncat;
    Spinner spnsubCat;
    Button btnGo;
    ArrayList<String> alcategory;
    ArrayAdapter<String> aacategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spncat = findViewById(R.id.spinnerCat);
        spnsubCat = findViewById(R.id.spinnerSubCat);
        btnGo = findViewById(R.id.buttonGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = spncat.getSelectedItemPosition();
                int pos1 = spnsubCat.getSelectedItemPosition();
                String [][] sites ={
                        {"https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life",
                        },
                        {"https://www.rp.edu.sg/soi/full-time-diplomas/details/r47/" ,
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12/"},

                } ;
                String URL = sites[pos][pos1];
                Intent intent = new Intent(getBaseContext(),MainActivityPages.class);
                intent.putExtra("URL", URL+"");
                startActivity(intent);
            }

        });
        alcategory = new ArrayList<>();
        aacategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alcategory);

        String[] strcategory = getResources().getStringArray(R.array.SubCategoryRP);
        alcategory.addAll(Arrays.asList(strcategory));
        spnsubCat.setAdapter(aacategory);

        spncat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        alcategory.clear();
                        String[] strcategory = getResources().getStringArray(R.array.SubCategoryRP);
                        alcategory.addAll(Arrays.asList(strcategory));
                        aacategory.notifyDataSetChanged();
                        break;
                    case 1:
                        alcategory.clear();
                        String[] strcategory1 = getResources().getStringArray(R.array.SubCategorySOI);
                        alcategory.addAll(Arrays.asList(strcategory1));
                        aacategory.notifyDataSetChanged();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
package com.example.myplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static  ArrayList<String> places = new ArrayList<>();
    static  ArrayList<LatLng> locations = new ArrayList<>();
    static  ArrayAdapter arrayAdapter;
    Button button;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayList<String> latitude = new ArrayList<>();
//        ArrayList<String> longitude = new ArrayList<>();
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataSave();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReadMe();
            }
        });

        places.clear();
        locations.clear();


//        if (places.size() > 0 && latitude.size() >  0 && longitude.size() >0){
//            if (places.size() == latitude.size() && places.size() == longitude.size()){
//                for (int i = 0 ; i<latitude.size(); i++){
//                    locations.add(new LatLng(Double.parseDouble(latitude.get(i) ), Double.parseDouble(longitude.get(i))));
//                }
//            }
//        }
//        else {

      //}

        //creating the list view and passing the array adapter
        ListView listView = findViewById(R.id.listView);
        places.add("Add a new place");
        locations.add(new LatLng(0,0));
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , places);
        listView.setAdapter(arrayAdapter);


        //Setting up onclick actions
        //goes to the maps activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext() , MapsActivity.class);
                intent.putExtra("placeNumber" , i);
                startActivity(intent);
            }
        });

    }


    public void openDataSave(){
        Intent intent = new Intent(this, DataSave.class);
        startActivity(intent);
    }


    public void openReadMe(){
        Intent intent = new Intent(this, ReadMe.class);
        startActivity(intent);
    }




}

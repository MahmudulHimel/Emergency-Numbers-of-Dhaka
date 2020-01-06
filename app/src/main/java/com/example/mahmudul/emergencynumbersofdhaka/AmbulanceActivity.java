package com.example.mahmudul.emergencynumbersofdhaka;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by MAHMUDUL on 8/2/2018.
 */

public class AmbulanceActivity extends AppCompatActivity {

    ImageView imageView;


    // Array of strings...
    String[] mobileArray = {"Ad-Din Hospitals Ambulance Service","Al-Amin Ambulance Service","Al-Markazul lslami Ambulance Service",
            "Alif Ambulance Service","Anju-Man-E-Mufidul Islam Ambulance Service","Apanjon Ambulance Service",
            "Apollo Hospitals Ambulance Service","BIRDEM Ambulance Service","Bangabondhu Sheikh Mujib Medical University",
            "Bangladesh Medical College Hospital","CMH Ambulance Service","Day-Night Ambulance Service",
            "Dhaka Medical College Hospital","Dhaka Eye Hospital Ambulance Service","Dhaka Shishu Hospital Ambulance Service",
            "Fire Service Ambulance","Green Ambulance Service","Heart Hospital Ambulance Service","Holy Family Red Crescent Hospital",
            "ICDDRB (Cholere Hospital) Ambulance Service","Life Line Health care Limited","Medinova Medical Service Ltd.",
            "Monowara Hospital (pvt.) Ltd. Ambulance Service","National Heart Institute Ambulance Service",
            "Rafa Ambulance Service","Red Crescent Society Ambulance Service","Sir Salimullah Medical College & Mitford Hospital Ambulance Service",
            "Shahid Suhrawardi Hospital Ambulance Service","Shefa Ambulance Services","South Asian Hospital Ambulance Service",
            "Square Hospital Ambulance Service","Orthopedic Hospital Ambulance Service","Prime General Hospital Ambulance Service",
            "United Hospital Ambulance Service","Labaid Hospital Ambulance service","Lab-Aid Cardiac Hospital Ambulance Service",
            "Lab-Aid Cardiac Hospital Ambulance Service","Lab-Aid Cardiac Hospital Ambulance Service"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        ListView listView = (ListView) findViewById(R.id.ambulance_list);
        imageView = (ImageView) findViewById(R.id.back);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });



        //use for screen rotation fixed to portrait//
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.ambulance_listview,R.id.ambulance_show ,mobileArray);


        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                /*Toast.makeText(PoliceActivity.this,mobileArray[position],Toast.LENGTH_SHORT).show();*/

                try {
                    int arr = getApplicationContext().getResources().getIdentifier("ambulance_" + position, "array",
                            getPackageName());
                    new MaterialDialog.Builder(AmbulanceActivity.this)
                            .title(mobileArray[position])
                            .items(arr)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    String data = text.toString();
                                    if(data.contains("Telephone"))
                                    {
                                        String tel = data.replace("Telephone: ", "");
                                        try {
                                            Intent intent = new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:"+tel));
                                            startActivity(intent);
                                        }catch (SecurityException e){}
                                    }else if(data.contains("Mobile"))
                                    {
                                        String tel = data.replace("Mobile: ", "");
                                        try {
                                            Intent intent = new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:"+tel));
                                            startActivity(intent);
                                        }catch (SecurityException e){}

                                    }else
                                    {
                                        return ;

                                    }
                                }

                            })
                            .show();
                }catch (Exception e){}
            }

        });


    }
}


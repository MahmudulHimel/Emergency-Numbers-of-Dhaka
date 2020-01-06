package com.example.mahmudul.emergencynumbersofdhaka;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import static java.security.AccessController.getContext;

/**
 * Created by MAHMUDUL on 7/28/2018.
 */

public class PoliceActivity  extends AppCompatActivity {

    ImageView imageView;


    // Array of strings...
    String[] mobileArray = {"Adabor Police Station","Airport Police Station","Badda Police Station",
            "Banani Police Station","Bongshal Police Station","Cantonment Police Station","Chalkbazar Police Station",
            "Dakshin Khan Police Station","Darus-Salam Police Station","Demra Police Station",
            "Dhanmondi Police Station","Gandaria Police Station","Gulshan Police Station",
            "Hazaribag Police Station","Jatrabari Police Station",
            "Kafrul Police Station","Kalabagan Police Station","Kamrangirchar Police Station","Khilgaon Police Station",
            "Khilkhet Police Station","Kodomtali Police Station","Kotwali Police Station","Lalbag Police Station",
            "Mirpur Model Police Station","Mohammadpur Police Station","Motijheel Police Station","Mugda Police Station",
            "New Market Police Station","Pallabi Police Station","Paltan Model Police Station",
            "Ramna Model Police Station","Rampura Police Station","Rupnagar Police Station","Sabujbag Police Station",
            "Shah Ali Police Station","Shahbag Police Station","Shahjahanpur Police Station","Sutrapur Police Station",
            "Shyampur Police Station","Sher-e-Bangla Nagar Police Station","Tejgaon Industrial Area Police Station",
            "Tejgaon Police Station","Turag Police Station","Uttar Khan Police Station",
            "Uttara East Police Station","Uttara West Police Station","Bhashantek Police Station","Vatara Police Station",
            "Wari Police Station"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        ListView listView = (ListView) findViewById(R.id.police_list);
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
                R.layout.police_listview, R.id.police_show, mobileArray);

        listView.setAdapter(adapter);




       // String[] items = {"Adabor", "Airport", "Ashulia", "Badda"};
        //ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.police_listview, R.id.police_show);


//        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                /*Toast.makeText(PoliceActivity.this,mobileArray[position],Toast.LENGTH_SHORT).show();*/

                try {
                    int arr = getApplicationContext().getResources().getIdentifier("police_" + position, "array",
                            getPackageName());
                    new MaterialDialog.Builder(PoliceActivity.this)
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


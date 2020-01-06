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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by MAHMUDUL on 7/28/2018.
 */

public class FireActivity extends AppCompatActivity {

    ImageView imageView;


    // Array of strings...
    String[] mobileArray = {"Baridhara Fire Station","DEPZ Fire Station","Demra Fire Station","Dhamrai Fire Station",
            "Hazaribag Fire Station",
            "Keraniganj Fire Station","Khilgaon Fire Station","Kurmitola Fire Station","Lalbag Fire Station",
            "Mirpur Fire Station","Mohammadpur Fire Station","Polashi Barrak Fire Station",
            "Postogola Fire Station","Sadarghat Fire Station","Sadarghat River Fire Station","Savar Fire Station",
            "Sutrapur Fire Station",
            "Siddique Bazar Fire Station","Tejgaon Fire Station","Uttara Fire Station"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        ListView listView = (ListView) findViewById(R.id.fire_list);
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
                R.layout.fire_listview,R.id.fire_show ,mobileArray);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                /*Toast.makeText(PoliceActivity.this,mobileArray[position],Toast.LENGTH_SHORT).show();*/

                try {
                    int arr = getApplicationContext().getResources().getIdentifier("fire_" + position, "array",
                            getPackageName());
                    new MaterialDialog.Builder(FireActivity.this)
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

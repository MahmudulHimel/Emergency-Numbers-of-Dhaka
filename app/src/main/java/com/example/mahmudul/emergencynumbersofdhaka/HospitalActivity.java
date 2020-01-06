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
 * Created by MAHMUDUL on 7/28/2018.
 */

public class HospitalActivity  extends AppCompatActivity {

    ImageView imageView;


    // Array of strings...
    String[] mobileArray = {"Ad-din Medical College Hospital","Ahmed Medical Center Limited",
            "Ahsania Mission Cancer and General Hospital","Aichi Medical College and Hospital",
            "AL-MANAR HOSPITAL LTD.","Al-Noor Eye Hospital","Al Helal Specialized Hospital","Apollo Hospital",
            "Asgar Ali Hospital","Asian Hospital","Badda General Hospital Pvt. Ltd.","Bangladesh Eye Hospital",
            "Bangabandhu Sheikh Mujib Medical University (BSMMU)","Bangladesh Spine and Orthopedic Hospital",
            "Bangladesh Specialized Hospital(BSH)","Bangladesh ENT Hospital","Bangladesh Eye Hospital",
            "Bari-Ilizarov Orthopedic Centre",
            "BIRDEM General Hospital","Birdem General Hospital-2 (for Women and Children)","B.I.H.S General Hospital",
            "BRB Hospitals Ltd.","Care Medical College Hospital","Central Hospital Ltd.","City Hospital",
            "CKD Urology Hospital","Dhaka Medical College Hospital","Dhaka Paediatric-Neonatal & General Hospital Limited",
            "Dhaka Central International Medical College & Hospital","Dhaka Shishu Hospital","Dhaka Eye Hospital",
            "Dhaka Eye Care Hospital","Dhaka Mohanagar General Hospital","Dhamrai Upazila Health Complex",
            "Delta Medical College and Hospital","Delta Hospital Limited","Dr. MR khan shishu hospital & institute of child health",
            "Dr. Salahuddin hospital","Eden Multicare Hospital","ENT and Head Neck Cancer Hospital and Institute",
            "Eye Hospital","Farabi General Hospital","Fashion Eye Hospital and Institute","Green Life Medical College Hospital",
            "Greenland Hospital Limited","Health and Hope Specialised Hospital","Health Aid Diagnostic and Hospital","Hitech Multicare Hospital",
            "Holy Family Red Crescent Medical College Hospital","Ibn Sina Hospital","Ibn Sina Medical College Hospital",
            "Ibrahim Cardiac Hospital & Research Institute","Impulse Hospital","Ispahani Islamia Eye Institute and Hospital",
            "Islami Bank Hospital","Islami Bank Central Hospital","Islami Bank Hospital Mirpur","Japan Bangladesh Friendship Hospital",
            "KC Hospital & Diagnostic Center","Keraniganj Upazila Health Complex","Kidney Foundation Hospital and Research Institute Bangladesh",
            "Kingston Hospital","Kurmitola General Hospital","Labaid Specialized Hospital","Labaid Cardiac Hospital",
            "Labaid diagnostic kalabagan Branch","Lions Eye Institute & Hospital","MARKS Medical College & Hospital",
            "Mirpur General Hospital & Diagnostic Centre","Medi Aid General Hospital Ltd","Meditech General Hospital (Pvt) Ltd.",
            "Medical College For Women and Hospital","Metropolitan Medical Centre Ltd.","MH Samorita Hospital & Medical College",
            "Samorita Hospital","Millennium Heart & General Hospital Ltd.","Mirpur Adhunik Hospital And Diagnostic Center",
            "Modern Psychiatric Hospital (Pvt.)","Monowara Hospital (Pvt.) Ltd.","Monowara Orthopedic and General Hospital",
            "Nez-E-Noor Hospital (Pvt)Ltd.","National Institute of Cardiovascular Diseases","National Institute of Mental Health and Hospital",
            "National Institute of Neurosciences & Hospital","National Institute of Ear, Nose & Throat","National Institute of Diseases of the Chest and Hospital (NIDCH)",
            "National Heart Foundation Hospital & Research Institute","National Institute of Kidney Diseases & Urology",
            "Neurology Foundation Hospital","Oncology Centre and Hospital Limited","OGSB Maternity Hospital","Orion Renal & General Hospital",
            "Padma General Hospital Ltd.","Popular Diagnostic Center Ltd. Mirpur","Popular Diagnostic Center Ltd. Shyamoli",
            "Popular Diagnostic Centre Ltd. Dhanmondi","Renaiissance Hospital","Radical Hospital","Rushmono General Hospital",
            "Saphena General Hospital Limited","Shaheed Suhrawardy Medical College and Hospital","Shahabuddin Medical College And Hospital",
            "S.P.R.C & Neurology Hospital","Square Hospital","The Barakah General Hospital Limited","United Hospital Limited",
            "Universal Medical College And Hospital Ltd.","Uttara Central Hospital & Diagnostic Center","Vision Eye Hospital",
            "Women And Children’S Hospital Pvt.Ltd.","Yamagata Dhaka Friendship Hospital","Z.H. Sikder Women’S College & Hospital (Pvt) Ltd"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        ListView listView = (ListView) findViewById(R.id.hospital_list);
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
                R.layout.hospital_listview,R.id.hospital_show ,mobileArray);


        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                /*Toast.makeText(PoliceActivity.this,mobileArray[position],Toast.LENGTH_SHORT).show();*/

                try {
                    int arr = getApplicationContext().getResources().getIdentifier("hospital_" + position, "array",
                            getPackageName());
                    new MaterialDialog.Builder(HospitalActivity.this)
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
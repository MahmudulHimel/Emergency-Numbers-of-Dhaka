package com.example.mahmudul.emergencynumbersofdhaka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawer();

        //use for screen rotation fixed to portrait//
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        ImageView imageView = (ImageView) findViewById(R.id.police);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PoliceActivity.class);
                startActivity(intent);


            }
        });


        ImageView imageView1 = (ImageView) findViewById(R.id.fire);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FireActivity.class);
                startActivity(intent);


            }
        });


        ImageView imageView2 = (ImageView) findViewById(R.id.hospital);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HospitalActivity.class);
                startActivity(intent);


            }
        });


        ImageView imageView3 = (ImageView) findViewById(R.id.ambulance);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AmbulanceActivity.class);
                startActivity(intent);


            }
        });


        /*Make a call on button click*/

        Button button = (Button) findViewById(R.id.police_call);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:01672767449"));
                    startActivity(intent);
                }catch (SecurityException e){}
            }

            });

        Button button1 = (Button) findViewById(R.id.fire_call);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:01521308474"));
                    startActivity(intent);
                }catch (SecurityException e){}
            }

        });


        }

    //For Material Drawer--

    public void setUpDrawer()
    {

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorPrimary)
                .withSelectionListEnabled(false)
                .withProfileImagesVisible(false)

                .build();

        //to add image in drawer
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.main)

                .build();


        //Create the drawer result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
                //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Police Station").withIcon(FontAwesome.Icon.faw_building).withIdentifier(1).withSelectable(true),
                        new PrimaryDrawerItem().withName("Fire Station").withIcon(FontAwesome.Icon.faw_fire_extinguisher).withIdentifier(2).withSelectable(true),
                        new PrimaryDrawerItem().withName("Nearest Hospital").withIcon(FontAwesome.Icon.faw_hospital_o).withIdentifier(3).withSelectable(true),
                        new PrimaryDrawerItem().withName("Ambulance").withIcon(FontAwesome.Icon.faw_ambulance).withIdentifier(4).withSelectable(true),
                        new PrimaryDrawerItem().withName("About Us").withIcon(FontAwesome.Icon.faw_info).withIdentifier(5).withSelectable(true)

                         )

            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {

                                Intent intent1 = new Intent(MainActivity.this, PoliceActivity.class);
                                startActivity(intent1);

                            } else if (drawerItem.getIdentifier() == 2) {

                                Intent intent1 = new Intent(MainActivity.this, FireActivity.class);
                                startActivity(intent1);


                            }else if (drawerItem.getIdentifier() == 3) {
                                Intent intent1 = new Intent(MainActivity.this, HospitalActivity.class);
                                startActivity(intent1);


                            }else if (drawerItem.getIdentifier() == 4) {
                                Intent intent1 = new Intent(MainActivity.this, AmbulanceActivity.class);
                                startActivity(intent1);

                            }else if (drawerItem.getIdentifier() == 5) {
                                Intent intent1 = new Intent(MainActivity.this, About.class);
                                startActivity(intent1);


                            } else if (drawerItem.getIdentifier() == -1) {

                            }
                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        finish();
                        return true;
                    }
                })
                .build();
    }

  //add a dialog box after pressing a back button

    @Override
    public void onBackPressed() {

        AlertDialog.Builder BackAlertDialog = new AlertDialog.Builder(MainActivity.this);

        BackAlertDialog.setTitle("Exit Alert !");

        BackAlertDialog.setMessage("Are you sure want to exit ?");


        BackAlertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //Exit from activity.
                        finish();
                    }
                });

        BackAlertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //Cancel alert dialog box .
                        dialog.cancel();
                    }
                });

        BackAlertDialog.show();

        return;
    }

}









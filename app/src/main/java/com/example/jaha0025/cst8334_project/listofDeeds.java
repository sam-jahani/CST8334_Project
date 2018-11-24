package com.example.jaha0025.cst8334_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Picture;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class listofDeeds extends AppCompatActivity {
    public Cursor cursor;
    ActOfKindness act;
    ListView listView;
    TextView titleText;
    MediaPlayer testSound3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // populateListView();

        setContentView(R.layout.activity_listof_deeds);

       Play();

//        ArrayList<ActOfKindness> acts = ActOfKindness.insertActs(this);
//        ActDbAdapter adapter = new ActDbAdapter(this);
//        adapter.open();
//        for(ActOfKindness act : acts) {
//            ContentValues newValues = new ContentValues();
//            newValues.put(ActDbAdapter.TITLE, act.aTitle);
//            newValues.put(ActDbAdapter.DESCRIPTION, act.aDescription);
//            newValues.put(ActDbAdapter.QUESTION, act.aQuestion);
//            adapter.insertAct(newValues);
//        }
//        cursor = adapter.getActs();
//        titleText = findViewById(R.id.grid_AOK_Title);
//
//        if(cursor.moveToFirst()) {
//            act = ActDbAdapter.getActFromCursor(cursor);
//            titleText.setText(act.aTitle);
//        }

        //THIS WILL GO TO HOME SCREEN
        ImageView homeButton = findViewById(R.id.homeButton2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(listofDeeds.this, MainMenu.class);
                startActivity(k);
            }
        });


        listView = findViewById(R.id.listview);

        ActsofK aok1 = new ActsofK("0", "Your Own Act of Kindness");
        ActsofK aok2 = new ActsofK("1", "Charity Donation Jar");
        ActsofK aok3 = new ActsofK("2", "Welcome a Neighbour");
        ActsofK aok4 = new ActsofK("3", "Card for Your Letter Carrier");
        ActsofK aok5 = new ActsofK("4", "Share With Friends");
        ActsofK aok6 = new ActsofK("5", "Local Bookstore");
        ActsofK aok7 = new ActsofK("6", "Card For Your Doctor");
        ActsofK aok8 = new ActsofK("7", "Deliver Dinner");
        ActsofK aok9 = new ActsofK("8", " Cashier`s Name");
        ActsofK aok10 = new ActsofK("9", "Library");
        ActsofK aok11 = new ActsofK("10", "Garbage Bins");
        ActsofK aok12 = new ActsofK("11", "Invite to Play");
        ActsofK aok13 = new ActsofK("12", "Story Time");
        ActsofK aok14 = new ActsofK("13", "Guide Dog Donation");
        ActsofK aok15 = new ActsofK("14", "Deliver Art Work");
        ActsofK aok16 = new ActsofK("15", "Community Association");
        ActsofK aok17 = new ActsofK("16", "Pet-Sitting");
        ActsofK aok18 = new ActsofK("17", "Card for a Firefighter");
        ActsofK aok19 = new ActsofK("18", "Play Date");
        ActsofK aok20 = new ActsofK("19", "Food Bank Donation");
        ActsofK aok21 = new ActsofK("20", "Dog Walking");
        ActsofK aok22 = new ActsofK("21", "Neighbour`s Mail");
        ActsofK aok23 = new ActsofK("22", "Bring Treats");
        ActsofK aok24 = new ActsofK("23", "Share Bubbles");
        ActsofK aok25 = new ActsofK("24", "Parking Meters");
        ActsofK aok26 = new ActsofK("25", "Paper Airplanes");
        ActsofK aok27 = new ActsofK("26", "Groceries");
        ActsofK aok28 = new ActsofK("27", "Obstacle Course");
        ActsofK aok29= new ActsofK("28", "Pick Up Garbage");
        ActsofK aok30 = new ActsofK("29", "Say Hello");
        ActsofK aok31 = new ActsofK("30", "Card a Police for Officer");
        ActsofK aok32 = new ActsofK("31", "Clean Your Park");
        ActsofK aok33 = new ActsofK("31", "Share Chalk");
        ActsofK aok34 = new ActsofK("33", "Hand-Me- Downs");
        ActsofK aok35 = new ActsofK("34", "Community Page");
        ActsofK aok36 = new ActsofK("35", "Neighbour`s Garbage");
        ActsofK aok37 = new ActsofK("36", "Bake Sale");
        ActsofK aok38 = new ActsofK("37", "Cardboard Box Fun");
        ActsofK aok39 = new ActsofK("38", "Card for Waste Collection Operator");
        ActsofK aok40 = new ActsofK("39", "Share Toys");
        ActsofK aok41 = new ActsofK("40", "Postcard");
        ActsofK aok42 = new ActsofK("41", "Travel Bag");
        ActsofK aok43 = new ActsofK("42", "Picture for a Stranger");
        ActsofK aok44 = new ActsofK("43", "Collect Mail");
        ActsofK aok45 = new ActsofK("44", "Painting Day");
        ActsofK aok46 = new ActsofK("45", "Go for a Walk");
        ActsofK aok47 = new ActsofK("46", "Hold the Door");
        ActsofK aok48 = new ActsofK("47", "Local Movie Rental");
        ActsofK aok49 = new ActsofK("48", "Dog Bag");
        ActsofK aok50 = new ActsofK("49", "Farmer`s Market");
        ActsofK aok51 = new ActsofK("50", "Write a Letter");
        ActsofK aok52 = new ActsofK("51", "Picture for Server");






       // ActsofK aok11 = new ActsofK("10", "Charity Donation Jar");
//
//        Your Own Act of Kindness
//        Charity Donation Jar
//        Welcome a Neighbour
//        Card for Your Letter Carrier
//        Share With Friends
//        Local Bookstore
//        Card For Your Doctor
//        Deliver Dinner
//        Cashier’s Name
//        Library

        ArrayList<ActsofK> listofaok = new ArrayList<>();


        listofaok.add(aok1);
        listofaok.add(aok2);
        listofaok.add(aok3);
        listofaok.add(aok4);
        listofaok.add(aok5);
        listofaok.add(aok6);
        listofaok.add(aok7);
        listofaok.add(aok8);
        listofaok.add(aok9);
        listofaok.add(aok10);
        listofaok.add(aok11);
        listofaok.add(aok12);
        listofaok.add(aok13);
        listofaok.add(aok14);
        listofaok.add(aok15);
        listofaok.add(aok16);
        listofaok.add(aok17);
        listofaok.add(aok18);
        listofaok.add(aok19);
        listofaok.add(aok20);
        listofaok.add(aok21);
        listofaok.add(aok22);
        listofaok.add(aok23);
        listofaok.add(aok24);
        listofaok.add(aok25);
        listofaok.add(aok26);
        listofaok.add(aok27);
        listofaok.add(aok28);
        listofaok.add(aok29);
        listofaok.add(aok30);
        listofaok.add(aok31);
        listofaok.add(aok32);
        listofaok.add(aok33);
        listofaok.add(aok34);
        listofaok.add(aok35);
        listofaok.add(aok36);
        listofaok.add(aok37);
        listofaok.add(aok38);
        listofaok.add(aok39);
        listofaok.add(aok40);
        listofaok.add(aok41);
        listofaok.add(aok42);
        listofaok.add(aok43);
        listofaok.add(aok44);
        listofaok.add(aok45);
        listofaok.add(aok46);
        listofaok.add(aok47);
        listofaok.add(aok48);
        listofaok.add(aok49);
        listofaok.add(aok50);
        listofaok.add(aok51);
        listofaok.add(aok52);
        // ArrayAdapter adapter = new ArrayAdapter()

        AokListAdapter adapter = new AokListAdapter(this, R.layout.adapter_view_layout, listofaok);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent k = new Intent(listofDeeds.this, GoodDeed.class);
                ActsofK aok = (ActsofK) listView.getItemAtPosition(position);
                //k.putExtra(text,GoodDeed.class);
                k.putExtra("ID", aok.getId());
                startActivity(k);
            }
        });


//
//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent k = new Intent(listofDeeds.this, GoodDeed.class);
//                startActivity(k);
//            }
//        });

        TextView textView = findViewById(R.id.aok);
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent k = new Intent(listofDeeds.this, GoodDeed.class);
//                startActivity(k);
//            }
//        });


        ImageView profileButton = findViewById(R.id.profileButton2);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(listofDeeds.this, UserProfile.class);
                startActivity(k);
            }
        });


    }

    public void onPause() {
       // testSound3.reset();
        testSound3.release();
        super.onPause();

    }



    public void Play() {


        if (testSound3 == null) {

            // instaniate the MediaPlayer object
            testSound3 = MediaPlayer.create(this, R.raw.testsound_mainmenu);
            //starting the recording
            testSound3.start();
            // when the recording is finished release the MediaPlayer
            testSound3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //this code actually releases the player
                    //testSound.release();
                    testSound3.start();

                }
            });
        }
    }
}

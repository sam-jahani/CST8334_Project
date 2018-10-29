package com.example.jaha0025.cst8334_project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        ImageView homeButton = findViewById(R.id.homeButton2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(listofDeeds.this, MainMenu.class);
                startActivity(k);
            }
        });


        ListView listView = findViewById(R.id.listview);

        ActsofK aok1 = new ActsofK("1", "Your Own Act of Kindness");
        ActsofK aok2 = new ActsofK("2", "Welcome a Neighbour");
        ActsofK aok3 = new ActsofK("3", "Card for Your Letter Carrier");
        ActsofK aok4 = new ActsofK("4", "Share With Friends");
        ActsofK aok5 = new ActsofK("5", "Local Bookstore");
        ActsofK aok6 = new ActsofK("6", "Card For Your Doctor");
        ActsofK aok7 = new ActsofK("7", "Deliver Dinner");
        ActsofK aok8 = new ActsofK("8", " Cashier’s Name");
        ActsofK aok9 = new ActsofK("9", "Library");
        ActsofK aok10 = new ActsofK("10", "Charity Donation Jar");


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

        // ArrayAdapter adapter = new ArrayAdapter()

        AokListAdapter adapter = new AokListAdapter(this, R.layout.adapter_view_layout, listofaok);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent k = new Intent(listofDeeds.this, GoodDeed.class);
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

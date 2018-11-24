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
    ActOfKindness act;
    UserAct userAct;
    ListView listView;
    TextView titleText;
    MediaPlayer testSound3;
    Cursor cursor_act;
    Cursor cursor_userAct;
    ArrayList<ActsofK> actsofK;
    ActDbAdapter adapter = new ActDbAdapter(this);
    int uId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication app = (MyApplication)getApplication();
        uId = app.getUserId();
        setContentView(R.layout.activity_listof_deeds);

        Play();

        ImageView homeButton = findViewById(R.id.homeButton2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(listofDeeds.this, MainMenu.class);
                startActivity(k);
            }
        });


        listView = findViewById(R.id.listview);
        actsofK = new ArrayList<ActsofK>();
        adapter.open();
        cursor_act = adapter.getActs();
        cursor_userAct = adapter.getUserActs(uId);
        ActsofK actofK;
        String completed;
        if(cursor_userAct.moveToFirst() && cursor_act.moveToFirst())
        {
            do {
                act = ActDbAdapter.getActFromCursor(cursor_act);
                userAct = ActDbAdapter.getUserActFromCursor(cursor_userAct);
                completed = userAct.isComplete() ? "Completed!" : "Not Completed";
                actofK = new ActsofK(String.valueOf(act.aId), act.aTitle, completed);
                actsofK.add(actofK);
            }while (cursor_act.moveToNext() && cursor_userAct.moveToNext());

        }

        AokListAdapter adapter = new AokListAdapter(this, R.layout.adapter_view_layout, actsofK);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent k = new Intent(listofDeeds.this, GoodDeed.class);
                ActsofK aok = (ActsofK) listView.getItemAtPosition(position);
                k.putExtra("ID", aok.getId());
                startActivity(k);
            }
        });


        TextView textView = findViewById(R.id.aok);

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

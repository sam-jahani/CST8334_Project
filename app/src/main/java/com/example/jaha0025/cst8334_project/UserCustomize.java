package com.example.jaha0025.cst8334_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.widget.ScrollView;
import android.widget.TextView;

public class UserCustomize extends AppCompatActivity {

    MediaPlayer testSound1;
    ImageView back;
    TextView head;
    TextView shirt;
    TextView pants;
    TextView all;
    ScrollView headScroll;
    ScrollView shirtScroll;
    ScrollView pantsScroll;
    ScrollView allScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_customize);

        Play();

        back = findViewById(R.id.av_close);
        head  = findViewById(R.id.av_head_text);
        shirt = findViewById(R.id.av_shirt_text);
        pants = findViewById(R.id.av_pants_text);
        all = findViewById(R.id.av_all_text);
        headScroll = findViewById(R.id.av_head_scrollview);
        shirtScroll = findViewById(R.id.av_shirt_scrollview);
        pantsScroll = findViewById(R.id.av_pants_scrollview);
        allScroll = findViewById(R.id.av_all_scrollview);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCustomize.this, UserProfile.class);
                startActivity(intent);
            }
        });


        head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                headScroll.setVisibility(View.VISIBLE);
                headScroll.scrollTo(0,0);

                shirtScroll.setVisibility(View.INVISIBLE);
                pantsScroll.setVisibility(View.INVISIBLE);
                allScroll.setVisibility(View.INVISIBLE);

            }
        });

        shirt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                shirtScroll.setVisibility(View.VISIBLE);
                shirtScroll.scrollTo(0,0);

                headScroll.setVisibility(View.INVISIBLE);
                pantsScroll.setVisibility(View.INVISIBLE);
                allScroll.setVisibility(View.INVISIBLE);

            }
        });

        pants.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pantsScroll.setVisibility(View.VISIBLE);
                pantsScroll.scrollTo(0,0);

                headScroll.setVisibility(View.INVISIBLE);
                shirtScroll.setVisibility(View.INVISIBLE);
                allScroll.setVisibility(View.INVISIBLE);

            }
        });

        all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                allScroll.setVisibility(View.VISIBLE);
                allScroll.scrollTo(0,0);

                headScroll.setVisibility(View.INVISIBLE);
                shirtScroll.setVisibility(View.INVISIBLE);
                pantsScroll.setVisibility(View.INVISIBLE);

            }
        });

    }

    public void Play() {
        if (testSound1 == null) {

            // instaniate the MediaPlayer object
            testSound1 = MediaPlayer.create(this, R.raw.testsound_mainmenu);
            //            //starting the recording
            testSound1.start();
            // when the recording is finished release the MediaPlayer
            testSound1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //this code actually releases the player
                    //testSound.release();
                    testSound1.start();

                }
            });
        }
    }

    public void onPause(){
        testSound1.release();
        super.onPause();
    }
}

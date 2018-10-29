package com.example.jaha0025.cst8334_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class UserCustomize extends AppCompatActivity {

    MediaPlayer testSound1 = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_customize);

        ImageView back = findViewById(R.id.av_close);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCustomize.this, UserProfile.class);
                startActivity(intent);
            }
        });

        Play();


    }

    public void Play() {
        if (testSound1 == null) {

            // instaniate the MediaPlayer object
            testSound1 = MediaPlayer.create(this, R.raw.test_sound);
            //starting the recording
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


package com.example.jaha0025.cst8334_project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
    ImageView shulkShirt;
    ImageView shulkScroll;
    ImageView iceShirt;
    ImageView iceScroll;
    ImageView pinkShirt;
    ImageView pinkScroll;
    ImageView pants1;
    ImageView pants1Scroll;
    ImageView pants2;
    ImageView pants2Scroll;
    ImageView pants3;
    ImageView pants3Scroll;
    ImageView skinTone1;
    ImageView skinTone2;
    ImageView star1;
    ImageView star2;
    ImageView avatar1;
    ImageView avatar2;
    ImageView baldChoco;
    ImageView baldChocoHeadScroll;
    ImageView vanill;
    ImageView vanillaHeadScroll;
    ImageView blondeGirl;
    ImageView blondeGirlScroll;
    ImageView chocoHair;
    ImageView chocoHairScroll;
    TextView head;
   // TextView head2;
    TextView shirt;
    TextView pants;
    TextView all;
    ScrollView headScroll1;
    ScrollView headScroll2;
    ScrollView shirtScroll;
    ScrollView pantsScroll;
    ScrollView allScroll;
    String currentSkinTone;
    int tone;
    boolean clicked;
    User user;
    int uid;
    ActDbAdapter adapter;
    Cursor curse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_customize);

        adapter = new ActDbAdapter(this);
        adapter.open();
        MyApplication myApp = (MyApplication)getApplication();
        uid = myApp.getUserId();
        curse = adapter.getUser(uid);


        Play();

        //avatar
        avatar1 = findViewById(R.id.av_character);
      // avatar2 = findViewById(R.id.av_character2);

        //skin tones
        skinTone1 = findViewById(R.id.av_skin_tone_1);
        skinTone2 = findViewById(R.id.av_skin_tone_2);

        star1 = findViewById(R.id.av_skin_tone_1_star);
        star2 = findViewById(R.id.av_skin_tone_2_star);

        //shirts

        shulkShirt = findViewById(R.id.av_character_shulk);
        shulkScroll = findViewById(R.id.av_shulk_scroll);

        iceShirt = findViewById(R.id.av_character_ice);
        iceScroll = findViewById(R.id.av_ice_scroll);

        pinkShirt = findViewById(R.id.av_character_pink);
        pinkScroll = findViewById(R.id.av_pink_scroll);

        //pants
        pants1 = findViewById(R.id.av_character_pants1);
        pants1Scroll = findViewById(R.id.av_Pants1_Scroll);

        pants2 = findViewById(R.id.av_character_pants2);
        pants2Scroll = findViewById(R.id.av_Pants2_Scroll);

        pants3 = findViewById(R.id.av_character_pants3);
        pants3Scroll = findViewById(R.id.av_Pants3_Scroll);


        //heads
        baldChoco = findViewById(R.id.aligned_bald_choco);
        baldChocoHeadScroll = findViewById(R.id.av_bald_choco_scroll);

        vanill = findViewById(R.id.av_character);
        vanillaHeadScroll = findViewById(R.id.av_vanill_Scroll);

        blondeGirl = findViewById(R.id.aligned_blondegirl);
        blondeGirlScroll = findViewById(R.id.blondGirlScroll);

        chocoHair = findViewById(R.id.aligned_choco_hair);
        chocoHairScroll = findViewById(R.id.chocoHairHead);


        //back button
        back = findViewById(R.id.av_close);

        //text headers
        head  = findViewById(R.id.av_head_text);
        head.setTextColor(Color.WHITE);

      //  head2 = findViewById(R.id.av_head_text2);
      //  head2.setTextColor(Color.RED);
       // head2.setVisibility(View.INVISIBLE);




        shirt = findViewById(R.id.av_shirt_text);
        shirt.setTextColor(Color.WHITE);

        pants = findViewById(R.id.av_pants_text);
        pants.setTextColor(Color.WHITE);

        all = findViewById(R.id.av_all_text);
        all.setTextColor(Color.WHITE);

        headScroll1 = findViewById(R.id.av_head_scrollview1);
        headScroll2 = findViewById(R.id.av_head_scrollview2);
        shirtScroll = findViewById(R.id.av_shirt_scrollview);
        pantsScroll = findViewById(R.id.av_pants_scrollview);
        allScroll = findViewById(R.id.av_all_scrollview);


        //DB goodies
        if(curse.moveToFirst())
        {
            user = ActDbAdapter.getUserFromCursor(curse);
        }

        //seperate switch blocks for each customizable item
        //set int value whenever user clicks on the item
        //the avatar is updated whenever the user backs out of the application
        //the avatar is loaded in onCreate when the screen is opened up and sets visibility depending on DB int values
        switch (user.getuShirt())
        {
            case 1:
                shulkShirt.setVisibility(View.VISIBLE);


                iceShirt.setVisibility(View.INVISIBLE);
                pinkShirt.setVisibility(View.INVISIBLE);
                break;
            case 2:

                pinkShirt.setVisibility(View.VISIBLE);


                iceShirt.setVisibility(View.INVISIBLE);
                shulkShirt.setVisibility(View.INVISIBLE);

                break;

            case 3:

                iceShirt.setVisibility(View.VISIBLE);


                shulkShirt.setVisibility(View.INVISIBLE);
                pinkShirt.setVisibility(View.INVISIBLE);

                break;


            default:

        }

        switch (user.getuHead())
        {
            case 1:

                vanill.setVisibility(View.VISIBLE);


                baldChoco.setVisibility(View.INVISIBLE);
                blondeGirl.setVisibility(View.INVISIBLE);
                chocoHair.setVisibility(View.INVISIBLE);

                break;

            case 2:

                blondeGirl.setVisibility(View.VISIBLE);


                vanill.setVisibility(View.INVISIBLE);
                baldChoco.setVisibility(View.INVISIBLE);
                chocoHair.setVisibility(View.INVISIBLE);

                break;

            case 3:

                baldChoco.setVisibility(View.VISIBLE);
                user.setuHead(3);

                vanill.setVisibility(View.INVISIBLE);
                blondeGirl.setVisibility(View.INVISIBLE);
                chocoHair.setVisibility(View.INVISIBLE);

                break;

            case 4:

                chocoHair.setVisibility(View.VISIBLE);
                user.setuHead(4);

                vanill.setVisibility(View.INVISIBLE);
                baldChoco.setVisibility(View.INVISIBLE);
                blondeGirl.setVisibility(View.INVISIBLE);

                break;

            default:
                vanill.setVisibility(View.VISIBLE);
        }

        switch(user.getuPants())
        {
            case 1:

                pants1.setVisibility(View.VISIBLE);


                pants2.setVisibility(View.INVISIBLE);
                pants3.setVisibility(View.INVISIBLE);

                break;

            case 2:

                pants2.setVisibility(View.VISIBLE);


                pants1.setVisibility(View.INVISIBLE);
                pants3.setVisibility(View.INVISIBLE);

                break;

            case 3:

                pants3.setVisibility(View.VISIBLE);
                user.setuPants(3);

                pants1.setVisibility(View.INVISIBLE);
                pants2.setVisibility(View.INVISIBLE);

                break;

            default:
                pants1.setVisibility(View.VISIBLE);

        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCustomize.this, UserProfile.class);
                startActivity(intent);

                adapter.updateAvatar(user);
            }
        });


        //header logic
        head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clicked = true;
                if (tone == 2) {
                    headScroll1.setVisibility(View.INVISIBLE);
                    headScroll2.setVisibility(View.VISIBLE);
                    headScroll2.scrollTo(0, 0);
                    head.setTextColor(Color.RED);

                    shirtScroll.setVisibility(View.INVISIBLE);
                    pantsScroll.setVisibility(View.INVISIBLE);
                    allScroll.setVisibility(View.INVISIBLE);

                    // head2.setVisibility(View.INVISIBLE);

                    shirt.setTextColor(Color.WHITE);
                    pants.setTextColor(Color.WHITE);
                    all.setTextColor(Color.WHITE);


                }else{
                    headScroll2.setVisibility(View.INVISIBLE);
                    headScroll1.setVisibility(View.VISIBLE);
                    headScroll1.scrollTo(0, 0);
                    head.setTextColor(Color.RED);

                    shirtScroll.setVisibility(View.INVISIBLE);
                    pantsScroll.setVisibility(View.INVISIBLE);
                    allScroll.setVisibility(View.INVISIBLE);
                    // headScroll2.setVisibility(View.INVISIBLE);
                    // head2.setVisibility(View.INVISIBLE);

                    shirt.setTextColor(Color.WHITE);
                    pants.setTextColor(Color.WHITE);
                    all.setTextColor(Color.WHITE);

                }

            }
        });

        shirt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clicked = false;
                shirtScroll.setVisibility(View.VISIBLE);
                shirtScroll.scrollTo(0,0);
                shirt.setTextColor(Color.RED);

                headScroll1.setVisibility(View.INVISIBLE);
                headScroll2.setVisibility(View.INVISIBLE);
                pantsScroll.setVisibility(View.INVISIBLE);
                allScroll.setVisibility(View.INVISIBLE);


                head.setTextColor(Color.WHITE);
                pants.setTextColor(Color.WHITE);
                all.setTextColor(Color.WHITE);

            }
        });

        pants.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clicked = false;
                pantsScroll.setVisibility(View.VISIBLE);
                pantsScroll.scrollTo(0,0);
                pants.setTextColor(Color.RED);

                headScroll1.setVisibility(View.INVISIBLE);
                headScroll2.setVisibility(View.INVISIBLE);
                shirtScroll.setVisibility(View.INVISIBLE);
                allScroll.setVisibility(View.INVISIBLE);

                head.setTextColor(Color.WHITE);
              //  head2.setTextColor(Color.WHITE);
                shirt.setTextColor(Color.WHITE);
                all.setTextColor(Color.WHITE);

            }
        });

        all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clicked = false;
                allScroll.setVisibility(View.VISIBLE);
                allScroll.scrollTo(0,0);
                all.setTextColor(Color.RED);

                headScroll1.setVisibility(View.INVISIBLE);
                headScroll2.setVisibility(View.INVISIBLE);
                shirtScroll.setVisibility(View.INVISIBLE);
                pantsScroll.setVisibility(View.INVISIBLE);

                head.setTextColor(Color.WHITE);
             //   head2.setTextColor(Color.WHITE);
                shirt.setTextColor(Color.WHITE);
                pants.setTextColor(Color.WHITE);

            }
        });

        //shirts logic
        shulkScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               shulkShirt.setVisibility(View.VISIBLE);
               user.setuShirt(1);

               iceShirt.setVisibility(View.INVISIBLE);
               pinkShirt.setVisibility(View.INVISIBLE);
            }
        });

        iceScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                iceShirt.setVisibility(View.VISIBLE);
                user.setuShirt(3);

                shulkShirt.setVisibility(View.INVISIBLE);
                pinkShirt.setVisibility(View.INVISIBLE);

            }
        });

        pinkScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pinkShirt.setVisibility(View.VISIBLE);
                user.setuShirt(2);

                iceShirt.setVisibility(View.INVISIBLE);
                shulkShirt.setVisibility(View.INVISIBLE);

            }
        });

        //pants logic
        pants1Scroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pants1.setVisibility(View.VISIBLE);
                user.setuPants(1);

                pants2.setVisibility(View.INVISIBLE);
                pants3.setVisibility(View.INVISIBLE);

            }
        });

        pants2Scroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pants2.setVisibility(View.VISIBLE);
                user.setuPants(2);

                pants1.setVisibility(View.INVISIBLE);
                pants3.setVisibility(View.INVISIBLE);

            }
        });

        pants3Scroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pants3.setVisibility(View.VISIBLE);
                user.setuPants(3);

                pants1.setVisibility(View.INVISIBLE);
                pants2.setVisibility(View.INVISIBLE);

            }
        });

        //skin tones logic
        skinTone1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.INVISIBLE);

               // avatar1.setVisibility(View.VISIBLE);
              //  avatar2.setVisibility(View.INVISIBLE);

                tone = 1;

                if(clicked == true){
                    head.performClick();
                }

            }
        });

        skinTone2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                star2.setVisibility(View.VISIBLE);
                star1.setVisibility(View.INVISIBLE);

              //  avatar1.setVisibility(View.INVISIBLE);
              //  avatar2.setVisibility(View.VISIBLE);

                tone = 2;

                if(clicked == true){
                    head.performClick();
                }

            }
        });

        vanillaHeadScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                vanill.setVisibility(View.VISIBLE);
                user.setuHead(1);

                baldChoco.setVisibility(View.INVISIBLE);
                blondeGirl.setVisibility(View.INVISIBLE);
                chocoHair.setVisibility(View.INVISIBLE);



            }
        });

        baldChocoHeadScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                baldChoco.setVisibility(View.VISIBLE);
                user.setuHead(3);

                vanill.setVisibility(View.INVISIBLE);
                blondeGirl.setVisibility(View.INVISIBLE);
                chocoHair.setVisibility(View.INVISIBLE);
            }
        });

        blondeGirlScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                blondeGirl.setVisibility(View.VISIBLE);
                user.setuHead(2);

                vanill.setVisibility(View.INVISIBLE);
                baldChoco.setVisibility(View.INVISIBLE);
                chocoHair.setVisibility(View.INVISIBLE);
            }
        });

        chocoHairScroll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                chocoHair.setVisibility(View.VISIBLE);
                user.setuHead(4);

                vanill.setVisibility(View.INVISIBLE);
                baldChoco.setVisibility(View.INVISIBLE);
                blondeGirl.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void Play() {
        if (testSound1 == null) {

            // instaniate the MediaPlayer object
            testSound1 = MediaPlayer.create(this, R.raw.jermal_dearly_beloved);
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

    public void onStart(){
        super.onStart();
    }
}


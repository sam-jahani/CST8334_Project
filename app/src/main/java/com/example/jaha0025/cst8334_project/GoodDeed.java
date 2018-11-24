package com.example.jaha0025.cst8334_project;

/*
*************************************
       PLEASE READ BELOW
**************************************
Functions of this Screen
******************************
 This is the GoodDeed Screen ( There will be 52 versions of this Screen each with a seperated good deed)
    -> User can request the audio version of the good deed
    -> User can submit pictures / text of the Good Deed
    -> User can check it off as done
    -> Application will display points/coins for successfully submitted Good Deed
    -> User can go back to CascadingGoodDeeds
    -> User can go to the Shop Screen
    -> Go Back to the main menu screen
Components
************
-> Speech to Text
-> Visuals
-> Audio
-> Database Connection
    ->  Storing Audio, Text , and Visuals to the Database
Next Screen
************
->MainMenu (User Clicks Back)
->CascadingGoodDeed (The selected Good Deed)
-> Shop
 */

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;



public class GoodDeed extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 50;
    ImageView imageViewUpload;
    TextView textView, textView2, textView3;
    Cursor cursor;
    Cursor cursor_userAct;
    ActOfKindness act;
    MediaPlayer testSound2;
    EditText editText;
    int num;
    String voice1;
    int aoknum;
    int uId;
    UserAct userAct;
    ActDbAdapter adapter;

    ImageView speechBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deed);
        MyApplication app = (MyApplication) getApplication();
        uId = app.getUserId();
        // Play();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //sanmac
        adapter = new ActDbAdapter(this);
        adapter.open();

        cursor = adapter.getActs();
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        editText = findViewById(R.id.editText2);
        textView2.setTextColor(Color.BLACK);
        textView3.setTextColor(Color.BLACK);

        textView3.setTextSize(20);

        String id = getIntent().getExtras().getString("ID");
        if(cursor.moveToFirst()){
            cursor.move(Integer.parseInt(id));
            act = ActDbAdapter.getActFromCursor(cursor);
            cursor_userAct = adapter.getUserAct(uId, act.aId);

            textView.setText(act.aDescription);
            textView2.setText(act.aQuestion);
            textView3.setText(act.aTitle);
            if(cursor_userAct.moveToFirst())
            {
                userAct = ActDbAdapter.getUserActFromCursor(cursor_userAct);
                editText.setText(userAct.getAnswer());
            }

        }
        imageViewUpload= findViewById(R.id.imageView2);

        imageViewUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        ImageView rightArrow = findViewById(R.id.rightArrow);

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(cursor.moveToNext()){
                    act = ActDbAdapter.getActFromCursor(cursor);
                    cursor_userAct = adapter.getUserAct(uId, act.aId);
                    textView.setText(act.aDescription);
                    textView2.setText(act.aQuestion);
                    textView3.setText(act.aTitle);
                    if(cursor_userAct.moveToFirst())
                    {
                        userAct = ActDbAdapter.getUserActFromCursor(cursor_userAct);
                        editText.setText(userAct.getAnswer());
                    }
                }
            }
        });

        ImageView leftArrow = findViewById(R.id.leftArrow);
        leftArrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cursor.moveToPrevious()){
                    act = ActDbAdapter.getActFromCursor(cursor);
                    cursor_userAct = adapter.getUserAct(uId, act.aId);
                    textView.setText(act.aDescription);
                    textView2.setText(act.aQuestion);
                    textView3.setText(act.aTitle);
                    if(cursor_userAct.moveToFirst())
                    {
                        userAct = ActDbAdapter.getUserActFromCursor(cursor_userAct);
                        editText.setText(userAct.getAnswer());
                    }
                }
            }
        });

        ImageView homeButton = findViewById(R.id.homeButton);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(GoodDeed.this, MainMenu.class);
                startActivity(k);
            }
        });

        ImageView profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent k = new Intent(GoodDeed.this, UserProfile.class);
                startActivity(k);
            }
        });

        ImageView exitBut = findViewById(R.id.exit);

        exitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(GoodDeed.this, listofDeeds.class);
                startActivity(k);
            }
        });


        ImageView doneBut = findViewById(R.id.doneBut);
        doneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = editText.getText().toString();
                userAct.setAnswer(answer);
                adapter.updateUserAct(userAct);
                Toast.makeText(GoodDeed.this,"Congratultions you have completed this Act of Kindness.", Toast.LENGTH_LONG).show();
                Intent k = new Intent(GoodDeed.this, listofDeeds.class);
                startActivity(k);
            }
        });

        ImageView microphone = findViewById(R.id.microphone);

        microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                try{
                    startActivityForResult(intent,200);
                }catch (ActivityNotFoundException a){
                    Toast.makeText(getApplicationContext(),"Intent problem", Toast.LENGTH_SHORT).show();
                }

                // editText.setText(result(0));
            }
        });

        speechBut = findViewById(R.id.speechBut);
        String Aokid = getIntent().getExtras().getString("ID");
        final int intAOK = Integer.parseInt(Aokid);

        speechBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerOfSound(intAOK);
            } // END onClick()
        }); // END buttonHello

        // aoknum =

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case (10): {
                // do this if request code is 10.
                if (requestCode == 10 && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageViewUpload.setImageBitmap(imageBitmap);
                }
            }
            break;

            case (11):
            {

                if (requestCode==11 &&resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                }
            }
            break;
        }





    }



    public void onStop(){

        testSound2.release();
        super.onStop();

    }

    public void Play(){

        String id = getIntent().getExtras().getString("ID");
        if(testSound2 == null){

            // if(cursor.move(0)){

            // instaniate the MediaPlayer object
            testSound2 = MediaPlayer.create(this,R.raw.aok_make_your_own);
            //starting the recording
            testSound2.start();
            // when the recording is finished release the MediaPlayer

            testSound2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //this code actually releases the player

                    testSound2.start();



                }
            });






        }


//        public void getSpeechInput(View view){
//
//            Intent intent = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//
//            startActivityForResult(intent,10);
//
//        }
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//
//
//            switch (requestCode) {
//                case 10:
//
//                    if (requestCode == RESULT_OK && data != null) {
//                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                        editText.setText(result.get(0));
//                    }
//                    break;
//            }
//
//        }

    }

    protected void managerOfSound(int aoknum) {
        //String id = getIntent().getExtras().getString("ID");

        //aoknum = Integer.parseInt(id);

        if (testSound2!=null) {
            testSound2.reset();
            testSound2.release();
        }
        switch (aoknum){
            case 0:
                testSound2 = MediaPlayer.create(this, R.raw.aok_make_your_own);
                break;

            case 1:
                testSound2 = MediaPlayer.create(this, R.raw.aok2_charity_donation_jar);
                break;

            case 2:
                testSound2 = MediaPlayer.create(this, R.raw.aok_3);
                break;
            case 3:
                testSound2 = MediaPlayer.create(this, R.raw.aok_4);
                break;
            case 4:
                testSound2 = MediaPlayer.create(this, R.raw.aok_5);
                break;
            case 5:
                testSound2 = MediaPlayer.create(this, R.raw.aok_6);
                break;
            case 6:
                testSound2 = MediaPlayer.create(this, R.raw.aok_7);
                break;
            case 7:
                testSound2 = MediaPlayer.create(this, R.raw.aok_8);
                break;
            case 8:
                testSound2 = MediaPlayer.create(this, R.raw.aok_9);
                break;
            case 9:
                testSound2 = MediaPlayer.create(this, R.raw.aok_10);
                break;
            default:
                testSound2 = MediaPlayer.create(this, R.raw.recording);
        }


//
//        if (aoknum==0)
//            testSound2 = MediaPlayer.create(this, R.raw.aok_make_your_own);
//        else if (aoknum == 1)
//            testSound2 = MediaPlayer.create(this, R.raw.aok2_charity_donation_jar);
//        else
//            testSound2 = MediaPlayer.create(this, R.raw.recording);
        testSound2.start();
        // testSound2.release();
        // super.onPause();


    }
}
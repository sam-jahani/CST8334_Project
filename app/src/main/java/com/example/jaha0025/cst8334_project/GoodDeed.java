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
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    public Cursor cursor;
    ActOfKindness act;
    MediaPlayer testSound2;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deed);

        Play();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //sanmac
        ArrayList<ActOfKindness> acts = ActOfKindness.insertActs(this);
        ActDbAdapter adapter = new ActDbAdapter(this);
        adapter.open();
        for(ActOfKindness act : acts) {
            ContentValues newValues = new ContentValues();
            newValues.put(ActDbAdapter.TITLE, act.aTitle);
            newValues.put(ActDbAdapter.DESCRIPTION, act.aDescription);
            newValues.put(ActDbAdapter.QUESTION, act.aQuestion);
            adapter.insertAct(newValues);
        }
        cursor = adapter.getActs();
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        if(cursor.moveToFirst()){
            act = ActDbAdapter.getActFromCursor(cursor);

            textView.setText(act.aDescription);
            textView2.setText(act.aQuestion);
            textView3.setText(act.aTitle);
            //textView.setText("Make a “Welcome to the Neighbourhood” basket for a family who has just moved in. Include some cookies or muffins. Make a list of fun things to do in the neighbourhood – you could include your favourite ice cream store, movie store, the library, your favourite parks, etc. You could also include a hand drawn map. Deliver the basket to the new family’s home. Bring your whole family to deliver the basket to your new neighbours - this way everyone can be introduced.");
            //  textView.setFontFeatureSettings("gothicfontbold.tff");
            //textView.setBackgroundColor(Color.YELLOW);

            //textView2.setText("How did this make the new family feel? Did you learn anything interesting about the family? Where did they move from? How old are the kids? How did it make you feel to give this basket to the new family?");
            textView2.setTextColor(Color.BLACK);


            //textView3.setText("Welcome a Neighbour");
            textView3.setTextColor(Color.BLACK);

            textView3.setTextSize(20);
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
                    textView.setText(act.aDescription);
                    textView2.setText(act.aQuestion);
                    textView3.setText(act.aTitle);
                }
            }
        });

        ImageView leftArrow = findViewById(R.id.leftArrow);


        Random r = new Random();
        int rando = r.nextInt(10) + 1;

        leftArrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cursor.moveToPrevious()){


                    act = ActDbAdapter.getActFromCursor(cursor);
                    textView.setText(act.aDescription);
                    textView2.setText(act.aQuestion);
                    textView3.setText(act.aTitle);
                }
            }
        });



        //Button button = findViewById(R.id.button);
        //button.setBackground(getDrawable(@drawable.bee.png));
        //button.setText();

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
                // Toast doneToast = Toast.makeText(getApplicationContext(),"Congratultions you have completed this Act of Kindness, I love you.", Toast.LENGTH_LONG);
                //doneToast.show();
                Toast.makeText(GoodDeed.this,"Congratultions you have completed this Act of Kindness, I love you.", Toast.LENGTH_LONG).show();
                Intent k = new Intent(GoodDeed.this, listofDeeds.class);
                startActivity(k);
            }
        });

         editText = findViewById(R.id.editText2);

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



    public void onPause(){

        testSound2.release();
        super.onPause();

    }

    public void Play(){


        if(testSound2 == null){

            // instaniate the MediaPlayer object
            testSound2 = MediaPlayer.create(this,R.raw.testsound_mainmenu);
            //starting the recording
            testSound2.start();
            // when the recording is finished release the MediaPlayer
            testSound2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //this code actually releases the player
                    //testSound.release();
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
}
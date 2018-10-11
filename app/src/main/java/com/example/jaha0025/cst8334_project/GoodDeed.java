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

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GoodDeed extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 50;
    ImageView imageViewUpload;
    TextView textView, textView2, textView3;
    public Cursor cursor;
    ActOfKindness act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deed);
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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewUpload.setImageBitmap(imageBitmap);
        }
    }
}

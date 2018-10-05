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

// Author: Philip Tharakan


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodDeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deed);

//        ImageView imageView = find

        TextView textView = findViewById(R.id.textView);
        textView.setText("Make a “Welcome to the Neighbourhood” basket for a family who has just moved in. Include some cookies or muffins. Make a list of fun things to do in the neighbourhood – you could include your favourite ice cream store, movie store, the library, your favourite parks, etc. You could also include a hand drawn map. Deliver the basket to the new family’s home. Bring your whole family to deliver the basket to your new neighbours - this way everyone can be introduced.");
      //  textView.setFontFeatureSettings("gothicfontbold.tff");
        //textView.setBackgroundColor(Color.YELLOW);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("How did this make the new family feel? Did you learn anything interesting about the family? Where did they move from? How old are the kids? How did it make you feel to give this basket to the new family?");
        textView2.setTextColor(Color.BLACK);

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText("Welcome a Neighbour");
        textView3.setTextColor(Color.BLACK);

        textView3.setTextSize(20);


        //Button button = findViewById(R.id.button);
        //button.setBackground(getDrawable(@drawable.bee.png));
        //button.setText();


    }
}

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




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GoodDeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deed);



    }
}

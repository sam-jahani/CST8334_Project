package com.example.jaha0025.cst8334_project;
//TEST comments by PHILIP
//TEST comments by ZEEL
//Another test push

/*

*************************************
       PLEASE READ BELOW
**************************************
Functions of this Screen
******************************
screen where the user can swipe through the cards with the good deeds
when they select one that it will go to the actual good deed screen(GoodDeed)

    -> User can select a Good Deed from the list view
    -> Go Back to the main menu screen

Components
************
-> ListView ( Scrollable list with all the good deeds)
-> Visuals
-> Audio
-> Database Connection


Next Screen
************
->MainMenu (User Clicks Back)
->GoodDeed (The selected Good Deeed)




 */





import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CascadingGoodDeeds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cascading_good_deeds);
    }
}

package com.example.jaha0025.cst8334_project;


/*

**************************************
       PLEASE READ BELOW
**************************************


 Functions of this Screen
 *************************
-> User enters and can decide to do the following
        -> Profile
        -> Cascading Good Deeds
        -> Avatar
        -> Shop
        -> Exit

  Components
 ************
-> Audio (Backround music)
-> Visuals


 Next Screen
 ***************
-> LoginPage  (If user clicks exit )
-> CascadingGoodDeed
-> UserProile
-> Shop

 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    ImageView login_button;
    ImageView home_button;
    ImageView yellow_profile;
    ImageView purple_acts;
    ImageView speechbaloon;
    ImageView  add;
    MediaPlayer testSound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Play();

        // insantiating images and buttons
        login_button = findViewById(R.id.main_loginBtn);
        login_button.setBackgroundColor(Color.TRANSPARENT);
        home_button = findViewById(R.id.imageView5);
        yellow_profile = findViewById(R.id.imageView7);
        purple_acts = findViewById(R.id.imageView8);
        speechbaloon = findViewById(R.id.imageView6);
      //  add = findViewById(R.id.add);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainMenu.this, LoginPage.class);
                startActivity(myintent);
            }
        });
        // on click listener to the home button (furthest left on the top)
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Intent myintent = new Intent(MainMenu.this, GoodDeed.class);
//                startActivity(myintent);


                AlertDialog alertDialog = new AlertDialog.Builder(MainMenu.this).create();
                alertDialog.setTitle("Maybe you clicked the wrong button ?");
                alertDialog.setMessage("You are already on the main menu screen");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
//                                Intent myintent = new Intent(MainMenu.this, GoodDeed.class);
//                                startActivity(myintent);

                                //dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });




        yellow_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(MainMenu.this).create();
                alertDialog.setTitle("This button will take you to the profile screen");
                alertDialog.setMessage("Do you want to go to the profile screen?");


                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent myintent = new Intent(MainMenu.this, UserProfile.class);
                                    startActivityForResult(myintent,50);

                                    dialog.dismiss();

                            }
                        });
                alertDialog.show();

            }
        });

/*

The following is code for when a user presses on the Random acts of kindness button ( Rightmost button at the top of the screen).
When the imaged is clicked an alert dialog box pops up asking the user if they want to go to the Random Acts Screen?
The test audio is also played


 */

        purple_acts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(MainMenu.this).create();


                alertDialog.setTitle("This button will take you to the Random Acts of Kindness to perfrom");
                alertDialog.setMessage("Do you want to go to the screen that lists the random acts of kindess?");

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent myintent = new Intent(MainMenu.this, listofDeeds.class);
                                startActivityForResult(myintent,50);

                                dialog.dismiss();
                            }
                        });



                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        });


                alertDialog.show();

            }
        });



//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//              AlertDialog alertDialog = new AlertDialog.Builder(MainMenu.this).create();
//                alertDialog.setTitle("This button will take the user to a random act of kindness that is selected from an SQL statement");
//                alertDialog.setMessage("Will you do it?");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                               //Play();
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
//
//            }
//        });




    }//onCreate




    /*
    This method is for playing the sound file recording which is supposed to say  " Welcome Back!  Try this random act of kindness"

     */
    public void Play(){

        if(testSound == null){

            // instaniate the MediaPlayer object
            testSound = MediaPlayer.create(this,R.raw.testsound_mainmenu);
            //starting the recording
            testSound.start();
            // when the recording is finished release the MediaPlayer
            testSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //this code actually releases the player
                    testSound.start();
                }
            });
        }



    }





    /*

    This block of code is to ensure that the sound player is released when leaving this page
    in order to ensure that resources are not locked up

     */

    @Override
    protected void onPause(){
//        testSound.release();
        super.onPause();
    }

    @Override
    public void onStop(){

        testSound.release();
        super.onStop();
    }






}

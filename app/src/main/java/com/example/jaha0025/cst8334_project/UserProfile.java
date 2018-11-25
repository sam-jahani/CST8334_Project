package com.example.jaha0025.cst8334_project;


/*


**************************************
       PLEASE READ BELOW
**************************************


 Functions of this Screen
 *************************
-> User modifies profile data
-> Name, Age,  Occupation, Hobbies



  Components
 ************
-> Audio (Backround music)
-> Visuals
-> Database Component


 Next Screen
 ***************
-> MainMenu






 */


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "UserProfile";

    MediaPlayer proTestSound;

    ImageView proAvatarImg;
    ImageView proBadgesImg;
    ImageView proBeeImg;

    TextView proNameTabTxt;
    TextView proLevelTxt;

    TextView proNameTxt;
    EditText proNameEdit;

    TextView proAgeTxt;
    EditText proAgeEdit;

    TextView proGradeTxt;
    EditText proGradeEdit;

    TextView proAboutTxt;
    EditText proAboutEdit;

    ImageButton proEditBtn;
    ImageButton proSaveBtn;

    ProgressBar proProgress;
    TextView proProgressTxt;

    ImageButton proCustomBtn;
    ImageButton proHomeBtn;
    ImageButton proProfileBtn;
    ImageButton proActsBtn;

    User user;
    ActDbAdapter adapter;
    public Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Log.i(ACTIVITY_NAME, "In OnCreate()");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        proBeeImg = (ImageView) findViewById(R.id.proImgBee);
        proAvatarImg = (ImageView) findViewById(R.id.proImgAvatar);
        proBadgesImg = (ImageView) findViewById(R.id.proImgBadges);
        proNameTabTxt = (TextView) findViewById(R.id.proTxtNameTab);
        proLevelTxt = (TextView) findViewById(R.id.proTxtLevel);

        proNameTxt = (TextView) findViewById(R.id.proTxtName);
        proNameEdit = (EditText) findViewById(R.id.proEdtName);
        proNameEdit.setEnabled(false);

        proAgeTxt = (TextView) findViewById(R.id.proTxtAge);
        proAgeEdit = (EditText) findViewById(R.id.proEdtAge);
        proAgeEdit.setEnabled(false);

        proGradeTxt = (TextView) findViewById(R.id.proTxtGrade);
        proGradeEdit = (EditText) findViewById(R.id.proEdtGrade);
        proGradeEdit.setEnabled(false);

        proAboutTxt = (TextView) findViewById(R.id.proTxtAbout);
        proAboutEdit = (EditText) findViewById(R.id.proEdtAbout);
        proAboutEdit.setEnabled(false);

        MyApplication app = (MyApplication) getApplication();
        adapter = new ActDbAdapter(this);
        adapter.open();
        cursor = adapter.getUser(app.getUserId());

        if (cursor.moveToFirst()) {
            user = adapter.getUserFromCursor(cursor);
        }

        Log.i("usertest", String.valueOf(user.getuId()));

        proNameEdit.setText(user.getuName());
        proAgeEdit.setText(String.valueOf(user.getuAge()));
        proGradeEdit.setText(user.getuGrade());
        proAboutEdit.setText(user.getuAbout());

        Play();

        proEditBtn = (ImageButton) findViewById(R.id.proBtnEdit);
        proEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proNameEdit.setEnabled(true);
                proAgeEdit.setEnabled(true);
                proGradeEdit.setEnabled(true);
                proAboutEdit.setEnabled(true);
            }
        });

        proSaveBtn = (ImageButton) findViewById(R.id.proBtnSave);
        proSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proNameEdit.setEnabled(false);
                proAgeEdit.setEnabled(false);
                proGradeEdit.setEnabled(false);
                proAboutEdit.setEnabled(false);

                user.setuName(proNameEdit.getText().toString());
                user.setuAge(Integer.parseInt(proAgeEdit.getText().toString()));
                user.setuGrade(proGradeEdit.getText().toString());
                user.setuAbout(proAboutEdit.getText().toString());
                adapter.updateUser(user);

            }
        });

        switch (user.getuHead())
        {
            case 1:

                proAvatarImg.setImageDrawable(getDrawable(R.drawable.ninonormalhead));
                break;

            case 2:

                proAvatarImg.setImageDrawable(getDrawable(R.drawable.blondegirlhead));
                break;

            case 3:

                proAvatarImg.setImageDrawable(getDrawable(R.drawable.bald_choco));
                break;

            case 4:

                proAvatarImg.setImageDrawable(getDrawable(R.drawable.chocohairhead));
                break;

            default:
                proAvatarImg.setImageDrawable(getDrawable(R.drawable.ninonormalhead));
        }

        Cursor pCursor = adapter.getCompletedUserActs(app.getUserId());
        int completedActs = pCursor.getCount();

        proLevelTxt.setText("Level " + String.valueOf(completedActs / 10 + 1));
        if (completedActs / 10 + 1 == 6){
            proLevelTxt.setText("Legendary");
        }

        proProgress = (ProgressBar) findViewById(R.id.proProgressBar);
        Log.i("Completed Task", String.valueOf(completedActs));
        proProgress.setProgress(completedActs % 10);

        proProgressTxt = (TextView) findViewById(R.id.proTxtProgress);
        proProgressTxt.setText(completedActs % 10 + "/" + proProgress.getMax());

        switch (completedActs / 10 + 1){

            case 1:
                proBadgesImg.setImageDrawable(getDrawable(R.drawable.zeel_badges_grey));
                break;

            case 2:
                proBadgesImg.setImageDrawable(getDrawable(R.drawable.zeel_badges_green));
                break;

            case 3:
                proBadgesImg.setImageDrawable(getDrawable(R.drawable.zeel_badges_blue));
                break;

            case 4:
                proBadgesImg.setImageDrawable(getDrawable(R.drawable.zeel_badges_red));
                break;

            case 5:
                proBadgesImg.setImageDrawable(getDrawable(R.drawable.zeel_badges_purple));
                break;

            case 6:
                proBadgesImg.setImageDrawable(getDrawable(R.drawable.zeel_badges_legendary));
                break;

        }
        proCustomBtn = (ImageButton) findViewById(R.id.proBtnCustom);
        proCustomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proIntentCustom = new Intent(UserProfile.this, UserCustomize.class);
                startActivityForResult(proIntentCustom, 50);
            }
        });

        proHomeBtn = (ImageButton) findViewById(R.id.proBtnHome);
        proHomeBtn.setBackgroundColor(Color.TRANSPARENT);
        proHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proIntentHome = new Intent(UserProfile.this, MainMenu.class);
                startActivityForResult(proIntentHome, 50);
            }
        });

        proProfileBtn = (ImageButton) findViewById(R.id.proBtnProfile);
        proProfileBtn.setBackgroundColor(Color.TRANSPARENT);

        proActsBtn = (ImageButton) findViewById(R.id.proBtnActs);
        proActsBtn.setBackgroundColor(Color.TRANSPARENT);
        proActsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proIntentActs = new Intent(UserProfile.this, listofDeeds.class);
                startActivityForResult(proIntentActs, 50);
            }
        });

    }

    public void Play() {

        if (proTestSound == null) {

            // instaniate the MediaPlayer object
            proTestSound = MediaPlayer.create(this, R.raw.zeel_profile_background_music);
            //starting the recording
            proTestSound.start();
            // when the recording is finished release the MediaPlayer
            proTestSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //this code actually releases the player
                    proTestSound.start();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In OnResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In OnStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In OnPause()");
    }

    @Override
    protected void onStop() {
        proTestSound.release();
        super.onStop();
        Log.i(ACTIVITY_NAME, "In OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In OnDestroy()");
    }

}

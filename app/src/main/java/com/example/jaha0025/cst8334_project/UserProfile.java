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
    protected int proProgressStatus = 0;

    ImageButton proCustomBtn;
    ImageButton proHomeBtn;
    ImageButton proProfileBtn;
    ImageButton proActsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Log.i(ACTIVITY_NAME,"In OnCreate()");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        proBeeImg = (ImageView) findViewById(R.id.proImgBee);

        proNameTabTxt = (TextView) findViewById(R.id.proTxtNameTab);
        proLevelTxt = (TextView) findViewById(R.id.proTxtLevel);

        proNameTxt = (TextView) findViewById(R.id.proTxtName);
        proNameEdit = (EditText) findViewById(R.id.proEdtName);
        proNameEdit.setEnabled(false);
//        proNameEdit.setOnEditorActionListener(new DoneOnEditorActionListener());

        proAgeTxt = (TextView) findViewById(R.id.proTxtAge);
        proAgeEdit = (EditText) findViewById(R.id.proEdtAge);
        proAgeEdit.setEnabled(false);

        proGradeTxt = (TextView) findViewById(R.id.proTxtGrade);
        proGradeEdit = (EditText) findViewById(R.id.proEdtGrade);
        proGradeEdit.setEnabled(false);

        proAboutTxt = (TextView) findViewById(R.id.proTxtAbout);
        proAboutEdit = (EditText) findViewById(R.id.proEdtAbout);
        proAboutEdit.setEnabled(false);

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

           }
       });

        proProgress = (ProgressBar) findViewById(R.id.proProgressBar);

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
                startActivityForResult(proIntentHome,50);
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
                startActivityForResult(proIntentActs,50);
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
//                    proTestSound.release();
                }
            });
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In OnResume()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"In OnStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In OnPause()");
    }
    @Override
    protected void onStop(){
        proTestSound.release();
        super.onStop();
        Log.i(ACTIVITY_NAME,"In OnStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In OnDestroy()");
    }

}

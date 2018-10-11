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
import android.widget.ProgressBar;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "UserProfile";

    TextView proNameTabTxt;
    TextView proLevelTxt;

    TextView proNameTxt;
    EditText proNameEdit;
    ImageButton proEditNameBtn;

    TextView proAgeTxt;
    EditText proAgeEdit;
    ImageButton proEditAgeBtn;

    TextView proGradeTxt;
    EditText proGradeEdit;
    ImageButton proEditGradeBtn;

    TextView proAboutTxt;
    EditText proAboutEdit;
    ImageButton proEditAboutBtn;

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


        proNameTabTxt = (TextView) findViewById(R.id.proTxtNameTab);
        proLevelTxt = (TextView) findViewById(R.id.proTxtLevel);

        proNameTxt = (TextView) findViewById(R.id.proTxtName);
        proNameEdit = (EditText) findViewById(R.id.proEdtName);
//        proNameEdit.setOnEditorActionListener(new DoneOnEditorActionListener());
        proNameEdit.setEnabled(false);
        proNameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    proNameEdit.setEnabled(false);
                }
            }
        });
        proEditNameBtn = (ImageButton) findViewById(R.id.proBtnEditName);
        proEditNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proNameEdit.setEnabled(true);
            }
        });

        proAgeTxt = (TextView) findViewById(R.id.proTxtAge);
        proAgeEdit = (EditText) findViewById(R.id.proEdtAge);
        proAgeEdit.setEnabled(false);
        proAgeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    proAgeEdit.setEnabled(false);
                }
            }
        });
        proEditAgeBtn = (ImageButton) findViewById(R.id.proBtnEditAge);
        proEditAgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                proAgeEdit.setEnabled(true);
            }
        });

        proGradeTxt = (TextView) findViewById(R.id.proTxtGrade);
        proGradeEdit = (EditText) findViewById(R.id.proEdtGrade);
        proGradeEdit.setEnabled(false);
        proGradeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    proGradeEdit.setEnabled(false);
                }
            }
        });
        proEditGradeBtn = (ImageButton) findViewById(R.id.proBtnEditGrade);
        proEditGradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proGradeEdit.setEnabled(true);
            }
        });

        proAboutTxt = (TextView) findViewById(R.id.proTxtAbout);
        proAboutEdit = (EditText) findViewById(R.id.proEdtAbout);
        proAboutEdit.setEnabled(false);
        proAboutEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    proAboutEdit.setEnabled(false);
                }
            }
        });
        proEditAboutBtn = (ImageButton) findViewById(R.id.proBtnEditAbout);
        proEditAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proAboutEdit.setEnabled(true);
            }
        });

        proProgress = (ProgressBar) findViewById(R.id.proProgressBar);

        proCustomBtn = (ImageButton) findViewById(R.id.proBtnCustom);
        proCustomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent proIntentCustom = new Intent(UserProfile.this, UserCustomize.class);
//                startActivityForResult(proIntentCustom.this, 50);
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
                Intent proIntentActs = new Intent(UserProfile.this, GoodDeed.class);
                startActivityForResult(proIntentActs,50);
            }
        });

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
        super.onStop();
        Log.i(ACTIVITY_NAME,"In OnStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In OnDestroy()");
    }

}

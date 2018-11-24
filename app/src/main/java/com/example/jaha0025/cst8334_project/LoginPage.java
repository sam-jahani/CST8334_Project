package com.example.jaha0025.cst8334_project;


/*

**************************************
       PLEASE READ BELOW
**************************************
Functions of this Screen

-> User enters the  application hears enters in their user name and password
-> Application verifies the username and password ( Database Communication)
-> Allows the user to proceed to Main menu

Components

-> Database Connection
-> Visuals
-> Audio


Next Screen
->MainMenu




 */


import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginPage";

    final Context logContext = this;

    ImageView logTitleImg;

    TextView logLoginTxt;
    EditText logLoginEdt;

    TextView logPassTxt;
    EditText logPassEdt;

    Button logLoginBtn;
    Button logNewBtn;

    TextView logNewLoginTxt;
    EditText logNewLoginEdt;

    TextView logNewPassTxt;
    EditText logNewPassEdt;

    Button logBackBtn;
    Button logSubmitBtn;
    ActDbAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //declaring adapter
        adapter = new ActDbAdapter(this);
        adapter.open();

        logTitleImg = (ImageView) findViewById(R.id.logImgTitle);

        logLoginTxt = (TextView) findViewById(R.id.logTxtLogin);
        logLoginEdt = (EditText) findViewById(R.id.logEdtLogin);

        logPassTxt = (TextView) findViewById(R.id.logTxtPass);
        logPassEdt = (EditText) findViewById(R.id.logEdtPass);

        logLoginBtn = (Button) findViewById(R.id.logBtnLogin);
        logLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login =  logLoginEdt.getText().toString();
                String password = logPassEdt.getText().toString();
                int uId;
                Cursor cursor =  adapter.getUser(login, password);
                if(cursor.moveToFirst()) {
                    uId = ActDbAdapter.getUserFromCursor(cursor).getuId();
                    MyApplication app = (MyApplication) getApplication();
                    app.setUserId(uId);
                    Intent logIntentLogin = new Intent(LoginPage.this, MainMenu.class);
                    startActivity(logIntentLogin);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Login or password does not match",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        logNewBtn = (Button) findViewById(R.id.logBtnNew);
        logNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final Dialog logNewDialog = new Dialog(logContext);
//                logNewDialog.setContentView(R.layout.login_dialog);
//
//                logNewLoginTxt = (TextView) findViewById(R.id.logTxtNewLogin);
//                logNewLoginEdt = (EditText) findViewById(R.id.logEdtNewLogin);
//
//                logNewPassTxt = (TextView) findViewById(R.id.logTxtNewPass);
//                logNewPassEdt = (EditText) findViewById(R.id.logEdtNewPass);
//
//                logBackBtn = (Button) findViewById(R.id.logBtnBack);
//                logBackBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        logNewDialog.dismiss();
//                    }
//                });
//
//                logSubmitBtn = (Button) findViewById(R.id.logBtnSubmit);
//                logSubmitBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        final Dialog logConfirmDialog = new Dialog(LoginPage.this)
//                    }
//                });

                AlertDialog alertDialog = new AlertDialog.Builder(logContext).create();
                alertDialog.setTitle("Create New Account ");

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                newUserCreation();
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

    }

    protected void newUserCreation(){
        final AlertDialog.Builder logBuild = new AlertDialog.Builder(LoginPage.this);
        final LayoutInflater inflater1 = LoginPage.this.getLayoutInflater();
        View d = inflater1.inflate(R.layout.login_dialog, null);
        logNewLoginTxt = (TextView) d.findViewById(R.id.logTxtNewLogin);
        logNewLoginEdt = (EditText) d.findViewById(R.id.logEdtNewLogin);

        logNewPassTxt = (TextView) d.findViewById(R.id.logTxtNewPass);
        logNewPassEdt = (EditText) d.findViewById(R.id.logEdtNewPass);

        logBackBtn = (Button) d.findViewById(R.id.logBtnBack);
        logSubmitBtn = (Button) d.findViewById(R.id.logBtnSubmit);

        Log.d("LoginPage","newUserCreation()");

        logBuild.setView(d);
        logBuild.show();

        final SharedPreferences logsharedPreference = getSharedPreferences("Login",MODE_PRIVATE);
        logNewLoginEdt.setText(logsharedPreference.getString("Default Login",""));
        logNewPassEdt.setText(logsharedPreference.getString("Password",""));


        logSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String login =  logNewLoginEdt.getText().toString();
               String password = logNewPassEdt.getText().toString();
               int uId;

               ContentValues newValues = new ContentValues();
               newValues.put(ActDbAdapter.U_LOGIN, login);
               newValues.put(ActDbAdapter.U_PASS, password);

               if (adapter.insertUser(newValues) == -1)
               {
                   Toast toast = Toast.makeText(getApplicationContext(), "Login already exists",Toast.LENGTH_LONG);
                   toast.show();
               }
               else
               {
                   Cursor cursor = adapter.getUser(login, password);
                   if(cursor.moveToFirst()) {
                       User user = ActDbAdapter.getUserFromCursor(cursor);
                       cursor = adapter.getActs();
                       ActOfKindness act;
                       if (cursor.moveToFirst()) {
                           do {
                               act = ActDbAdapter.getActFromCursor(cursor);
                               adapter.insertUserAct(user, act);

                           } while (cursor.moveToNext());
                       }
                       Toast toast = Toast.makeText(getApplicationContext(),
                               "User Created", Toast.LENGTH_LONG);
                       toast.show();
                   }
                   else {
                       Toast toast = Toast.makeText(getApplicationContext(),
                               "User not Created", Toast.LENGTH_LONG);
                       toast.show();
                   }
                   /*Cursor cursor = adapter.getUsers();
                   if (cursor.moveToFirst()) {
                       do {

                           User user1 = ActDbAdapter.getUserFromCursor(cursor);
                           Log.i("users", user1.getuLogin());

                       } while (cursor.moveToNext());
                   }*/

                   Intent logIntentDialog = new Intent(LoginPage.this, LoginPage.class);
                   startActivityForResult(logIntentDialog, 50);
               }
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In OnDestroy()");
    }
}

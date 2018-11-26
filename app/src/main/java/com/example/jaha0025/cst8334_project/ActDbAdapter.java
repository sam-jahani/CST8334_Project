package com.example.jaha0025.cst8334_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ActDbAdapter {
    private final static String DATABASE_NAME = "ACTSOFKINDNESS.db";
    private final static String ACT_TABLE = "ACT_TABLE";
    private final static String USER_TABLE = "USER_TABLE";
    private final static String USERACT_TABLE = "USERACT_TABLE";
    private final static int DATABASE_VERSION = 9;
    private static Context context;
    public static String TAG = ActDbAdapter.class.getSimpleName();

    private DatabaseHelper myDBHelper;
    SQLiteDatabase myDB;
    public static final String KEY_ROWID_ACT = "AID";
    public static final String TITLE = "TITLE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String QUESTION = "QUESTION";
    public static final String[] ACT_FIELDS = new String[] {
            KEY_ROWID_ACT,
            TITLE,
            DESCRIPTION,
            QUESTION
    };
    public static final String KEY_ROWID_USER = "UID";
    public static final String U_LOGIN = "ULOGIN";
    public static final String U_PASS = "UPASS";
    public static final String U_NAME = "UNAME";
    public static final String U_AGE = "UAGE";
    public static final String U_GRADE = "UGRADE";
    public static final String U_ABOUT = "UABOUT";
    public static final String U_HEAD = "UHEAD";
    public static final String U_SHIRT = "USHIRT";
    public static final String U_PANTS = "UPANTS";
    public static final String[] USER_FIELDS = new String[] {
            KEY_ROWID_USER,
            U_LOGIN,
            U_PASS,
            U_NAME,
            U_AGE,
            U_GRADE,
            U_ABOUT,
            U_HEAD,
            U_SHIRT,
            U_PANTS
    };
    public static final String KEY_ROWID_USERACT = "UAID";
    public static final String DRAWFILE = "DRAWFILE";
    public static final String ANSWER = "ANSWER";
    public static final String COMPLETE = "COMPLETE";
    public static final String[] USERACT_FIELDS = new String[] {
            KEY_ROWID_USERACT,
            KEY_ROWID_USER,
            KEY_ROWID_ACT,
            DRAWFILE,
            ANSWER,
            COMPLETE
    };

    private static final String CREATE_TABLE_ACT =
            "create table " + ACT_TABLE + "(" +
                    KEY_ROWID_ACT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TITLE + " text," +
                    DESCRIPTION + " text," +
                    QUESTION + " text);";
    private static final String CREATE_TABLE_USER =
            "create table " + USER_TABLE + "(" +
                    KEY_ROWID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    U_LOGIN + " text," +
                    U_PASS + " text," +
                    U_NAME + " text," +
                    U_AGE + " INTEGER," +
                    U_GRADE + " text," +
                    U_ABOUT + " text," +
                    U_HEAD + " INTEGER," +
                    U_SHIRT + " INTEGER," +
                    U_PANTS + " INTEGER, CONSTRAINT login_unique UNIQUE (" +
                    U_LOGIN + " ));";

    private static final String CREATE_TABLE_USERACT =
            "create table " + USERACT_TABLE + "(" +
                    KEY_ROWID_USERACT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_ROWID_USER + " INTEGER," +
                    KEY_ROWID_ACT + " INTEGER," +
                    DRAWFILE + " text," +
                    ANSWER + " text," +
                    COMPLETE + " INTEGER);";
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE_ACT);
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_USERACT);
            ArrayList<ActOfKindness> acts = ActOfKindness.insertActs(context);
            for(ActOfKindness act : acts) {
                ContentValues newValues = new ContentValues();
                newValues.put(ActDbAdapter.TITLE, act.aTitle);
                newValues.put(ActDbAdapter.DESCRIPTION, act.aDescription);
                newValues.put(ActDbAdapter.QUESTION, act.aQuestion);
                db.insertWithOnConflict(ACT_TABLE, null,
                        newValues, SQLiteDatabase.CONFLICT_IGNORE);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            Log.w(TAG, "Upgrading database from version " + oldversion + " to "
                    + newversion + ", which will erase all old data");
            db.execSQL("DROP TABLE IF EXISTS " + ACT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + USERACT_TABLE);
            onCreate(db);
        }

    }
    public ActDbAdapter(Context context) {
        this.context = context;
    }

    public ActDbAdapter open()  throws SQLException {
        myDBHelper = new DatabaseHelper(context);
        myDB = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (myDBHelper != null)
        {
            myDBHelper.close();
        }
    }

    public long insertAct(ContentValues initialValues) {
        return myDB.insertWithOnConflict(ACT_TABLE, null,
                initialValues, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public long insertUserAct(User user, ActOfKindness act) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID_USER, user.getuId());
        initialValues.put(KEY_ROWID_ACT, act.aId);
        initialValues.put(COMPLETE, 0);
        return myDB.insertWithOnConflict(USERACT_TABLE, null,
            initialValues, SQLiteDatabase.CONFLICT_IGNORE);

    }

    public long insertUser(ContentValues initialValues) {
        return myDB.insertWithOnConflict(USER_TABLE, null,
                initialValues, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public Cursor getActs() {
        return myDB.query(ACT_TABLE, ACT_FIELDS, null, null, null,
                            null, null);
    }

    public Cursor getUsers() {
        return myDB.query(USER_TABLE, USER_FIELDS, null, null, null,
                null, null);
    }

    public Cursor getUserActs(int uId) {
        String SELECT_USERACTS =
                "SELECT  * FROM " + USERACT_TABLE + " WHERE " + KEY_ROWID_USER
                        + " = " + uId;
        return myDB.rawQuery(SELECT_USERACTS, null);
    }
    public Cursor getUserAct(int uId, int aId) {
        String SELECT_USERACTS =
                "SELECT  * FROM " + USERACT_TABLE + " WHERE " + KEY_ROWID_USER
                        + " = " + uId + " AND " + KEY_ROWID_ACT + " = " + aId;
        return myDB.rawQuery(SELECT_USERACTS, null);
    }

    public Cursor getUser(String login, String password) {
        String SELECT_USER =
                "SELECT  * FROM " + USER_TABLE + " WHERE " + U_LOGIN
                        + " = '" + login + "' AND " + U_PASS + " = '" + password +"'";
        return myDB.rawQuery(SELECT_USER, null);
    }

    public Cursor getUser(int uId) {
        String SELECT_USER =
                "SELECT  * FROM " + USER_TABLE + " WHERE " + KEY_ROWID_USER
                        + " = " + uId;
        return myDB.rawQuery(SELECT_USER, null);
    }
    public Cursor getRemainingUserActs(int uId) {
        String SELECT_REMAIN_USERACT =
                "SELECT  * FROM " + USERACT_TABLE + " WHERE " + KEY_ROWID_USER
                + " = " + uId + " AND " + COMPLETE + " = 0";
        return myDB.rawQuery(SELECT_REMAIN_USERACT, null);
    }

    public Cursor getCompletedUserActs(int uId) {
        String SELECT_REMAIN_USERACT =
                "SELECT  * FROM " + USERACT_TABLE + " WHERE " + KEY_ROWID_USER
                        + " = " + uId + " AND " + COMPLETE + " = 1 ";
        return myDB.rawQuery(SELECT_REMAIN_USERACT, null);
    }

    public long updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(U_NAME, user.getuName());
        values.put(U_AGE, user.getuAge());
        values.put(U_GRADE, user.getuGrade());
        values.put(U_ABOUT, user.getuAbout());

        return myDB.update(USER_TABLE, values, KEY_ROWID_USER + " = ?",
                new String[] { String.valueOf(user.getuId()) });
    }
    public long updateAvatar(User user) {
        ContentValues values = new ContentValues();
        values.put(U_HEAD, user.getuHead());
        values.put(U_SHIRT, user.getuShirt());
        values.put(U_PANTS, user.getuPants());

        return myDB.update(USER_TABLE, values, KEY_ROWID_USER + " = ?",
                new String[] { String.valueOf(user.getuId()) });
    }

    public long updateUserAct(UserAct userAct) {
        ContentValues values = new ContentValues();
        values.put(DRAWFILE, userAct.getDrawFile());
        values.put(ANSWER, userAct.getAnswer());
        values.put(COMPLETE, 1);

        return myDB.update(USERACT_TABLE, values, KEY_ROWID_USER + " = ? AND " +
                KEY_ROWID_ACT + " = ?",
                new String[] { String.valueOf(userAct.getuId()), String.valueOf(userAct.getaId())});
    }

    public static ActOfKindness getActFromCursor(Cursor cursor) {
        ActOfKindness act = new ActOfKindness();
        act.aId = cursor.getInt(cursor.getColumnIndex(KEY_ROWID_ACT));
        act.aTitle = cursor.getString(cursor.getColumnIndex(TITLE));
        act.aDescription = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        act.aQuestion = cursor.getString(cursor.getColumnIndex(QUESTION));
        return act;
    }
    public static User getUserFromCursor(Cursor cursor) {
        User user = new User();
        user.uId = cursor.getInt(cursor.getColumnIndex(KEY_ROWID_USER));
        user.uLogin = cursor.getString(cursor.getColumnIndex(U_LOGIN));
        user.uPass = cursor.getString(cursor.getColumnIndex(U_PASS));
        user.uName = cursor.getString(cursor.getColumnIndex(U_NAME));
        user.uGrade = cursor.getString(cursor.getColumnIndex(U_GRADE));
        user.uAbout = cursor.getString(cursor.getColumnIndex(U_ABOUT));
        user.uAge = cursor.getInt(cursor.getColumnIndex(U_AGE));
        user.setuHead(cursor.getInt(cursor.getColumnIndex(U_HEAD)));
        user.setuShirt(cursor.getInt(cursor.getColumnIndex(U_SHIRT)));
        user.setuPants(cursor.getInt(cursor.getColumnIndex(U_PANTS)));
        return user;
    }

    public static UserAct getUserActFromCursor(Cursor cursor) {
        UserAct userAct = new UserAct(cursor.getInt(cursor.getColumnIndex(KEY_ROWID_USER)),
                cursor.getInt(cursor.getColumnIndex(KEY_ROWID_ACT)));
        userAct.setUaId(cursor.getInt(cursor.getColumnIndex(KEY_ROWID_USERACT)));
        userAct.setComplete(cursor.getInt(cursor.getColumnIndex(COMPLETE)) == 1);
        userAct.setDrawFile(cursor.getString(cursor.getColumnIndex(DRAWFILE)));
        userAct.setAnswer(cursor.getString(cursor.getColumnIndex(ANSWER)));
        return userAct;
    }
}

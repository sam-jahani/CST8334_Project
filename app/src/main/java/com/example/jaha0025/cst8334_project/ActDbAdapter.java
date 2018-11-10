package com.example.jaha0025.cst8334_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ActDbAdapter {
    private final static String DATABASE_NAME = "ACTSOFKINDNESS.db";
    private final static String ACT_TABLE = "ACT_TABLE";
    private final static int DATABASE_VERSION = 1;
    private static Context context;
    public static String TAG = ActDbAdapter.class.getSimpleName();

    private DatabaseHelper myDBHelper;
    SQLiteDatabase myDB;
    public static final String KEY_ROWID = "ID";
    public static final String TITLE = "TITLE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String QUESTION = "QUESTION";
    public static final String[] ACT_FIELDS = new String[] {
            KEY_ROWID,
            TITLE,
            DESCRIPTION,
            QUESTION
    };
    private static final String CREATE_TABLE_ACT =
            "create table " + ACT_TABLE + "(" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TITLE + " text," +
                    DESCRIPTION + " text," +
                    QUESTION + " text);";
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_ACT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            Log.w(TAG, "Upgrading database from version " + oldversion + " to "
                    + newversion + ", which will erase all old data");
            db.execSQL("DROP TABLE IF EXISTS " + ACT_TABLE);
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

    public Cursor getActs() {
        return myDB.query(ACT_TABLE, ACT_FIELDS, null, null, null,
                            null, null);
    }

    public static ActOfKindness getActFromCursor(Cursor cursor) {
        ActOfKindness act = new ActOfKindness();
        act.aId = cursor.getInt(cursor.getColumnIndex(KEY_ROWID));
        act.aTitle = cursor.getString(cursor.getColumnIndex(TITLE));
        act.aDescription = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        act.aQuestion = cursor.getString(cursor.getColumnIndex(QUESTION));
        return act;
    }

//    public Cursor viewData(){
//        SQLiteDatabase db= this.getReadableDatabase();
//        String query = "Select * from "+DB_TABLE;
//        Cursor cursor =  db.rawQuery(query, null);
//
//        return cursor;
//    }
}

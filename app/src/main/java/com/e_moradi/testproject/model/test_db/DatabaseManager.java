package com.e_moradi.testproject.model.test_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "myInfo.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tbl_person";
    private static final String dID = "id";
    private static final String dName = "name";
    private static final String dFamily = "family";


    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String cQuery = "CREATE TABLE " +
                TABLE_NAME +
                "( " + dID + " INTEGER PRIMARY KEY," +
                dName + " TEXT," +
                dFamily + " TEXT" + ");";
        sqLiteDatabase.execSQL(cQuery);


        Log.e("Essi", "Create Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPerson(Person iprs) {
        SQLiteDatabase idb = this.getReadableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(dID, iprs.pID);
        icv.put(dName, iprs.pName);
        icv.put(dFamily, iprs.pFamily);


        idb.insert(TABLE_NAME, null, icv);
        Log.e("Essi", "Insert Table ----> " + icv.toString());
        idb.close();
        Log.e("Essi", "*******InsetData in DB*****");
    }

    public Person getPerson(String gID) {
        Person gPrs = new Person();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TABLE_NAME + " WHERE  " + dID + " = " + gID;
        Cursor gCur = gdb.rawQuery(gQuery, null);
        if (gCur.moveToFirst()) {
            gPrs.pName = gCur.getString(1);
            gPrs.pFamily = gCur.getString(2);
        }
        Log.e("Essi", "Get Person Method");
        Log.e("Essi", gPrs.toString());
        Log.e("Essi", gPrs.pName);
        Log.e("Essi", gPrs.pFamily);

        return gPrs;

    }

    public void updatePerson(Person uprs) {
        SQLiteDatabase udb = this.getWritableDatabase();
        ContentValues ucv = new ContentValues();
        ucv.put(dName, uprs.pName);
        ucv.put(dFamily, uprs.pFamily);
        udb.update(TABLE_NAME, ucv, dID + " = ? ", new String[]{String.valueOf(uprs.pID)});
        Log.e("Essi", "Call Update DB");
    }


    public boolean deletePerson(Person dprs) {
        SQLiteDatabase ddb = this.getWritableDatabase();
        long dResult = ddb.delete(TABLE_NAME, dID + " =? ", new String[]{String.valueOf(dprs.pID)});

        if (dResult == 0) {
            return false;
        } else return true;
    }
    public int personCount(){
        String gQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase gdb =this.getReadableDatabase();
        Cursor gCur =gdb.rawQuery(gQuery,null);
        int cResult=gCur.getCount();
        return cResult;
    }


}

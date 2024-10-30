package com.e_moradi.testproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomerDBManger extends SQLiteOpenHelper {


    private static final String DB_NAME = "customer.db";
    private static final int DB_VERSION = 1;
    //column of DB
    private static final String TABLE_NAME = "tbl_Customer";
    private static final String dID = "id_cus";
    private static final String dCODE = "code_cus";
    private static final String dNAME = "name_cus";
    private static final String dTEL = "tel_cus";
    private static final String dMOBILE = "mobile_cus";
    private static final String dEMAIL = "email_cus";
    private static final String dADDRESS = "address_cus";
    private static final String dDESC = "desc_cus";


    public CustomerDBManger
            (@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CQuery = "CREATE TABLE IF  NOT EXISTS " +
                TABLE_NAME +
                "( " +
                dID + " INTEGER  PRIMARY KEY AUTOINCREMENT   ," +
                dCODE + " INTEGER  UNIQUE ," +
                dNAME + " TEXT ," +
                dTEL + " TEXT   ," +
                dMOBILE + " TEXT  ," +
                dEMAIL + " TEXT," +
                dADDRESS + " TEXT," +
                dDESC + " TEXT " + ");";
        sqLiteDatabase.execSQL(CQuery);
        Log.e("Essi", "Create Table: " + TABLE_NAME + "in DB");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertCustomer(Customer iCus) {

        SQLiteDatabase idb = this.getReadableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(dCODE, iCus.customerCode);
        icv.put(dNAME, iCus.customerName);
        icv.put(dTEL, iCus.customerTel);
        icv.put(dMOBILE, iCus.customerMobile);
        icv.put(dEMAIL, iCus.customerEmail);
        icv.put(dADDRESS, iCus.customerAddress);
        icv.put(dDESC, iCus.customerDesc);

        idb.insert(TABLE_NAME, null, icv);
        Log.e("Essi", "Call Insert Table");
        Log.e("Essi", "Insert Table ----> " + icv.toString());

        Log.e("Essi", icv.toString());
        Log.e("Essi", "*******InsertData in DB*****");
        idb.close();
    }

    public Customer getCustomer(String gCode,Context con ) {
        Customer gCustomer = new Customer();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + dCODE + " = " + gCode;
        Cursor gCur = gdb.rawQuery(gQuery, null);
        if (gCur.moveToFirst()) {
            gCustomer.customerCode = gCur.getString(1);
            gCustomer.customerName = gCur.getString(2);
            gCustomer.customerTel = gCur.getString(3);
            gCustomer.customerMobile = gCur.getString(4);
            gCustomer.customerEmail = gCur.getString(5);
            gCustomer.customerAddress = gCur.getString(6);
            gCustomer.customerDesc = gCur.getString(7);
        } else {
            makeToastCus(con);

        }
        gdb.close();
        gCur.close();
        return gCustomer;
    }


    public void updateCustomer(Customer uCus) {
        SQLiteDatabase udb = this.getWritableDatabase();
        ContentValues ucv = new ContentValues();
        ucv.put(dCODE, uCus.customerCode);
        ucv.put(dNAME, uCus.customerName);
        ucv.put(dTEL, uCus.customerTel);
        ucv.put(dMOBILE, uCus.customerMobile);
        ucv.put(dEMAIL, uCus.customerEmail);
        ucv.put(dADDRESS, uCus.customerAddress);
        ucv.put(dDESC, uCus.customerDesc);
        udb.update(TABLE_NAME, ucv, dCODE + " = ? ", new String[]{String.valueOf(uCus.customerCode)});

        Log.e("Essi", "Call Update DB");
        Log.e("Essi", uCus.customerCode);
        Log.e("Essi", uCus.customerName);


    }


    public boolean deleteCustomer(Customer dCus) {
        SQLiteDatabase ddb = this.getWritableDatabase();
        long dResult = ddb.delete(TABLE_NAME, dCODE + " =? ", new String[]{String.valueOf(dCus.customerCode)});
        if (dResult == 0) {
            return false;
        } else return true;

    }


    public int customerCount() {
        String gQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase gdb = this.getReadableDatabase();
        Cursor gCur = gdb.rawQuery(gQuery, null);
        int cResult = gCur.getCount();
        return cResult;
    }

    private void makeToastCus(Context context) {
        Toast.makeText(context, "کد خریدار ثبت نشده", Toast.LENGTH_SHORT).show();
    }
}

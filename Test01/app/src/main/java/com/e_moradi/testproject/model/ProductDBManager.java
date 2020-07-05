package com.e_moradi.testproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ProductDBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "product.db";
    private static final int DB_VERSION = 1;
    //columns of DB
    private static final String TABLE_NAME = "tbl_Product";
    private static final String dID_PRO = "id_pro";
    private static final String dCODE_PRO = "code_pro";
    private static final String dPRICE_PRO = "price_pro";
    private static final String dNAME_PRO = "name_pro";


    public ProductDBManager(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CPQuery = " CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME +
                " ( " +
                dID_PRO + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + dCODE_PRO + " INTEGER UNIQUE , "
                + dNAME_PRO + " TEXT , "
                + dPRICE_PRO + " TEXT "
                + ");";
        sqLiteDatabase.execSQL(CPQuery);
        Log.e("Essi", "Create Table: " + TABLE_NAME + "in DB");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertProduct(Product iPro) {
        SQLiteDatabase idbPro = this.getReadableDatabase();
        ContentValues icvPro = new ContentValues();
        icvPro.put(dCODE_PRO, iPro.productCode);
        icvPro.put(dNAME_PRO, iPro.productName);
        icvPro.put(dPRICE_PRO, iPro.productPrice);

        idbPro.insert(TABLE_NAME, null, icvPro);
        Log.e("Essi", "Call Insert Table" + TABLE_NAME);
        Log.e("Essi", "Insert Table ----> " + icvPro.toString());

        Log.e("Essi", icvPro.toString());
        Log.e("Essi", "*******InsertData in DB*****" + TABLE_NAME);
        idbPro.close();

    }

    public Product getProduct(String gCodePro,Context con) {
        Product gProCustomer = new Product();
        SQLiteDatabase gProDB = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + dCODE_PRO + " = " + gCodePro;
        Cursor gCurPro = gProDB.rawQuery(gQuery, null);
        if (gCurPro.moveToFirst()) {
            gProCustomer.productCode = gCurPro.getString(1);
            gProCustomer.productName = gCurPro.getString(2);
            gProCustomer.productPrice = gCurPro.getString(3);
        } else {
            makeToastPro(con);

        }
        gProDB.close();
        gCurPro.close();
        return gProCustomer;
    }

    public void updateProduct(Product uPro) {
        SQLiteDatabase uProDb = this.getWritableDatabase();
        ContentValues uProCv = new ContentValues();
        uProCv.put(dCODE_PRO, uPro.productCode);
        uProCv.put(dNAME_PRO, uPro.productName);
        uProCv.put(dPRICE_PRO, uPro.productPrice);

        uProDb.update(TABLE_NAME, uProCv, dCODE_PRO + " = ? ", new String[]{String.valueOf(uPro.productCode)});

        Log.e("Essi", "Call Update DB");
        Log.e("Essi", uPro.productCode);
        Log.e("Essi", uPro.productName);


    }
    public boolean deleteProduct(Product dPro) {
        SQLiteDatabase ddbPro = this.getWritableDatabase();
        long dResult = ddbPro.delete(TABLE_NAME, dCODE_PRO + " =? ", new String[]{String.valueOf(dPro.productCode)});
        if (dResult == 0) {
            return false;
        } else return true;

    }
    public int productCount() {
        String gQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase gdb = this.getReadableDatabase();
        Cursor gCur = gdb.rawQuery(gQuery, null);
        int cResultPro = gCur.getCount();
        return cResultPro;
    }


    private void makeToastPro(Context context) {
        Toast.makeText(context, "کد کالا ثبت نشده", Toast.LENGTH_SHORT).show();
    }


}

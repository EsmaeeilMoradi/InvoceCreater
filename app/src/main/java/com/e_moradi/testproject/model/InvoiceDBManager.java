package com.e_moradi.testproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class InvoiceDBManager extends SQLiteOpenHelper {


    private static final String DB_NAME = "invoice.db";
    private static final int DB_VERSION = 1;


    //column of DB
    private static final String TABLE_NAME = "tbl_invoice";
    private static final String dID_Invoice = "id_invoice";
    //column of DB Title Invoice
    private static final String dFACTORY_NAME = "factory_name";
    private static final String dNumber_Invoice = "number_invoice";
    private static final String dDate_Invoice = "date_invoice";
    //column of DB  Customer
    private static final String dCodeCus_Invoice = "code_cus_invoice";
    private static final String dNameCus_Invoice = "name_cus_invoice";
    private static final String dAddressCus_Invoice = "address_cus_invoice";
    private static final String dTelCus_Invoice = "tel_cus_invoice";
    private static final String dMobileCus_Invoice = "mobile_cus_invoice";
    //column of DB  Product in 10 ROW
    //column of DB  Product in 1 ROW
    private static final String dRowPro1_Invoice = "row_por1_invoice";
    private static final String dCodePro1_Invoice = "code_por1_invoice";
    private static final String dNamePro1_Invoice = "name_por1_invoice";
    private static final String dNumberPro1_Invoice = "number_por1_invoice";
    private static final String dUnitPricePro1_Invoice = "unit_Price_por1_invoice";
    private static final String dSumRowPro1_Invoice = "sum_row_por1_invoice";
    //column of DB  Product in 2 ROW
    private static final String dRowPro2_Invoice = "row_por2_invoice";
    private static final String dCodePro2_Invoice = "code_por2_invoice";
    private static final String dNamePro2_Invoice = "name_por2_invoice";
    private static final String dNumberPro2_Invoice = "number_por2_invoice";
    private static final String dUnitPricePro2_Invoice = "unit_Price_por2_invoice";
    private static final String dSumRowPro2_Invoice = "sum_row_por2_invoice";
    //column of DB  Product in 3 ROW
    private static final String dRowPro3_Invoice = "row_por3_invoice";
    private static final String dCodePro3_Invoice = "code_por3_invoice";
    private static final String dNamePro3_Invoice = "name_por3_invoice";
    private static final String dNumberPro3_Invoice = "number_por3_invoice";
    private static final String dUnitPricePro3_Invoice = "unit_Price_por3_invoice";
    private static final String dSumRowPro3_Invoice = "sum_row_por3_invoice";
    //column of DB  Product in 4 ROW
    private static final String dRowPro4_Invoice = "row_por4_invoice";
    private static final String dCodePro4_Invoice = "code_por4_invoice";
    private static final String dNamePro4_Invoice = "name_por4_invoice";
    private static final String dNumberPro4_Invoice = "number_por4_invoice";
    private static final String dUnitPricePro4_Invoice = "unit_Price_por4_invoice";
    private static final String dSumRowPro4_Invoice = "sum_row_por4_invoice";
    //column of DB  Product in 5 ROW
    private static final String dRowPro5_Invoice = "row_por5_invoice";
    private static final String dCodePro5_Invoice = "code_por5_invoice";
    private static final String dNamePro5_Invoice = "name_por5_invoice";
    private static final String dNumberPro5_Invoice = "number_por5_invoice";
    private static final String dUnitPricePro5_Invoice = "unit_Price_por5_invoice";
    private static final String dSumRowPro5_Invoice = "sum_row_por5_invoice";
    //column of DB  Product in 6 ROW
    private static final String dRowPro6_Invoice = "row_por6_invoice";
    private static final String dCodePro6_Invoice = "code_por6_invoice";
    private static final String dNamePro6_Invoice = "name_por6_invoice";
    private static final String dNumberPro6_Invoice = "number_por6_invoice";
    private static final String dUnitPricePro6_Invoice = "unit_Price_por6_invoice";
    private static final String dSumRowPro6_Invoice = "sum_row_por6_invoice";
    //column of DB  Product in 7 ROW
    private static final String dRowPro7_Invoice = "row_por7_invoice";
    private static final String dCodePro7_Invoice = "code_por7_invoice";
    private static final String dNamePro7_Invoice = "name_por7_invoice";
    private static final String dNumberPro7_Invoice = "number_por7_invoice";
    private static final String dUnitPricePro7_Invoice = "unit_Price_por7_invoice";
    private static final String dSumRowPro7_Invoice = "sum_row_por7_invoice";
    //column of DB  Product in 8 ROW
    private static final String dRowPro8_Invoice = "row_por8_invoice";
    private static final String dCodePro8_Invoice = "code_por8_invoice";
    private static final String dNamePro8_Invoice = "name_por8_invoice";
    private static final String dNumberPro8_Invoice = "number_por8_invoice";
    private static final String dUnitPricePro8_Invoice = "unit_Price_por8_invoice";
    private static final String dSumRowPro8_Invoice = "sum_row_por8_invoice";
    //column of DB  Product in 9 ROW
    private static final String dRowPro9_Invoice = "row_por9_invoice";
    private static final String dCodePro9_Invoice = "code_por9_invoice";
    private static final String dNamePro9_Invoice = "name_por9_invoice";
    private static final String dNumberPro9_Invoice = "number_por9_invoice";
    private static final String dUnitPricePro9_Invoice = "unit_Price_por9_invoice";
    private static final String dSumRowPro9_Invoice = "sum_row_por9_invoice";
    //column of DB  Product in 10 ROW
    private static final String dRowPro10_Invoice = "row_por10_invoice";
    private static final String dCodePro10_Invoice = "code_por10_invoice";
    private static final String dNamePro10_Invoice = "name_por10_invoice";
    private static final String dNumberPro10_Invoice = "number_por10_invoice";
    private static final String dUnitPricePro10_Invoice = "unit_Price_por10_invoice";
    private static final String dSumRowPro10_Invoice = "sum_row_por10_invoice";

    //end of Invoice
    private static final String dSumTotal_Invoice = "sum_total_invoice";
    private static final String dCostSend_Invoice = "cost_send_invoice";
    private static final String dDue_Invoice = "due_invoice";
    private static final String dDeposit_Invoice = "deposit_invoice";
    private static final String dDiscount_Invoice = "discount_invoice";
    private static final String dFinalSum_Invoice = "final_sum_invoice";


    public InvoiceDBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CPQuery = " CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME +
                " ( " +
                dID_Invoice + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + dFACTORY_NAME + " TEXT  , "
                + dNumber_Invoice + " INTEGER UNIQUE , "
                + dDate_Invoice + " TEXT , "

                + dCodeCus_Invoice + " INTEGER , "
                + dNameCus_Invoice + " TEXT , "
                + dAddressCus_Invoice + " TEXT , "
                + dTelCus_Invoice + " TEXT , "
                + dMobileCus_Invoice + " TEXT , "

                + dRowPro1_Invoice + " INTEGER, "
                + dCodePro1_Invoice + " INTEGER , "
                + dNamePro1_Invoice + " TEXT , "
                + dNumberPro1_Invoice + " INTEGER , "
                + dUnitPricePro1_Invoice + " INTEGER , "
                + dSumRowPro1_Invoice + " INTEGER , "
                + dRowPro2_Invoice + " INTEGER, "
                + dCodePro2_Invoice + " INTEGER , "
                + dNamePro2_Invoice + " TEXT , "
                + dNumberPro2_Invoice + " INTEGER , "
                + dUnitPricePro2_Invoice + " INTEGER , "
                + dSumRowPro2_Invoice + " INTEGER , "
                + dRowPro3_Invoice + " INTEGER, "
                + dCodePro3_Invoice + " INTEGER , "
                + dNamePro3_Invoice + " TEXT , "
                + dNumberPro3_Invoice + " INTEGER , "
                + dUnitPricePro3_Invoice + " INTEGER , "
                + dSumRowPro3_Invoice + " INTEGER , "
                + dRowPro4_Invoice + " INTEGER, "
                + dCodePro4_Invoice + " INTEGER , "
                + dNamePro4_Invoice + " TEXT , "
                + dNumberPro4_Invoice + " INTEGER , "
                + dUnitPricePro4_Invoice + " INTEGER , "
                + dSumRowPro4_Invoice + " INTEGER , "
                + dRowPro5_Invoice + " INTEGER, "
                + dCodePro5_Invoice + " INTEGER , "
                + dNamePro5_Invoice + " TEXT , "
                + dNumberPro5_Invoice + " INTEGER , "
                + dUnitPricePro5_Invoice + " INTEGER , "
                + dSumRowPro5_Invoice + " INTEGER , "
                + dRowPro6_Invoice + " INTEGER, "
                + dCodePro6_Invoice + " INTEGER , "
                + dNamePro6_Invoice + " TEXT , "
                + dNumberPro6_Invoice + " INTEGER , "
                + dUnitPricePro6_Invoice + " INTEGER , "
                + dSumRowPro6_Invoice + " INTEGER , "
                + dRowPro7_Invoice + " INTEGER, "
                + dCodePro7_Invoice + " INTEGER , "
                + dNamePro7_Invoice + " TEXT , "
                + dNumberPro7_Invoice + " INTEGER , "
                + dUnitPricePro7_Invoice + " INTEGER , "
                + dSumRowPro7_Invoice + " INTEGER , "
                + dRowPro8_Invoice + " INTEGER, "
                + dCodePro8_Invoice + " INTEGER , "
                + dNamePro8_Invoice + " TEXT , "
                + dNumberPro8_Invoice + " INTEGER , "
                + dUnitPricePro8_Invoice + " INTEGER , "
                + dSumRowPro8_Invoice + " INTEGER , "
                + dRowPro9_Invoice + " INTEGER, "
                + dCodePro9_Invoice + " INTEGER , "
                + dNamePro9_Invoice + " TEXT , "
                + dNumberPro9_Invoice + " INTEGER , "
                + dUnitPricePro9_Invoice + " INTEGER , "
                + dSumRowPro9_Invoice + " INTEGER , "
                + dRowPro10_Invoice + " INTEGER, "
                + dCodePro10_Invoice + " INTEGER , "
                + dNamePro10_Invoice + " TEXT , "
                + dNumberPro10_Invoice + " INTEGER , "
                + dUnitPricePro10_Invoice + " INTEGER , "
                + dSumRowPro10_Invoice + " INTEGER , "


                + dSumTotal_Invoice + " INTEGER , "
                + dCostSend_Invoice + " INTEGER , "
                + dDue_Invoice + " INTEGER , "
                + dDeposit_Invoice + " INTEGER , "
                + dDiscount_Invoice + " INTEGER , "
                + dFinalSum_Invoice + " INTEGER  "
                + ");";
        db.execSQL(CPQuery);
        Log.e("Essi", "Create Table: " + TABLE_NAME + "in DB");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertInvoice(Invoice iInvoice) {
        SQLiteDatabase idbInvoice = this.getReadableDatabase();
        ContentValues icvInvoice = new ContentValues();


        icvInvoice.put(dFACTORY_NAME, iInvoice.factoryName);
        icvInvoice.put(dNumber_Invoice, iInvoice.invoiceNumber);
        icvInvoice.put(dDate_Invoice, iInvoice.invoiceDate);


        icvInvoice.put(dCodeCus_Invoice, iInvoice.invoiceCusCode);
        icvInvoice.put(dNameCus_Invoice, iInvoice.invoiceCusName);
        icvInvoice.put(dAddressCus_Invoice, iInvoice.invoiceCusAddress);
        icvInvoice.put(dTelCus_Invoice, iInvoice.invoiceCusTel);
        icvInvoice.put(dMobileCus_Invoice, iInvoice.invoiceCusMobile);


        icvInvoice.put(dRowPro1_Invoice, iInvoice.invoiceProRow1);
        icvInvoice.put(dCodePro1_Invoice, iInvoice.invoiceProCode1);
        icvInvoice.put(dNamePro1_Invoice, iInvoice.invoiceProName1);
        icvInvoice.put(dNumberPro1_Invoice, iInvoice.invoiceProNum1);
        icvInvoice.put(dUnitPricePro1_Invoice, iInvoice.invoiceProUnitPrice1);
        icvInvoice.put(dSumRowPro1_Invoice, iInvoice.invoiceProSumPrice1);

        icvInvoice.put(dRowPro2_Invoice, iInvoice.invoiceProRow2);
        icvInvoice.put(dCodePro2_Invoice, iInvoice.invoiceProCode2);
        icvInvoice.put(dNamePro2_Invoice, iInvoice.invoiceProName2);
        icvInvoice.put(dNumberPro2_Invoice, iInvoice.invoiceProNum2);
        icvInvoice.put(dUnitPricePro2_Invoice, iInvoice.invoiceProUnitPrice2);
        icvInvoice.put(dSumRowPro2_Invoice, iInvoice.invoiceProSumPrice2);

        icvInvoice.put(dRowPro3_Invoice, iInvoice.invoiceProRow3);
        icvInvoice.put(dCodePro3_Invoice, iInvoice.invoiceProCode3);
        icvInvoice.put(dNamePro3_Invoice, iInvoice.invoiceProName3);
        icvInvoice.put(dNumberPro3_Invoice, iInvoice.invoiceProNum3);
        icvInvoice.put(dUnitPricePro3_Invoice, iInvoice.invoiceProUnitPrice3);
        icvInvoice.put(dSumRowPro3_Invoice, iInvoice.invoiceProSumPrice3);

        icvInvoice.put(dRowPro4_Invoice, iInvoice.invoiceProRow4);
        icvInvoice.put(dCodePro4_Invoice, iInvoice.invoiceProCode4);
        icvInvoice.put(dNamePro4_Invoice, iInvoice.invoiceProName4);
        icvInvoice.put(dNumberPro4_Invoice, iInvoice.invoiceProNum4);
        icvInvoice.put(dUnitPricePro4_Invoice, iInvoice.invoiceProUnitPrice4);
        icvInvoice.put(dSumRowPro4_Invoice, iInvoice.invoiceProSumPrice4);

        icvInvoice.put(dRowPro5_Invoice, iInvoice.invoiceProRow5);
        icvInvoice.put(dCodePro5_Invoice, iInvoice.invoiceProCode5);
        icvInvoice.put(dNamePro5_Invoice, iInvoice.invoiceProName5);
        icvInvoice.put(dNumberPro5_Invoice, iInvoice.invoiceProNum5);
        icvInvoice.put(dUnitPricePro5_Invoice, iInvoice.invoiceProUnitPrice5);
        icvInvoice.put(dSumRowPro5_Invoice, iInvoice.invoiceProSumPrice5);

        icvInvoice.put(dRowPro6_Invoice, iInvoice.invoiceProRow6);
        icvInvoice.put(dCodePro6_Invoice, iInvoice.invoiceProCode6);
        icvInvoice.put(dNamePro6_Invoice, iInvoice.invoiceProName6);
        icvInvoice.put(dNumberPro6_Invoice, iInvoice.invoiceProNum6);
        icvInvoice.put(dUnitPricePro6_Invoice, iInvoice.invoiceProUnitPrice6);
        icvInvoice.put(dSumRowPro6_Invoice, iInvoice.invoiceProSumPrice6);

        icvInvoice.put(dRowPro7_Invoice, iInvoice.invoiceProRow7);
        icvInvoice.put(dCodePro7_Invoice, iInvoice.invoiceProCode7);
        icvInvoice.put(dNamePro7_Invoice, iInvoice.invoiceProName7);
        icvInvoice.put(dNumberPro7_Invoice, iInvoice.invoiceProNum7);
        icvInvoice.put(dUnitPricePro7_Invoice, iInvoice.invoiceProUnitPrice7);
        icvInvoice.put(dSumRowPro7_Invoice, iInvoice.invoiceProSumPrice7);

        icvInvoice.put(dRowPro8_Invoice, iInvoice.invoiceProRow8);
        icvInvoice.put(dCodePro8_Invoice, iInvoice.invoiceProCode8);
        icvInvoice.put(dNamePro8_Invoice, iInvoice.invoiceProName8);
        icvInvoice.put(dNumberPro8_Invoice, iInvoice.invoiceProNum8);
        icvInvoice.put(dUnitPricePro8_Invoice, iInvoice.invoiceProUnitPrice8);
        icvInvoice.put(dSumRowPro8_Invoice, iInvoice.invoiceProSumPrice8);

        icvInvoice.put(dRowPro9_Invoice, iInvoice.invoiceProRow9);
        icvInvoice.put(dCodePro9_Invoice, iInvoice.invoiceProCode9);
        icvInvoice.put(dNamePro9_Invoice, iInvoice.invoiceProName9);
        icvInvoice.put(dNumberPro9_Invoice, iInvoice.invoiceProNum9);
        icvInvoice.put(dUnitPricePro9_Invoice, iInvoice.invoiceProUnitPrice9);
        icvInvoice.put(dSumRowPro9_Invoice, iInvoice.invoiceProSumPrice9);

        icvInvoice.put(dRowPro10_Invoice, iInvoice.invoiceProRow10);
        icvInvoice.put(dCodePro10_Invoice, iInvoice.invoiceProCode10);
        icvInvoice.put(dNamePro10_Invoice, iInvoice.invoiceProName10);
        icvInvoice.put(dNumberPro10_Invoice, iInvoice.invoiceProNum10);
        icvInvoice.put(dUnitPricePro10_Invoice, iInvoice.invoiceProUnitPrice10);
        icvInvoice.put(dSumRowPro10_Invoice, iInvoice.invoiceProSumPrice10);


        icvInvoice.put(dSumTotal_Invoice, iInvoice.invoiceSumRowsTotal);
        icvInvoice.put(dCostSend_Invoice, iInvoice.invoiceCostSend);
        icvInvoice.put(dDue_Invoice, iInvoice.invoiceDue);
        icvInvoice.put(dDeposit_Invoice, iInvoice.invoiceDeposit);
        icvInvoice.put(dDiscount_Invoice, iInvoice.invoiceDiscount);
        icvInvoice.put(dFinalSum_Invoice, iInvoice.invoiceFinalSum);


        icvInvoice.put(dFACTORY_NAME, iInvoice.factoryName);
        icvInvoice.put(dFACTORY_NAME, iInvoice.factoryName);


        idbInvoice.insert(TABLE_NAME, null, icvInvoice);
        Log.e("Essi", "Call Insert Table" + TABLE_NAME);
        Log.e("Essi", "Insert Table ----> " + icvInvoice.toString());

        Log.e("Essi", icvInvoice.toString());
        Log.e("Essi", "*******InsertData in DB*****" + TABLE_NAME);
        idbInvoice.close();

    }

    public Invoice getInvoice(String gNumInvoice, Context context) {
        Invoice gInvoice = new Invoice();
        SQLiteDatabase gDBInvoice = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + dNumber_Invoice + " = " + gNumInvoice;
        Cursor gCursorInvoice = gDBInvoice.rawQuery(gQuery, null);
        if (gCursorInvoice.moveToFirst()) {
            Log.e("Essi", "invoiceNumber" + gCursorInvoice.getString(2));


            gInvoice.factoryName = gCursorInvoice.getString(1);
            gInvoice.invoiceNumber = gCursorInvoice.getString(2);
            gInvoice.invoiceDate = gCursorInvoice.getString(3);
            gInvoice.invoiceCusCode = gCursorInvoice.getString(4);
            gInvoice.invoiceCusName = gCursorInvoice.getString(5);
            gInvoice.invoiceCusAddress = gCursorInvoice.getString(6);
            gInvoice.invoiceCusTel = gCursorInvoice.getString(7);
            gInvoice.invoiceCusMobile = gCursorInvoice.getString(8);


            gInvoice.invoiceProCode1 = gCursorInvoice.getString(10);
            gInvoice.invoiceProName1 = gCursorInvoice.getString(11);
            gInvoice.invoiceProNum1 = gCursorInvoice.getString(12);
            gInvoice.invoiceProUnitPrice1 = gCursorInvoice.getString(13);
            gInvoice.invoiceProSumPrice1 = gCursorInvoice.getString(14);


            gInvoice.invoiceProCode2 = gCursorInvoice.getString(16);
            gInvoice.invoiceProName2 = gCursorInvoice.getString(17);
            gInvoice.invoiceProNum2 = gCursorInvoice.getString(18);
            gInvoice.invoiceProUnitPrice2 = gCursorInvoice.getString(19);
            gInvoice.invoiceProSumPrice2 = gCursorInvoice.getString(20);


            gInvoice.invoiceProCode3 = gCursorInvoice.getString(22);
            gInvoice.invoiceProName3 = gCursorInvoice.getString(23);
            gInvoice.invoiceProNum3 = gCursorInvoice.getString(24);
            gInvoice.invoiceProUnitPrice3 = gCursorInvoice.getString(25);
            gInvoice.invoiceProSumPrice3 = gCursorInvoice.getString(26);


            gInvoice.invoiceProCode4 = gCursorInvoice.getString(28);
            gInvoice.invoiceProName4 = gCursorInvoice.getString(29);
            gInvoice.invoiceProNum4 = gCursorInvoice.getString(30);
            gInvoice.invoiceProUnitPrice4 = gCursorInvoice.getString(31);
            gInvoice.invoiceProSumPrice4 = gCursorInvoice.getString(32);


            gInvoice.invoiceProCode5 = gCursorInvoice.getString(34);
            gInvoice.invoiceProName5 = gCursorInvoice.getString(35);
            gInvoice.invoiceProNum5 = gCursorInvoice.getString(36);
            gInvoice.invoiceProUnitPrice5 = gCursorInvoice.getString(37);
            gInvoice.invoiceProSumPrice5 = gCursorInvoice.getString(38);


            gInvoice.invoiceProCode6 = gCursorInvoice.getString(40);
            gInvoice.invoiceProName6 = gCursorInvoice.getString(41);
            gInvoice.invoiceProNum6 = gCursorInvoice.getString(42);
            gInvoice.invoiceProUnitPrice6 = gCursorInvoice.getString(43);
            gInvoice.invoiceProSumPrice6 = gCursorInvoice.getString(44);


            gInvoice.invoiceProCode7 = gCursorInvoice.getString(46);
            gInvoice.invoiceProName7 = gCursorInvoice.getString(47);
            gInvoice.invoiceProNum7 = gCursorInvoice.getString(48);
            gInvoice.invoiceProUnitPrice7 = gCursorInvoice.getString(49);
            gInvoice.invoiceProSumPrice7 = gCursorInvoice.getString(50);


            gInvoice.invoiceProCode8 = gCursorInvoice.getString(52);
            gInvoice.invoiceProName8 = gCursorInvoice.getString(53);
            gInvoice.invoiceProNum8 = gCursorInvoice.getString(54);
            gInvoice.invoiceProUnitPrice8 = gCursorInvoice.getString(55);
            gInvoice.invoiceProSumPrice8 = gCursorInvoice.getString(56);


            gInvoice.invoiceProCode9 = gCursorInvoice.getString(58);
            gInvoice.invoiceProName9 = gCursorInvoice.getString(59);
            gInvoice.invoiceProNum9 = gCursorInvoice.getString(60);
            gInvoice.invoiceProUnitPrice9 = gCursorInvoice.getString(61);
            gInvoice.invoiceProSumPrice9 = gCursorInvoice.getString(62);


            gInvoice.invoiceProCode10 = gCursorInvoice.getString(64);
            gInvoice.invoiceProName10 = gCursorInvoice.getString(65);
            gInvoice.invoiceProNum10 = gCursorInvoice.getString(66);
            gInvoice.invoiceProUnitPrice10 = gCursorInvoice.getString(67);
            gInvoice.invoiceProSumPrice10 = gCursorInvoice.getString(68);


            gInvoice.invoiceSumRowsTotal = gCursorInvoice.getString(69);
            gInvoice.invoiceCostSend = gCursorInvoice.getString(70);
            gInvoice.invoiceDue = gCursorInvoice.getString(71);
            gInvoice.invoiceDeposit = gCursorInvoice.getString(72);
            gInvoice.invoiceDiscount = gCursorInvoice.getString(73);
            gInvoice.invoiceFinalSum = gCursorInvoice.getString(74);
        } else {
            makeToastCus(context);
        }

        gDBInvoice.close();
        gCursorInvoice.close();
        return gInvoice;
    }

    public int invoiceCount() {
        String gQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase gdb = this.getReadableDatabase();
        Cursor gCur = gdb.rawQuery(gQuery, null);
        int cResult = gCur.getCount();
        return cResult;
    }


    public boolean deleteInvoice(Invoice dInvoice) {

        SQLiteDatabase ddbI = this.getWritableDatabase();
        long dResult = ddbI.delete(TABLE_NAME, dNumber_Invoice + " =? ",
                new String[]{String.valueOf(dInvoice.invoiceNumber)});
        if (dResult == 0) {
            return false;
        } else return true;
    }


    public void updateInvoice(Invoice updateInvoice) {
        SQLiteDatabase udbInvoice = this.getWritableDatabase();
        ContentValues UCVInvoice = new ContentValues();


        UCVInvoice.put(dFACTORY_NAME, updateInvoice.factoryName);
        UCVInvoice.put(dNumber_Invoice, updateInvoice.invoiceNumber);
        UCVInvoice.put(dDate_Invoice, updateInvoice.invoiceDate);


        UCVInvoice.put(dCodeCus_Invoice, updateInvoice.invoiceCusCode);
        UCVInvoice.put(dNameCus_Invoice, updateInvoice.invoiceCusName);
        UCVInvoice.put(dAddressCus_Invoice, updateInvoice.invoiceCusAddress);
        UCVInvoice.put(dTelCus_Invoice, updateInvoice.invoiceCusTel);
        UCVInvoice.put(dMobileCus_Invoice, updateInvoice.invoiceCusMobile);


        UCVInvoice.put(dRowPro1_Invoice, updateInvoice.invoiceProRow1);
        UCVInvoice.put(dCodePro1_Invoice, updateInvoice.invoiceProCode1);
        UCVInvoice.put(dNamePro1_Invoice, updateInvoice.invoiceProName1);
        UCVInvoice.put(dNumberPro1_Invoice, updateInvoice.invoiceProNum1);
        UCVInvoice.put(dUnitPricePro1_Invoice, updateInvoice.invoiceProUnitPrice1);
        UCVInvoice.put(dSumRowPro1_Invoice, updateInvoice.invoiceProSumPrice1);

        UCVInvoice.put(dRowPro2_Invoice, updateInvoice.invoiceProRow2);
        UCVInvoice.put(dCodePro2_Invoice, updateInvoice.invoiceProCode2);
        UCVInvoice.put(dNamePro2_Invoice, updateInvoice.invoiceProName2);
        UCVInvoice.put(dNumberPro2_Invoice, updateInvoice.invoiceProNum2);
        UCVInvoice.put(dUnitPricePro2_Invoice, updateInvoice.invoiceProUnitPrice2);
        UCVInvoice.put(dSumRowPro2_Invoice, updateInvoice.invoiceProSumPrice2);

        UCVInvoice.put(dRowPro3_Invoice, updateInvoice.invoiceProRow3);
        UCVInvoice.put(dCodePro3_Invoice, updateInvoice.invoiceProCode3);
        UCVInvoice.put(dNamePro3_Invoice, updateInvoice.invoiceProName3);
        UCVInvoice.put(dNumberPro3_Invoice, updateInvoice.invoiceProNum3);
        UCVInvoice.put(dUnitPricePro3_Invoice, updateInvoice.invoiceProUnitPrice3);
        UCVInvoice.put(dSumRowPro3_Invoice, updateInvoice.invoiceProSumPrice3);

        UCVInvoice.put(dRowPro4_Invoice, updateInvoice.invoiceProRow4);
        UCVInvoice.put(dCodePro4_Invoice, updateInvoice.invoiceProCode4);
        UCVInvoice.put(dNamePro4_Invoice, updateInvoice.invoiceProName4);
        UCVInvoice.put(dNumberPro4_Invoice, updateInvoice.invoiceProNum4);
        UCVInvoice.put(dUnitPricePro4_Invoice, updateInvoice.invoiceProUnitPrice4);
        UCVInvoice.put(dSumRowPro4_Invoice, updateInvoice.invoiceProSumPrice4);

        UCVInvoice.put(dRowPro5_Invoice, updateInvoice.invoiceProRow5);
        UCVInvoice.put(dCodePro5_Invoice, updateInvoice.invoiceProCode5);
        UCVInvoice.put(dNamePro5_Invoice, updateInvoice.invoiceProName5);
        UCVInvoice.put(dNumberPro5_Invoice, updateInvoice.invoiceProNum5);
        UCVInvoice.put(dUnitPricePro5_Invoice, updateInvoice.invoiceProUnitPrice5);
        UCVInvoice.put(dSumRowPro5_Invoice, updateInvoice.invoiceProSumPrice5);

        UCVInvoice.put(dRowPro6_Invoice, updateInvoice.invoiceProRow6);
        UCVInvoice.put(dCodePro6_Invoice, updateInvoice.invoiceProCode6);
        UCVInvoice.put(dNamePro6_Invoice, updateInvoice.invoiceProName6);
        UCVInvoice.put(dNumberPro6_Invoice, updateInvoice.invoiceProNum6);
        UCVInvoice.put(dUnitPricePro6_Invoice, updateInvoice.invoiceProUnitPrice6);
        UCVInvoice.put(dSumRowPro6_Invoice, updateInvoice.invoiceProSumPrice6);

        UCVInvoice.put(dRowPro7_Invoice, updateInvoice.invoiceProRow7);
        UCVInvoice.put(dCodePro7_Invoice, updateInvoice.invoiceProCode7);
        UCVInvoice.put(dNamePro7_Invoice, updateInvoice.invoiceProName7);
        UCVInvoice.put(dNumberPro7_Invoice, updateInvoice.invoiceProNum7);
        UCVInvoice.put(dUnitPricePro7_Invoice, updateInvoice.invoiceProUnitPrice7);
        UCVInvoice.put(dSumRowPro7_Invoice, updateInvoice.invoiceProSumPrice7);

        UCVInvoice.put(dRowPro8_Invoice, updateInvoice.invoiceProRow8);
        UCVInvoice.put(dCodePro8_Invoice, updateInvoice.invoiceProCode8);
        UCVInvoice.put(dNamePro8_Invoice, updateInvoice.invoiceProName8);
        UCVInvoice.put(dNumberPro8_Invoice, updateInvoice.invoiceProNum8);
        UCVInvoice.put(dUnitPricePro8_Invoice, updateInvoice.invoiceProUnitPrice8);
        UCVInvoice.put(dSumRowPro8_Invoice, updateInvoice.invoiceProSumPrice8);

        UCVInvoice.put(dRowPro9_Invoice, updateInvoice.invoiceProRow9);
        UCVInvoice.put(dCodePro9_Invoice, updateInvoice.invoiceProCode9);
        UCVInvoice.put(dNamePro9_Invoice, updateInvoice.invoiceProName9);
        UCVInvoice.put(dNumberPro9_Invoice, updateInvoice.invoiceProNum9);
        UCVInvoice.put(dUnitPricePro9_Invoice, updateInvoice.invoiceProUnitPrice9);
        UCVInvoice.put(dSumRowPro9_Invoice, updateInvoice.invoiceProSumPrice9);

        UCVInvoice.put(dRowPro10_Invoice, updateInvoice.invoiceProRow10);
        UCVInvoice.put(dCodePro10_Invoice, updateInvoice.invoiceProCode10);
        UCVInvoice.put(dNamePro10_Invoice, updateInvoice.invoiceProName10);
        UCVInvoice.put(dNumberPro10_Invoice, updateInvoice.invoiceProNum10);
        UCVInvoice.put(dUnitPricePro10_Invoice, updateInvoice.invoiceProUnitPrice10);
        UCVInvoice.put(dSumRowPro10_Invoice, updateInvoice.invoiceProSumPrice10);


        UCVInvoice.put(dSumTotal_Invoice, updateInvoice.invoiceSumRowsTotal);
        UCVInvoice.put(dCostSend_Invoice, updateInvoice.invoiceCostSend);
        UCVInvoice.put(dDue_Invoice, updateInvoice.invoiceDue);
        UCVInvoice.put(dDeposit_Invoice, updateInvoice.invoiceDeposit);
        UCVInvoice.put(dDiscount_Invoice, updateInvoice.invoiceDiscount);
        UCVInvoice.put(dFinalSum_Invoice, updateInvoice.invoiceFinalSum);


        udbInvoice.update(TABLE_NAME, UCVInvoice, dNumber_Invoice + " = ? ", new String[]{String.valueOf(updateInvoice.invoiceNumber)});


        Log.e("Essi", " **-->Call Update DB INVOICE<--** ");
        Log.e("Essi", updateInvoice.invoiceNumber);
        Log.e("Essi", updateInvoice.invoiceCusName);

    }


    private void makeToastCus(Context context) {
        Toast.makeText(context, "فاکتور ثبت نشده", Toast.LENGTH_SHORT).show();
    }
}




/*

    public Invoice getInvoice(String gNumInvoice, Context context) {
        Invoice gInvoice = new Invoice();
        SQLiteDatabase gDBInvoice = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + dNumber_Invoice + " = " + gNumInvoice;
        Cursor gCursorInvoice = gDBInvoice.rawQuery(gQuery, null);
        if (gCursorInvoice.moveToFirst()) {
            gInvoice.invoiceNumber = gCursorInvoice.getString(2);
            Log.e("Essi", "invoiceNumber" + gCursorInvoice.getString(2));
            gInvoice.invoiceDate = gCursorInvoice.getString(3);
            gInvoice.invoiceCusCode = gCursorInvoice.getString(4);
            gInvoice.invoiceCusName = gCursorInvoice.getString(5);
            gInvoice.invoiceSumRowsTotal = gCursorInvoice.getString(69);
            gInvoice.invoiceCostSend = gCursorInvoice.getString(70);
            gInvoice.invoiceDue = gCursorInvoice.getString(71);
            gInvoice.invoiceDeposit = gCursorInvoice.getString(72);
            gInvoice.invoiceDiscount = gCursorInvoice.getString(73);
            gInvoice.invoiceFinalSum = gCursorInvoice.getString(74);
        } else {
            makeToastCus(context);
        }

        gDBInvoice.close();
        gCursorInvoice.close();
        return gInvoice;
    }

 */
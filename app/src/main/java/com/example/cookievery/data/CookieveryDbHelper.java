package com.example.cookievery.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CookieveryDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "cookievery.db";

    private static CookieveryDbHelper dbHelper;

    public static CookieveryDbHelper single(Context context) {
        if (dbHelper == null) {
            dbHelper = new CookieveryDbHelper(context);
        }
        return dbHelper;
    }

    public CookieveryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CookieveryContract.FeedCliente.SQL_CREATE_CLIENTE);
        db.execSQL(CookieveryContract.FeedProducto.SQL_CREATE_PRODUCTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CookieveryContract.FeedCliente.SQL_DELETE_CLIENTE);
        db.execSQL(CookieveryContract.FeedProducto.SQL_DELETE_PRODUCTO);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

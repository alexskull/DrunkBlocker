package com.example.luisgluna.vista_lista_expandible;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis G Luna on 16/03/2015.
 */
public class BdHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DBandroid.sqlite";
    public static final int DB_SCHEME_VERSION = 1;

    public BdHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager.CREATE_TABLE);
       // db.execSQL(DataBaseManager.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

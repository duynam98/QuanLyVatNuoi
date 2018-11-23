package com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.Constant;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "QuanLyVatNuoi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VATNUOI_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VATNUOI );
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}

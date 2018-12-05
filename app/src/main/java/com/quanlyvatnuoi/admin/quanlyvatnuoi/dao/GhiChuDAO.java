package com.quanlyvatnuoi.admin.quanlyvatnuoi.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.Constant;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.Ghichu;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GhiChuDAO implements Constant {

    DatabaseHelper databaseHelper;

    public GhiChuDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertnote(Ghichu ghichu){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, ghichu.getId());
        values.put(COLUMN_TITLE, ghichu.getTieude());
        values.put(COLUMN_NOIDUNG, ghichu.getNoidung());
        values.put(COLUMN_TIME, ghichu.getThoigian());
        db.insert(NOTE_TABLE, null, values);
        db.close();
    }

    public List<Ghichu> getAllNote(){
        List<Ghichu> ghichus = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + NOTE_TABLE;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String tieude = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String noidung = cursor.getString(cursor.getColumnIndex(COLUMN_NOIDUNG));
                String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
                Ghichu ghichu = new Ghichu(id, tieude, noidung, time);
                ghichus.add(ghichu);
            }while (cursor.moveToNext());
        }
        db.close();
        return ghichus;
    }

    public void deleteNote(Ghichu ghichu) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(NOTE_TABLE, COLUMN_ID + " = ?",
                new String[]{String.valueOf(ghichu.getId())});
        db.close();
    }

    public long updateNote(Ghichu ghichu) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, ghichu.getId());
        values.put(COLUMN_TITLE, ghichu.getTieude());
        values.put(COLUMN_NOIDUNG, ghichu.getNoidung());
        values.put(COLUMN_TIME, ghichu.getThoigian());
        return db.update(NOTE_TABLE, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(ghichu.getId())});
    }
}

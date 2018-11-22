package com.quanlyvatnuoi.admin.quanlyvatnuoi.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.Constant;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.VatNuoi;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class VatNuoiDAO implements Constant {

    DatabaseHelper databaseHelper;

    public VatNuoiDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertvatnuoi(VatNuoi vatNuoi){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, vatNuoi.getTenvatnuoi());
        values.put(COLUMN_TINHTRANG, vatNuoi.getTinhtrangsuckhoe());
        values.put(COLUMN_LOAITHUCAN, vatNuoi.getLoaithucan());
        values.put(COLUMN_THOIGIAN, vatNuoi.getThoigiannuoi());
        values.put(COLUMN_SOLUONG, vatNuoi.getSoluong());
        values.put(COLUMN_IMAGE, vatNuoi.getImage());
        long id = db.insert(TABLE_VATNUOI, null, values);
        db.close();
        Log.e("addvn", id+"");
    }

    public List<VatNuoi> getAllVatNuoi(){
        List<VatNuoi> vatNuoiList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VATNUOI;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String tinhtrang = cursor.getString(cursor.getColumnIndex(COLUMN_TINHTRANG));
                String loaithucan = cursor.getString(cursor.getColumnIndex(COLUMN_LOAITHUCAN));
                String thoigian = cursor.getString(cursor.getColumnIndex(COLUMN_THOIGIAN));
                int soluong = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SOLUONG)));
                String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
                VatNuoi vatNuoi = new VatNuoi(name, tinhtrang, loaithucan, thoigian, soluong, image);
                vatNuoiList.add(vatNuoi);
            }while (cursor.moveToNext());
        }
        db.close();
        return vatNuoiList;
    }

    public void delete(VatNuoi vatNuoi){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_VATNUOI, COLUMN_NAME + "=?", new String[]{vatNuoi.getTenvatnuoi()});
        db.close();
    }

    public void updateVatNuoi(VatNuoi vatNuoi){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, vatNuoi.getTenvatnuoi());
        values.put(COLUMN_TINHTRANG, vatNuoi.getTinhtrangsuckhoe());
        values.put(COLUMN_LOAITHUCAN, vatNuoi.getLoaithucan());
        values.put(COLUMN_THOIGIAN, vatNuoi.getThoigiannuoi());
        values.put(COLUMN_SOLUONG, vatNuoi.getSoluong());
        values.put(COLUMN_IMAGE, vatNuoi.getImage());
        db.update(TABLE_VATNUOI, values, COLUMN_NAME + "=?", new String[]{vatNuoi.getTenvatnuoi()});
    }
}

package com.quanlyvatnuoi.admin.quanlyvatnuoi.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.quanlyvatnuoi.admin.quanlyvatnuoi.Constant;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.User;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Constant {

    DatabaseHelper databaseHelper;

    public UserDao(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertUser(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUserName());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONENUMBER, user.getPhone());
        db.insert(USER_TABLE, null, contentValues);
        db.close();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + USER_TABLE;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER));
                User user = new User(user_name, password, name, phoneNumber);
                users.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return users;
    }

    public User getUser(String username) {
        User user = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONENUMBER},
                COLUMN_USERNAME + "=?",
                new String[]{username},
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER));
            user = new User(user_name, password, name, phone);
        }
        cursor.close();
        return user;
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(USER_TABLE, COLUMN_USERNAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
        db.close();
    }

    public long updateUser(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUserName());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONENUMBER, user.getPhone());
        return db.update(USER_TABLE, contentValues, COLUMN_USERNAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
    }


}

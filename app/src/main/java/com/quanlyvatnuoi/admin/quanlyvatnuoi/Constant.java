package com.quanlyvatnuoi.admin.quanlyvatnuoi;

public interface Constant {

    //Table vật nuôi
    String TABLE_VATNUOI = "VatNuoi";
    String COLUMN_NAME = "Name";
    String COLUMN_TINHTRANG = "TinhTrang";
    String COLUMN_LOAITHUCAN = "LoạiThucAn";
    String COLUMN_THOIGIAN = "ThoiGian";
    String COLUMN_SOLUONG = "SoLuong";
    String COLUMN_IMAGE = "Image";

    String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_VATNUOI + "(" +
            COLUMN_NAME + " TEXT PRIMARY KEY," +
            COLUMN_TINHTRANG + " TEXT," +
            COLUMN_LOAITHUCAN + " TEXT," +
            COLUMN_THOIGIAN + " TEXT," +
            COLUMN_SOLUONG + " int," +
            COLUMN_IMAGE + " TEXT" +
            ")";

    //Table User
    String USER_TABLE = "users";
    String COLUMN_USERNAME = "Username";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_USER_NAME  = "Name";
    String COLUMN_PHONENUMBER = "Phonenumber";

    String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_USER_NAME + " VARCHAR," +
            COLUMN_PHONENUMBER + " VARCHAR" +
            ")";



}

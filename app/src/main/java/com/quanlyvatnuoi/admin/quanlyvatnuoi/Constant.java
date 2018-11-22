package com.quanlyvatnuoi.admin.quanlyvatnuoi;

public interface Constant {

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


}

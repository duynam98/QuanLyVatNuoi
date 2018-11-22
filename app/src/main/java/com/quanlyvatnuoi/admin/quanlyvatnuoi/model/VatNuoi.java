package com.quanlyvatnuoi.admin.quanlyvatnuoi.model;

public class VatNuoi {

    String tenvatnuoi;
    String tinhtrangsuckhoe;
    String loaithucan;
    String thoigiannuoi;
    int soluong;
    String image;

    public VatNuoi(String tenvatnuoi, String tinhtrangsuckhoe, String loaithucan, String thoigiannuoi, int soluong, String image) {
        this.tenvatnuoi = tenvatnuoi;
        this.tinhtrangsuckhoe = tinhtrangsuckhoe;
        this.loaithucan = loaithucan;
        this.thoigiannuoi = thoigiannuoi;
        this.soluong = soluong;
        this.image = image;
    }

    public VatNuoi(String tinhtrangsuckhoe, String loaithucan, String thoigiannuoi, int soluong, String image) {
        this.tinhtrangsuckhoe = tinhtrangsuckhoe;
        this.loaithucan = loaithucan;
        this.thoigiannuoi = thoigiannuoi;
        this.soluong = soluong;
        this.image = image;
    }

    public VatNuoi() {
    }

    public String getTenvatnuoi() {
        return tenvatnuoi;
    }

    public void setTenvatnuoi(String tenvatnuoi) {
        this.tenvatnuoi = tenvatnuoi;
    }

    public String getTinhtrangsuckhoe() {
        return tinhtrangsuckhoe;
    }

    public void setTinhtrangsuckhoe(String tinhtrangsuckhoe) {
        this.tinhtrangsuckhoe = tinhtrangsuckhoe;
    }

    public String getLoaithucan() {
        return loaithucan;
    }

    public void setLoaithucan(String loaithucan) {
        this.loaithucan = loaithucan;
    }

    public String getThoigiannuoi() {
        return thoigiannuoi;
    }

    public void setThoigiannuoi(String thoigiannuoi) {
        this.thoigiannuoi = thoigiannuoi;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

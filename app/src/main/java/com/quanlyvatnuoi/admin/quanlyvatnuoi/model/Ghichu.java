package com.quanlyvatnuoi.admin.quanlyvatnuoi.model;

public class Ghichu {

    String id;
    String tieude;
    String noidung;
    String thoigian;

    public Ghichu(String id, String tieude, String noidung, String thoigian) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.thoigian = thoigian;
    }

    public Ghichu(String tieude, String noidung, String thoigian) {
        this.tieude = tieude;
        this.noidung = noidung;
        this.thoigian = thoigian;
    }

    public Ghichu() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}

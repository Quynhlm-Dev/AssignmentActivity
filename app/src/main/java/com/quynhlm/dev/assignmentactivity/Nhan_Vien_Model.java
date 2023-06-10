package com.quynhlm.dev.assignmentactivity;

import java.io.Serializable;

public class Nhan_Vien_Model implements Serializable {
    private int imglogo;
    private String id;
    private String name;
    private String phongBan;

    private String gioiTinh;

    public Nhan_Vien_Model(int imglogo, String id, String name, String phongBan, String gioiTinh) {
        this.imglogo = imglogo;
        this.id = id;
        this.name = name;
        this.phongBan = phongBan;
        this.gioiTinh = gioiTinh;
    }

    public int getImglogo() {
        return imglogo;
    }

    public void setImglogo(int imglogo) {
        this.imglogo = imglogo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}

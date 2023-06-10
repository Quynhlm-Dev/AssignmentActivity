package com.quynhlm.dev.assignmentactivity;

public class Phong_Ban_Model {
    private int img;
    private String phongban;

    public Phong_Ban_Model(int img, String phongban) {
        this.img = img;
        this.phongban = phongban;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPhongban() {
        return phongban;
    }

    public void setPhongban(String phongban) {
        this.phongban = phongban;
    }
}

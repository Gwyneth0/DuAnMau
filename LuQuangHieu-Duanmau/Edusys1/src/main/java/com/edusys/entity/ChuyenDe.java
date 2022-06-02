package com.edusys.entity;

public class ChuyenDe {
    private String maCD;
    private String tenCD;
    private double hocPhi;
    private int thoiLuong;
    private String hinh;
    private String moTa;

    @Override
    public String toString() {
        return this.tenCD;
    }

    public ChuyenDe(String maCD, String tenCD, double hocPhi, int thoiLuong, String hinh, String moTa) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.hinh = hinh;
        this.moTa = moTa;
    }

    public ChuyenDe() {
    }

    @Override
    public boolean equals(Object obj) {
        ChuyenDe other = (ChuyenDe) obj;
        return other.getMaCD().equals(this.getMaCD());
    }
    
    

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}

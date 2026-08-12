package vn.edu.eaut.lab5.model;

import java.sql.Timestamp;

public class HoaDon {
    private int maHD;
    private int maKH;
    private Timestamp ngayLap;
    private double tongTien;

    public HoaDon() {}

    public HoaDon(int maHD, int maKH, Timestamp ngayLap, double tongTien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public int getMaHD() { return maHD; }
    public void setMaHD(int maHD) { this.maHD = maHD; }
    public int getMaKH() { return maKH; }
    public void setMaKH(int maKH) { this.maKH = maKH; }
    public Timestamp getNgayLap() { return ngayLap; }
    public void setNgayLap(Timestamp ngayLap) { this.ngayLap = ngayLap; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}
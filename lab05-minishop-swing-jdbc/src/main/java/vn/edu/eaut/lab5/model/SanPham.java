package vn.edu.eaut.lab5.model;

public class SanPham {
    private int maSP;
    private String tenSP;
    private double gia;
    private int soLuong;

    public SanPham() {}

    public SanPham(int maSP, String tenSP, double gia, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public SanPham(String tenSP, double gia, int soLuong) {
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }
    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    @Override
    public String toString() {
        return tenSP;
    }
}
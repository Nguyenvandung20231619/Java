package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.KhachHangDAL;
import vn.edu.eaut.lab5.model.KhachHang;

import java.util.List;

public class KhachHangBUS {
    private KhachHangDAL khDAL = new KhachHangDAL();

    public List<KhachHang> getAll() {
        return khDAL.getAll();
    }

    public boolean add(KhachHang kh) {
        if (kh.getHoTen() == null || kh.getHoTen().trim().isEmpty()) return false;
        return khDAL.insert(kh);
    }

    public boolean update(KhachHang kh) {
        if (kh.getMaKH() <= 0) return false;
        return khDAL.update(kh);
    }

    public boolean delete(int maKH) {
        return khDAL.delete(maKH);
    }
}
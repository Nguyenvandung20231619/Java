package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.SanPhamDAL;
import vn.edu.eaut.lab5.model.SanPham;

import java.util.List;

public class SanPhamBUS {
    private SanPhamDAL spDAL = new SanPhamDAL();

    public List<SanPham> getAll() {
        return spDAL.getAll();
    }

    public boolean add(SanPham sp) {
        if (sp.getTenSP() == null || sp.getTenSP().trim().isEmpty()) return false;
        if (sp.getGia() < 0 || sp.getSoLuong() < 0) return false;
        return spDAL.insert(sp);
    }

    public boolean update(SanPham sp) {
        if (sp.getMaSP() <= 0) return false;
        return spDAL.update(sp);
    }

    public boolean delete(int maSP) {
        return spDAL.delete(maSP);
    }
}
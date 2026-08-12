package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.HoaDonDAL;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.HoaDon;

import java.util.List;

public class HoaDonBUS {
    private HoaDonDAL hdDAL = new HoaDonDAL();

    public boolean taoHoaDon(HoaDon hd, List<ChiTietHoaDon> listCTHD) {
        if (hd.getMaKH() <= 0 || listCTHD.isEmpty()) return false;
        return hdDAL.insertHoaDon(hd, listCTHD);
    }
}
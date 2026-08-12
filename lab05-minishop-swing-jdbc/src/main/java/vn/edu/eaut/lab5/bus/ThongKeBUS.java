package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.ThongKeDAL;

public class ThongKeBUS {
    private ThongKeDAL tkDAL = new ThongKeDAL();

    public double getTongDoanhThu() {
        return tkDAL.getTongDoanhThu();
    }

    public int getTongHoaDon() {
        return tkDAL.getTongHoaDon();
    }
}
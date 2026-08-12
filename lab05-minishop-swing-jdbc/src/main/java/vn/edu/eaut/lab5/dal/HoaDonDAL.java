package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.HoaDon;

import java.sql.*;
import java.util.List;

public class HoaDonDAL {
    public boolean insertHoaDon(HoaDon hd, List<ChiTietHoaDon> listCTHD) {
        String sqlHD = "INSERT INTO hoa_don(ma_kh, tong_tien) VALUES(?, ?)";
        String sqlCT = "INSERT INTO chi_tiet_hoa_don(ma_hd, ma_sp, so_luong, don_gia) VALUES(?, ?, ?, ?)";
        String sqlUpdateSP = "UPDATE san_pham SET so_luong = so_luong - ? WHERE ma_sp = ?";

        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement stmtHD = conn.prepareStatement(sqlHD, Statement.RETURN_GENERATED_KEYS);
            stmtHD.setInt(1, hd.getMaKH());
            stmtHD.setDouble(2, hd.getTongTien());
            stmtHD.executeUpdate();

            ResultSet rs = stmtHD.getGeneratedKeys();
            int maHD = 0;
            if (rs.next()) {
                maHD = rs.getInt(1);
            }

            PreparedStatement stmtCT = conn.prepareStatement(sqlCT);
            PreparedStatement stmtSP = conn.prepareStatement(sqlUpdateSP);

            for (ChiTietHoaDon ct : listCTHD) {
                stmtCT.setInt(1, maHD);
                stmtCT.setInt(2, ct.getMaSP());
                stmtCT.setInt(3, ct.getSoLuong());
                stmtCT.setDouble(4, ct.getDonGia());
                stmtCT.addBatch();

                stmtSP.setInt(1, ct.getSoLuong());
                stmtSP.setInt(2, ct.getMaSP());
                stmtSP.addBatch();
            }

            stmtCT.executeBatch();
            stmtSP.executeBatch();

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
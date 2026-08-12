package vn.edu.eaut.lab5.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.SanPham;

public class SanPhamDAL {

    // 1. Lấy toàn bộ danh sách sản phẩm
    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT ma_sp, ten_sp, don_gia, so_luong FROM san_pham";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("ma_sp"));
                sp.setTenSP(rs.getString("ten_sp"));
                sp.setGia(rs.getDouble("don_gia")); // Khớp với cột don_gia trong CSDL
                sp.setSoLuong(rs.getInt("so_luong"));
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới sản phẩm
    public boolean insert(SanPham sp) {
        String sql = "INSERT INTO san_pham (ten_sp, don_gia, so_luong) VALUES (?, ?, ?)";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sp.getTenSP());
            stmt.setDouble(2, sp.getGia());
            stmt.setInt(3, sp.getSoLuong());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Cập nhật sản phẩm
    public boolean update(SanPham sp) {
        String sql = "UPDATE san_pham SET ten_sp = ?, don_gia = ?, so_luong = ? WHERE ma_sp = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sp.getTenSP());
            stmt.setDouble(2, sp.getGia());
            stmt.setInt(3, sp.getSoLuong());
            stmt.setInt(4, sp.getMaSP());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Xóa sản phẩm theo mã
    public boolean delete(int maSP) {
        String sql = "DELETE FROM san_pham WHERE ma_sp = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maSP);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Tìm kiếm sản phẩm theo tên
    public List<SanPham> searchByName(String keyword) {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT ma_sp, ten_sp, don_gia, so_luong FROM san_pham WHERE ten_sp LIKE ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getInt("ma_sp"));
                    sp.setTenSP(rs.getString("ten_sp"));
                    sp.setGia(rs.getDouble("don_gia"));
                    sp.setSoLuong(rs.getInt("so_luong"));
                    list.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
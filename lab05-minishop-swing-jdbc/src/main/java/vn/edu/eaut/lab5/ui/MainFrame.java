package vn.edu.eaut.lab5.ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Quản Lý Cửa Hàng - MiniShop (Swing + JDBC)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        SanPhamPanel pnlSanPham = new SanPhamPanel();
        KhachHangPanel pnlKhachHang = new KhachHangPanel();
        HoaDonPanel pnlHoaDon = new HoaDonPanel();
        ThongKePanel pnlThongKe = new ThongKePanel();

        tabbedPane.addTab("Sản phẩm", pnlSanPham);
        tabbedPane.addTab("Khách hàng", pnlKhachHang);
        tabbedPane.addTab("Bán hàng", pnlHoaDon);
        tabbedPane.addTab("Thống kê", pnlThongKe);

        // Load lại dữ liệu combobox khi chọn tab Bán hàng
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 2) {
                pnlHoaDon.loadCombobox();
            } else if (tabbedPane.getSelectedIndex() == 3) {
                pnlThongKe.loadThongKe();
            }
        });

        add(tabbedPane);
    }
}
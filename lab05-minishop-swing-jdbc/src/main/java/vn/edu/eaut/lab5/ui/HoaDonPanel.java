package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.HoaDonBUS;
import vn.edu.eaut.lab5.bus.KhachHangBUS;
import vn.edu.eaut.lab5.bus.SanPhamBUS;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.HoaDon;
import vn.edu.eaut.lab5.model.KhachHang;
import vn.edu.eaut.lab5.model.SanPham;
import vn.edu.eaut.lab5.util.MessageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonPanel extends JPanel {
    private JComboBox<KhachHang> cbKhachHang;
    private JComboBox<SanPham> cbSanPham;
    private JTextField txtSoLuong;
    private JLabel lblTongTien;
    private JTable table;
    private DefaultTableModel model;

    private SanPhamBUS spBUS = new SanPhamBUS();
    private KhachHangBUS khBUS = new KhachHangBUS();
    private HoaDonBUS hdBUS = new HoaDonBUS();

    private List<ChiTietHoaDon> listCTHD = new ArrayList<>();
    private double tongTien = 0;

    public HoaDonPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel pnlTop = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlTop.setBorder(BorderFactory.createTitledBorder("Bán hàng"));
        
        cbKhachHang = new JComboBox<>();
        cbSanPham = new JComboBox<>();
        txtSoLuong = new JTextField("1");

        pnlTop.add(new JLabel("Khách hàng:")); pnlTop.add(cbKhachHang);
        pnlTop.add(new JLabel("Chọn sản phẩm:")); pnlTop.add(cbSanPham);
        pnlTop.add(new JLabel("Số lượng mua:")); pnlTop.add(txtSoLuong);

        JButton btnThemSP = new JButton("Thêm vào giỏ");
        JPanel pnlAdd = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlAdd.add(btnThemSP);

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.add(pnlTop, BorderLayout.CENTER);
        pnlHeader.add(pnlAdd, BorderLayout.SOUTH);
        add(pnlHeader, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 16));
        JButton btnThanhToan = new JButton("Thanh toán & Lưu hóa đơn");

        pnlBottom.add(lblTongTien, BorderLayout.WEST);
        pnlBottom.add(btnThanhToan, BorderLayout.EAST);
        add(pnlBottom, BorderLayout.SOUTH);

        loadCombobox();
        btnThemSP.addActionListener(e -> addToCart());
        btnThanhToan.addActionListener(e -> thanhToan());
    }

    public void loadCombobox() {
        cbKhachHang.removeAllItems();
        cbSanPham.removeAllItems();
        for (KhachHang kh : khBUS.getAll()) cbKhachHang.addItem(kh);
        for (SanPham sp : spBUS.getAll()) cbSanPham.addItem(sp);
    }

    private void addToCart() {
        SanPham sp = (SanPham) cbSanPham.getSelectedItem();
        if (sp == null) return;
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0 || sl > sp.getSoLuong()) {
                MessageUtil.showError(this, "Số lượng không hợp lệ hoặc vượt quá tồn kho!");
                return;
            }
            double thànhTiền = sl * sp.getGia();
            listCTHD.add(new ChiTietHoaDon(0, sp.getMaSP(), sl, sp.getGia()));
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sl, sp.getGia(), thànhTiền});
            
            tongTien += thànhTiền;
            lblTongTien.setText("Tổng tiền: " + tongTien + " VNĐ");
        } catch (Exception ex) {
            MessageUtil.showError(this, "Số lượng phải là số nguyên!");
        }
    }

    private void thanhToan() {
        KhachHang kh = (KhachHang) cbKhachHang.getSelectedItem();
        if (kh == null || listCTHD.isEmpty()) {
            MessageUtil.showError(this, "Chưa chọn khách hàng hoặc giỏ hàng trống!");
            return;
        }

        HoaDon hd = new HoaDon(0, kh.getMaKH(), null, tongTien);
        if (hdBUS.taoHoaDon(hd, listCTHD)) {
            MessageUtil.showInfo(this, "Thanh toán thành công!");
            listCTHD.clear();
            model.setRowCount(0);
            tongTien = 0;
            lblTongTien.setText("Tổng tiền: 0 VNĐ");
            loadCombobox();
        } else {
            MessageUtil.showError(this, "Lỗi thanh toán!");
        }
    }
}
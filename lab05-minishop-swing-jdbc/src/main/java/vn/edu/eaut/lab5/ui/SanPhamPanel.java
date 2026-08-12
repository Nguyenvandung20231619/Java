package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.SanPhamBUS;
import vn.edu.eaut.lab5.model.SanPham;
import vn.edu.eaut.lab5.util.MessageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SanPhamPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtTen, txtGia, txtSoLuong;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private SanPhamBUS spBUS = new SanPhamBUS();
    private int selectedId = -1;

    public SanPhamPanel() {
        setLayout(new BorderLayout(10, 10));

        // Form nhập liệu
        JPanel pnlForm = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        pnlForm.add(new JLabel("Tên SP:")); txtTen = new JTextField(); pnlForm.add(txtTen);
        pnlForm.add(new JLabel("Giá:")); txtGia = new JTextField(); pnlForm.add(txtGia);
        pnlForm.add(new JLabel("Số lượng:")); txtSoLuong = new JTextField(); pnlForm.add(txtSoLuong);

        // Buttons
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        pnlButtons.add(btnThem); pnlButtons.add(btnSua); pnlButtons.add(btnXoa); pnlButtons.add(btnLamMoi);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlForm, BorderLayout.CENTER);
        pnlTop.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlTop, BorderLayout.NORTH);

        // Bảng dữ liệu
        model = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Giá", "Số lượng"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Events
        loadData();
        table.getSelectionModel().addListSelectionListener(e -> fillForm());
        btnThem.addActionListener(e -> addSP());
        btnSua.addActionListener(e -> updateSP());
        btnXoa.addActionListener(e -> deleteSP());
        btnLamMoi.addActionListener(e -> clearForm());
    }

    private void loadData() {
        model.setRowCount(0);
        List<SanPham> list = spBUS.getAll();
        for (SanPham sp : list) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getGia(), sp.getSoLuong()});
        }
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            selectedId = (int) model.getValueAt(row, 0);
            txtTen.setText(model.getValueAt(row, 1).toString());
            txtGia.setText(model.getValueAt(row, 2).toString());
            txtSoLuong.setText(model.getValueAt(row, 3).toString());
        }
    }

    private void clearForm() {
        selectedId = -1;
        txtTen.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        table.clearSelection();
    }

    private void addSP() {
        try {
            SanPham sp = new SanPham(txtTen.getText(), Double.parseDouble(txtGia.getText()), Integer.parseInt(txtSoLuong.getText()));
            if (spBUS.add(sp)) {
                MessageUtil.showInfo(this, "Thêm thành công!");
                loadData(); clearForm();
            } else MessageUtil.showError(this, "Thêm thất bại!");
        } catch (Exception ex) { MessageUtil.showError(this, "Dữ liệu nhập không hợp lệ!"); }
    }

    private void updateSP() {
        if (selectedId == -1) return;
        try {
            SanPham sp = new SanPham(selectedId, txtTen.getText(), Double.parseDouble(txtGia.getText()), Integer.parseInt(txtSoLuong.getText()));
            if (spBUS.update(sp)) {
                MessageUtil.showInfo(this, "Cập nhật thành công!");
                loadData(); clearForm();
            } else MessageUtil.showError(this, "Cập nhật thất bại!");
        } catch (Exception ex) { MessageUtil.showError(this, "Dữ liệu nhập không hợp lệ!"); }
    }

    private void deleteSP() {
        if (selectedId == -1) return;
        if (MessageUtil.showConfirm(this, "Bạn có chắc muốn xóa?")) {
            if (spBUS.delete(selectedId)) {
                MessageUtil.showInfo(this, "Xóa thành công!");
                loadData(); clearForm();
            } else MessageUtil.showError(this, "Xóa thất bại!");
        }
    }
}
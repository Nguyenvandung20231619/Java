package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.KhachHangBUS;
import vn.edu.eaut.lab5.model.KhachHang;
import vn.edu.eaut.lab5.util.MessageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KhachHangPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtTen, txtSdt, txtDiaChi;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private KhachHangBUS khBUS = new KhachHangBUS();
    private int selectedId = -1;

    public KhachHangPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        pnlForm.add(new JLabel("Họ tên:")); txtTen = new JTextField(); pnlForm.add(txtTen);
        pnlForm.add(new JLabel("SĐT:")); txtSdt = new JTextField(); pnlForm.add(txtSdt);
        pnlForm.add(new JLabel("Địa chỉ:")); txtDiaChi = new JTextField(); pnlForm.add(txtDiaChi);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm"); btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa"); btnLamMoi = new JButton("Làm mới");
        pnlButtons.add(btnThem); pnlButtons.add(btnSua); pnlButtons.add(btnXoa); pnlButtons.add(btnLamMoi);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlForm, BorderLayout.CENTER);
        pnlTop.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlTop, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Mã KH", "Họ tên", "SĐT", "Địa chỉ"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
        table.getSelectionModel().addListSelectionListener(e -> fillForm());
        btnThem.addActionListener(e -> addKH());
        btnSua.addActionListener(e -> updateKH());
        btnXoa.addActionListener(e -> deleteKH());
        btnLamMoi.addActionListener(e -> clearForm());
    }

    private void loadData() {
        model.setRowCount(0);
        List<KhachHang> list = khBUS.getAll();
        for (KhachHang kh : list) {
            model.addRow(new Object[]{kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getDiaChi()});
        }
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            selectedId = (int) model.getValueAt(row, 0);
            txtTen.setText(model.getValueAt(row, 1).toString());
            txtSdt.setText(model.getValueAt(row, 2).toString());
            txtDiaChi.setText(model.getValueAt(row, 3).toString());
        }
    }

    private void clearForm() {
        selectedId = -1;
        txtTen.setText(""); txtSdt.setText(""); txtDiaChi.setText("");
        table.clearSelection();
    }

    private void addKH() {
        KhachHang kh = new KhachHang(txtTen.getText(), txtSdt.getText(), txtDiaChi.getText());
        if (khBUS.add(kh)) {
            MessageUtil.showInfo(this, "Thêm khách hàng thành công!");
            loadData(); clearForm();
        } else MessageUtil.showError(this, "Vui lòng nhập đầy đủ thông tin!");
    }

    private void updateKH() {
        if (selectedId == -1) return;
        KhachHang kh = new KhachHang(selectedId, txtTen.getText(), txtSdt.getText(), txtDiaChi.getText());
        if (khBUS.update(kh)) {
            MessageUtil.showInfo(this, "Cập nhật thành công!");
            loadData(); clearForm();
        } else MessageUtil.showError(this, "Cập nhật thất bại!");
    }

    private void deleteKH() {
        if (selectedId == -1) return;
        if (MessageUtil.showConfirm(this, "Xác nhận xóa khách hàng này?")) {
            if (khBUS.delete(selectedId)) {
                MessageUtil.showInfo(this, "Xóa thành công!");
                loadData(); clearForm();
            } else MessageUtil.showError(this, "Không thể xóa khách hàng đã có hóa đơn!");
        }
    }
}
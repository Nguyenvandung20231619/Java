package vn.edu.eaut.lab3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bai08QuanLySinhVien extends JFrame {
    private final JTextField txtId = new JTextField(15);
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtGpa = new JTextField(15);

    private final DefaultTableModel tableModel;
    private final JTable table;
    private final List<Student> studentList = new ArrayList<>();

    public Bai08QuanLySinhVien() {
        setTitle("Bài 8 - Quản Lý Sinh Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Form nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        inputPanel.add(new JLabel("Mã sinh viên:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Họ và tên:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Điểm TB:"));
        inputPanel.add(txtGpa);

        // Nút chức năng
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.CENTER);
        topContainer.add(btnPanel, BorderLayout.SOUTH);

        // Cấu hình Bảng JTable
        String[] columns = {"Mã SV", "Họ và Tên", "Điểm TB", "Xếp loại"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép sửa trực tiếp ô trên bảng
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(topContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Sự kiện click vào 1 dòng trong JTable
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Student s = studentList.get(selectedRow);
                txtId.setText(s.getId());
                txtName.setText(s.getName());
                txtGpa.setText(String.valueOf(s.getGpa()));
                txtId.setEditable(false); // Không sửa Mã SV khi chọn
            }
        });

        // Bắt sự kiện nút bấm
        btnAdd.addActionListener(e -> themSinhVien());
        btnEdit.addActionListener(e -> suaSinhVien());
        btnDelete.addActionListener(e -> xoaSinhVien());
        btnClear.addActionListener(e -> lamMoi());

        setSize(600, 450);
        setLocationRelativeTo(null);
    }

    private void themSinhVien() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String gpaStr = txtGpa.getText().trim();

        if (id.isEmpty() || name.isEmpty() || gpaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra trùng Mã SV
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            double gpa = Double.parseDouble(gpaStr);
            if (gpa < 0.0 || gpa > 10.0) {
                JOptionPane.showMessageDialog(this, "Điểm TB phải từ 0.0 đến 10.0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student s = new Student(id, name, gpa);
            studentList.add(s);
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getGpa(), s.xepLoai()});
            lamMoi();
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm TB phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suaSinhVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng sinh viên cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = txtName.getText().trim();
        String gpaStr = txtGpa.getText().trim();

        try {
            double gpa = Double.parseDouble(gpaStr);
            if (gpa < 0.0 || gpa > 10.0) {
                JOptionPane.showMessageDialog(this, "Điểm TB phải từ 0.0 đến 10.0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student s = studentList.get(selectedRow);
            s.setName(name);
            s.setGpa(gpa);

            // Cập nhật lại JTable
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(gpa, selectedRow, 2);
            tableModel.setValueAt(s.xepLoai(), selectedRow, 3);

            lamMoi();
            JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thành công!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm TB phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaSinhVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng sinh viên cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            studentList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            lamMoi();
        }
    }

    private void lamMoi() {
        txtId.setText("");
        txtName.setText("");
        txtGpa.setText("");
        txtId.setEditable(true);
        table.clearSelection();
        txtId.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai08QuanLySinhVien().setVisible(true));
    }
}
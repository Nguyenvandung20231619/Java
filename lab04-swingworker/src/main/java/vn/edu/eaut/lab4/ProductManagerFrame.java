package vn.edu.eaut.lab4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ProductManagerFrame extends JFrame {
    private JTextField txtId, txtName, txtPrice;
    private JButton btnAdd, btnEdit, btnDelete, btnSaveCsv, btnLoadCsv;
    private JTable tableProducts;
    private DefaultTableModel tableModel;

    public ProductManagerFrame() {
        setTitle("Bài 10 - Mini Project: Quản lý sản phẩm CSV");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inputs
        txtId = new JTextField(10);
        txtName = new JTextField(15);
        txtPrice = new JTextField(10);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Mã SP:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Tên SP:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Đơn giá:"));
        formPanel.add(txtPrice);

        // Buttons
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnSaveCsv = new JButton("Lưu file CSV");
        btnLoadCsv = new JButton("Đọc file CSV");

        JPanel actionPanel = new JPanel();
        actionPanel.add(btnAdd);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);
        actionPanel.add(btnSaveCsv);
        actionPanel.add(btnLoadCsv);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(formPanel, BorderLayout.CENTER);
        topContainer.add(actionPanel, BorderLayout.SOUTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Đơn giá"}, 0);
        tableProducts = new JTable(tableModel);

        add(topContainer, BorderLayout.NORTH);
        add(new JScrollPane(tableProducts), BorderLayout.CENTER);

        // Events
        tableProducts.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());
        btnAdd.addActionListener(e -> addProduct());
        btnEdit.addActionListener(e -> editProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnSaveCsv.addActionListener(e -> saveCsvAsync());
        btnLoadCsv.addActionListener(e -> loadCsvAsync());
    }

    private void fillFormFromTable() {
        int selectedRow = tableProducts.getSelectedRow();
        if (selectedRow >= 0) {
            txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtPrice.setText(tableModel.getValueAt(selectedRow, 2).toString());
        }
    }

    private void addProduct() {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        tableModel.addRow(new Object[]{txtId.getText().trim(), txtName.getText().trim(), txtPrice.getText().trim()});
        clearInputs();
    }

    private void editProduct() {
        int selectedRow = tableProducts.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txtId.getText().trim(), selectedRow, 0);
            tableModel.setValueAt(txtName.getText().trim(), selectedRow, 1);
            tableModel.setValueAt(txtPrice.getText().trim(), selectedRow, 2);
            clearInputs();
        } else {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
        }
    }

    private void deleteProduct() {
        int selectedRow = tableProducts.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            clearInputs();
        } else {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
        }
    }

    private void clearInputs() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
    }

    private void loadCsvAsync() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File file = chooser.getSelectedFile();

        btnLoadCsv.setEnabled(false);
        SwingWorker<List<String[]>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<String[]> doInBackground() throws Exception {
                List<String[]> rows = new ArrayList<>();
                try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 3) {
                            rows.add(parts);
                        }
                    }
                }
                return rows;
            }

            @Override
            protected void done() {
                try {
                    List<String[]> rows = get();
                    tableModel.setRowCount(0);
                    for (String[] row : rows) {
                        tableModel.addRow(row);
                    }
                    JOptionPane.showMessageDialog(ProductManagerFrame.this, "Đọc file thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProductManagerFrame.this, "Lỗi đọc file CSV!");
                }
                btnLoadCsv.setEnabled(true);
            }
        };
        worker.execute();
    }

    private void saveCsvAsync() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để lưu");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File file = chooser.getSelectedFile();

        btnSaveCsv.setEnabled(false);
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String line = String.format("%s,%s,%s",
                                tableModel.getValueAt(i, 0),
                                tableModel.getValueAt(i, 1),
                                tableModel.getValueAt(i, 2));
                        writer.write(line);
                        writer.newLine();
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(ProductManagerFrame.this, "Lưu file thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProductManagerFrame.this, "Lỗi lưu file!");
                }
                btnSaveCsv.setEnabled(true);
            }
        };
        worker.execute();
    }
}
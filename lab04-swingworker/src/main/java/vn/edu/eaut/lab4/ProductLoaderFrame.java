package vn.edu.eaut.lab4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductLoaderFrame extends JFrame {
    private JButton btnLoad;
    private JProgressBar progressBar;
    private JLabel lblStatus;
    private JTable tableProducts;
    private DefaultTableModel tableModel;

    public ProductLoaderFrame() {
        setTitle("Bài 9 - Mô phỏng tải danh sách sản phẩm");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnLoad = new JButton("Tải sản phẩm");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        lblStatus = new JLabel("Chưa tải dữ liệu");

        tableModel = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Đơn giá"}, 0);
        tableProducts = new JTable(tableModel);

        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        topPanel.add(btnLoad);
        topPanel.add(progressBar);
        topPanel.add(lblStatus);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tableProducts), BorderLayout.CENTER);

        btnLoad.addActionListener(e -> loadProducts());
    }

    private static class Product {
        String id, name;
        double price;

        Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    private void loadProducts() {
        btnLoad.setEnabled(false);
        tableModel.setRowCount(0);
        lblStatus.setText("Đang kết nối CSDL giả lập...");

        SwingWorker<List<Product>, Integer> worker = new SwingWorker<>() {
            @Override
            protected List<Product> doInBackground() throws Exception {
                List<Product> list = List.of(
                        new Product("SP01", "Bàn phím", 250000),
                        new Product("SP02", "Chuột", 150000),
                        new Product("SP03", "Màn hình", 2500000),
                        new Product("SP04", "Tai nghe", 450000),
                        new Product("SP05", "Loa Bluetooth", 850000)
                );

                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(200); // Giả lập độ trễ IO/Database
                    setProgress(i * 10);
                }
                return list;
            }

            @Override
            protected void done() {
                try {
                    List<Product> list = get();
                    for (Product p : list) {
                        tableModel.addRow(new Object[]{p.id, p.name, String.format("%.0f", p.price)});
                    }
                    lblStatus.setText("Tải danh sách hoàn tất (" + list.size() + " sản phẩm)");
                } catch (Exception ex) {
                    lblStatus.setText("Lỗi tải dữ liệu");
                }
                btnLoad.setEnabled(true);
            }
        };

        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        worker.execute();
    }
}
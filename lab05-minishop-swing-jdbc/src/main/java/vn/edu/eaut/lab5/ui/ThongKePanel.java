package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.ThongKeBUS;

import javax.swing.*;
import java.awt.*;

public class ThongKePanel extends JPanel {
    private JLabel lblDoanhThu, lblSoHoaDon;
    private ThongKeBUS tkBUS = new ThongKeBUS();

    public ThongKePanel() {
        setLayout(new GridLayout(2, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel pnlDoanhThu = new JPanel(new BorderLayout());
        pnlDoanhThu.setBorder(BorderFactory.createTitledBorder("Tổng Doanh Thu"));
        lblDoanhThu = new JLabel("0 VNĐ", SwingConstants.CENTER);
        lblDoanhThu.setFont(new Font("Arial", Font.BOLD, 24));
        lblDoanhThu.setForeground(Color.RED);
        pnlDoanhThu.add(lblDoanhThu, BorderLayout.CENTER);

        JPanel pnlHoaDon = new JPanel(new BorderLayout());
        pnlHoaDon.setBorder(BorderFactory.createTitledBorder("Tổng Số Hóa Đơn Đã Bán"));
        lblSoHoaDon = new JLabel("0", SwingConstants.CENTER);
        lblSoHoaDon.setFont(new Font("Arial", Font.BOLD, 24));
        lblSoHoaDon.setForeground(Color.BLUE);
        pnlHoaDon.add(lblSoHoaDon, BorderLayout.CENTER);

        add(pnlDoanhThu);
        add(pnlHoaDon);

        JButton btnRefresh = new JButton("Cập nhật lại thống kê");
        btnRefresh.addActionListener(e -> loadThongKe());
        add(btnRefresh);

        loadThongKe();
    }

    public void loadThongKe() {
        lblDoanhThu.setText(tkBUS.getTongDoanhThu() + " VNĐ");
        lblSoHoaDon.setText(tkBUS.getTongHoaDon() + " Hóa đơn");
    }
}
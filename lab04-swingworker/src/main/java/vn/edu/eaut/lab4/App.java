package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {
        setTitle("Lab 04 - Menu Bài Tập SwingWorker");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnB1 = new JButton("Bài 1: Đồng hồ đếm ngược");
        JButton btnB2 = new JButton("Bài 2: Mô phỏng tải dữ liệu");
        JButton btnB3 = new JButton("Bài 3: Tính tổng số nguyên tố");
        JButton btnB4 = new JButton("Bài 4: Tìm Fibonacci (Memoization)");
        JButton btnB5 = new JButton("Bài 5: Đọc file & đếm số dòng");
        JButton btnB6 = new JButton("Bài 6: Hủy tác vụ SwingWorker");
        JButton btnB7 = new JButton("Bài 7: Tìm từ khóa trong file");
        JButton btnB8 = new JButton("Bài 8: Đọc CSV điểm sinh viên");
        JButton btnB9 = new JButton("Bài 9: Mô phỏng tải sản phẩm");
        JButton btnB10 = new JButton("Bài 10: Mini Project Quản lý sản phẩm");

        btnB1.addActionListener(e -> new CountdownFrame().setVisible(true));
        btnB2.addActionListener(e -> new ProgressDemoFrame().setVisible(true));
        btnB3.addActionListener(e -> new PrimeSumFrame().setVisible(true));
        btnB4.addActionListener(e -> new FibonacciFrame().setVisible(true));
        btnB5.addActionListener(e -> new FileLineCounterFrame().setVisible(true));
        btnB6.addActionListener(e -> new CancelTaskFrame().setVisible(true));
        btnB7.addActionListener(e -> new SearchKeywordFrame().setVisible(true));
        btnB8.addActionListener(e -> new CsvStudentFrame().setVisible(true));
        btnB9.addActionListener(e -> new ProductLoaderFrame().setVisible(true));
        btnB10.addActionListener(e -> new ProductManagerFrame().setVisible(true));

        panel.add(btnB1);
        panel.add(btnB2);
        panel.add(btnB3);
        panel.add(btnB4);
        panel.add(btnB5);
        panel.add(btnB6);
        panel.add(btnB7);
        panel.add(btnB8);
        panel.add(btnB9);
        panel.add(btnB10);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new App().setVisible(true);
        });
    }
}
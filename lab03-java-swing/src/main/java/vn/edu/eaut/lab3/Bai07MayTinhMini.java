package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai07MayTinhMini extends JFrame {
    private final JTextField txtNum1 = new JTextField(10);
    private final JTextField txtNum2 = new JTextField(10);
    private final JTextField txtResult = new JTextField(10);
    private final JTextArea txtHistory = new JTextArea(8, 30);

    public Bai07MayTinhMini() {
        setTitle("Bài 7 - Máy Tính Mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu và nút bấm
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // Hàng 1: Ô nhập dữ liệu
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Số 1:"));
        inputPanel.add(txtNum1);
        inputPanel.add(new JLabel("Số 2:"));
        inputPanel.add(txtNum2);

        // Hàng 2: Các nút phép tính
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Cộng (+)");
        JButton btnSub = new JButton("Trừ (-)");
        JButton btnMul = new JButton("Nhân (*)");
        JButton btnDiv = new JButton("Chia (/)");
        JButton btnClear = new JButton("Clear");

        btnPanel.add(btnAdd);
        btnPanel.add(btnSub);
        btnPanel.add(btnMul);
        btnPanel.add(btnDiv);
        btnPanel.add(btnClear);

        // Hàng 3: Kết quả
        JPanel resultPanel = new JPanel(new FlowLayout());
        resultPanel.add(new JLabel("Kết quả:"));
        txtResult.setEditable(false);
        resultPanel.add(txtResult);

        topPanel.add(inputPanel);
        topPanel.add(btnPanel);
        topPanel.add(resultPanel);

        // Lịch sử phép tính
        txtHistory.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtHistory);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lịch sử tính toán"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Bắt sự kiện nút bấm
        btnAdd.addActionListener(e -> tinhToan('+'));
        btnSub.addActionListener(e -> tinhToan('-'));
        btnMul.addActionListener(e -> tinhToan('*'));
        btnDiv.addActionListener(e -> tinhToan('/'));
        btnClear.addActionListener(e -> xoaForm());

        pack();
        setLocationRelativeTo(null);
    }

    private void tinhToan(char phepTinh) {
        try {
            double num1 = Double.parseDouble(txtNum1.getText().trim());
            double num2 = Double.parseDouble(txtNum2.getText().trim());
            double res = 0;

            if (phepTinh == '/' && Math.abs(num2) < 1e-9) {
                JOptionPane.showMessageDialog(this, "Lỗi: Không thể chia cho 0!", "Lỗi toán học", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (phepTinh) {
                case '+': res = num1 + num2; break;
                case '-': res = num1 - num2; break;
                case '*': res = num1 * num2; break;
                case '/': res = num1 / num2; break;
            }

            txtResult.setText(String.format("%.4f", res));
            String record = String.format("%.2f %c %.2f = %.4f\n", num1, phepTinh, num2, res);
            txtHistory.append(record);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào phải là số hợp lệ!", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaForm() {
        txtNum1.setText("");
        txtNum2.setText("");
        txtResult.setText("");
        txtNum1.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai07MayTinhMini().setVisible(true));
    }
}
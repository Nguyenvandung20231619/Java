package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai06LoginForm extends JFrame {
    private final JTextField txtUsername = new JTextField(15);
    private final JPasswordField txtPassword = new JPasswordField(15);
    private final JComboBox<String> cbRole = new JComboBox<>(new String[]{"Admin", "User"});
    private final JCheckBox chkShowPassword = new JCheckBox("Hiển thị mật khẩu");

    public Bai06LoginForm() {
        setTitle("Bài 6 - Form Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel chứa các trường nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Dòng 0: Tài khoản
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Tài khoản:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtUsername, gbc);

        // Dòng 1: Mật khẩu
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtPassword, gbc);

        // Dòng 2: Vai trò
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(cbRole, gbc);

        // Dòng 3: Hiển thị mật khẩu
        gbc.gridx = 1; gbc.gridy = 3;
        inputPanel.add(chkShowPassword, gbc);

        // Chức năng ẩn/hiện mật khẩu
        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('•');
            }
        });

        // Nút Đăng nhập
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(e -> xuLyDangNhap());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnLogin);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void xuLyDangNhap() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String role = (String) cbRole.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra thông tin tài khoản theo quy ước
        boolean isSuccess = false;
        if ("Admin".equalsIgnoreCase(role) && "admin".equals(username) && "123456".equals(password)) {
            isSuccess = true;
        } else if ("User".equalsIgnoreCase(role) && "user".equals(username) && "123456".equals(password)) {
            isSuccess = true;
        }

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Chào mừng " + role + ": " + username, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản, mật khẩu hoặc vai trò không chính xác!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai06LoginForm().setVisible(true));
    }
}
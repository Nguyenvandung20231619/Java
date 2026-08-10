package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class SearchKeywordFrame extends JFrame {
    private JButton btnChooseFile;
    private JTextField txtKeyword;
    private JButton btnSearch;
    private JTextArea txtAreaResults;
    private JLabel lblStatus;
    private File selectedFile;

    public SearchKeywordFrame() {
        setTitle("Bài 7 - Tìm kiếm từ khóa trong file");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnChooseFile = new JButton("Chọn file .txt");
        txtKeyword = new JTextField(15);
        btnSearch = new JButton("Tìm kiếm");
        txtAreaResults = new JTextArea();
        txtAreaResults.setEditable(false);
        lblStatus = new JLabel("Chưa chọn file.");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnChooseFile);
        topPanel.add(new JLabel("Từ khóa:"));
        topPanel.add(txtKeyword);
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(txtAreaResults), BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnChooseFile.addActionListener(e -> chooseFile());
        btnSearch.addActionListener(e -> searchKeyword());
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            lblStatus.setText("File: " + selectedFile.getName());
        }
    }

    private void searchKeyword() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn file trước");
            return;
        }
        String keyword = txtKeyword.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa");
            return;
        }

        btnSearch.setEnabled(false);
        txtAreaResults.setText("");
        lblStatus.setText("Đang tìm kiếm...");

        SwingWorker<Integer, String> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                int matchCount = 0;
                int lineNumber = 0;
                String lowerKeyword = keyword.toLowerCase();

                try (BufferedReader reader = Files.newBufferedReader(
                        selectedFile.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lineNumber++;
                        if (line.toLowerCase().contains(lowerKeyword)) {
                            matchCount++;
                            publish("Dòng " + lineNumber + ": " + line);
                        }
                    }
                }
                return matchCount;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String text : chunks) {
                    txtAreaResults.append(text + "\n");
                }
            }

            @Override
            protected void done() {
                try {
                    int total = get();
                    lblStatus.setText("Tìm thấy " + total + " dòng chứa từ khóa.");
                } catch (Exception e) {
                    lblStatus.setText("Lỗi trong quá trình đọc file.");
                }
                btnSearch.setEnabled(true);
            }
        };

        worker.execute();
    }
}
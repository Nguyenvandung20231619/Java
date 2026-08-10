package vn.edu.eaut.lab4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class CsvStudentFrame extends JFrame {
    private JButton btnLoadCsv;
    private JTable tableStudents;
    private DefaultTableModel tableModel;
    private JLabel lblAvgScore;
    private JLabel lblMaxStudent;

    public CsvStudentFrame() {
        setTitle("Bài 8 - Đọc CSV điểm sinh viên");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnLoadCsv = new JButton("Chọn và Đọc File CSV");
        tableModel = new DefaultTableModel(new String[]{"Mã SV", "Họ Tên", "Điểm"}, 0);
        tableStudents = new JTable(tableModel);

        lblAvgScore = new JLabel("Điểm trung bình: ");
        lblMaxStudent = new JLabel("Sinh viên cao điểm nhất: ");

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(lblAvgScore);
        infoPanel.add(lblMaxStudent);

        add(btnLoadCsv, BorderLayout.NORTH);
        add(new JScrollPane(tableStudents), BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        btnLoadCsv.addActionListener(e -> loadCsvData());
    }

    private static class Student {
        String code, name;
        double score;

        Student(String code, String name, double score) {
            this.code = code;
            this.name = name;
            this.score = score;
        }
    }

    private void loadCsvData() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File csvFile = chooser.getSelectedFile();
        btnLoadCsv.setEnabled(false);
        tableModel.setRowCount(0);

        SwingWorker<java.util.List<Student>, Void> worker = new SwingWorker<>() {
            @Override
            protected java.util.List<Student> doInBackground() throws Exception {
                java.util.List<Student> list = new ArrayList<>();
                try (BufferedReader reader = Files.newBufferedReader(
                        csvFile.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    boolean isHeader = true;
                    while ((line = reader.readLine()) != null) {
                        if (isHeader) {
                            isHeader = false;
                            continue;
                        }
                        String[] parts = line.split(",");
                        if (parts.length >= 3) {
                            list.add(new Student(parts[0].trim(), parts[1].trim(),
                                    Double.parseDouble(parts[2].trim())));
                        }
                    }
                }
                return list;
            }

            @Override
            protected void done() {
                try {
                    java.util.List<Student> list = get();
                    double sum = 0;
                    Student maxStudent = null;

                    for (Student s : list) {
                        tableModel.addRow(new Object[]{s.code, s.name, s.score});
                        sum += s.score;
                        if (maxStudent == null || s.score > maxStudent.score) {
                            maxStudent = s;
                        }
                    }

                    if (!list.isEmpty()) {
                        double avg = sum / list.size();
                        lblAvgScore.setText(String.format("Điểm trung bình: %.2f", avg));
                        lblMaxStudent.setText("Sinh viên cao điểm nhất: " +
                                maxStudent.name + " (" + maxStudent.score + ")");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CsvStudentFrame.this, "Lỗi đọc file CSV!");
                }
                btnLoadCsv.setEnabled(true);
            }
        };

        worker.execute();
    }
}
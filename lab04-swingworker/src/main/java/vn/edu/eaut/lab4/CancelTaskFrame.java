package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;

public class CancelTaskFrame extends JFrame {
    private JButton btnStart;
    private JButton btnCancel;
    private JProgressBar progressBar;
    private JLabel lblStatus;
    private SwingWorker<Void, Integer> currentWorker;

    public CancelTaskFrame() {
        setTitle("Bài 6 - Hủy tác vụ SwingWorker");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnStart = new JButton("Bắt đầu");
        btnCancel = new JButton("Hủy");
        btnCancel.setEnabled(false);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        lblStatus = new JLabel("Trạng thái: Sẵn sàng");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnStart);
        buttonPanel.add(btnCancel);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(buttonPanel);
        panel.add(progressBar);
        panel.add(lblStatus);

        add(panel);

        btnStart.addActionListener(e -> startTask());
        btnCancel.addActionListener(e -> cancelTask());
    }

    private void startTask() {
        btnStart.setEnabled(false);
        btnCancel.setEnabled(true);
        progressBar.setValue(0);
        lblStatus.setText("Trạng thái: Đang xử lý...");

        currentWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    Thread.sleep(100);
                    setProgress(i);
                }
                return null;
            }

            @Override
            protected void done() {
                if (isCancelled()) {
                    lblStatus.setText("Trạng thái: Đã hủy tác vụ");
                } else {
                    lblStatus.setText("Trạng thái: Hoàn thành!");
                    progressBar.setValue(100);
                }
                btnStart.setEnabled(true);
                btnCancel.setEnabled(false);
            }
        };

        currentWorker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        currentWorker.execute();
    }

    private void cancelTask() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }
    }
}
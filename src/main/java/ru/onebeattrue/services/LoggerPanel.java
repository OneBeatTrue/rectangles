package ru.onebeattrue.services;

import javax.swing.*;
import java.awt.*;

public class LoggerPanel extends JPanel implements Logger {
    private JTextArea logArea;

    public LoggerPanel(Font labelFont) {
        setLayout(new BorderLayout());
        logArea = new JTextArea(5, 50);
        logArea.setEditable(false);
        logArea.setFont(labelFont);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(960, 100));
    }

    @Override
    public void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}

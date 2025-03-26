package ru.onebeattrue.services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    public InputPanel(Font labelFont, Font buttonFont, Storage storage, DrawingPanel drawingPanel) {
        setLayout(new GridLayout(3, 1, 5, 5));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(new KeyboardPanel(labelFont, buttonFont, storage, drawingPanel));
        add(createFilePanel(labelFont, buttonFont, storage, drawingPanel));
        add(createRandomPanel(labelFont, buttonFont, storage, drawingPanel));
    }

    private static JPanel createFilePanel(Font labelFont, Font buttonFont, Storage storage, JPanel drawingPanel) {
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel fileLabel = new JLabel("File");
        fileLabel.setFont(labelFont);
        filePanel.add(fileLabel);
        JButton addFileBtn = new JButton("Add");
        addFileBtn.setFont(buttonFont);
        filePanel.add(addFileBtn);
        JTextField textField = new JTextField(35);
        filePanel.add(textField);
        addFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.add(textField.getText());
                drawingPanel.repaint();
            }
        });

        return filePanel;
    }

    private static JPanel createRandomPanel(Font labelFont, Font buttonFont, Storage storage, DrawingPanel drawingPanel) {
        JPanel randomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel randomLabel = new JLabel("Random");
        randomLabel.setFont(labelFont);
        randomPanel.add(randomLabel);
        JButton addRandomBtn = new JButton("Add");
        addRandomBtn.setFont(buttonFont);
        addRandomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.add(drawingPanel.getRanges());
                drawingPanel.repaint();
            }
        });
        randomPanel.add(addRandomBtn);

        return randomPanel;
    }
}
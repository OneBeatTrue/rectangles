package ru.onebeattrue.services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {
    public BottomPanel(Font buttonFont, Storage storage, DrawingPanel drawingPanel, JPanel logPanel) {
        setLayout(new BorderLayout(5, 5));

        add(logPanel, BorderLayout.CENTER);
        add(createControlPanel(buttonFont, storage, drawingPanel), BorderLayout.EAST);
    }

    private static JPanel createControlPanel(Font buttonFont, Storage storage, JPanel drawingPanel) {

        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JButton findBtn = new JButton("Find");
        findBtn.setFont(buttonFont);
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.findMaxDistance();
                drawingPanel.repaint();
            }
        });

        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(buttonFont);
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.clear();
                drawingPanel.repaint();
            }
        });

        controlPanel.add(findBtn);
        controlPanel.add(resetBtn);
        controlPanel.setPreferredSize(new Dimension(320, 100));

        return controlPanel;
    }
}
package ru.onebeattrue.services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KeyboardPanel extends JPanel {
    private JTextField[] textFields = new JTextField[6];

    private Storage storage;

    private JPanel drawingPanel;

    public KeyboardPanel(Font labelFont, Font buttonFont, Storage storage, JPanel drawingPanel) {
        this.storage = storage;
        this.drawingPanel = drawingPanel;
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel keyboardLabel = new JLabel("Keyboard");
        keyboardLabel.setFont(labelFont);
        add(keyboardLabel);

        JButton addKeyboardBtn = new JButton("Add");
        addKeyboardBtn.setFont(buttonFont);
        add(addKeyboardBtn);

        String[] labels = {"x1:", "y1:", "x2:", "y2:", "x3:", "y3:"};
        for (int i = 0; i < labels.length; i++) {
            JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

            JLabel label = new JLabel(labels[i]);
            label.setFont(labelFont);
            fieldPanel.add(label);

            this.textFields[i] = new JTextField(5);
            this.textFields[i].setFont(labelFont);
            fieldPanel.add(this.textFields[i]);

            add(fieldPanel);
        }

        addKeyboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.add(
                        textFields[0].getText(),
                        textFields[1].getText(),
                        textFields[2].getText(),
                        textFields[3].getText(),
                        textFields[4].getText(),
                        textFields[5].getText()
                );
                drawingPanel.repaint();
            }
        });
    }
}

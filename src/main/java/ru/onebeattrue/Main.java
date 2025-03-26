package ru.onebeattrue;

import ru.onebeattrue.services.DrawingPanel;
import ru.onebeattrue.services.KeyboardPanel;
import ru.onebeattrue.services.LoggerPanel;
import ru.onebeattrue.services.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Rectangle Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout(5, 5));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        LoggerPanel logger = new LoggerPanel(labelFont);
        Storage storage = new Storage(logger);
        JPanel drawingPanel = new DrawingPanel(storage);

        frame.add(createInputPanel(labelFont, buttonFont, storage, drawingPanel), BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(createBottomPanel(labelFont, buttonFont, logger), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createInputPanel(Font labelFont, Font buttonFont, Storage storage, JPanel drawingPanel) {
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        inputPanel.add(new KeyboardPanel(labelFont, buttonFont, storage, drawingPanel));
        inputPanel.add(createFilePanel(labelFont, buttonFont));
        inputPanel.add(createRandomPanel(labelFont, buttonFont));

        return inputPanel;
    }

    private static JPanel createFilePanel(Font labelFont, Font buttonFont) {
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel fileLabel = new JLabel("File");
        fileLabel.setFont(labelFont);
        filePanel.add(fileLabel);
        JButton addFileBtn = new JButton("Add");
        addFileBtn.setFont(buttonFont);
        filePanel.add(addFileBtn);
        filePanel.add(new JTextField(35));

        return filePanel;
    }

    private static JPanel createRandomPanel(Font labelFont, Font buttonFont) {
        JPanel randomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel randomLabel = new JLabel("Random");
        randomLabel.setFont(labelFont);
        randomPanel.add(randomLabel);
        JButton addRandomBtn = new JButton("Add");
        addRandomBtn.setFont(buttonFont);
        randomPanel.add(addRandomBtn);

        return randomPanel;
    }

    private static JPanel createBottomPanel(Font labelFont, Font buttonFont, JPanel logPanel) {
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));

        bottomPanel.add(logPanel, BorderLayout.CENTER);
        bottomPanel.add(createControlPanel(buttonFont), BorderLayout.EAST);

        return bottomPanel;
    }

    private static JPanel createControlPanel(Font buttonFont) {

        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JButton findBtn = new JButton("Find");
        findBtn.setFont(buttonFont);
        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(buttonFont);
        controlPanel.add(findBtn);
        controlPanel.add(resetBtn);
        controlPanel.setPreferredSize(new Dimension(320, 100));

        return controlPanel;
    }
}

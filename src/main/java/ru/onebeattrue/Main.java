package ru.onebeattrue;

import ru.onebeattrue.services.*;

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
        DrawingPanel drawingPanel = new DrawingPanel(storage, logger);
        JPanel inputPanel = new InputPanel(labelFont, buttonFont, storage, drawingPanel);
        JPanel bottomPanel = new BottomPanel(buttonFont, storage, drawingPanel, logger);


        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        logger.log("Hello. You can add rectangles and find maximal intersection diameter. Enjoy!");

        frame.setVisible(true);
    }
}

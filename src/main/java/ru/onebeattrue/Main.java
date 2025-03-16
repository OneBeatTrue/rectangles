package ru.onebeattrue;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
//        Rectangle r1 = new Rectangle(new Vertex(0, 0), new Vertex(6, 6));
//        Rectangle r2 = new Rectangle(new Vertex(0, 0), new Vertex(6, 6), Math.PI / 2);
//        Polygon p = r1.intersect(r2);
//        Segment s = p.findMaxDistance();
//        System.out.println();
//        for (Vertex v : r1.vertices) {
//            System.out.println(v.x + " " + v.y);
//        }
//        System.out.println();
//        for (Vertex v : r2.vertices) {
//            System.out.println(v.x + " " + v.y);
//        }
//        System.out.println();
//        for (Vertex v : p.vertices) {
//            System.out.println(v.x + " " + v.y);
//        }
//        System.out.println();
//        System.out.println(s == null);
//        System.out.println(s.length());
//        System.out.println(s.firstVertex.x + " " + s.firstVertex.y);
//        System.out.println(s.secondVertex.x + " " + s.secondVertex.y);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Rectangle Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout(5, 5));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel keyboardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel randomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        JLabel keyboardLabel = new JLabel("Keyboard");
        keyboardLabel.setFont(labelFont);
        keyboardPanel.add(keyboardLabel);
        JButton addKeyboardBtn = new JButton("Add");
        addKeyboardBtn.setFont(buttonFont);
        keyboardPanel.add(addKeyboardBtn);

        String[] labels = {"x1:", "y1:", "x2:", "y2:", "x3:", "y3:"};
        for (String text : labels) {
            JPanel fieldPanel = new JPanel(new GridLayout(1, 2, 5, 5));

            JLabel label = new JLabel(text);
            label.setFont(labelFont);
            fieldPanel.add(label);

            JTextField textField = new JTextField(5);
            textField.setFont(labelFont);
            fieldPanel.add(textField);

            keyboardPanel.add(fieldPanel);
        }

        JLabel fileLabel = new JLabel("          File");
        fileLabel.setFont(labelFont);
        filePanel.add(fileLabel);
        JButton addFileBtn = new JButton("Add");
        addFileBtn.setFont(buttonFont);
        filePanel.add(addFileBtn);
        filePanel.add(new JTextField(35));

        JLabel randomLabel = new JLabel("  Random");
        randomLabel.setFont(labelFont);
        randomPanel.add(randomLabel);
        JButton addRandomBtn = new JButton("Add");
        addRandomBtn.setFont(buttonFont);
        randomPanel.add(addRandomBtn);

        inputPanel.add(keyboardPanel);
        inputPanel.add(filePanel);
        inputPanel.add(randomPanel);

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(50, 50, 200, 150);
            }
        };
        drawingPanel.setPreferredSize(new Dimension(960, 540));
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel logPanel = new JPanel(new BorderLayout());
        JTextArea logArea = new JTextArea(3, 50);
        logArea.setEditable(false);
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        logPanel.setPreferredSize(new Dimension(960, 100));

        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JButton findBtn = new JButton("Find");
        findBtn.setFont(buttonFont);
        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(buttonFont);
        controlPanel.add(findBtn);
        controlPanel.add(resetBtn);
        controlPanel.setPreferredSize(new Dimension(320, 100));

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(logPanel, BorderLayout.CENTER);
        bottomPanel.add(controlPanel, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
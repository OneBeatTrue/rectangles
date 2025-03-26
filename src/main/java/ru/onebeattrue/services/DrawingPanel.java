package ru.onebeattrue.services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class DrawingPanel extends JPanel {
    private double scale = 1.0;

    private Storage storage;

    public DrawingPanel(Storage storage) {
        this.storage = storage;
//        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation = e.getWheelRotation();
                double zoomFactor = 1.1;

                if (rotation < 0) {
                    scale *= zoomFactor;
                } else {
                    scale /= zoomFactor;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.translate(getWidth() / 2, getHeight() / 2);
        g2.scale(scale, scale);
        g2.translate(-getWidth() / 2, -getHeight() / 2);

        storage.draw(g2);
    }
}
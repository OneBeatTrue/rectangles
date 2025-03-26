package ru.onebeattrue.services;

import ru.onebeattrue.models.DrawInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class DrawingPanel extends JPanel {
    private double scale = 1.0;

    private Storage storage;

    public DrawingPanel(Storage storage) {
        this.storage = storage;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation = e.getWheelRotation();
                double zoomFactor = 1.1;

                if (rotation < 0) {
                    scale *= zoomFactor;
                }
                else {
                    scale /= zoomFactor;
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawInfo drawInfo : this.storage.getDrawInfo()) {
            if (drawInfo == null) {
                continue;
            }

            int n = drawInfo.vertices().size();
            int[] xPoints = new int[n];
            int[] yPoints = new int[n];

            for (int i = 0; i < n; i++) {
                xPoints[i] = (int) (drawInfo.vertices().get(i).x * this.scale + (double) getWidth() / 2);
                yPoints[i] = (int) (-drawInfo.vertices().get(i).y * this.scale + (double) getHeight() / 2);
            }

            g.setColor(drawInfo.color());
            g.drawPolyline(xPoints, yPoints, n);
        }
    }
}
package ru.onebeattrue.services;

import ru.onebeattrue.entities.Vertex;
import ru.onebeattrue.models.CoordinateRanges;
import ru.onebeattrue.models.DrawInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private double scale = 1.0;

    private Storage storage;

    private ArrayList<Vertex> clicks;

    private Logger logger;

    public DrawingPanel(Storage storage, Logger logger) {
        this.storage = storage;
        this.logger = logger;
        clicks = new ArrayList<>();
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double clickX = ((double) e.getX() - (double) getWidth() / 2) / scale;
                double clickY = -((double) e.getY() - (double) getHeight() / 2) / scale;
                clicks.add(new Vertex(clickX, clickY));
                logger.log("Added point (" + String.format("%.3f , %.3f", clickX, clickY) + ").");

                if (clicks.size() == 3) {
                    storage.add(clicks.get(0), clicks.get(1), clicks.get(2));
                    clicks.clear();
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
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

            g2d.setColor(drawInfo.color());
            g2d.drawPolyline(xPoints, yPoints, n);
        }
    }

    public CoordinateRanges getRanges() {
        return new CoordinateRanges(((double) getWidth()) / this.scale, ((double) getHeight()) / this.scale);
    }
}
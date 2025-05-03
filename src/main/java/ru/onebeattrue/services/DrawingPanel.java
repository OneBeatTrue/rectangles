package ru.onebeattrue.services;

import ru.onebeattrue.entities.Vertex;
import ru.onebeattrue.models.CoordinateRanges;
import ru.onebeattrue.models.DrawInfo;
import ru.onebeattrue.models.Globals;

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

    private Font gridFont;

    public DrawingPanel(Storage storage, Font gridFont) {
        this.storage = storage;
        this.gridFont = gridFont;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation = e.getWheelRotation();
                double zoomFactor = 1.1;

                if (rotation < 0) {
                    if (scale < Globals.maxScale) {
                        scale *= zoomFactor;
                    }
                }
                else {
                    if (scale > Globals.minScale) {
                        scale /= zoomFactor;
                    }
                }

                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double clickX = ((double) e.getX() - (double) getWidth() / 2) / scale;
                double clickY = -((double) e.getY() - (double) getHeight() / 2) / scale;
                storage.add(new Vertex(clickX, clickY));
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2, Globals.gridCell);
        g2.setStroke(new BasicStroke(3));
        int pointSize = 10;
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

            g2.setColor(drawInfo.color());
            if (n == 1) {
                g2.fillOval(xPoints[0] - pointSize / 2, yPoints[0] - pointSize / 2, pointSize, pointSize);
            }
            else {
                g2.drawPolyline(xPoints, yPoints, n);
            }
        }
    }

    private void drawGrid(Graphics2D g2, int step) {
        g2.setFont(this.gridFont);
        g2.setStroke(new BasicStroke(1));
        int midX = getWidth() / 2;
        int midY = getHeight() / 2;
        int shiftX = -25;
        int shiftY = 10;


        for (int x = midX + step; x < getWidth(); x += step) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(x, 0, x, getHeight());
            g2.setColor(Color.BLACK);
            String label = String.format("%.2f", (x - midX) / this.scale);
            g2.drawString(label, x + shiftX, midY + shiftY);
        }

        for (int x = midX - step; x > 0; x -= step) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(x, 0, x, getHeight());
            g2.setColor(Color.BLACK);
            String label = String.format("%.2f", (x - midX) / this.scale);
            g2.drawString(label, x + shiftX, midY + shiftY);
        }

        for (int y = midY + step; y < getHeight(); y += step) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(0, y, getWidth(), y);
            g2.setColor(Color.BLACK);
            String label = String.format("%.2f", -(y - midY) / this.scale);
            g2.drawString(label, midX + shiftX, y + shiftY);
        }

        for (int y = midY - step; y > 0; y -= step) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(0, y, getWidth(), y);
            g2.setColor(Color.BLACK);
            String label = String.format("%.2f", -(y - midY) / this.scale);
            g2.drawString(label, midX + shiftX, y + shiftY);
        }

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(0, midY, getWidth(), midY);
        g2.drawLine(midX, 0, midX, getHeight());
        g2.drawString("0", midX - 10, midY + 10);
        g2.drawString(String.format("1:%.5f", 1 / this.scale), 0, 10);
    }


    public CoordinateRanges getRanges() {
        return new CoordinateRanges(((double) getWidth()) / this.scale, ((double) getHeight()) / this.scale);
    }
}
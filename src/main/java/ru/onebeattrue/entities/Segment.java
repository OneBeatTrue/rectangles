package ru.onebeattrue.entities;

import java.awt.*;

public class Segment extends AbstractShape {
    Vertex firstVertex, secondVertex;

    public Segment(Vertex firstVertex, Vertex secondVertex){
        super(Color.RED, Color.LIGHT_GRAY);
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public double length() {
        return firstVertex.distance(secondVertex);
    }


    @Override
    public void draw(Graphics g) {
        int[] xPoints = new int[2];
        int[] yPoints = new int[2];

        xPoints[0] = (int) this.firstVertex.x;
        xPoints[1] = (int) this.secondVertex.x;

        yPoints[0] = (int) this.firstVertex.y;
        yPoints[1] = (int) this.secondVertex.y;


        g.setColor(this.color);
        g.drawPolyline(xPoints, yPoints, 2);
    }
}

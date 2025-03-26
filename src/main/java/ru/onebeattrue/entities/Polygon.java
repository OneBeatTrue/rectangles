package ru.onebeattrue.entities;

import java.awt.*;
import java.util.ArrayList;

public class Polygon extends AbstractShape {
    ArrayList<Vertex> vertices;

    Polygon() {
        this.vertices = new ArrayList<>();
    }
    Polygon(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Polygon rotate(double phi, Vertex center) {
        ArrayList<Vertex> newVertices = new ArrayList<>();
        for (Vertex vertex : vertices) {
            newVertices.add(vertex.rotate(phi, center));
        }

        return new Polygon(newVertices);
    }

    public Segment findMaxDistance() {
        if (this.vertices.size() < 2) {
            return null;
        }
        Segment maxSegment = new Segment(
                this.vertices.get(0),
                this.vertices.get(1)
        );
        for (Vertex firstVertex : this.vertices) {
            for (Vertex secondVertex : this.vertices) {
                if (firstVertex != secondVertex) {
                    Segment segment = new Segment(
                            firstVertex,
                            secondVertex
                    );
                    if (segment.length() > maxSegment.length()) {
                        maxSegment = segment;
                    }
                }
            }
        }

        return maxSegment;
    }

    @Override
    public void draw(Graphics g) {
        int n = vertices.size() + 1;
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];

        for (int i = 0; i < n; i++) {
            xPoints[i] = (int) vertices.get(i % vertices.size()).x;
            yPoints[i] = (int) vertices.get(i % vertices.size()).y;
        }

        g.setColor(this.color);
        g.drawPolyline(xPoints, yPoints, n);
    }

    public Polygon intersect(Polygon other) {
        ArrayList<Vertex> intersection = new ArrayList<>(this.vertices);
        for (int i = 0; i < other.vertices.size(); i++) {
            Line line = new Line(
                    other.vertices.get(i),
                    other.vertices.get((i + 1) % other.vertices.size())
            );

            ArrayList<Vertex> updatedIntersection = new ArrayList<>();
            for (int j = 0; j < intersection.size(); j++) {
                Vertex firstVertex = intersection.get(j);
                Vertex secondVertex = intersection.get((j + 1) % intersection.size());
                System.out.println(firstVertex.x + " " + firstVertex.y + " " + line.check(firstVertex));
                if (line.check(firstVertex) <= 0) {
                    updatedIntersection.add(firstVertex);
                }
                if (line.check(firstVertex) * line.check(secondVertex) < 0) {
                    Vertex intersectionPoint = line.intersect(new Line(firstVertex, secondVertex));
                    if (intersectionPoint != null) {
                        updatedIntersection.add(intersectionPoint);
                    }
                }
            }
            intersection = updatedIntersection;
        }

        return new Polygon(intersection);
    }
}

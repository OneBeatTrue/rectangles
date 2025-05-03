package ru.onebeattrue.entities;

import ru.onebeattrue.models.DrawInfo;

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

//    public Polygon rotate(double phi, Vertex center) {
//        ArrayList<Vertex> newVertices = new ArrayList<>();
//        for (Vertex vertex : vertices) {
//            newVertices.add(vertex.rotate(phi, center));
//        }
//
//        return new Polygon(newVertices);
//    }

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
    public DrawInfo getDrawInfo() {
        ArrayList<Vertex> verts = this.vertices;
        verts.add(this.vertices.getFirst());
        return new DrawInfo(verts, this.color);
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

package ru.onebeattrue;

import java.util.ArrayList;

public class Polygon {
    ArrayList<Vertex> vertices;

    Polygon() {
        this.vertices = new ArrayList<>();
    }
    Polygon(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Segment findMaxDistance() {
        if (this.vertices.size() < 2) {
            return null;
        }
        Segment maxSegment = new Segment(
                this.vertices.get(0),
                this.vertices.get(1)
        );
        double maxLength = maxSegment.length();
        for (Vertex firstVertex : this.vertices) {
            for (Vertex secondVertex : this.vertices) {
                if (firstVertex == secondVertex) {
                    continue;
                }

                Segment segment = new Segment(
                        firstVertex,
                        secondVertex
                );
                if (segment.length() > maxLength) {
                    maxSegment = segment;
                    maxLength = segment.length();
                }
            }
        }

        return maxSegment;
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

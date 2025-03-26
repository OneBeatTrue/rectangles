package ru.onebeattrue.services;

import ru.onebeattrue.entities.*;
import ru.onebeattrue.entities.Polygon;
import ru.onebeattrue.entities.Rectangle;
import ru.onebeattrue.entities.Shape;

import java.awt.*;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Polygon> polygons = new ArrayList<>();

    private ArrayList<Shape> highlights = new ArrayList<>();

    private Logger logger;

    public Storage(Logger logger) {
        this.logger = logger;
    }

//    public void add(String filename) {
//        try {
//            Polygon polygon = new Rectangle(new Vertex(x1, y1), new Vertex(x2, y2), new Vertex(x3, y3));
//            add(polygon);
//        } catch (IllegalArgumentException e) {
//            this.error(e.getMessage());
//        }
//    }

    public void add(String x1, String y1, String x2, String y2, String x3, String y3) {
        try {
            Polygon polygon = new Rectangle(new Vertex(x1, y1), new Vertex(x2, y2), new Vertex(x3, y3));
            add(polygon);
            this.logger.log("Rectangle successfully added.");
        } catch (IllegalArgumentException e) {
            this.error(e.getMessage());
        }
    }

    private void error(String errorMessage) {
        logger.log("[ERROR] " + errorMessage);
    }

    public void add(Polygon polygon) {
        lowlight();
        this.polygons.add(polygon);
    }

    public void clear() {
        this.polygons.clear();
        lowlight();
    }

    private void lowlight(){
        if (!this.highlights.isEmpty()) {
            for (Shape shape : this.highlights) {
                shape.lowlight();
            }
            this.highlights.clear();
        }
    }

    public Segment findMaxDistance() {
        lowlight();
        Segment maxSegment = null;
        for (int i = 0; i < this.polygons.size(); i++) {
            for (int j = i + 1; j < this.polygons.size(); j++) {
                Polygon firstPolygon = this.polygons.get(i);
                Polygon secondPolygon = this.polygons.get(j);
                Polygon intersection = firstPolygon.intersect(secondPolygon);
                Segment segment = intersection.findMaxDistance();
                if (segment == null) {
                    continue;
                }
                if (maxSegment == null || segment.length() > maxSegment.length()) {
                    maxSegment = segment;
                    Segment finalMaxSegment = maxSegment;
                    this.highlights = new ArrayList<>() {{
                        add(firstPolygon);
                        add(secondPolygon);
                        add(finalMaxSegment);
                    }};
                }
            }
        }

        for (Shape shape: this.highlights) {
            shape.highlight();
        }

        if (maxSegment == null) {
            this.error("Intersections not found.");
        }
        else {
            this.logger.log("Maximal intersection diameter is " + maxSegment.length() + " length.");
        }
        return maxSegment;
    }

    public void draw(Graphics g) {
        for (Polygon polygon : this.polygons) {
            polygon.draw(g);
        }
        for (Shape shape : this.highlights) {
            shape.draw(g);
        }
    }
}
